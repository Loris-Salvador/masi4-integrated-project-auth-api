package be.hepl.authapi.domain.repository;

import be.hepl.authapi.domain.model.customer.AnonymousCustomerLog;
import be.hepl.authapi.domain.model.customer.CustomerLog;

import java.time.Instant;
import java.util.List;

/// <comments>
/// Interface utilisée par les implémentations qui permettent de stocker les logs clients
/// </comments>
public interface CustomerLogRepository {
    void save(CustomerLog customerLog);

    List<AnonymousCustomerLog> getAnonymousCustomerLogsSince(Instant since);
}
