package be.hepl.authapi.application.usecase.signup;

import be.hepl.authapi.application.service.ChallengeStorageService;
import be.hepl.authapi.application.usecase.ChallengeStatus;
import be.hepl.authapi.application.usecase.ChallengeType;
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

    public ChallengeStatus verify(String challengeSend, String email, ChallengeType challengeType) {
        String correctChallenge = challengeStorageService.getChallenge(email);

        if(correctChallenge.equals(challengeSend)) {

            if(challengeType == ChallengeType.EMAIL)
                clientRepository.updateEmailVerification(email, true);
            else
                clientRepository.updatePhoneVerification(email, true);

            return ChallengeStatus.OK;
        }

        return ChallengeStatus.INCORRECT;
    }
}
