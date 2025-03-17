package be.hepl.authapi.application.dto.request.challenge;

public record VerifyChallengeRequest(
        String email,
        String challenge
) {}