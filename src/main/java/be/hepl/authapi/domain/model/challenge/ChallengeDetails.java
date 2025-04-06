package be.hepl.authapi.domain.model.challenge;


public record ChallengeDetails(
        ChallengeType type,
        String timestamp,
        String challenge
)
{}
