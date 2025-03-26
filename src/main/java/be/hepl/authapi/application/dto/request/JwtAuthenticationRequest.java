package be.hepl.authapi.application.dto.request;

/// <comments>
/// Objet reçu lors de le demande d'authentification avec le JWT Token
/// </comments>
public record JwtAuthenticationRequest(
        String token
) {}
