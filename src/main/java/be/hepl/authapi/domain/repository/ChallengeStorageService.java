package be.hepl.authapi.domain.repository;

import java.util.concurrent.TimeUnit;

public interface ChallengeStorageService {
    void storeChallenge(String email, String challenge, int timeout);

    String getChallenge(String email);

    void removeChallenge(String email);
}
