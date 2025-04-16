package be.hepl.authapi.application.dto.response;

import be.hepl.authapi.domain.model.customer.AnonymousCustomerLog;

import java.util.List;

public record AnonymousCustomerLogResponse(
        long timestamp,
        List<AnonymousCustomerLog> customerLogs
) {
}
