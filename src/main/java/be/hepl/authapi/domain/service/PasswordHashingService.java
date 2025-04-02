package be.hepl.authapi.domain.service;

/// <comments>
/// Interface utilisée par les implémentations qui permettent d'hasher un mdp et de le vérifier
/// </comments>
public interface PasswordHashingService {
    String hashPassword(String password);
    boolean verifyPassword(String rawPassword, String hashedPassword);
}
