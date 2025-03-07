package be.hepl.authapi.application.service;

import be.hepl.authapi.domain.model.User;
import be.hepl.authapi.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class AuthService {

    private static final int PIN_LENGTH = 6;

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean authenticate(String username, String password) {
        User user = userRepository.getByUsername(username);

        if (user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }


    public String generateChallenge() {
        SecureRandom random = new SecureRandom();
        StringBuilder pin = new StringBuilder(PIN_LENGTH);

        for (int i = 0; i < PIN_LENGTH; i++) {
            int digit = random.nextInt(10);
            pin.append(digit);
        }

        return pin.toString();
    }
}