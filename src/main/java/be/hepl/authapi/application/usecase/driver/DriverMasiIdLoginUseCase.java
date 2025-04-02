package be.hepl.authapi.application.usecase.driver;

import be.hepl.authapi.application.dto.request.MasiIdLoginRequest;
import be.hepl.authapi.application.dto.response.MasiIdLoginResponse;
import be.hepl.authapi.domain.service.MasiIdService;
import be.hepl.authapi.domain.service.MasiIdWebSocketSessionService;
import be.hepl.authapi.domain.exception.UserNotFoundException;
import be.hepl.authapi.domain.model.jwt.Role;
import be.hepl.authapi.domain.repository.DriverRepository;
import org.springframework.stereotype.Component;

@Component
public class DriverMasiIdLoginUseCase {

    private final DriverRepository driverRepository;

    private final MasiIdService masiIdService;


    public DriverMasiIdLoginUseCase(final DriverRepository driverRepository, final MasiIdService masiIdService, MasiIdWebSocketSessionService masiIdWebSocketSessionService) {
        this.driverRepository = driverRepository;
        this.masiIdService = masiIdService;
    }

    public MasiIdLoginResponse loginUseCase(MasiIdLoginRequest loginRequest) {

        String phone;

        try
        {
            driverRepository.findById(loginRequest.phoneNumber());

            phone = masiIdService.UserConnection(loginRequest.phoneNumber(), Role.DRIVER);
        }
        catch (UserNotFoundException e)
        {
            return new MasiIdLoginResponse("ERROR", e.getMessage());
        }


        //création réponse
        return new MasiIdLoginResponse("SEND", "Check your masi id app");
    }
}
