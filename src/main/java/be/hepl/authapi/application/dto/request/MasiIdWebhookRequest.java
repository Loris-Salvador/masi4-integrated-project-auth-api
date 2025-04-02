package be.hepl.authapi.application.dto.request;

import be.hepl.authapi.domain.model.jwt.Role;

public record MasiIdWebhookRequest(
        String phone
) {
}
