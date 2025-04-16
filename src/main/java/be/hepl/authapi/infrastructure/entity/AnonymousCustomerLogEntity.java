package be.hepl.authapi.infrastructure.entity;

import be.hepl.authapi.domain.model.customer.CustomerLoginMethod;
import be.hepl.authapi.domain.model.customer.Gender;

import java.time.Instant;
import java.time.LocalDateTime;

public record AnonymousCustomerLogEntity(
        Instant timestamp,
        CustomerLoginMethod method,
        Boolean success,
        Gender gender,
        Instant birthday
) {
}
