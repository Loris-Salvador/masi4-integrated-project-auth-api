package be.hepl.authapi.application.dto.request;

public record AuthRequest(
        String email,
        String password
) {}