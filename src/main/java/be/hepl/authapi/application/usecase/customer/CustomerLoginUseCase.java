package be.hepl.authapi.application.usecase.customer;

import be.hepl.authapi.application.dto.request.CustomerLoginRequest;
import be.hepl.authapi.application.service.PasswordHashingService;
import be.hepl.authapi.application.usecase.SendChallengeUseCase;
import be.hepl.authapi.domain.exception.DoubleAuthenticationNotVerified;
import be.hepl.authapi.domain.exception.IncorrectPasswordException;
import be.hepl.authapi.domain.model.challenge.ChallengeType;
import be.hepl.authapi.domain.model.customer.Customer;
import be.hepl.authapi.domain.repository.CustomerRepository;
import org.springframework.stereotype.Component;

@Component
public class CustomerLoginUseCase {

    private final CustomerRepository customerRepository;

    private final PasswordHashingService passwordHashingService;

    private final SendChallengeUseCase sendChallengeUseCase;


    public CustomerLoginUseCase(CustomerRepository customerRepository, PasswordHashingService passwordHashingService, SendChallengeUseCase sendChallengeUseCase) {
        this.customerRepository = customerRepository;
        this.passwordHashingService = passwordHashingService;
        this.sendChallengeUseCase = sendChallengeUseCase;
    }

    public void verify(CustomerLoginRequest customerLoginRequest, ChallengeType challengeType) {

        Customer customer = customerRepository.findByEmail(customerLoginRequest.email());

        if (!passwordHashingService.verifyPassword(customerLoginRequest.password(), customer.getPassword())) {
            throw new IncorrectPasswordException("The customer password is incorrect");
        }

        if(challengeType == ChallengeType.EMAIL)
        {
            if(!customer.isEmailVerified())
            {
                throw new DoubleAuthenticationNotVerified("Email is not verified");
            }
        }
        else if(challengeType == ChallengeType.SMS)
        {
            if(!customer.isPhoneVerified())
            {
                throw new DoubleAuthenticationNotVerified("Phone number is not verified");
            }
        }

        sendChallengeUseCase.send(customerLoginRequest.email(), challengeType);
    }
}