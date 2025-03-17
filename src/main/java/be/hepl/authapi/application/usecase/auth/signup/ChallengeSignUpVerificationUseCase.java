package be.hepl.authapi.application.usecase.auth.signup;

import be.hepl.authapi.application.service.ChallengeStorageService;
import be.hepl.authapi.domain.model.ChallengeDetails;
import be.hepl.authapi.domain.model.ChallengeType;
import be.hepl.authapi.domain.exception.IncorrectChallengeException;
import be.hepl.authapi.domain.repository.ClientRepository;
import org.springframework.stereotype.Component;

@Component
public class ChallengeSignUpVerificationUseCase {

    private final ChallengeStorageService challengeStorageService;

    private final ClientRepository clientRepository;

    public ChallengeSignUpVerificationUseCase(ChallengeStorageService challengeStorageService, ClientRepository clientRepository) {
        this.challengeStorageService = challengeStorageService;
        this.clientRepository = clientRepository;
    }

    public void verify(String challengeSend, String email, ChallengeType challengeType) {
        ChallengeDetails correctChallenge = challengeStorageService.getChallenge(email);


        if(!correctChallenge.challenge().equals(challengeSend)) {
            throw new IncorrectChallengeException();
        }

        if(challengeType == ChallengeType.EMAIL)
            clientRepository.updateEmailVerification(email, true);
        else
            clientRepository.updatePhoneVerification(email, true);
    }
}
