package be.hepl.authapi.application.service;

public interface SMSService {
    void sendSMS(String phoneNumber, String message);
}
