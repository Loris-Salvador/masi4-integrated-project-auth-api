package be.hepl.authapi.infrastructure.entity;

import be.hepl.authapi.domain.model.customer.CustomerLoginMethod;
import be.hepl.authapi.domain.model.customer.Gender;

import java.time.Instant;

public record AnonymousDriverLogEntity(
        Instant timestamp,
        Boolean success,
        Gender gender,
        Instant birthday
) {
}
