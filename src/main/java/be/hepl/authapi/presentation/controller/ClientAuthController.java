package be.hepl.authapi.presentation.controller;

import be.hepl.authapi.application.command.AuthCommand;
import be.hepl.authapi.application.response.AuthResponse;
import be.hepl.authapi.application.usecase.ChallengeStatus;
import be.hepl.authapi.application.usecase.ChallengeType;
import be.hepl.authapi.application.usecase.SendChallengeUseCase;
import be.hepl.authapi.presentation.request.AuthRequest;
import be.hepl.authapi.presentation.request.ChallengeRequest;
import be.hepl.authapi.application.usecase.auth.*;
import be.hepl.authapi.common.mapper.AuthRequestMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/client/auth")
public class ClientAuthController {

    private final PasswordVerificationUseCase passwordVerificationUseCase;

    private final ChallengeAuthVerificationUseCase challengeAuthVerificationUseCase;

    private final SendChallengeUseCase sendChallengeUseCase;


    public ClientAuthController(PasswordVerificationUseCase authUseCase,
                                SendChallengeUseCase sendChallengeUseCase,
                                ChallengeAuthVerificationUseCase challengeAuthVerificationUseCase)
    {

        this.passwordVerificationUseCase = authUseCase;
        this.sendChallengeUseCase = sendChallengeUseCase;
        this.challengeAuthVerificationUseCase = challengeAuthVerificationUseCase;
    }

    @PostMapping("/email")
    public ResponseEntity<String> emailAuthentication(@RequestBody AuthRequest authRequest) {

        AuthCommand authCommand = AuthRequestMapper.INSTANCE.toCommand(authRequest);

        AuthResponse result = passwordVerificationUseCase.verify(authCommand);

        if(result.status() == AuthStatus.USER_NOT_FOUND)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result.message());
        }
        if(result.status() == AuthStatus.FAILED)
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result.message());
        }

        sendChallengeUseCase.sendChallenge(authRequest.email(), ChallengeType.EMAIL);

        return ResponseEntity.status(HttpStatus.OK).body("Challenge sent");
    }

    @PostMapping("/phone")
    public ResponseEntity<String> smsAuthentication(@RequestBody AuthRequest authRequest) {

        AuthCommand authCommand = AuthRequestMapper.INSTANCE.toCommand(authRequest);
        AuthResponse result = passwordVerificationUseCase.verify(authCommand);

        if(result.status() == AuthStatus.USER_NOT_FOUND)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result.message());
        }
        if(result.status() == AuthStatus.FAILED)
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result.message());
        }

        sendChallengeUseCase.sendChallenge(authRequest.email(), ChallengeType.SMS);

        return ResponseEntity.status(HttpStatus.OK).body("Challenge sent");
    }



    @PostMapping("/challenge")
    public ResponseEntity<String> verifyChallenge(@RequestBody ChallengeRequest request)
    {
        ChallengeStatus challengeStatus = challengeAuthVerificationUseCase.verify(request.challenge(), request.email());

        if(challengeStatus == ChallengeStatus.OK)
        {
            //JWT
            return ResponseEntity.status(HttpStatus.OK).build();
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
