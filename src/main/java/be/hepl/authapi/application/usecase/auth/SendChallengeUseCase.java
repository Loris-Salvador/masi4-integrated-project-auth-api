package be.hepl.authapi.application.usecase.auth;

import be.hepl.authapi.application.service.EmailService;
import be.hepl.authapi.application.service.SMSService;
import be.hepl.authapi.domain.model.ChallengeDetails;
import be.hepl.authapi.domain.model.ChallengeType;
import be.hepl.authapi.domain.model.Client;
import be.hepl.authapi.application.service.ChallengeStorageService;
import be.hepl.authapi.domain.repository.ClientRepository;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.time.Instant;

@Component
public class SendChallengeUseCase {

    private final SMSService smsService;

    private final EmailService emailService;

    private final ClientRepository userRepository;

    private final ChallengeStorageService challengeStorageService;

    public SendChallengeUseCase(SMSService smsService, ClientRepository clientRepository, EmailService emailService, ChallengeStorageService challengeStorageService) {
        this.smsService = smsService;
        this.userRepository = clientRepository;
        this.emailService = emailService;
        this.challengeStorageService = challengeStorageService;
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

        ChallengeDetails challengeDetails = new ChallengeDetails(challengeType, Instant.now().getEpochSecond(), email);

        challengeStorageService.storeChallenge(email, challengeDetails, 5);
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
