package be.hepl.authapi.domain.model.jwt;

/// <comments>
/// Token renvoyé lors de l'authentification
/// </comments>
public record Jwt (
        String accessToken,
        String refreshToken
){
}
