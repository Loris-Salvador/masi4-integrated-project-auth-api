package be.hepl.authapi.domain.repository;

import be.hepl.authapi.domain.model.customer.AnonymousCustomerLog;
import be.hepl.authapi.domain.model.customer.CustomerLog;

import java.time.Instant;
import java.util.List;


public interface CustomerLogRepository {
    void save(CustomerLog customerLog);

    List<AnonymousCustomerLog> getAnonymousCustomerLogsSince(Instant since);
}
