package be.hepl.authapi.application.usecase;

import be.hepl.authapi.domain.model.User;
import be.hepl.authapi.domain.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class PasswordVerificationUseCase {

    private final UserRepository userRepository;

    public PasswordVerificationUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean verify(String email, String password) {
        User user = userRepository.findByEmail(email);

        if (user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }
}