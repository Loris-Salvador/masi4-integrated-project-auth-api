package be.hepl.authapi.domain.model.token;

public record Token (
        String accessToken,
        String refreshToken
){
}