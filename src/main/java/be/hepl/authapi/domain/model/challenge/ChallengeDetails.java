package be.hepl.authapi.domain.model.challenge;

/// <comments>
/// Objet stocké dans le cache Redis
/// </comments>
public record ChallengeDetails(
        ChallengeType type,
        Long timestamp,
        String challenge
)
{}
