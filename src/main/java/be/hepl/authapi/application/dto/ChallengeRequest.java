package be.hepl.authapi.application.dto;

public class ChallengeRequest {
    private String challenge;


    public ChallengeRequest() {

    }

    public ChallengeRequest(String challenge) {
        this.challenge = challenge;
    }

    public String getChallenge() {
        return challenge;
    }

    public void setChallenge(String challenge) {
        this.challenge = challenge;
    }
}
