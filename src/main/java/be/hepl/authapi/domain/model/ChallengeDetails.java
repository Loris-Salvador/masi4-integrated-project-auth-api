package be.hepl.authapi.domain.model;

public record ChallengeDetails(
        ChallengeType type,
        Long timestamp,
        String challenge
)
{}
