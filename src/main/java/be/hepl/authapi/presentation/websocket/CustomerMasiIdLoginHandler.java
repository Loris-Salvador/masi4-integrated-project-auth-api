package be.hepl.authapi.presentation.websocket;

import be.hepl.authapi.application.dto.request.MasiIdLoginRequest;
import be.hepl.authapi.application.dto.response.MasiIdLoginResponse;
import be.hepl.authapi.application.usecase.customer.CustomerMasiIdLoginUseCase;
import be.hepl.authapi.application.service.MasiIdSessionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Component
public class CustomerMasiIdLoginHandler extends TextWebSocketHandler {

    private final MasiIdSessionService masiIdSessionService;


    private final CustomerMasiIdLoginUseCase customerMasiIdLoginUseCase;

    public CustomerMasiIdLoginHandler(MasiIdSessionService masiIdSessionService,
                                      CustomerMasiIdLoginUseCase customerMasiIdLoginUseCase)
    {
        this.masiIdSessionService = masiIdSessionService;
        this.customerMasiIdLoginUseCase = customerMasiIdLoginUseCase;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        MasiIdLoginRequest request = objectMapper.readValue(message.getPayload(), MasiIdLoginRequest.class);


        MasiIdLoginResponse response = customerMasiIdLoginUseCase.loginUseCase(request);

        masiIdSessionService.addCustomerSession(request.phoneNumber(), session);

        String jsonResponse = objectMapper.writeValueAsString(response);
        session.sendMessage(new TextMessage(jsonResponse));

    }
}
