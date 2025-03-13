package be.hepl.authapi.application.usecase.auth;

import be.hepl.authapi.application.service.ChallengeStorageService;
import be.hepl.authapi.domain.exception.IncorrectChallengeException;
import org.springframework.stereotype.Component;

@Component
public class ChallengeAuthVerificationUseCase {

    private final ChallengeStorageService challengeStorageService;

    public ChallengeAuthVerificationUseCase(final ChallengeStorageService challengeStorageService) {
        this.challengeStorageService = challengeStorageService;
    }

    public void verify(String challengeSend, String email) {
        String correctChallenge = challengeStorageService.getChallenge(email);

        if(!correctChallenge.equals(challengeSend)) {
            throw new IncorrectChallengeException();
        }

        challengeStorageService.removeChallenge(email);
    }
}
