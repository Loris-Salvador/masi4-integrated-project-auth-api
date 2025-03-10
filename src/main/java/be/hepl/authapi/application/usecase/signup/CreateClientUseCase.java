package be.hepl.authapi.application.usecase.signup;

import be.hepl.authapi.application.command.ClientCreateCommand;
import be.hepl.authapi.application.response.ClientCreateResponse;
import be.hepl.authapi.common.mapper.ClientMapper;
import be.hepl.authapi.domain.model.Client;
import be.hepl.authapi.domain.repository.ClientRepository;
import org.springframework.stereotype.Component;

@Component
public class CreateClientUseCase {

    private final ClientRepository clientRepository;

    public CreateClientUseCase(final ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    public ClientCreateResponse create(ClientCreateCommand command) {
        Client client = ClientMapper.INSTANCE.toClient(command);

        client.setEmailVerified(false);
        client.setPhoneVerified(false);

        Client result = clientRepository.save(client);

        return ClientMapper.INSTANCE.toResponse(result);

    }
}
