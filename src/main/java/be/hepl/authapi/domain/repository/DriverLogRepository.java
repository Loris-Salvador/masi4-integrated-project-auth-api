package be.hepl.authapi.domain.repository;

import be.hepl.authapi.domain.model.driver.AnonymousDriverLog;

import java.time.Instant;
import java.util.List;

public interface DriverLogRepository {
    List<AnonymousDriverLog> getAnonymousDriverLogsSince(Instant since);
}
