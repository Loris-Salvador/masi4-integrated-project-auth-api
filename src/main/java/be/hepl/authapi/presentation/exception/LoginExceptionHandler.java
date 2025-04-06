package be.hepl.authapi.presentation.exception;

import be.hepl.authapi.application.exception.DoubleAuthenticationNotVerified;
import be.hepl.authapi.application.exception.IncorrectPasswordException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Order(1)
@ControllerAdvice
public class LoginExceptionHandler {

    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<String> handleIncorrectPasswordException(IncorrectPasswordException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(DoubleAuthenticationNotVerified.class)
    public ResponseEntity<String> handleDoubleAuthenticationNotVerifiedException(DoubleAuthenticationNotVerified e)
    {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
