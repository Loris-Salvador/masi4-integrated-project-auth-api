package be.hepl.authapi.application.dto.response;

import java.time.Instant;

/// <comments>
/// Objet renvoyé lors de la création d'un client (si tout est ok)
/// </comments>
public record ClientCreateResponse (
    String id,
    String email,
    String phoneNumber,
    String lastName,
    String firstName,
    String gender,
    boolean emailVerified,
    boolean phoneVerified,
    Instant birthDate,
    Instant createAccount
){}
