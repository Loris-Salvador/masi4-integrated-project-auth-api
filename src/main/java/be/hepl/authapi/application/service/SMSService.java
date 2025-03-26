package be.hepl.authapi.application.service;

/// <comments>
/// Interface utilisée par les implémentations qui permettent d'envoyé des SMS
/// </comments>
public interface SMSService {
    void sendSMS(String phoneNumber, String message);
}
