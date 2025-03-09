package be.hepl.authapi.application.usecase;

import be.hepl.authapi.application.dto.ChallengeResponse;
import org.springframework.stereotype.Component;

@Component
public class ChallengeVerificationUseCase {

    public ChallengeResponse verify(String challengeSend, String correctChallenge) {
        if(challengeSend.equals(correctChallenge)) {
            return new ChallengeResponse(ChallengeStatus.OK);
        }
        return new ChallengeResponse(ChallengeStatus.INCORRECT);
    }
}
