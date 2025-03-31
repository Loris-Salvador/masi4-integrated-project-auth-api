package be.hepl.authapi.domain.model.challenge;

import java.time.Instant;

/// <comments>
/// Objet stocké dans le cache Redis
/// </comments>
public record ChallengeDetails(
        ChallengeType type,
        String timestamp,
        String challenge
)
{}
