package be.hepl.authapi.application.service;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class AuthService {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int LENGTH = 32;

    public String generateChallenge() {
        SecureRandom random = new SecureRandom();
        StringBuilder challenge = new StringBuilder(LENGTH);

        for (int i = 0; i < LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            challenge.append(CHARACTERS.charAt(index));
        }

        return challenge.toString();
    }
}
