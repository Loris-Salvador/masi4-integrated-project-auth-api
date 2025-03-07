package be.hepl.authapi.config;

import be.hepl.authapi.presentation.websocket.EmailAuthSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final EmailAuthSocketHandler emailAuthSocketHandler;

    public WebSocketConfig(EmailAuthSocketHandler emailAuthSocketHandler) {
        this.emailAuthSocketHandler = emailAuthSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(emailAuthSocketHandler, "/ws/auth/email").setAllowedOrigins("*");
    }
}