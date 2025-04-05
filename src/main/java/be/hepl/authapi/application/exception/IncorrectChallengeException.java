package be.hepl.authapi.application.exception;

public class IncorrectChallengeException extends RuntimeException {
    public IncorrectChallengeException(String message) {
        super(message);
    }

    public IncorrectChallengeException() {}
}
