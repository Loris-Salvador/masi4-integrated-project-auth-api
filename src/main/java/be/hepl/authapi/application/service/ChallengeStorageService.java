package be.hepl.authapi.application.service;

import be.hepl.authapi.domain.model.ChallengeDetails;

public interface ChallengeStorageService {
    void storeChallenge(String email, ChallengeDetails challengeDetails, int timeout);

    ChallengeDetails getChallenge(String email);

    void removeChallenge(String email);
}
