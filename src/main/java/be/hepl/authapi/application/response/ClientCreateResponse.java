package be.hepl.authapi.application.response;

public record ClientCreateResponse (
    String id,
    String email,
    String telephoneNumber,
    String name,
    String firstName,
    String gender,
    boolean emailVerified,
    boolean phoneVerified
){}
