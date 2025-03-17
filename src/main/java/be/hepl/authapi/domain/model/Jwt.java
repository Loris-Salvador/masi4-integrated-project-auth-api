package be.hepl.authapi.domain.model;

public record Jwt (
        String accessToken,
        String refreshToken
){
}
