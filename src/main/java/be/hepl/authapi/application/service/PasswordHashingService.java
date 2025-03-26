package be.hepl.authapi.application.service;

/// <comments>
/// Interface utilisée par les implémentations qui permettent d'hasher un mdp et de le vérifier
/// </comments>
public interface PasswordHashingService {
    String hashPassword(String password);
    boolean verifyPassword(String rawPassword, String hashedPassword);
}
