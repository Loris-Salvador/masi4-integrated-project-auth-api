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
public class EmailAuthSocketHandler extends TextWebSocketHandler {

    private final PasswordVerificationUseCase authUseCase;

    private final SendChallengeByEmailUseCase sendEmailUseCase;

    private final GenerateChallengeUseCase generateChallengeUseCase;

    private final ChallengeVerificationUseCase challengeVerificationUseCase;

    public EmailAuthSocketHandler(PasswordVerificationUseCase authUseCase,
                                  SendChallengeByEmailUseCase sendEmailUseCase,
                                  GenerateChallengeUseCase generateChallengeUseCase,
                                  ChallengeVerificationUseCase challengeVerificationUseCase)
    {

        this.authUseCase = authUseCase;
        this.sendEmailUseCase = sendEmailUseCase;
        this.generateChallengeUseCase = generateChallengeUseCase;
        this.challengeVerificationUseCase = challengeVerificationUseCase;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session,@NonNull TextMessage message) throws Exception {

        if(!session.getAttributes().containsKey(SessionAttribute.CHALLENGE))
        {
            System.out.println("EMAIL HANDLER : Message RECEIVED");

            passwordVerification(session, message);
        }
        else
        {
            System.out.println("EMAIL HANDLER :" + session.getAttributes().get("challenge"));

            challengeVerification(session, message);
        }
    }

    private void passwordVerification(WebSocketSession session, TextMessage message) throws Exception
    {
        ObjectMapper objectMapper = new ObjectMapper();
        AuthRequest request = objectMapper.readValue(message.getPayload(), AuthRequest.class);

        AuthStatus status;

        status = authUseCase.verify(request.getEmail(), request.getPassword());

        if(status == AuthStatus.USER_NOT_FOUND)
        {
            System.out.println("EMAIL HANDLER : User not found");

            AuthResponse response = new AuthResponse(status.toString(), "User not found check the email");

            String jsonResponse = objectMapper.writeValueAsString(response);

            session.sendMessage(new TextMessage(jsonResponse));

            session.close();

            return;
        }

        if(status == AuthStatus.FAILED)
        {
            System.out.println("EMAIL HANDLER : Password verification failed");

            AuthResponse response = new AuthResponse(status.toString(), "Password verification failed");

            String jsonResponse = objectMapper.writeValueAsString(response);

            session.sendMessage(new TextMessage(jsonResponse));

            session.close();

            return;
        }

        System.out.println("EMAIL HANDLER : Password OK");

        AuthResponse response = new AuthResponse(status.toString(), "");

        String jsonResponse = objectMapper.writeValueAsString(response);
        session.sendMessage(new TextMessage(jsonResponse));

        String challenge = generateChallengeUseCase.generateChallenge();

        sendEmailUseCase.send(request.getEmail(), challenge);

        session.getAttributes().put(SessionAttribute.CHALLENGE,challenge);
    }

    private void challengeVerification(WebSocketSession session, TextMessage message) throws Exception
    {
        ObjectMapper objectMapper = new ObjectMapper();
        ChallengeRequest request = objectMapper.readValue(message.getPayload(), ChallengeRequest.class);

        ChallengeStatus challengeStatus = challengeVerificationUseCase.verify(request.getChallenge(), (String) session.getAttributes().get(SessionAttribute.CHALLENGE));


        ChallengeResponse challengeResponse = new ChallengeResponse(challengeStatus);
        String jsonResponse = objectMapper.writeValueAsString(challengeResponse);

        session.sendMessage(new TextMessage(jsonResponse));

        session.close();
    }

}