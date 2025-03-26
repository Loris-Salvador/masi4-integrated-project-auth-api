package be.hepl.authapi.infrastructure.repository;

import be.hepl.authapi.domain.repository.ChallengeRepository;
import be.hepl.authapi.domain.exception.UserNotFoundException;
import be.hepl.authapi.domain.model.challenge.ChallengeDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/// <comments>
/// Implémentation qui sauvegarde les challenges dans le cache Redis
/// </comments>
@Service
public class RedisChallengeRepositoryService implements ChallengeRepository {

    private final StringRedisTemplate redisTemplate;

    public RedisChallengeRepositoryService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void storeChallenge(String email, ChallengeDetails challengeDetails, int timeout) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String challengeJson = objectMapper.writeValueAsString(challengeDetails);
            redisTemplate.opsForValue().set(email, challengeJson, timeout, TimeUnit.MINUTES);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing challenge details", e);
        }
    }

    public ChallengeDetails getChallenge(String email) {
        String challengeJson = redisTemplate.opsForValue().get(email);
        ObjectMapper objectMapper = new ObjectMapper();


        if (challengeJson == null) {
            throw new UserNotFoundException("Challenge not found for email: " + email);
        }

        try {
            return objectMapper.readValue(challengeJson, ChallengeDetails.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error deserializing challenge details", e);
        }
    }

    public void removeChallenge(String email) {
        redisTemplate.delete(email);
    }
}