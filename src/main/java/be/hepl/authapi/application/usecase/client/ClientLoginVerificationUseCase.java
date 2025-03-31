package be.hepl.authapi.application.usecase.client;

import be.hepl.authapi.domain.repository.ChallengeRepository;
import be.hepl.authapi.domain.exception.IncorrectChallengeException;
import be.hepl.authapi.domain.model.jwt.Jwt;
import be.hepl.authapi.domain.model.jwt.Role;
import be.hepl.authapi.domain.model.challenge.ChallengeDetails;
import be.hepl.authapi.domain.model.challenge.ChallengeType;
import be.hepl.authapi.domain.model.client.Client;
import be.hepl.authapi.domain.model.client.ClientLog;
import be.hepl.authapi.domain.model.client.ClientLoginMethod;
import be.hepl.authapi.domain.repository.ClientLogRepository;
import be.hepl.authapi.domain.repository.ClientRepository;
import be.hepl.authapi.application.service.JwtService;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashMap;

/// <comments>
/// Use case utilisé lors de la deuxième phase de login (2FA - vérification du challenge)
/// </comments>
@Component
public class ClientLoginVerificationUseCase {

    private final ChallengeRepository challengeRepository;

    private final ClientRepository clientRepository;

    private final ClientLogRepository clientLogRepository;

    private final JwtService jwtService;

    public ClientLoginVerificationUseCase(ChallengeRepository challengeRepository,
                                          ClientRepository clientRepository,
                                          ClientLogRepository clientLogRepository,
                                          JwtService jwtService
                                             ) {
        this.challengeRepository = challengeRepository;
        this.clientRepository = clientRepository;
        this.clientLogRepository = clientLogRepository;
        this.jwtService = jwtService;
    }

    public Jwt verify(String challengeReceive, String email) {
        ChallengeDetails challengeDetails = challengeRepository.getChallenge(email);

        Instant timeStamp = Instant.now();

        Client client = clientRepository.findByEmail(email);

        ClientLoginMethod loginMethod = null;

        ClientLog clientLog = new ClientLog();


        if(challengeDetails.type() == ChallengeType.EMAIL)
        {
            clientLog.setEmail(email);
            loginMethod = ClientLoginMethod.EMAIL;
        }
        else if(challengeDetails.type() == ChallengeType.SMS)
        {
            loginMethod = ClientLoginMethod.PHONE;
            clientLog.setPhoneNumber(client.getPhoneNumber());
        }

        clientLog.setLastName(client.getLastName());
        clientLog.setFirstName(client.getFirstName());
        clientLog.setGender(client.getGender());
        clientLog.setClientId(client.getId());
        clientLog.setMethod(loginMethod);
        clientLog.setTimestamp(timeStamp);
        clientLog.setChallengeReceiveTimestamp(timeStamp);
        clientLog.setChallengeSendTimestamp(Instant.parse(challengeDetails.timestamp()));
        clientLog.setChallengeReceive(challengeReceive);
        clientLog.setChallengeSend(challengeDetails.challenge());


        if(!challengeDetails.challenge().equals(challengeReceive)) {
            clientLog.setSuccess(Boolean.FALSE);
            clientLogRepository.save(clientLog);
            throw new IncorrectChallengeException();
        }

        clientLog.setSuccess(Boolean.TRUE);

        clientLogRepository.save(clientLog);

        challengeRepository.removeChallenge(email);

        return jwtService.generateTokens(client.getId(), Role.CLIENT, new HashMap<>());
    }
}
