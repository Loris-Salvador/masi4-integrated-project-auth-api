package be.hepl.authapi.domain.model.customer;

import java.time.Instant;

public record AnonymousCustomerLog(
        Instant timestamp,
        CustomerLoginMethod method,
        Boolean success,
        Gender gender,
        Instant birthday
) {
}
