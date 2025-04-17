package be.hepl.authapi.application.dto.response;

import be.hepl.authapi.domain.model.driver.AnonymousDriverLog;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AnonymousDriverLogResponse(

        @JsonProperty("last_log_driver_logs")
        Long timestamp,
        @JsonProperty("driver_logs")
        List<AnonymousDriverLog> driverLogs
) {
}
