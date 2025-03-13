package be.hepl.authapi.application.dto.response;

import be.hepl.authapi.application.usecase.auth.AuthStatus;

public record AuthResponse(
        AuthStatus status,
        String message
) {}