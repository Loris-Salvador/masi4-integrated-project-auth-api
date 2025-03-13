package be.hepl.authapi.domain.exception;

public class IncorrectChallengeException extends RuntimeException {
    public IncorrectChallengeException(String message) {
        super(message);
    }

    public IncorrectChallengeException() {}
}
