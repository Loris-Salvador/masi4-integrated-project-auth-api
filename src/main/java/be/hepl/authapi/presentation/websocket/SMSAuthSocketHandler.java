package be.hepl.authapi.presentation.websocket;

import be.hepl.authapi.application.dto.AuthRequest;
import be.hepl.authapi.application.dto.AuthResponse;
import be.hepl.authapi.application.dto.ChallengeRequest;
import be.hepl.authapi.application.dto.ChallengeResponse;
import be.hepl.authapi.application.usecase.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class SMSAuthSocketHandler extends TextWebSocketHandler {

    private final PasswordVerificationUseCase passwordVerificationUseCase;

    private final GenerateChallengeUseCase generateChallengeUseCase;

    private final SendChallengeBySMSUseCase sendChallengeBySMSUseCase;

    private final ChallengeVerificationUseCase challengeVerificationUseCase;

    public SMSAuthSocketHandler(PasswordVerificationUseCase authUseCase,
                                  GenerateChallengeUseCase generateChallengeUseCase,
                                  SendChallengeBySMSUseCase sendChallengeBySMSUseCase,
                                  ChallengeVerificationUseCase challengeVerificationUseCase)
    {
        this.passwordVerificationUseCase = authUseCase;
        this.generateChallengeUseCase = generateChallengeUseCase;
        this.sendChallengeBySMSUseCase = sendChallengeBySMSUseCase;
        this.challengeVerificationUseCase = challengeVerificationUseCase;
    }



    @Override
    protected void handleTextMessage(WebSocketSession session, @NonNull TextMessage message) throws Exception {
        if(!session.getAttributes().containsKey(SessionAttribute.CHALLENGE))
        {
            ObjectMapper objectMapper = new ObjectMapper();
            AuthRequest request = objectMapper.readValue(message.getPayload(), AuthRequest.class);

            AuthResponse response = passwordVerificationUseCase.verify(request);
            String jsonResponse = objectMapper.writeValueAsString(response);

            session.sendMessage(new TextMessage(jsonResponse));

            if(response.getStatus() != AuthStatus.OK)
            {
                session.close();
                return;
            }

            String challenge = generateChallengeUseCase.generateChallenge();
            sendChallengeBySMSUseCase.sendChallenge(request.getEmail(), challenge);

            session.getAttributes().put(SessionAttribute.CHALLENGE,challenge);
        }
        else
        {

            ObjectMapper objectMapper = new ObjectMapper();
            ChallengeRequest request = objectMapper.readValue(message.getPayload(), ChallengeRequest.class);

            ChallengeResponse response = challengeVerificationUseCase.verify(request.getChallenge(), (String) session.getAttributes().get(SessionAttribute.CHALLENGE));
            String jsonResponse = objectMapper.writeValueAsString(response);

            session.sendMessage(new TextMessage(jsonResponse));

            session.close();
        }
    }
}
