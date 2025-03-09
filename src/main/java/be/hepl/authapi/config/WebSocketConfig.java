package be.hepl.authapi.config;

import be.hepl.authapi.presentation.websocket.ClientEmailAuthHandler;
import be.hepl.authapi.presentation.websocket.ClientSMSAuthHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final ClientEmailAuthHandler emailAuthSocketHandler;

    private final ClientSMSAuthHandler smsAuthSocketHandler;

    public WebSocketConfig(ClientEmailAuthHandler emailAuthSocketHandler, ClientSMSAuthHandler smsAuthSocketHandler) {
        this.emailAuthSocketHandler = emailAuthSocketHandler;
        this.smsAuthSocketHandler = smsAuthSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(emailAuthSocketHandler, "/ws/client/auth/email").setAllowedOrigins("*");
        registry.addHandler(smsAuthSocketHandler, "/ws/client/auth/sms").setAllowedOrigins("*");
    }
}