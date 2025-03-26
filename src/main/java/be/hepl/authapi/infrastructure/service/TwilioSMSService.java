package be.hepl.authapi.infrastructure.service;

import be.hepl.authapi.application.service.SMSService;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Value;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

/// <comments>
/// Implémentation du service d'envoi de SMS (Twilio)
/// </comments>
@Service
public class TwilioSMSService implements SMSService {

    @Value("${twilio.phone_number}")
    private String fromNumber;

    @Override
    public void sendSMS(String phoneNumber, String message) {
        Message.creator(
                new PhoneNumber(phoneNumber),  // Destinataire
                new PhoneNumber(fromNumber),  // Numéro Twilio
                message
        ).create();
    }
}
