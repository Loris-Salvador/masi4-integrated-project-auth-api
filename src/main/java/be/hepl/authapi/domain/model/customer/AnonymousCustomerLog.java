package be.hepl.authapi.domain.model.customer;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record AnonymousCustomerLog(
        Instant timestamp,
        CustomerLoginMethod method,
        Boolean success,
        Gender gender,
        int age
) {
}
