package be.hepl.authapi.application.usecase.auth;

import be.hepl.authapi.application.dto.request.AuthRequest;
import be.hepl.authapi.domain.exception.IncorrectPasswordException;
import be.hepl.authapi.domain.model.Client;
import be.hepl.authapi.domain.repository.ClientRepository;
import org.springframework.stereotype.Component;

@Component
public class PasswordVerificationUseCase {

    private final ClientRepository clientRepository;

    public PasswordVerificationUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void verify(AuthRequest authRequest) {

        Client client = clientRepository.findByEmail(authRequest.email());

        if (!client.getPassword().equals(authRequest.password())) {
            throw new IncorrectPasswordException("The client password is incorrect");
        }
    }
}