package be.hepl.authapi.application.dto.request;

/// <comments>
/// Objet reçu lorsqu'on vérifie si le challenge est OK
/// </comments>
public record VerifyChallengeRequest(
        String email,
        String challenge
) {}