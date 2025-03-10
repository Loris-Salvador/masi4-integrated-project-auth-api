package be.hepl.authapi.application.usecase.auth;

import be.hepl.authapi.domain.repository.ChallengeStorageService;
import org.springframework.stereotype.Component;

@Component
public class ChallengeVerificationUseCase {

    private final ChallengeStorageService challengeStorageService;

    public ChallengeVerificationUseCase(final ChallengeStorageService challengeStorageService) {
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
