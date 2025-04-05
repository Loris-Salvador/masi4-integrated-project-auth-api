package be.hepl.authapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig {
    @Value("${spring.mail.username}")
    private String from;

    public String getFrom() {
        return from;
    }
}
