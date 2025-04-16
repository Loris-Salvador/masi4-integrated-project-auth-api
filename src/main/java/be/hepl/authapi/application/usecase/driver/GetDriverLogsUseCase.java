package be.hepl.authapi.application.usecase.driver;

import be.hepl.authapi.application.dto.response.AnonymousCustomerLogResponse;
import be.hepl.authapi.application.dto.response.AnonymousDriverLogResponse;
import be.hepl.authapi.domain.model.customer.AnonymousCustomerLog;
import be.hepl.authapi.domain.model.driver.AnonymousDriverLog;
import be.hepl.authapi.domain.repository.DriverLogRepository;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Component
public class GetDriverLogsUseCase {

    private final DriverLogRepository driverLogRepository;

    public GetDriverLogsUseCase(DriverLogRepository driverLogRepository) {
        this.driverLogRepository = driverLogRepository;
    }

    public AnonymousDriverLogResponse getDriverLogs(Instant instant)
    {

        List<AnonymousDriverLog> originalLogs = driverLogRepository.getAnonymousDriverLogsSince(instant);
        List<AnonymousDriverLog> updatedLogs = new ArrayList<>();

        long responseTimestamp;

        AnonymousDriverLog lastLog = originalLogs.getLast();
        responseTimestamp = lastLog.timestamp().getEpochSecond();



        for (AnonymousDriverLog log : originalLogs) {
            Instant truncatedTimestamp = log.timestamp()
                    .atZone(ZoneId.of("UTC"))
                    .toLocalDate()
                    .atStartOfDay(ZoneId.of("UTC"))
                    .toInstant();

            updatedLogs.add(new AnonymousDriverLog(
                    truncatedTimestamp,
                    log.success(),
                    log.gender(),
                    log.age()
            ));
        }



        return new AnonymousDriverLogResponse(responseTimestamp, updatedLogs);
    }
}
