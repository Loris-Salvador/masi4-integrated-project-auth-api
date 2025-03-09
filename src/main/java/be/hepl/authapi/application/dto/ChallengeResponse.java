package be.hepl.authapi.application.dto;

import be.hepl.authapi.application.usecase.ChallengeStatus;

public class ChallengeResponse
{
    ChallengeStatus status;

    public ChallengeResponse()
    {

    }

    public ChallengeResponse(ChallengeStatus status)
    {
        this.status = status;
    }

    public ChallengeStatus getStatus() {
        return status;
    }

    public void setStatus(ChallengeStatus status) {
        this.status = status;
    }
}
