package be.hepl.authapi.presentation.websocket;

import be.hepl.authapi.application.dto.request.MasiIdLoginRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class DriverMasiIdLoginHandler extends TextWebSocketHandler {

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        MasiIdLoginRequest request = objectMapper.readValue(message.getPayload(), MasiIdLoginRequest.class);

    }

}
