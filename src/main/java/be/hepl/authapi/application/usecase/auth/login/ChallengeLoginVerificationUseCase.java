package be.hepl.authapi.application.usecase.auth.login;

import be.hepl.authapi.application.service.ChallengeStorageService;
import be.hepl.authapi.domain.exception.IncorrectChallengeException;
import be.hepl.authapi.domain.model.ChallengeDetails;
import org.springframework.stereotype.Component;

@Component
public class ChallengeLoginVerificationUseCase {

    private final ChallengeStorageService challengeStorageService;

    public ChallengeLoginVerificationUseCase(final ChallengeStorageService challengeStorageService) {
        this.challengeStorageService = challengeStorageService;
    }

    public void verify(String challengeSend, String email) {
        ChallengeDetails correctChallenge = challengeStorageService.getChallenge(email);

        if(!correctChallenge.challenge().equals(challengeSend)) {
            throw new IncorrectChallengeException();
        }

        challengeStorageService.removeChallenge(email);
    }
}
