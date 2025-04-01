package be.hepl.authapi.application.usecase;

import be.hepl.authapi.application.service.EmailService;
import be.hepl.authapi.application.service.SMSService;
import be.hepl.authapi.domain.model.challenge.ChallengeDetails;
import be.hepl.authapi.domain.model.challenge.ChallengeType;
import be.hepl.authapi.domain.model.customer.Customer;
import be.hepl.authapi.domain.repository.ChallengeRepository;
import be.hepl.authapi.domain.repository.CustomerRepository;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.time.Instant;

/// <comments>
/// Use case utilisé lors de la demande d'envoi de challenge
/// </comments>
@Component
public class SendChallengeUseCase {

    private final static int CHALLENGE_LENGTH = 6;

    private final SMSService smsService;

    private final EmailService emailService;

    private final CustomerRepository userRepository;

    private final ChallengeRepository challengeRepository;

    public SendChallengeUseCase(SMSService smsService, CustomerRepository customerRepository, EmailService emailService, ChallengeRepository challengeRepository) {
        this.smsService = smsService;
        this.userRepository = customerRepository;
        this.emailService = emailService;
        this.challengeRepository = challengeRepository;
    }

    public void send(String email, ChallengeType challengeType)
    {
        Customer user = userRepository.findByEmail(email);

        String challenge = generateChallenge();


        if(challengeType == ChallengeType.SMS)
        {
            String message = "Voici votre code unique : " + challenge;
            smsService.sendSMS(user.getPhoneNumber(), message);
        }
        else if (challengeType == ChallengeType.EMAIL)
        {
            String subject = "Verification Code";
            emailService.sendSimpleMessage(email, subject, "Voici votre code unique : " + challenge);
        }

        ChallengeDetails challengeDetails = new ChallengeDetails(challengeType, Instant.now().toString(), challenge);

        challengeRepository.storeChallenge(email, challengeDetails, 5);
    }

    private String generateChallenge() {
        SecureRandom random = new SecureRandom();
        StringBuilder pin = new StringBuilder(CHALLENGE_LENGTH);

        for (int i = 0; i < CHALLENGE_LENGTH; i++) {
            int digit = random.nextInt(10);
            pin.append(digit);
        }

        return pin.toString();
    }

}
