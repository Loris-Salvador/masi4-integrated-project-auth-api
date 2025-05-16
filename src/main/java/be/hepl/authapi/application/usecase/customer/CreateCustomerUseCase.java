package be.hepl.authapi.application.usecase.customer;

import be.hepl.authapi.application.dto.request.CustomerCreateRequest;
import be.hepl.authapi.application.dto.request.VerifyChallengeRequest;
import be.hepl.authapi.application.dto.response.CustomerCreateResponse;
import be.hepl.authapi.application.mapper.CustomerCreateRequestToCustomerMapper;
import be.hepl.authapi.application.mapper.CustomerToCustomerCreateResponseMapper;
import be.hepl.authapi.application.service.PasswordHashingService;
import be.hepl.authapi.domain.model.customer.Customer;
import be.hepl.authapi.domain.repository.CustomerRepository;
import org.springframework.stereotype.Component;
import be.hepl.authapi.domain.repository.CustomerRepository;


import java.time.Instant;
import java.util.Random;

@Component
public class CreateCustomerUseCase {

    private final CustomerRepository customerRepository;

    private final PasswordHashingService passwordHashingService;

    public CreateCustomerUseCase(final CustomerRepository customerRepository, final PasswordHashingService passwordHashingService) {
        this.customerRepository = customerRepository;
        this.passwordHashingService = passwordHashingService;
    }


    public CustomerCreateResponse create(CustomerCreateRequest request) {
        Customer customer = CustomerCreateRequestToCustomerMapper.INSTANCE.map(request);

        Customer c;
        String customerId;

        do {
            customerId = generateCustomerId();

            c = customerRepository.findById(customerId);
        }
        while (c != null);

        customerId = generateCustomerId();

        customer.setId(customerId);
        customer.setEmailVerified(false);
        customer.setPhoneVerified(false);

        customer.setCreateAccount(Instant.now());

        customer.setPassword(passwordHashingService.hashPassword(request.password()));

        Customer result = customerRepository.save(customer);
        return CustomerToCustomerCreateResponseMapper.INSTANCE.map(result);
    }

    private String generateCustomerId() {
        Random rand = new Random();
        int customerId = rand.nextInt(90000000) + 10000000;
        return String.valueOf(customerId);
    }


}
