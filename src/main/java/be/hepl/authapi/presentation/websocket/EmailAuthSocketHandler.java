package be.hepl.authapi.presentation.websocket;

import be.hepl.authapi.application.dto.AuthRequest;
import be.hepl.authapi.application.dto.AuthResponse;
import be.hepl.authapi.application.usecase.PasswordVerificationUseCase;
import be.hepl.authapi.application.usecase.SendChallengeByEmailUseCase;
import be.hepl.authapi.domain.Exception.UserNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class EmailAuthSocketHandler extends TextWebSocketHandler {

    private final PasswordVerificationUseCase authUseCase;

    private final SendChallengeByEmailUseCase sendEmailUseCase;

    public EmailAuthSocketHandler(PasswordVerificationUseCase authUseCase, SendChallengeByEmailUseCase sendEmailUseCase) {
        this.authUseCase = authUseCase;
        this.sendEmailUseCase = sendEmailUseCase;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        if(!session.getAttributes().containsKey(SessionAttribute.CHALLENGE))
        {
            System.out.println("EMAIL HANDLER : Message received");

            ObjectMapper objectMapper = new ObjectMapper();
            AuthRequest request = objectMapper.readValue(message.getPayload(), AuthRequest.class);

            boolean passwordOk = false;

            try
            {
                passwordOk = authUseCase.verify(request.getEmail(), request.getPassword());

            }
            catch(UserNotFoundException e)
            {
                System.out.println("EMAIL HANDLER : User not found");

                AuthResponse response = new AuthResponse("Failed", "User not found");

                String jsonResponse = objectMapper.writeValueAsString(response);

                session.sendMessage(new TextMessage(jsonResponse));
            }

            if(!passwordOk)
            {
                System.out.println("EMAIL HANDLER : Password verification failed");

                AuthResponse response = new AuthResponse("OK", "Incorrect password");

                String jsonResponse = objectMapper.writeValueAsString(response);

                session.sendMessage(new TextMessage(jsonResponse));
            }

            System.out.println("EMAIL HANDLER : Password OK");

            sendEmailUseCase.sendEmail(request.getEmail());
        }
        else
        {
            System.out.println("EMAIL HANDLER : else :" + session.getAttributes().get("challenge"));
        }
    }

}