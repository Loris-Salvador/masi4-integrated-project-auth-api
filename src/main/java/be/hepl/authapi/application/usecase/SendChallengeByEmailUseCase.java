package be.hepl.authapi.application.usecase;

import be.hepl.authapi.application.service.EmailService;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class SendChallengeByEmailUseCase {

    private static final int PIN_LENGTH = 6;

    private final EmailService emailService;

    public SendChallengeByEmailUseCase(EmailService emailService) {
        this.emailService = emailService;
    }

    public void sendEmail(String to) {
        String challenge = generateChallenge();
        String subject = "Verification Code";
        emailService.sendSimpleMessage(to, subject, "Voici votre code unique" + challenge);
    }

    private static String generateChallenge() {
        SecureRandom random = new SecureRandom();
        StringBuilder pin = new StringBuilder(PIN_LENGTH);

        for (int i = 0; i < PIN_LENGTH; i++) {
            int digit = random.nextInt(10);
            pin.append(digit);
        }

        return pin.toString();
    }
}
