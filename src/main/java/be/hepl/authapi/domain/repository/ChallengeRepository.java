package be.hepl.authapi.domain.repository;

import be.hepl.authapi.domain.model.challenge.ChallengeDetails;

/// <comments>
/// Interface utilisée par les implémentations qui permettent de stocker les challenges
/// </comments>
public interface ChallengeRepository {
    void storeChallenge(String email, ChallengeDetails challengeDetails, int timeout);

    ChallengeDetails getChallenge(String email);

    void removeChallenge(String email);
}
