package be.hepl.authapi.application.dto;

public class EmailAuthRequest {
    private String username;

    private String password;

    public EmailAuthRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public EmailAuthRequest() {}

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() { return this.username; }

    public String getPassword() { return this.password; }

}

