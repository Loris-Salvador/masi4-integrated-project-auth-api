package be.hepl.authapi.presentation.controller;

import be.hepl.authapi.application.dto.request.AuthRequest;
import be.hepl.authapi.application.dto.request.ChallengeRequest;
import be.hepl.authapi.application.usecase.ChallengeType;
import be.hepl.authapi.application.usecase.SendChallengeUseCase;
import be.hepl.authapi.application.usecase.auth.*;
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

    private final SendChallengeIfVerifiedUseCase sendChallengeIfVerifiedUseCase;


    public ClientAuthController(PasswordVerificationUseCase authUseCase,
                                SendChallengeIfVerifiedUseCase sendChallengeIfVerifiedUseCase,
                                ChallengeAuthVerificationUseCase challengeAuthVerificationUseCase)
    {

        this.passwordVerificationUseCase = authUseCase;
        this.sendChallengeIfVerifiedUseCase = sendChallengeIfVerifiedUseCase;
        this.challengeAuthVerificationUseCase = challengeAuthVerificationUseCase;
    }

    @PostMapping("/email")
    public ResponseEntity<String> emailAuthentication(@RequestBody AuthRequest authRequest) {
        passwordVerificationUseCase.verify(authRequest);

        sendChallengeIfVerifiedUseCase.send(authRequest.email(), ChallengeType.EMAIL);

        return ResponseEntity.status(HttpStatus.OK).body("The challenge has been sent");
    }

    @PostMapping("/phone")
    public ResponseEntity<String> smsAuthentication(@RequestBody AuthRequest authRequest) {

        passwordVerificationUseCase.verify(authRequest);

        sendChallengeIfVerifiedUseCase.send(authRequest.email(), ChallengeType.SMS);

        return ResponseEntity.status(HttpStatus.OK).body("The challenge has been sent");
    }



    @PostMapping({"/phone/challenge", "/email/challenge"})
    public ResponseEntity<String> verifyChallenge(@RequestBody ChallengeRequest request)
    {
        challengeAuthVerificationUseCase.verify(request.challenge(), request.email());

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
