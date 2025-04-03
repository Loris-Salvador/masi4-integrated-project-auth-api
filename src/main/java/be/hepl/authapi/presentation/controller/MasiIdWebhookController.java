package be.hepl.authapi.presentation.controller;

import be.hepl.authapi.application.dto.request.MasiIdWebhookRequest;
import be.hepl.authapi.application.usecase.customer.CustomerMasIidWebhookUseCase;
import be.hepl.authapi.application.usecase.driver.DriverMasiIdWebhookUseCase;
import be.hepl.authapi.domain.model.jwt.Role;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/masi-id/webhook")
public class MasiIdWebhookController {

    private final DriverMasiIdWebhookUseCase driverMasiIdWebhookUseCase;

    private final CustomerMasIidWebhookUseCase customerMasiIdWebhookUseCase;

    public MasiIdWebhookController(DriverMasiIdWebhookUseCase driverMasiIdWebhookUseCase,
                                   CustomerMasIidWebhookUseCase customerMasiIdWebhookUseCase)
    {
        this.driverMasiIdWebhookUseCase = driverMasiIdWebhookUseCase;
        this.customerMasiIdWebhookUseCase = customerMasiIdWebhookUseCase;
    }

    @PostMapping
    public void masiIdWebhook(@RequestParam Role role, @RequestBody MasiIdWebhookRequest request) throws IOException {
        if(role == Role.CUSTOMER)
            customerMasiIdWebhookUseCase.authenticateCustomer(request.phoneNumber());
        else
            driverMasiIdWebhookUseCase.authenticateDriver(request.phoneNumber());
    }
}
