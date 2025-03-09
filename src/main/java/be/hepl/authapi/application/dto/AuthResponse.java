package be.hepl.authapi.application.dto;

import be.hepl.authapi.application.usecase.AuthStatus;

public class AuthResponse {
    private AuthStatus status;
    private String message;

    public AuthResponse(AuthStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AuthStatus getStatus() {
        return status;
    }

    public void setStatus(AuthStatus status) {
        this.status = status;
    }
}
