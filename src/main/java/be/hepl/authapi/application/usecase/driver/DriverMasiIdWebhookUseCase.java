package be.hepl.authapi.application.usecase.driver;

import be.hepl.authapi.domain.model.jwt.Role;
import be.hepl.authapi.domain.websocket.MasiIdWebSocketManager;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DriverMasiIdWebhookUseCase {

    private final MasiIdWebSocketManager masiIdWebSocketManager;

    public DriverMasiIdWebhookUseCase(MasiIdWebSocketManager masiIdWebSocketManager) {
        this.masiIdWebSocketManager = masiIdWebSocketManager;
    }

    public void authenticateDriver(String phone) throws IOException {
        masiIdWebSocketManager.authenticateDriver(phone, phone);
    }
}
