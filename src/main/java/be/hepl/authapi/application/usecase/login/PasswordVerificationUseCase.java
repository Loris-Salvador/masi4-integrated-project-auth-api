package be.hepl.authapi.application.usecase.login;

import be.hepl.authapi.application.dto.request.AuthRequest;
import be.hepl.authapi.application.service.PasswordHashingService;
import be.hepl.authapi.domain.exception.IncorrectPasswordException;
import be.hepl.authapi.domain.model.Client;
import be.hepl.authapi.domain.repository.ClientRepository;
import org.springframework.stereotype.Component;

@Component
public class PasswordVerificationUseCase {

    private final ClientRepository clientRepository;

    private final PasswordHashingService passwordHashingService;

    public PasswordVerificationUseCase(ClientRepository clientRepository, PasswordHashingService passwordHashingService) {
        this.clientRepository = clientRepository;
        this.passwordHashingService = passwordHashingService;
    }

    public void verify(AuthRequest authRequest) {

        Client client = clientRepository.findByEmail(authRequest.email());

        if (!passwordHashingService.verifyPassword(authRequest.password(), client.getPassword())) {
            throw new IncorrectPasswordException("The client password is incorrect");
        }
    }
}