package be.hepl.authapi.application.usecase.driver;

import be.hepl.authapi.application.dto.request.MasiIdLoginRequest;
import be.hepl.authapi.application.dto.response.MasiIdLoginResponse;
import be.hepl.authapi.domain.model.driver.Driver;
import be.hepl.authapi.domain.model.driver.DriverLog;
import be.hepl.authapi.domain.model.masiid.MasiIdLoginStatus;
import be.hepl.authapi.application.service.MasiIdService;
import be.hepl.authapi.domain.exception.UserNotFoundException;
import be.hepl.authapi.domain.model.token.Role;
import be.hepl.authapi.domain.repository.DriverLogRepository;
import be.hepl.authapi.domain.repository.DriverRepository;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class DriverMasiIdLoginUseCase {

    private final DriverRepository driverRepository;

    private final DriverLogRepository driverLogRepository;

    private final MasiIdService masiIdService;

    public DriverMasiIdLoginUseCase(final DriverRepository driverRepository, final MasiIdService masiIdService, DriverLogRepository driverLogRepository) {
        this.driverRepository = driverRepository;
        this.masiIdService = masiIdService;
        this.driverLogRepository = driverLogRepository;
    }

    public MasiIdLoginResponse loginUseCase(MasiIdLoginRequest loginRequest) {
        try
        {
            driverRepository.findById(loginRequest.phoneNumber());

            masiIdService.UserConnection(loginRequest.phoneNumber(), Role.DRIVER);
        }
        catch (UserNotFoundException e)
        {
            DriverLog driverLog = new DriverLog();

            driverLog.setSuccess(false);
            driverLog.setTimestamp(Instant.now());

            driverLogRepository.save(driverLog);
            return new MasiIdLoginResponse(MasiIdLoginStatus.ERROR, e.getMessage());
        }



        return new MasiIdLoginResponse(MasiIdLoginStatus.SEND, "Check your masi id app");
    }
}
