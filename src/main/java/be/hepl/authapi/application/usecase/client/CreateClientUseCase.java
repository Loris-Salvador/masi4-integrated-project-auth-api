package be.hepl.authapi.application.usecase.client;

import be.hepl.authapi.application.dto.request.ClientCreateRequest;
import be.hepl.authapi.application.dto.response.ClientCreateResponse;
import be.hepl.authapi.application.mapper.ClientCreateRequestToClientMapper;
import be.hepl.authapi.application.mapper.ClientToClientCreateResponseMapper;
import be.hepl.authapi.application.service.PasswordHashingService;
import be.hepl.authapi.domain.model.client.Client;
import be.hepl.authapi.domain.repository.ClientRepository;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Random;

/// <comments>
/// Use case utilisé lors de la création d'un client
/// </comments>
@Component
public class CreateClientUseCase {

    private final ClientRepository clientRepository;

    private final PasswordHashingService passwordHashingService;

    public CreateClientUseCase(final ClientRepository clientRepository, final PasswordHashingService passwordHashingService) {
        this.clientRepository = clientRepository;
        this.passwordHashingService = passwordHashingService;
    }


    public ClientCreateResponse create(ClientCreateRequest request) {
        Client client = ClientCreateRequestToClientMapper.INSTANCE.map(request);

        Client c;
        String clientId;

        do {
            clientId = generateClientId();

            c = clientRepository.findById(clientId);
        }
        while (c != null);

        clientId = generateClientId();

        client.setId(clientId);
        client.setEmailVerified(false);
        client.setPhoneVerified(false);

        client.setCreateAccount(Instant.now().getEpochSecond());

        client.setPassword(passwordHashingService.hashPassword(request.password()));

        Client result = clientRepository.save(client);
        return ClientToClientCreateResponseMapper.INSTANCE.map(result);
    }

    private String generateClientId() {
        Random rand = new Random();
        int clientId = rand.nextInt(90000000) + 10000000;
        return String.valueOf(clientId);
    }


}
