package be.hepl.authapi.application.usecase.client.login;

import be.hepl.authapi.domain.repository.ChallengeRepository;
import be.hepl.authapi.domain.model.challenge.ChallengeDetails;
import be.hepl.authapi.domain.model.challenge.ChallengeType;
import be.hepl.authapi.domain.exception.IncorrectChallengeException;
import be.hepl.authapi.domain.repository.ClientRepository;
import org.springframework.stereotype.Component;

@Component
public class ClientLoginMethodVerificationCase {

    private final ChallengeRepository challengeRepository;

    private final ClientRepository clientRepository;

    public ClientLoginMethodVerificationCase(ChallengeRepository challengeRepository, ClientRepository clientRepository) {
        this.challengeRepository = challengeRepository;
        this.clientRepository = clientRepository;
    }

    public void verify(String challengeSend, String email, ChallengeType challengeType) {
        ChallengeDetails correctChallenge = challengeRepository.getChallenge(email);


        if(!correctChallenge.challenge().equals(challengeSend)) {
            throw new IncorrectChallengeException();
        }

        if(challengeType == ChallengeType.EMAIL)
            clientRepository.updateEmailVerification(email, true);
        else
            clientRepository.updatePhoneVerification(email, true);
    }
}
