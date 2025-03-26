package be.hepl.authapi.application.dto.response;

import java.time.LocalDate;

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
    LocalDate birthDate,
    LocalDate createAccount
){}
