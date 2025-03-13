package be.hepl.authapi.application.dto.response;

import java.time.LocalDate;

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
