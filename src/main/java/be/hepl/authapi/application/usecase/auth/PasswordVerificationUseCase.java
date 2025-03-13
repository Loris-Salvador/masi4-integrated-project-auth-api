package be.hepl.authapi.application.usecase.auth;

import be.hepl.authapi.application.dto.request.AuthRequest;
import be.hepl.authapi.application.dto.response.AuthResponse;
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
            Client client = clientRepository.findByEmail(authRequest.email());

            if (client.getPassword().equals(authRequest.password())) {
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