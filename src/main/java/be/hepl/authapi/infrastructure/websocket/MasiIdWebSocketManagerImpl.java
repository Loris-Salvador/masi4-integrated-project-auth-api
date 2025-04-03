package be.hepl.authapi.infrastructure.websocket;

import be.hepl.authapi.domain.model.masiid.MasiIdLoginStatus;
import be.hepl.authapi.domain.model.token.Token;
import be.hepl.authapi.domain.service.TokenService;
import be.hepl.authapi.domain.model.token.Role;
import be.hepl.authapi.domain.websocket.MasiIdWebSocketManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class MasiIdWebSocketManagerImpl implements MasiIdWebSocketManager {

    private Map<String, WebSocketSession> drivers = new HashMap<>();

    private Map<String, WebSocketSession> customers = new HashMap<>();

    private final TokenService tokenService;

    public MasiIdWebSocketManagerImpl(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    public void addCustomerSession(String sessionId, WebSocketSession session) {
        customers.put(sessionId, session);
    }

    public void addDriverSession(String sessionId, WebSocketSession session) {
        drivers.put(sessionId, session);
    }

    public void removeDriverSession(String sessionId) {
        drivers.remove(sessionId);
    }

    public void removeCustomerSession(String sessionId) {
        customers.remove(sessionId);
    }

    public void authenticateCustomer(String sessionId, String id) throws IOException {
        WebSocketSession session = customers.get(sessionId);
        ObjectMapper objectMapper = new ObjectMapper();
        Token token = tokenService.generateTokens(id, Role.CUSTOMER);
        MasiIdTokenResponse response = new MasiIdTokenResponse(MasiIdLoginStatus.OK, token);
        String jsonResponse = objectMapper.writeValueAsString(response);
        session.sendMessage(new TextMessage(jsonResponse));

        session.close();
    }

    public void authenticateDriver(String sessionId, String id) throws IOException {
        WebSocketSession session = drivers.get(sessionId);
        ObjectMapper objectMapper = new ObjectMapper();
        Token token = tokenService.generateTokens(id, Role.DRIVER);
        MasiIdTokenResponse response = new MasiIdTokenResponse(MasiIdLoginStatus.OK, token);
        String jsonResponse = objectMapper.writeValueAsString(response);
        session.sendMessage(new TextMessage(jsonResponse));

        session.close();
    }
}
