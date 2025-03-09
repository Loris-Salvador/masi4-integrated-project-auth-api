package be.hepl.authapi.config;

import be.hepl.authapi.presentation.websocket.EmailAuthSocketHandler;
import be.hepl.authapi.presentation.websocket.SMSAuthSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final EmailAuthSocketHandler emailAuthSocketHandler;

    private final SMSAuthSocketHandler smsAuthSocketHandler;

    public WebSocketConfig(EmailAuthSocketHandler emailAuthSocketHandler, SMSAuthSocketHandler smsAuthSocketHandler) {
        this.emailAuthSocketHandler = emailAuthSocketHandler;
        this.smsAuthSocketHandler = smsAuthSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(emailAuthSocketHandler, "/ws/auth/email").setAllowedOrigins("*");
        registry.addHandler(smsAuthSocketHandler, "/ws/auth/sms").setAllowedOrigins("*");
    }
}