package be.hepl.authapi.application.dto.request;

public record ClientLoginRequest(
        String email,
        String password
) {}