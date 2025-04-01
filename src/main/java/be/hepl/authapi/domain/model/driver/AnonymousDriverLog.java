package be.hepl.authapi.domain.model.driver;

import be.hepl.authapi.domain.model.customer.Gender;

import java.time.Instant;

public record AnonymousDriverLog(
    Instant timestamp,
    Boolean success,
    Gender gender,
    Instant birthday
) {
}
