package be.hepl.authapi.presentation.websocket;

import be.hepl.authapi.application.dto.request.MasiIdLoginRequest;
import be.hepl.authapi.application.dto.response.MasiIdLoginResponse;
import be.hepl.authapi.domain.model.jwt.Role;
import be.hepl.authapi.domain.service.MasiIdWebSocketSessionService;
import be.hepl.authapi.application.usecase.driver.DriverMasiIdLoginUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class DriverMasiIdLoginHandler extends TextWebSocketHandler {

    private final MasiIdWebSocketSessionService masiIdWebSocketSessionService;

    private final DriverMasiIdLoginUseCase driverMasiIdLoginUseCase;


    public DriverMasiIdLoginHandler(MasiIdWebSocketSessionService masiIdWebSocketSessionService,
                                    DriverMasiIdLoginUseCase driverMasiIdLoginUseCase)
    {
        this.masiIdWebSocketSessionService = masiIdWebSocketSessionService;
        this.driverMasiIdLoginUseCase = driverMasiIdLoginUseCase;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        MasiIdLoginRequest request = objectMapper.readValue(message.getPayload(), MasiIdLoginRequest.class);


        MasiIdLoginResponse response = driverMasiIdLoginUseCase.loginUseCase(request);

        masiIdWebSocketSessionService.addDriverSession(request.phoneNumber(), session);

        String jsonResponse = objectMapper.writeValueAsString(response);
        session.sendMessage(new TextMessage(jsonResponse));
    }

}
