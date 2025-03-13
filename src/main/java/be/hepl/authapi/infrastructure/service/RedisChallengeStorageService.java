package be.hepl.authapi.infrastructure.service;

import be.hepl.authapi.application.service.ChallengeStorageService;
import be.hepl.authapi.domain.exception.UserNotFoundException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisChallengeStorageService implements ChallengeStorageService {

    private final StringRedisTemplate redisTemplate;

    public RedisChallengeStorageService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void storeChallenge(String email, String challenge, int timeout) {
        redisTemplate.opsForValue().set(email, challenge, timeout, TimeUnit.MINUTES);
    }

    public String getChallenge(String email) {
        String challenge = redisTemplate.opsForValue().get(email);

        if (challenge == null) {
            throw new UserNotFoundException("Challenge not found for email: " + email);
        }
        return challenge;    }

    public void removeChallenge(String email) {
        redisTemplate.delete(email);
    }
}
