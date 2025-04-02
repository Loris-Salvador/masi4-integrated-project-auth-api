package be.hepl.authapi.presentation.controller.customer;

import be.hepl.authapi.application.dto.request.MasiIdWebhookRequest;
import be.hepl.authapi.application.usecase.MasiIdWebhookUseCase;
import be.hepl.authapi.domain.model.jwt.Role;
import be.hepl.authapi.domain.service.MasiIdWebSocketSessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/masi-id/webhook")
public class MasiIdWebhookController {

    private final MasiIdWebhookUseCase webhookUseCase;

    public MasiIdWebhookController(MasiIdWebhookUseCase webhookUseCase) {
        this.webhookUseCase = webhookUseCase;
    }

    @PostMapping
    public void masiIdWebhook(@RequestParam String role, @RequestBody MasiIdWebhookRequest request) throws IOException {
        Role r = Role.valueOf(role);

        webhookUseCase.authenticateUser(r, request.phone());
    }

}
