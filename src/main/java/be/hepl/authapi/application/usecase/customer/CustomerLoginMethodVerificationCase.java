package be.hepl.authapi.application.usecase.customer;

import be.hepl.authapi.domain.repository.ChallengeRepository;
import be.hepl.authapi.domain.model.challenge.ChallengeDetails;
import be.hepl.authapi.domain.model.challenge.ChallengeType;
import be.hepl.authapi.domain.exception.IncorrectChallengeException;
import be.hepl.authapi.domain.repository.CustomerRepository;
import org.springframework.stereotype.Component;

/// <comments>
/// Use case qui permet de vérifier une 2FA (utilisé quand le client veut vérifier son telephone ou mail)
/// </comments>
@Component
public class CustomerLoginMethodVerificationCase {

    private final ChallengeRepository challengeRepository;

    private final CustomerRepository customerRepository;

    public CustomerLoginMethodVerificationCase(ChallengeRepository challengeRepository, CustomerRepository customerRepository) {
        this.challengeRepository = challengeRepository;
        this.customerRepository = customerRepository;
    }

    public void verify(String challengeSend, String email, ChallengeType challengeType) {
        ChallengeDetails correctChallenge = challengeRepository.getChallenge(email);


        if(!correctChallenge.challenge().equals(challengeSend)) {
            throw new IncorrectChallengeException();
        }

        if(challengeType == ChallengeType.EMAIL)
            customerRepository.updateEmailVerification(email, true);
        else
            customerRepository.updatePhoneVerification(email, true);
    }
}
