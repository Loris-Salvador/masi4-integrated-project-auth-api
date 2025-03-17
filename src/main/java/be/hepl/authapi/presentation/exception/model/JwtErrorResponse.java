package be.hepl.authapi.presentation.exception.model;

public record JwtErrorResponse(
        JwtErrorCode errorCode,
        String message
) {
}
