package be.hepl.authapi.application.dto;

public class ChallengeAuthRequest {
    private String challenge;


    public ChallengeAuthRequest() {

    }

    public ChallengeAuthRequest(String challenge) {
        this.challenge = challenge;
    }

    public String getChallenge() {
        return challenge;
    }

    public void setChallenge(String challenge) {
        this.challenge = challenge;
    }
}
