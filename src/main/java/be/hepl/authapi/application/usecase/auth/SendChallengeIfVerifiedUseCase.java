package be.hepl.authapi.application.usecase.auth;

import be.hepl.authapi.application.usecase.ChallengeType;
import be.hepl.authapi.application.usecase.SendChallengeUseCase;
import be.hepl.authapi.domain.exception.DoubleAuthenticationNotVerified;
import be.hepl.authapi.domain.model.Client;
import be.hepl.authapi.domain.repository.ClientRepository;
import org.springframework.stereotype.Component;

@Component
public class SendChallengeIfVerifiedUseCase {

    private final SendChallengeUseCase sendChallengeUseCase;

    private final ClientRepository userRepository;


    public SendChallengeIfVerifiedUseCase(ClientRepository clientRepository, SendChallengeUseCase sendChallengeUseCase) {
        this.userRepository = clientRepository;
        this.sendChallengeUseCase = sendChallengeUseCase;
    }

    public void send(String email, ChallengeType challengeType)
    {
        Client client = userRepository.findByEmail(email);

        if(challengeType == ChallengeType.EMAIL)
        {
            if(!client.isEmailVerified())
            {
                throw new DoubleAuthenticationNotVerified("Email is not verified");
            }
        }
        else if(challengeType == ChallengeType.SMS)
        {
            if(!client.isPhoneVerified())
            {
                throw new DoubleAuthenticationNotVerified("Phone number is not verified");
            }
        }

        sendChallengeUseCase.send(email, challengeType);
    }

}
