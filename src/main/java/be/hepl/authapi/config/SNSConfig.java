package be.hepl.authapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SNSConfig {
    @Value("${sns.symmetric_key}")
    private String key;

    public String getKey() {
        return key;
    }
}
