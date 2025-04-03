package be.hepl.authapi.application.usecase.customer;

import be.hepl.authapi.domain.model.customer.Customer;
import be.hepl.authapi.domain.repository.CustomerRepository;
import be.hepl.authapi.domain.websocket.MasiIdWebSocketManager;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomerMasIidWebhookUseCase {

    private final MasiIdWebSocketManager masiIdWebSocketManager;

    private final CustomerRepository customerRepository;

    public CustomerMasIidWebhookUseCase(MasiIdWebSocketManager masiIdWebSocketManager, CustomerRepository customerRepository) {
        this.masiIdWebSocketManager = masiIdWebSocketManager;
        this.customerRepository = customerRepository;
    }

    public void authenticateCustomer(String phone) throws IOException {

        Customer customer = customerRepository.findByPhoneNumber(phone);

        masiIdWebSocketManager.authenticateCustomer(phone, customer.getId());
    }
}
