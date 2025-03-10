package be.hepl.authapi.application.command;

public record AuthCommand(
        String email,
        String password
) {}