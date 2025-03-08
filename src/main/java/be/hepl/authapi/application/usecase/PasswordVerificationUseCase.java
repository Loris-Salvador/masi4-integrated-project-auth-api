package be.hepl.authapi.application.usecase;

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

    public AuthStatus verify(String email, String password) {

        try
        {
            User user = userRepository.findByEmail(email);

            if (user.getPassword().equals(password)) {
                return AuthStatus.OK;
            }
            return AuthStatus.FAILED;
        }
        catch(UserNotFoundException e)
        {
            return AuthStatus.USER_NOT_FOUND;
        }

    }
}