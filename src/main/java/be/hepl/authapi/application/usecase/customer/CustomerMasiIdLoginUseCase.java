package be.hepl.authapi.application.usecase.customer;

import be.hepl.authapi.application.dto.request.MasiIdLoginRequest;
import be.hepl.authapi.application.dto.response.MasiIdLoginResponse;
import be.hepl.authapi.domain.exception.UserNotFoundException;
import be.hepl.authapi.domain.model.customer.Customer;
import be.hepl.authapi.domain.model.customer.CustomerLog;
import be.hepl.authapi.domain.model.customer.CustomerLoginMethod;
import be.hepl.authapi.domain.model.masiid.MasiIdLoginStatus;
import be.hepl.authapi.domain.model.token.Role;
import be.hepl.authapi.domain.repository.CustomerLogRepository;
import be.hepl.authapi.domain.repository.CustomerRepository;
import be.hepl.authapi.application.service.MasiIdService;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class CustomerMasiIdLoginUseCase {

    private final CustomerRepository customerRepository;

    private final CustomerLogRepository customerLogRepository;

    private final MasiIdService masiIdService;

    public CustomerMasiIdLoginUseCase(final CustomerRepository customerRepository, final MasiIdService masiIdService, CustomerLogRepository customerLogRepository) {
        this.customerRepository = customerRepository;
        this.masiIdService = masiIdService;
        this.customerLogRepository = customerLogRepository;
    }

    public MasiIdLoginResponse loginUseCase(MasiIdLoginRequest loginRequest) {

        try
        {
            customerRepository.findByPhoneNumber(loginRequest.phoneNumber());

            masiIdService.UserConnection(loginRequest.phoneNumber(), Role.CUSTOMER);
        }
        catch(UserNotFoundException e)
        {
            CustomerLog customerLog = new CustomerLog();

            customerLog.setTimestamp(Instant.now());
            customerLog.setMethod(CustomerLoginMethod.MASI_ID);

            customerLog.setSuccess(Boolean.FALSE);

            customerLogRepository.save(customerLog);

            return new MasiIdLoginResponse(MasiIdLoginStatus.ERROR, "USER NOT FOUND");
        }


        return new MasiIdLoginResponse(MasiIdLoginStatus.SEND, "Check your masi id app");
    }
}
