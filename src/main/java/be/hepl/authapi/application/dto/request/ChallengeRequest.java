package be.hepl.authapi.application.dto.request;

public record ChallengeRequest(
        String email,
        String challenge
) {}