package be.hepl.authapi.application.usecase.client;

import be.hepl.authapi.domain.model.client.ClientLogAnonymous;
import be.hepl.authapi.domain.repository.ClientLogRepository;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
public class GetClientLogsUseCase {

    private final ClientLogRepository clientLogRepository;

    public GetClientLogsUseCase(ClientLogRepository clientLogRepository)
    {
        this.clientLogRepository = clientLogRepository;
    }

    public List<ClientLogAnonymous> getClientLogs(Instant instant)
    {
        return clientLogRepository.getClientLogsSince(instant);
    }
}
