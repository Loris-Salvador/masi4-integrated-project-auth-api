package be.hepl.authapi.application.usecase.driver;

import be.hepl.authapi.domain.model.driver.AnonymousDriverLog;
import be.hepl.authapi.domain.repository.DriverLogRepository;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
public class GetDriverLogsUseCase {

    private final DriverLogRepository driverLogRepository;

    public GetDriverLogsUseCase(DriverLogRepository driverLogRepository) {
        this.driverLogRepository = driverLogRepository;
    }

    public List<AnonymousDriverLog> getDriverLogs(Instant instant)
    {
        return driverLogRepository.getAnonymousDriverLogsSince(instant);
    }
}
