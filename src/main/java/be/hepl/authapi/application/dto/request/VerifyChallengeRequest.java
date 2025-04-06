package be.hepl.authapi.application.dto.request;

public record VerifyChallengeRequest(
        String email,
        String challenge
) {}