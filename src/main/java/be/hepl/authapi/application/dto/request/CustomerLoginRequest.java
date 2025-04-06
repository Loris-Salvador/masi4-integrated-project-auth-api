package be.hepl.authapi.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record CustomerLoginRequest(
        String email,
        String password
) {}