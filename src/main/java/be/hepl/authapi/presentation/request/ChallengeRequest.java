package be.hepl.authapi.presentation.request;

public record ChallengeRequest(
        String email,
        String challenge
) {}