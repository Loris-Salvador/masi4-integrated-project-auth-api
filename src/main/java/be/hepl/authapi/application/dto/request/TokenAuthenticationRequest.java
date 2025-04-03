package be.hepl.authapi.application.dto.request;

/// <comments>
/// Objet reçu lors de le demande d'authentification avec le JWT Token
/// </comments>
public record TokenAuthenticationRequest(
        String token
) {}
