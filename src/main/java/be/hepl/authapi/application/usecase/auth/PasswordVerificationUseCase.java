package be.hepl.authapi.application.usecase.auth;

import be.hepl.authapi.application.dto.AuthRequest;
import be.hepl.authapi.application.dto.AuthResponse;
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

    public AuthResponse verify(AuthRequest authRequest) {

        try
        {
            Client user = clientRepository.findByEmail(authRequest.getEmail());

            if (user.getPassword().equals(authRequest.getPassword())) {
                return new AuthResponse(AuthStatus.OK, "");
            }
            return new AuthResponse(AuthStatus.FAILED, "Password verification failed");
        }
        catch(ClientNotFoundException e)
        {
            return new AuthResponse(AuthStatus.USER_NOT_FOUND, "Client not found");
        }

    }
}