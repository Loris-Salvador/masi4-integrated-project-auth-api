package be.hepl.authapi.application.command;

public record AuthCommand(
        String email,
        String password,
        String telephoneNumber,
        String name,
        String firstName,
        String gender
) {}