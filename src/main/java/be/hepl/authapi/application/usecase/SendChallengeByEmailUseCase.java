package be.hepl.authapi.application.usecase;

import be.hepl.authapi.application.service.EmailService;
import org.springframework.stereotype.Component;

@Component
public class SendChallengeByEmailUseCase {


    private final EmailService emailService;

    public SendChallengeByEmailUseCase(EmailService emailService) {
        this.emailService = emailService;
    }

    public void sendChallenge(String email, String challenge) {
        String subject = "Verification Code";
        emailService.sendSimpleMessage(email, subject, "Voici votre code unique : " + challenge);
    }

}
