package be.hepl.authapi.config;

import be.hepl.authapi.application.dto.response.MasiIdLoginResponse;
import be.hepl.authapi.presentation.websocket.CustomerMasiIdLoginHandler;
import be.hepl.authapi.presentation.websocket.DriverMasiIdLoginHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final CustomerMasiIdLoginHandler masiIdCustomerLoginHandler;

    private final DriverMasiIdLoginHandler masiIdDriverLoginHandler;


    public WebSocketConfig(CustomerMasiIdLoginHandler masiIdCustomerLoginHandler, DriverMasiIdLoginHandler masiIdDriverLoginHandler) {
        this.masiIdCustomerLoginHandler = masiIdCustomerLoginHandler;
        this.masiIdDriverLoginHandler = masiIdDriverLoginHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(masiIdCustomerLoginHandler, "/ws/customer/login/masi-id").setAllowedOrigins("*");
        registry.addHandler(masiIdDriverLoginHandler, "/ws/driver/login/masi-id").setAllowedOrigins("*");
    }
}