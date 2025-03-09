package be.hepl.authapi.application.usecase;

import be.hepl.authapi.application.service.SMSService;
import be.hepl.authapi.domain.model.User;
import be.hepl.authapi.domain.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class SendChallengeBySMSUseCase {

    private final SMSService smsService;

    private final UserRepository userRepository;

    public SendChallengeBySMSUseCase(SMSService smsService, UserRepository userRepository) {
        this.smsService = smsService;
        this.userRepository = userRepository;
    }

    public void sendChallenge(String email, String challenge)
    {
        User user = userRepository.findByEmail(email);

        String message = "Voici votre code unique : " + challenge;
        smsService.sendSMS(user.getTelephoneNumber(), message);
    }
}
