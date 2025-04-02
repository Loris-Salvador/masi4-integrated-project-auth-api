package be.hepl.authapi.infrastructure.service.model;

import be.hepl.authapi.domain.model.jwt.Role;

public record MasiIdServiceLoginResponse(
        String phoneNumber
) {
}
