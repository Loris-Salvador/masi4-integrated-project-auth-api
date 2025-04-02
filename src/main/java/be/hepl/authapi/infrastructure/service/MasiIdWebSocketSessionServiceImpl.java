package be.hepl.authapi.infrastructure.service;

import be.hepl.authapi.domain.service.JwtService;
import be.hepl.authapi.domain.service.MasiIdWebSocketSessionService;
import be.hepl.authapi.domain.model.jwt.Role;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class MasiIdWebSocketSessionServiceImpl implements MasiIdWebSocketSessionService {

    private Map<String, WebSocketSession> drivers = new HashMap<>();

    private Map<String, WebSocketSession> customers = new HashMap<>();

    private final JwtService jwtService;

    public MasiIdWebSocketSessionServiceImpl(JwtService jwtService) {
        this.jwtService = jwtService;
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
        String jsonResponse = objectMapper.writeValueAsString(jwtService.generateTokens(id, Role.DRIVER));
        session.sendMessage(new TextMessage(jsonResponse));

        session.close();
    }

    public void authenticateDriver(String sessionId, String id) throws IOException {
        WebSocketSession session = drivers.get(sessionId);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(jwtService.generateTokens(id, Role.CUSTOMER));
        session.sendMessage(new TextMessage(jsonResponse));

        session.close();
    }
}
