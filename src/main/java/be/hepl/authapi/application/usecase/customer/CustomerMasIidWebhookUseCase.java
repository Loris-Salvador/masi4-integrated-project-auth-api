package be.hepl.authapi.application.usecase.customer;

import be.hepl.authapi.domain.model.customer.Customer;
import be.hepl.authapi.domain.repository.CustomerRepository;
import be.hepl.authapi.application.service.MasiIdSessionService;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomerMasIidWebhookUseCase {

    private final MasiIdSessionService masiIdSessionService;

    private final CustomerRepository customerRepository;

    public CustomerMasIidWebhookUseCase(MasiIdSessionService masiIdSessionService, CustomerRepository customerRepository) {
        this.masiIdSessionService = masiIdSessionService;
        this.customerRepository = customerRepository;
    }

    public void authenticateCustomer(String phone) throws IOException {

        Customer customer = customerRepository.findByPhoneNumber(phone);

        masiIdSessionService.authenticateCustomer(phone, customer.getId());
    }
}
