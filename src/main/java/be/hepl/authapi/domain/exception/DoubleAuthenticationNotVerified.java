package be.hepl.authapi.domain.exception;

public class DoubleAuthenticationNotVerified extends RuntimeException {
    public DoubleAuthenticationNotVerified(String message) {
        super(message);
    }
}
