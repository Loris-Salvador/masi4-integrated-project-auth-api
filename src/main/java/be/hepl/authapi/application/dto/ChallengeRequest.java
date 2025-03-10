package be.hepl.authapi.application.dto;

public class ChallengeRequest {
    private String challenge;

    private String email;


    public ChallengeRequest() {

    }

    public ChallengeRequest(String challenge, String email) {
        this.challenge = challenge;
        this.email = email;
    }

    public String getChallenge() {
        return challenge;
    }

    public void setChallenge(String challenge) {
        this.challenge = challenge;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
