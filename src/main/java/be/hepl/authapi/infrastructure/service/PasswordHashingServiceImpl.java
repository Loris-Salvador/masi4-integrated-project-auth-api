package be.hepl.authapi.infrastructure.service;

import be.hepl.authapi.application.service.PasswordHashingService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
