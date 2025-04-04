package be.hepl.authapi.application.usecase.customer;

import be.hepl.authapi.domain.model.customer.Customer;
import be.hepl.authapi.domain.model.customer.CustomerLog;
import be.hepl.authapi.domain.model.token.Token;
import be.hepl.authapi.domain.repository.ChallengeRepository;
import be.hepl.authapi.domain.exception.IncorrectChallengeException;
import be.hepl.authapi.domain.model.token.Role;
import be.hepl.authapi.domain.model.challenge.ChallengeDetails;
import be.hepl.authapi.domain.model.challenge.ChallengeType;
import be.hepl.authapi.domain.model.customer.CustomerLoginMethod;
import be.hepl.authapi.domain.repository.CustomerLogRepository;
import be.hepl.authapi.domain.repository.CustomerRepository;
import be.hepl.authapi.application.service.TokenService;
import org.springframework.stereotype.Component;

import java.time.Instant;

/// <comments>
/// Use case utilisé lors de la deuxième phase de login (2FA - vérification du challenge)
/// </comments>
@Component
public class CustomerLoginVerificationUseCase {

    private final ChallengeRepository challengeRepository;

    private final CustomerRepository customerRepository;

    private final CustomerLogRepository customerLogRepository;

    private final TokenService tokenService;

    public CustomerLoginVerificationUseCase(ChallengeRepository challengeRepository,
                                            CustomerRepository customerRepository,
                                            CustomerLogRepository customerLogRepository,
                                            TokenService tokenService
                                             ) {
        this.challengeRepository = challengeRepository;
        this.customerRepository = customerRepository;
        this.customerLogRepository = customerLogRepository;
        this.tokenService = tokenService;
    }

    public Token verify(String challengeReceive, String email) {
        ChallengeDetails challengeDetails = challengeRepository.getChallenge(email);

        Instant timeStamp = Instant.now();

        Customer customer = customerRepository.findByEmail(email);

        CustomerLoginMethod loginMethod = null;

        CustomerLog customerLog = new CustomerLog();


        if(challengeDetails.type() == ChallengeType.EMAIL)
        {
            loginMethod = CustomerLoginMethod.EMAIL;
        }
        else if(challengeDetails.type() == ChallengeType.SMS)
        {
            loginMethod = CustomerLoginMethod.PHONE;
        }

        customerLog.setCustomerId(customer.getId());
        customerLog.setMethod(loginMethod);
        customerLog.setTimestamp(timeStamp);
        customerLog.setChallengeReceiveTimestamp(timeStamp);
        customerLog.setChallengeSendTimestamp(Instant.parse(challengeDetails.timestamp()));
        customerLog.setChallengeReceive(challengeReceive);
        customerLog.setChallengeSend(challengeDetails.challenge());


        if(!challengeDetails.challenge().equals(challengeReceive)) {
            customerLog.setSuccess(Boolean.FALSE);
            customerLogRepository.save(customerLog);
            throw new IncorrectChallengeException();
        }

        customerLog.setSuccess(Boolean.TRUE);

        customerLogRepository.save(customerLog);

        challengeRepository.removeChallenge(email);

        return tokenService.generateTokens(customer.getId(), Role.CUSTOMER);
    }
}
