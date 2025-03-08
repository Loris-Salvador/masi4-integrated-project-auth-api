package be.hepl.authapi.infrastructure.service;

import be.hepl.authapi.application.service.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;

    public EmailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("loris3salvador@gmail.com"); // L'adresse de l'expéditeur
        message.setTo(to); // L'adresse du destinataire
        message.setSubject(subject); // Le sujet de l'email
        message.setText(text); // Le contenu du message

        emailSender.send(message); // Envoi de l'email
    }
}
