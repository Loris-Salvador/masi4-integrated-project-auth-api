package be.hepl.authapi.domain.repository;

import be.hepl.authapi.domain.model.client.ClientLog;

public interface ClientLogRepository {
    ClientLog save(ClientLog clientLog);
}
