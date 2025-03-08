package be.hepl.authapi.application.usecase;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class GenerateChallengeUseCase {

    private static final int PIN_LENGTH = 6;

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
