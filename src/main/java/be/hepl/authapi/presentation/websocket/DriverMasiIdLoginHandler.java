package be.hepl.authapi.presentation.websocket;

import be.hepl.authapi.application.dto.request.MasiIdLoginRequest;
import be.hepl.authapi.application.dto.response.MasiIdLoginResponse;
import be.hepl.authapi.application.usecase.driver.DriverMasiIdLoginUseCase;
import be.hepl.authapi.application.service.MasiIdSessionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class DriverMasiIdLoginHandler extends TextWebSocketHandler {

    private final MasiIdSessionService masiIdSessionService;

    private final DriverMasiIdLoginUseCase driverMasiIdLoginUseCase;


    public DriverMasiIdLoginHandler(MasiIdSessionService masiIdSessionService,
                                    DriverMasiIdLoginUseCase driverMasiIdLoginUseCase)
    {
        this.masiIdSessionService = masiIdSessionService;
        this.driverMasiIdLoginUseCase = driverMasiIdLoginUseCase;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        MasiIdLoginRequest request = objectMapper.readValue(message.getPayload(), MasiIdLoginRequest.class);


        MasiIdLoginResponse response = driverMasiIdLoginUseCase.loginUseCase(request);

        masiIdSessionService.addDriverSession(request.phoneNumber(), session);

        String jsonResponse = objectMapper.writeValueAsString(response);
        session.sendMessage(new TextMessage(jsonResponse));
    }

}
