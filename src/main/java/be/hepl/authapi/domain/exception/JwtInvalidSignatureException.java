package be.hepl.authapi.domain.exception;

public class JwtInvalidSignatureException extends RuntimeException {
    public JwtInvalidSignatureException(String message) {
        super(message);
    }
}
