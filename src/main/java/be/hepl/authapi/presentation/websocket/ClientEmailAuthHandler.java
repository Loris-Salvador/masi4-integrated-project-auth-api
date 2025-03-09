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
public class ClientEmailAuthHandler extends TextWebSocketHandler {

    private final PasswordVerificationUseCase passwordVerificationUseCase;

    private final SendChallengeByEmailUseCase sendEmailUseCase;

    private final GenerateChallengeUseCase generateChallengeUseCase;

    private final ChallengeVerificationUseCase challengeVerificationUseCase;

    public ClientEmailAuthHandler(PasswordVerificationUseCase authUseCase,
                                  SendChallengeByEmailUseCase sendEmailUseCase,
                                  GenerateChallengeUseCase generateChallengeUseCase,
                                  ChallengeVerificationUseCase challengeVerificationUseCase)
    {

        this.passwordVerificationUseCase = authUseCase;
        this.sendEmailUseCase = sendEmailUseCase;
        this.generateChallengeUseCase = generateChallengeUseCase;
        this.challengeVerificationUseCase = challengeVerificationUseCase;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session,@NonNull TextMessage message) throws Exception {

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
            sendEmailUseCase.sendChallenge(request.getEmail(), challenge);

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