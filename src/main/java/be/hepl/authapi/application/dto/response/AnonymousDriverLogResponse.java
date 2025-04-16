package be.hepl.authapi.application.dto.response;

import be.hepl.authapi.domain.model.driver.AnonymousDriverLog;

import java.util.List;

public record AnonymousDriverLogResponse(
        long timestamp,
        List<AnonymousDriverLog> driverLogs
) {
}
