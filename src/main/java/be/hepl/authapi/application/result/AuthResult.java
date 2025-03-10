package be.hepl.authapi.application.result;

import be.hepl.authapi.application.usecase.auth.AuthStatus;

public record AuthResult(
        AuthStatus status,
        String message
) {}