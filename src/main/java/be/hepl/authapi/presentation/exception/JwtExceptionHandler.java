package be.hepl.authapi.presentation.exception;

import be.hepl.authapi.domain.exception.DoubleAuthenticationNotVerified;
import be.hepl.authapi.domain.exception.IncorrectPasswordException;
import be.hepl.authapi.domain.exception.JwtExpiredException;
import be.hepl.authapi.domain.exception.JwtInvalidSignatureException;
import be.hepl.authapi.presentation.exception.model.JwtErrorCode;
import be.hepl.authapi.presentation.exception.model.JwtErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class JwtExceptionHandler {

    @ExceptionHandler(JwtInvalidSignatureException.class)
    public ResponseEntity<JwtErrorResponse> handleJwtInvalidSignature(JwtInvalidSignatureException ex) {
        JwtErrorResponse response = new JwtErrorResponse(JwtErrorCode.INVALID_SIGNATURE, ex.getMessage());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(JwtExpiredException.class)
    public ResponseEntity<JwtErrorResponse> handleJwtInvalidSignature(JwtExpiredException e)
    {
        JwtErrorResponse response = new JwtErrorResponse(JwtErrorCode.TOKEN_EXPIRED, e.getMessage());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
}
