package be.hepl.authapi.domain.service;

import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;


public interface MasiIdWebSocketSessionService {
    void addCustomerSession(String sessionId, WebSocketSession session);

    void addDriverSession(String sessionId, WebSocketSession session);

    void removeDriverSession(String sessionId);

    void removeCustomerSession(String sessionId);

    void authenticateCustomer(String sessionId, String id) throws IOException;

    void authenticateDriver(String sessionId, String id) throws IOException;
}
