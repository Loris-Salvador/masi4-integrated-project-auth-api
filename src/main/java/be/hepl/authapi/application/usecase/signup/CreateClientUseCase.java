package be.hepl.authapi.application.usecase.signup;

import be.hepl.authapi.application.dto.request.ClientCreateRequest;
import be.hepl.authapi.application.dto.response.ClientCreateResponse;
import be.hepl.authapi.application.mapper.ClientCreateRequestToClientMapper;
import be.hepl.authapi.application.mapper.ClientToClientCreateResponseMapper;
import be.hepl.authapi.domain.model.Client;
import be.hepl.authapi.domain.repository.ClientRepository;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Random;

@Component
public class CreateClientUseCase {

    private final ClientRepository clientRepository;

    public CreateClientUseCase(final ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
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

        Client result = clientRepository.save(client);
        return ClientToClientCreateResponseMapper.INSTANCE.map(result);
    }

    private String generateClientId() {
        Random rand = new Random();
        int clientId = rand.nextInt(90000000) + 10000000;
        return String.valueOf(clientId);
    }
}
