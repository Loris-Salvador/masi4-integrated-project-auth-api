package be.hepl.authapi.application.usecase.customer;

import be.hepl.authapi.application.dto.response.AnonymousCustomerLogResponse;
import be.hepl.authapi.config.SNSConfig;
import be.hepl.authapi.domain.model.customer.AnonymousCustomerLog;
import be.hepl.authapi.domain.repository.CustomerLogRepository;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Component
public class GetCustomerLogsUseCase {

    private final CustomerLogRepository customerLogRepository;

    public GetCustomerLogsUseCase(CustomerLogRepository customerLogRepository)
    {
        this.customerLogRepository = customerLogRepository;
    }

    public AnonymousCustomerLogResponse getCustomerLogs(Instant instant) {
        instant = instant.plusSeconds(1);

        List<AnonymousCustomerLog> originalLogs = customerLogRepository.getAnonymousCustomerLogsSince(instant);
        List<AnonymousCustomerLog> updatedLogs = new ArrayList<>();

        long responseTimestamp;

        if(originalLogs.isEmpty())
            return new AnonymousCustomerLogResponse(null, updatedLogs);

        AnonymousCustomerLog lastLog = originalLogs.getLast();
        responseTimestamp = lastLog.timestamp().getEpochSecond();



        for (AnonymousCustomerLog log : originalLogs) {
            Instant truncatedTimestamp = log.timestamp()
                    .atZone(ZoneId.of("UTC"))
                    .toLocalDate()
                    .atStartOfDay(ZoneId.of("UTC"))
                    .toInstant();

            updatedLogs.add(new AnonymousCustomerLog(
                    truncatedTimestamp,
                    log.method(),
                    log.success(),
                    log.gender(),
                    log.age()
            ));
        }



        return new AnonymousCustomerLogResponse(responseTimestamp, updatedLogs);
    }

}
