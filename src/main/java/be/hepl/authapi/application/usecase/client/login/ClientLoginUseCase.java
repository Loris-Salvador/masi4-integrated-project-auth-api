package be.hepl.authapi.application.usecase.client.login;

import be.hepl.authapi.application.dto.request.ClientLoginRequest;
import be.hepl.authapi.application.service.PasswordHashingService;
import be.hepl.authapi.application.usecase.SendChallengeUseCase;
import be.hepl.authapi.domain.exception.DoubleAuthenticationNotVerified;
import be.hepl.authapi.domain.exception.IncorrectPasswordException;
import be.hepl.authapi.domain.model.challenge.ChallengeType;
import be.hepl.authapi.domain.model.client.Client;
import be.hepl.authapi.domain.repository.ClientRepository;
import org.springframework.stereotype.Component;

@Component
public class ClientLoginUseCase {

    private final ClientRepository clientRepository;

    private final PasswordHashingService passwordHashingService;

    private final SendChallengeUseCase sendChallengeUseCase;


    public ClientLoginUseCase(ClientRepository clientRepository, PasswordHashingService passwordHashingService, SendChallengeUseCase sendChallengeUseCase) {
        this.clientRepository = clientRepository;
        this.passwordHashingService = passwordHashingService;
        this.sendChallengeUseCase = sendChallengeUseCase;
    }

    public void verify(ClientLoginRequest clientLoginRequest, ChallengeType challengeType) {

        Client client = clientRepository.findByEmail(clientLoginRequest.email());

        if (!passwordHashingService.verifyPassword(clientLoginRequest.password(), client.getPassword())) {
            throw new IncorrectPasswordException("The client password is incorrect");
        }

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

        sendChallengeUseCase.send(clientLoginRequest.email(), challengeType);
    }
}