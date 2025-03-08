package be.hepl.authapi.application.dto;

public class AuthRequest {
    private String email;

    private String password;

    public AuthRequest(String username, String password) {
        this.email = username;
        this.password = password;
    }

    public AuthRequest() {}

    public void setEmail(String username) {
        this.email = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() { return this.email; }

    public String getPassword() { return this.password; }

}

