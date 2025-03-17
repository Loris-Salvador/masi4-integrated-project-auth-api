package be.hepl.authapi.application.service.challenge;

public interface SMSService {
    void sendSMS(String phoneNumber, String message);
}
