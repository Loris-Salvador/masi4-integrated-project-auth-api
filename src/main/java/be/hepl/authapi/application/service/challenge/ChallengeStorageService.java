package be.hepl.authapi.application.service.challenge;

import be.hepl.authapi.domain.model.challenge.ChallengeDetails;

public interface ChallengeStorageService {
    void storeChallenge(String email, ChallengeDetails challengeDetails, int timeout);

    ChallengeDetails getChallenge(String email);

    void removeChallenge(String email);
}
