package be.hepl.authapi.application.usecase.customer;

import be.hepl.authapi.domain.model.customer.Customer;
import be.hepl.authapi.domain.model.customer.CustomerLog;
import be.hepl.authapi.domain.model.customer.CustomerLoginMethod;
import be.hepl.authapi.domain.repository.CustomerLogRepository;
import be.hepl.authapi.domain.repository.CustomerRepository;
import be.hepl.authapi.application.service.MasiIdSessionService;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;

@Component
public class CustomerMasIidWebhookUseCase {

    private final MasiIdSessionService masiIdSessionService;

    private final CustomerRepository customerRepository;

    private final CustomerLogRepository customerLogRepository;

    public CustomerMasIidWebhookUseCase(MasiIdSessionService masiIdSessionService, CustomerRepository customerRepository, CustomerLogRepository customerLogRepository) {
        this.masiIdSessionService = masiIdSessionService;
        this.customerRepository = customerRepository;
        this.customerLogRepository = customerLogRepository;
    }

    public void authenticateCustomer(String phone) throws IOException {

        Customer customer = customerRepository.findByPhoneNumber(phone);

        masiIdSessionService.authenticateCustomer(phone, customer.getPhoneNumber());

        CustomerLog customerLog = new CustomerLog();

        customerLog.setCustomerId(customer.getId());
        customerLog.setTimestamp(Instant.now());
        customerLog.setMethod(CustomerLoginMethod.MASI_ID);

        customerLog.setSuccess(Boolean.TRUE);

        customerLogRepository.save(customerLog);
    }
}
