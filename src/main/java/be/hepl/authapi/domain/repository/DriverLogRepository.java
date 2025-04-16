package be.hepl.authapi.domain.repository;

import be.hepl.authapi.domain.model.customer.CustomerLog;
import be.hepl.authapi.domain.model.driver.AnonymousDriverLog;
import be.hepl.authapi.domain.model.driver.DriverLog;

import java.time.Instant;
import java.util.List;

public interface DriverLogRepository {

    void save(DriverLog driverLog);

    List<AnonymousDriverLog> getAnonymousDriverLogsSince(Instant since);
}
