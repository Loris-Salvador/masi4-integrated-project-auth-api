package be.hepl.authapi.presentation.request;

public record AuthRequest(
        String email,
        String password
) {}