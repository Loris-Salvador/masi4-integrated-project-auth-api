package be.hepl.authapi.application.dto.request;

/// <comments>
/// Objet reçu lors de la premiere phase de login
/// </comments>
public record ClientLoginRequest(
        String email,
        String password
) {}