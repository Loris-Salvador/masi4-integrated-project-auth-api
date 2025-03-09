package be.hepl.authapi.application.usecase;

import be.hepl.authapi.application.dto.AuthRequest;
import be.hepl.authapi.application.dto.AuthResponse;
import be.hepl.authapi.domain.exception.UserNotFoundException;
import be.hepl.authapi.domain.model.User;
import be.hepl.authapi.domain.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class PasswordVerificationUseCase {

    private final UserRepository userRepository;

    public PasswordVerificationUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AuthResponse verify(AuthRequest authRequest) {

        try
        {
            User user = userRepository.findByEmail(authRequest.getEmail());

            if (user.getPassword().equals(authRequest.getPassword())) {
                return new AuthResponse(AuthStatus.OK, "");
            }
            return new AuthResponse(AuthStatus.FAILED, "Password verification failed");
        }
        catch(UserNotFoundException e)
        {
            return new AuthResponse(AuthStatus.USER_NOT_FOUND, "User not found");
        }

    }
}