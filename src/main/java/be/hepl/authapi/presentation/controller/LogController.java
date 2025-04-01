package be.hepl.authapi.presentation.controller;

import be.hepl.authapi.application.usecase.client.GetClientLogsUseCase;
import be.hepl.authapi.domain.model.client.AnonymousClientLog;
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

    private final GetClientLogsUseCase getClientLogsUseCase;

    public LogController(GetClientLogsUseCase getClientLogsUseCase) {
        this.getClientLogsUseCase = getClientLogsUseCase;
    }

    @GetMapping("/client/logs")
    public ResponseEntity<List<AnonymousClientLog>> getClientLogsSince(@RequestParam long since) {
        Instant instant = Instant.ofEpochSecond(since);

        return ResponseEntity.ok(getClientLogsUseCase.getClientLogs(instant));
    }
}
