package be.hepl.authapi.application.dto.response;

import be.hepl.authapi.domain.model.customer.AnonymousCustomerLog;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AnonymousCustomerLogResponse(
        @JsonProperty("last_log_customer_logs")
        Long timestamp,
        @JsonProperty("customer_logs")
        List<AnonymousCustomerLog> customerLogs
) {
}
