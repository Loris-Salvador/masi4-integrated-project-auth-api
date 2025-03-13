package be.hepl.authapi.application.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordHashingServiceImpl implements PasswordHashingService {
    private final PasswordEncoder passwordEncoder;

    public PasswordHashingServiceImpl() {
        this.passwordEncoder = new BCryptPasswordEncoder(); // Service Infrastructure
    }

    public String hashPassword(String plainPassword) {
        return passwordEncoder.encode(plainPassword);
    }

    public boolean verifyPassword(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }
}
