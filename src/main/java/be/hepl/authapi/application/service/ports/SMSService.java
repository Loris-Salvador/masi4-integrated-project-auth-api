package be.hepl.authapi.application.service.ports;

public interface SMSService {
    void sendSMS(String phoneNumber, String message);
}
