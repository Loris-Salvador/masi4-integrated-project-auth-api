package be.hepl.authapi.domain.repository;

public interface ChallengeStorageService {
    void storeChallenge(String email, String challenge, int timeout);

    String getChallenge(String email);

    void removeChallenge(String email);
}
