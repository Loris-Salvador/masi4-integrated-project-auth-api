package be.hepl.authapi.presentation.controller;

import be.hepl.authapi.application.dto.CardDTO;
import be.hepl.authapi.application.dto.ChallengeDTO;
import be.hepl.authapi.application.service.AuthService;
import be.hepl.authapi.domain.model.User;
import be.hepl.authapi.domain.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/email")
public class AuthEmailController {

    private final AuthService authService;

    public AuthEmailController(AuthService authService, UserRepository userRepository) {
        this.authService = authService;
    }


    @PostMapping("/challenge")
    public String sign(@RequestBody CardDTO card)
    {
        return authService.generateChallenge();
    }

}
