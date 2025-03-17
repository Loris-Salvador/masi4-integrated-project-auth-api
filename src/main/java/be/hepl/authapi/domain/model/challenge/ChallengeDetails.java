package be.hepl.authapi.domain.model.challenge;

public record ChallengeDetails(
        ChallengeType type,
        Long timestamp,
        String challenge
)
{}
