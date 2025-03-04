package be.hepl.authapi.application.dto;

public class ChallengeDTO {
    private String challenge;


    public ChallengeDTO() {

    }

    public ChallengeDTO(String challenge) {
        this.challenge = challenge;
    }

    public String getChallenge() {
        return challenge;
    }

    public void setChallenge(String challenge) {
        this.challenge = challenge;
    }
}
