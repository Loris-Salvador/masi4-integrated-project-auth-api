package be.hepl.authapi.application.dto.request;

/// <comments>
/// Objet reçu lors de la demande de refresh
/// </comments>
public record JwtRefreshRequest(
        String refreshToken
) {
}
