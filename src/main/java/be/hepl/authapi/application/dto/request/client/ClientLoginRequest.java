package be.hepl.authapi.application.dto.request.client;

public record ClientLoginRequest(
        String email,
        String password
) {}