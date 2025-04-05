package be.hepl.authapi.application.exception;

public class JwtInvalidSignatureException extends RuntimeException {
    public JwtInvalidSignatureException(String message) {
        super(message);
    }
}
