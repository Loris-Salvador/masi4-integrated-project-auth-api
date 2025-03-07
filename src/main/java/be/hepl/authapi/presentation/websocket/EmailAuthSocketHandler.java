package be.hepl.authapi.presentation.websocket;

import be.hepl.authapi.application.dto.EmailAuthRequest;
import be.hepl.authapi.application.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class EmailAuthSocketHandler extends TextWebSocketHandler {

    private final AuthService authService;

    public EmailAuthSocketHandler(AuthService authService) {
        this.authService = authService;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        if(!session.getAttributes().containsKey(SessionAttribute.CHALLENGE))
        {
            System.out.println("EMAIL HANDLER : Message received");

            ObjectMapper objectMapper = new ObjectMapper();
            EmailAuthRequest request = objectMapper.readValue(message.getPayload(), EmailAuthRequest.class);

            boolean authenticate = authService.authenticate(request.getUsername(), request.getPassword());

            if(authenticate)
                System.out.println("EMAIL HANDLER : authenticated");
            else
                System.out.println("EMAIL HANDLER : Not authenticated");
        }
        else
        {
            System.out.println("EMAIL HANDLER : else :" + session.getAttributes().get("challenge"));
        }


    }
}