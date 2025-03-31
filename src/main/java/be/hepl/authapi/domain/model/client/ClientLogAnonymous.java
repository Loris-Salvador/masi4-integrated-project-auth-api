package be.hepl.authapi.domain.model.client;

import java.time.Instant;

public record ClientLogAnonymous(
        Instant timestamp,
        ClientLoginMethod method,
        Boolean success,
        Gender gender,
        Instant birthday
) {
}
