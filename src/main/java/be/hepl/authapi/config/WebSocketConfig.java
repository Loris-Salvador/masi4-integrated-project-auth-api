package be.hepl.authapi.config;

import be.hepl.authapi.presentation.websocket.MasiIdCustomerLoginHandler;
import be.hepl.authapi.presentation.websocket.MasiIdDriverLoginHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final MasiIdCustomerLoginHandler masiIdCustomerLoginHandler;

    private final MasiIdDriverLoginHandler masiIdDriverLoginHandler;


    public WebSocketConfig(MasiIdCustomerLoginHandler masiIdCustomerLoginHandler, MasiIdDriverLoginHandler masiIdDriverLoginHandler) {
        this.masiIdCustomerLoginHandler = masiIdCustomerLoginHandler;
        this.masiIdDriverLoginHandler = masiIdDriverLoginHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(masiIdCustomerLoginHandler, "/ws/customer/login/masi-id").setAllowedOrigins("*");
        registry.addHandler(masiIdDriverLoginHandler, "/ws/driver/login/masi-id").setAllowedOrigins("*");
    }
}