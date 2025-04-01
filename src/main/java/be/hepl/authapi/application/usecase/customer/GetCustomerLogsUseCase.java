package be.hepl.authapi.application.usecase.customer;

import be.hepl.authapi.domain.model.customer.AnonymousCustomerLog;
import be.hepl.authapi.domain.repository.CustomerLogRepository;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
public class GetCustomerLogsUseCase {

    private final CustomerLogRepository customerLogRepository;

    public GetCustomerLogsUseCase(CustomerLogRepository customerLogRepository)
    {
        this.customerLogRepository = customerLogRepository;
    }

    public List<AnonymousCustomerLog> getCustomerLogs(Instant instant)
    {
        return customerLogRepository.getAnonymousCustomerLogsSince(instant);
    }
}
