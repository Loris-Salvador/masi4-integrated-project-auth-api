package be.hepl.authapi.infrastructure.service;

import be.hepl.authapi.domain.service.PasswordHashingService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/// <comments>
/// Implémentation du service d'hasher/vérifier les mdp avec Bcrypt
/// </comments>
@Service
public class PasswordHashingServiceImpl implements PasswordHashingService {
    private final PasswordEncoder passwordEncoder;

    public PasswordHashingServiceImpl() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public String hashPassword(String plainPassword) {
        return passwordEncoder.encode(plainPassword);
    }

    public boolean verifyPassword(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }
}
