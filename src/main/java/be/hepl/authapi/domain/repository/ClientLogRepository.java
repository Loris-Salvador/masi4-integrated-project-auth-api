package be.hepl.authapi.domain.repository;

import be.hepl.authapi.domain.model.ClientLog;

public interface ClientLogRepository {
    ClientLog save(ClientLog clientLog);
}
