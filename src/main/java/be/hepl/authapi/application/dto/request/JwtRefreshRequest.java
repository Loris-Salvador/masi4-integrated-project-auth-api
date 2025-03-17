package be.hepl.authapi.application.dto.request;

public record JwtRefreshRequest(
        String refreshToken
) {
}
