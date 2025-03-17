package be.hepl.authapi.application.service.challenge;

public interface EmailService {

    void sendSimpleMessage(String to, String subject, String text);
}
