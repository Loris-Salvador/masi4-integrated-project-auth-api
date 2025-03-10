package be.hepl.authapi.presentation.controller;

import be.hepl.authapi.application.dto.AuthRequest;
import be.hepl.authapi.application.dto.AuthResponse;
import be.hepl.authapi.application.dto.ChallengeRequest;
import be.hepl.authapi.application.usecase.auth.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/client")
public class ClientController {

    private final PasswordVerificationUseCase passwordVerificationUseCase;

    private final ChallengeVerificationUseCase challengeVerificationUseCase;

    private final SendChallengeUseCase sendChallengeUseCase;


    public ClientController(PasswordVerificationUseCase authUseCase,
                                  SendChallengeUseCase sendChallengeUseCase,
                                  ChallengeVerificationUseCase challengeVerificationUseCase)
    {

        this.passwordVerificationUseCase = authUseCase;
        this.sendChallengeUseCase = sendChallengeUseCase;
        this.challengeVerificationUseCase = challengeVerificationUseCase;
    }

    @PostMapping("/auth/email")
    public ResponseEntity<String> emailAuthentication(@RequestBody AuthRequest authRequest) {

        AuthResponse response = passwordVerificationUseCase.verify(authRequest);

        if(response.getStatus() == AuthStatus.USER_NOT_FOUND)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response.getMessage());
        }
        if(response.getStatus() == AuthStatus.FAILED)
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response.getMessage());
        }

        sendChallengeUseCase.sendChallenge(authRequest.getEmail(), ChallengeType.EMAIL);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/auth/sms")
    public ResponseEntity<String> smsAuthentication(@RequestBody AuthRequest authRequest) {

        AuthResponse response = passwordVerificationUseCase.verify(authRequest);

        if(response.getStatus() == AuthStatus.USER_NOT_FOUND)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response.getMessage());
        }
        if(response.getStatus() == AuthStatus.FAILED)
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response.getMessage());
        }

        sendChallengeUseCase.sendChallenge(authRequest.getEmail(), ChallengeType.SMS);

        return ResponseEntity.status(HttpStatus.OK).build();
    }



    @PostMapping("/auth/challenge")
    public ResponseEntity<String> verifyChallenge(@RequestBody ChallengeRequest request)
    {
        ChallengeStatus challengeStatus = challengeVerificationUseCase.verify(request.getChallenge(), request.getEmail());

        if(challengeStatus == ChallengeStatus.OK)
        {
            return ResponseEntity.status(HttpStatus.OK).build();
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }


}
