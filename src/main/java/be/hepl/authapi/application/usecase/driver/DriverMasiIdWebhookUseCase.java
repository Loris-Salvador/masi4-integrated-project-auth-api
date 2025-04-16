package be.hepl.authapi.application.usecase.driver;

import be.hepl.authapi.application.service.MasiIdSessionService;
import be.hepl.authapi.domain.model.driver.DriverLog;
import be.hepl.authapi.domain.repository.DriverLogRepository;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;

@Component
public class DriverMasiIdWebhookUseCase {

    private final MasiIdSessionService masiIdSessionService;

    private final DriverLogRepository driverLogRepository;

    public DriverMasiIdWebhookUseCase(MasiIdSessionService masiIdSessionService, DriverLogRepository driverLogRepository) {
        this.masiIdSessionService = masiIdSessionService;
        this.driverLogRepository = driverLogRepository;
    }

    public void authenticateDriver(String phone) throws IOException {
        masiIdSessionService.authenticateDriver(phone, phone);

        DriverLog driverLog = new DriverLog();

        driverLog.setSuccess(true);
        driverLog.setTimestamp(Instant.now());
        driverLog.setPhoneNumber(phone);

        driverLogRepository.save(driverLog);
    }
}
