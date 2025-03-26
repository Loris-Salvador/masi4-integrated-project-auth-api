package be.hepl.authapi.domain.repository;

import be.hepl.authapi.domain.model.client.ClientLog;

/// <comments>
/// Interface utilisée par les implémentations qui permettent de stocker les logs clients
/// </comments>
public interface ClientLogRepository {
    void save(ClientLog clientLog);
}
