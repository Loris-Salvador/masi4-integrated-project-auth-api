package be.hepl.authapi.application.usecase.driver;

import be.hepl.authapi.application.dto.request.MasiIdLoginRequest;
import be.hepl.authapi.domain.model.driver.Driver;
import be.hepl.authapi.domain.repository.DriverRepository;
import org.springframework.stereotype.Component;

@Component
public class DriverMasiIdLoginUseCase {

    private final DriverRepository driverRepository;

    public DriverMasiIdLoginUseCase(final DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public void loginUseCase(MasiIdLoginRequest loginRequest) {

        //verifier si il existe si non on throw UserNotFoundExecption
        driverRepository.findById(loginRequest.phoneNumber());



    }
}
