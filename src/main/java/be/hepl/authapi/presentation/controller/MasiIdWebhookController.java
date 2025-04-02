package be.hepl.authapi.presentation.controller;

import be.hepl.authapi.application.dto.request.MasiIdWebhookRequest;
import be.hepl.authapi.application.usecase.MasiIdWebhookUseCase;
import be.hepl.authapi.domain.model.jwt.Role;
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
    public void masiIdWebhook(@RequestParam Role role, @RequestBody MasiIdWebhookRequest request) throws IOException {
        webhookUseCase.authenticateUser(role, request.phone());
    }

}
