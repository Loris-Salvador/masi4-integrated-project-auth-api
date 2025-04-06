package be.hepl.authapi.application.dto.request;

public record TokenRefreshRequest(
        String refreshToken
) {
}
