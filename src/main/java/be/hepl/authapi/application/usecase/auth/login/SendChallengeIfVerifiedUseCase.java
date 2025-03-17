package be.hepl.authapi.application.usecase.auth.login;

import be.hepl.authapi.domain.model.challenge.ChallengeType;
import be.hepl.authapi.application.usecase.auth.SendChallengeUseCase;
import be.hepl.authapi.domain.exception.DoubleAuthenticationNotVerified;
import be.hepl.authapi.domain.model.client.Client;
import be.hepl.authapi.domain.repository.ClientLogRepository;
import be.hepl.authapi.domain.repository.ClientRepository;
import org.springframework.stereotype.Component;

@Component
public class SendChallengeIfVerifiedUseCase {

    private final SendChallengeUseCase sendChallengeUseCase;

    private final ClientRepository userRepository;

    private final ClientLogRepository logRepository;


    public SendChallengeIfVerifiedUseCase(ClientRepository clientRepository, SendChallengeUseCase sendChallengeUseCase, ClientLogRepository clientLogRepository) {
        this.userRepository = clientRepository;
        this.sendChallengeUseCase = sendChallengeUseCase;
        this.logRepository = clientLogRepository;
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
