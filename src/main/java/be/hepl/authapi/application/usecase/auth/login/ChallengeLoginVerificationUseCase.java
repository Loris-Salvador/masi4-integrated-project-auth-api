package be.hepl.authapi.application.usecase.auth.login;

import be.hepl.authapi.application.service.challenge.ChallengeStorageService;
import be.hepl.authapi.domain.exception.IncorrectChallengeException;
import be.hepl.authapi.domain.model.Jwt;
import be.hepl.authapi.domain.model.Role;
import be.hepl.authapi.domain.model.challenge.ChallengeDetails;
import be.hepl.authapi.domain.model.challenge.ChallengeType;
import be.hepl.authapi.domain.model.client.Client;
import be.hepl.authapi.domain.model.client.ClientLog;
import be.hepl.authapi.domain.model.client.ClientLoginMethod;
import be.hepl.authapi.domain.repository.ClientLogRepository;
import be.hepl.authapi.domain.repository.ClientRepository;
import be.hepl.authapi.domain.repository.JwtService;
import be.hepl.authapi.infrastructure.service.security.JwtServiceImpl;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Component
public class ChallengeLoginVerificationUseCase {

    private final ChallengeStorageService challengeStorageService;

    private final ClientRepository clientRepository;

    private final ClientLogRepository clientLogRepository;

    private final JwtService jwtService;

    public ChallengeLoginVerificationUseCase(ChallengeStorageService challengeStorageService,
                                             ClientRepository clientRepository,
                                             ClientLogRepository clientLogRepository,
                                             JwtService jwtService
                                             ) {
        this.challengeStorageService = challengeStorageService;
        this.clientRepository = clientRepository;
        this.clientLogRepository = clientLogRepository;
        this.jwtService = jwtService;
    }

    public Jwt verify(String challengeReceive, String email) {
        ChallengeDetails challengeDetails = challengeStorageService.getChallenge(email);

        long timeStamp = Instant.now().getEpochSecond();

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

        clientLog.setClientId(client.getId());
        clientLog.setMethod(loginMethod);
        clientLog.setTimestamp(timeStamp);
        clientLog.setChallengeReceiveTimestamp(timeStamp);
        clientLog.setChallengeSendTimestamp(challengeDetails.timestamp());
        clientLog.setChallengeReceive(challengeReceive);
        clientLog.setChallengeSend(challengeDetails.challenge());


        if(!challengeDetails.challenge().equals(challengeReceive)) {
            clientLog.setSuccess(Boolean.FALSE);
            clientLogRepository.save(clientLog);
            throw new IncorrectChallengeException();
        }

        clientLog.setSuccess(Boolean.TRUE);

        clientLogRepository.save(clientLog);

        challengeStorageService.removeChallenge(email);

        return jwtService.generateTokens(client.getId(), Role.CLIENT, new HashMap<>());
    }
}
