package be.hepl.authapi.presentation.controller;

import be.hepl.authapi.application.usecase.customer.GetCustomerLogsUseCase;
import be.hepl.authapi.application.usecase.driver.GetDriverLogsUseCase;
import be.hepl.authapi.domain.model.customer.AnonymousCustomerLog;
import be.hepl.authapi.domain.model.driver.AnonymousDriverLog;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

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
    public ResponseEntity<List<AnonymousCustomerLog>> getCustomerLogsSince(@RequestParam long since) {
        Instant instant = Instant.ofEpochSecond(since);

        return ResponseEntity.ok(getCustomerLogsUseCase.getCustomerLogs(instant));
    }

    @GetMapping("/driver/logs")
    public ResponseEntity<List<AnonymousDriverLog>> getDriverLogsSince(@RequestParam long since) {
        Instant instant = Instant.ofEpochSecond(since);

        return ResponseEntity.ok(getDriverLogsUseCase.getDriverLogs(instant));
    }
}
