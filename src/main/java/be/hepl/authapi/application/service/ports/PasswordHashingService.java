package be.hepl.authapi.application.service.ports;

public interface PasswordHashingService {
    String hashPassword(String password);
    boolean verifyPassword(String rawPassword, String hashedPassword);
}
