package be.hepl.authapi.presentation.controller;

import be.hepl.authapi.application.dto.response.AnonymousCustomerLogResponse;
import be.hepl.authapi.application.dto.response.AnonymousDriverLogResponse;
import be.hepl.authapi.application.usecase.customer.GetCustomerLogsUseCase;
import be.hepl.authapi.application.usecase.driver.GetDriverLogsUseCase;
import be.hepl.authapi.domain.model.customer.AnonymousCustomerLog;
import be.hepl.authapi.domain.model.driver.AnonymousDriverLog;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@Tag(name = "Log")
@RestController
@RequestMapping("/api")
public class LogController {

    private final GetCustomerLogsUseCase getCustomerLogsUseCase;

    private final GetDriverLogsUseCase getDriverLogsUseCase;

    public LogController(GetCustomerLogsUseCase getCustomerLogsUseCase,
                         GetDriverLogsUseCase getDriverLogsUseCase)
    {
        this.getCustomerLogsUseCase = getCustomerLogsUseCase;
        this.getDriverLogsUseCase = getDriverLogsUseCase;
    }

    @GetMapping("/customer/logs")
    public ResponseEntity<AnonymousCustomerLogResponse> getCustomerLogsSince(@RequestParam long since) {
        Instant instant = Instant.ofEpochSecond(since);

        return ResponseEntity.ok(getCustomerLogsUseCase.getCustomerLogs(instant));
    }

    @GetMapping("/driver/logs")
    public ResponseEntity<AnonymousDriverLogResponse> getDriverLogsSince(@RequestParam long since) {
        Instant instant = Instant.ofEpochSecond(since);

        return ResponseEntity.ok(getDriverLogsUseCase.getDriverLogs(instant));
    }
}
