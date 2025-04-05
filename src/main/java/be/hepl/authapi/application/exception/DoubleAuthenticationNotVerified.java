package be.hepl.authapi.application.exception;

public class DoubleAuthenticationNotVerified extends RuntimeException {
    public DoubleAuthenticationNotVerified(String message) {
        super(message);
    }
}
