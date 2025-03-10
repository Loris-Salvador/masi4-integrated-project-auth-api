package be.hepl.authapi.application.usecase.auth;

import be.hepl.authapi.application.command.AuthCommand;
import be.hepl.authapi.application.result.AuthResult;
import be.hepl.authapi.domain.exception.ClientNotFoundException;
import be.hepl.authapi.domain.model.Client;
import be.hepl.authapi.domain.repository.ClientRepository;
import org.springframework.stereotype.Component;

@Component
public class PasswordVerificationUseCase {

    private final ClientRepository clientRepository;

    public PasswordVerificationUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public AuthResult verify(AuthCommand command) {

        try
        {
            Client user = clientRepository.findByEmail(command.email());

            if (user.getPassword().equals(command.password())) {
                return new AuthResult(AuthStatus.OK, "");
            }
            return new AuthResult(AuthStatus.FAILED, "Password verification failed");
        }
        catch(ClientNotFoundException e)
        {
            return new AuthResult(AuthStatus.USER_NOT_FOUND, "Client not found");
        }

    }
}