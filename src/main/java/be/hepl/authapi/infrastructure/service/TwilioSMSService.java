package be.hepl.authapi.infrastructure.service;

import be.hepl.authapi.config.TwilioConfig;
import be.hepl.authapi.application.service.SMSService;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

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
