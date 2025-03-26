package be.hepl.authapi.domain.model.jwt;

public record Jwt (
        String accessToken,
        String refreshToken
){
}
