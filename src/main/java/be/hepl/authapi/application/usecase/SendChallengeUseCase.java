package be.hepl.authapi.application.usecase;

import be.hepl.authapi.application.service.EmailService;
import be.hepl.authapi.application.service.SMSService;
import be.hepl.authapi.domain.model.challenge.ChallengeDetails;
import be.hepl.authapi.domain.model.challenge.ChallengeType;
import be.hepl.authapi.domain.model.client.Client;
import be.hepl.authapi.domain.repository.ChallengeRepository;
import be.hepl.authapi.domain.repository.ClientRepository;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.time.Instant;

@Component
public class SendChallengeUseCase {

    private final SMSService smsService;

    private final EmailService emailService;

    private final ClientRepository userRepository;

    private final ChallengeRepository challengeRepository;

    public SendChallengeUseCase(SMSService smsService, ClientRepository clientRepository, EmailService emailService, ChallengeRepository challengeRepository) {
        this.smsService = smsService;
        this.userRepository = clientRepository;
        this.emailService = emailService;
        this.challengeRepository = challengeRepository;
    }

    public void send(String email, ChallengeType challengeType)
    {
        Client user = userRepository.findByEmail(email);

        String challenge = generateChallenge(6);


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

        ChallengeDetails challengeDetails = new ChallengeDetails(challengeType, Instant.now().getEpochSecond(), challenge);

        challengeRepository.storeChallenge(email, challengeDetails, 5);
    }

    private String generateChallenge(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder pin = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int digit = random.nextInt(10);
            pin.append(digit);
        }

        return pin.toString();
    }

}
