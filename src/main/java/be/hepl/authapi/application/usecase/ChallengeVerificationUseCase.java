package be.hepl.authapi.application.usecase;

import org.springframework.stereotype.Component;

@Component
public class ChallengeVerificationUseCase {

    public ChallengeStatus verify(String challengeSend, String correctChallenge) {
        if(challengeSend.equals(correctChallenge)) {
            return ChallengeStatus.OK;
        }
        return ChallengeStatus.INCORRECT;
    }
}
