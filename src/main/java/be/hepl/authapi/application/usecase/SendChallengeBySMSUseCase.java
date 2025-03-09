package be.hepl.authapi.application.usecase;

import be.hepl.authapi.application.service.SMSService;
import be.hepl.authapi.domain.model.Client;
import be.hepl.authapi.domain.repository.ClientRepository;
import org.springframework.stereotype.Component;

@Component
public class SendChallengeBySMSUseCase {

    private final SMSService smsService;

    private final ClientRepository userRepository;

    public SendChallengeBySMSUseCase(SMSService smsService, ClientRepository clientRepository) {
        this.smsService = smsService;
        this.userRepository = clientRepository;
    }

    public void sendChallenge(String email, String challenge)
    {
        Client user = userRepository.findByEmail(email);

        String message = "Voici votre code unique : " + challenge;
        smsService.sendSMS(user.getTelephoneNumber(), message);
    }
}
