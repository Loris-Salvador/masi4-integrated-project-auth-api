package be.hepl.authapi.application.dto.response;

import java.time.Instant;

public record CustomerCreateResponse (
    String id,
    String email,
    String phoneNumber,
    String name,
    String firstName,
    String gender,
    boolean emailVerified,
    boolean phoneVerified,
    Instant birthday,
    Instant createAccount
){}
