package be.hepl.authapi.application.usecase;

import be.hepl.authapi.domain.model.jwt.Role;
import be.hepl.authapi.domain.service.MasiIdWebSocketSessionService;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MasiIdWebhookUseCase {

    private final MasiIdWebSocketSessionService sessionService;

    public MasiIdWebhookUseCase(MasiIdWebSocketSessionService sessionService) {
        this.sessionService = sessionService;
    }

    public void authenticateUser(Role role, String phone) throws IOException {
        sessionService.authenticateDriver(phone, phone);
    }
}
