package be.hepl.authapi.infrastructure.service;

import be.hepl.authapi.config.TwilioConfig;
import be.hepl.authapi.domain.service.SMSService;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Value;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

/// <comments>
/// Implémentation du service d'envoi de SMS (Twilio)
/// </comments>
@Service
public class TwilioSMSService implements SMSService {

    private final TwilioConfig twilioConfig;

    public TwilioSMSService(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
    }

    @Override
    public void sendSMS(String phoneNumber, String message) {
        Message.creator(
                new PhoneNumber(phoneNumber),  // Destinataire
                new PhoneNumber(twilioConfig.getFromNumber()),  // Numéro Twilio
                message
        ).create();
    }
}
