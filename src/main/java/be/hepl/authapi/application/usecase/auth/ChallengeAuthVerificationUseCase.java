package be.hepl.authapi.application.usecase.auth;

import be.hepl.authapi.application.service.ChallengeStorageService;
import be.hepl.authapi.application.usecase.ChallengeStatus;
import org.springframework.stereotype.Component;

@Component
public class ChallengeAuthVerificationUseCase {

    private final ChallengeStorageService challengeStorageService;

    public ChallengeAuthVerificationUseCase(final ChallengeStorageService challengeStorageService) {
        this.challengeStorageService = challengeStorageService;
    }

    public ChallengeStatus verify(String challengeSend, String email) {
        String correctChallenge = challengeStorageService.getChallenge(email);

        if(correctChallenge.equals(challengeSend)) {
            return ChallengeStatus.OK;
        }

        return ChallengeStatus.INCORRECT;
    }
}
