package be.hepl.authapi.application.service;

/// <comments>
/// Interface utilisée par les implémentations qui permettent d'envoyé des mails
/// </comments>
public interface EmailService {

    void sendSimpleMessage(String to, String subject, String text);
}
