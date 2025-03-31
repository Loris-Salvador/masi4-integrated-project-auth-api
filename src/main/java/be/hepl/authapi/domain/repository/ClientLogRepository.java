package be.hepl.authapi.domain.repository;

import be.hepl.authapi.domain.model.client.ClientLog;
import be.hepl.authapi.domain.model.client.ClientLogAnonymous;

import java.time.Instant;
import java.util.List;

/// <comments>
/// Interface utilisée par les implémentations qui permettent de stocker les logs clients
/// </comments>
public interface ClientLogRepository {
    void save(ClientLog clientLog);

    List<ClientLogAnonymous> getClientLogsSince(Instant since);
}
