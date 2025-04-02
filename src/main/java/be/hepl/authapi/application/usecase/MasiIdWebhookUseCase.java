package be.hepl.authapi.application.usecase;

import be.hepl.authapi.domain.model.jwt.Role;
import be.hepl.authapi.domain.websocket.MasiIdWebSocketManager;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MasiIdWebhookUseCase {

    private final MasiIdWebSocketManager masiIdWebSocketManager;

    public MasiIdWebhookUseCase(MasiIdWebSocketManager masiIdWebSocketManager) {
        this.masiIdWebSocketManager = masiIdWebSocketManager;
    }

    public void authenticateUser(Role role, String phone) throws IOException {
        masiIdWebSocketManager.authenticateDriver(phone, phone);
    }
}
