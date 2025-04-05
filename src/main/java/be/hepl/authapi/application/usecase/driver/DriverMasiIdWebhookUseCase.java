package be.hepl.authapi.application.usecase.driver;

import be.hepl.authapi.application.service.MasiIdSessionService;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DriverMasiIdWebhookUseCase {

    private final MasiIdSessionService masiIdSessionService;

    public DriverMasiIdWebhookUseCase(MasiIdSessionService masiIdSessionService) {
        this.masiIdSessionService = masiIdSessionService;
    }

    public void authenticateDriver(String phone) throws IOException {
        masiIdSessionService.authenticateDriver(phone, phone);
    }
}
