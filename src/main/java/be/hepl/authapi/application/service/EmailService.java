package be.hepl.authapi.application.service;

public interface EmailService {

    void sendSimpleMessage(String to, String subject, String text);
}
