package be.hepl.authapi.domain.model.token;

/// <comments>
/// Token renvoyé lors de l'authentification
/// </comments>
public record Token (
        String accessToken,
        String refreshToken
){
}
