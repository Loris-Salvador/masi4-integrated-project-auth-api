package be.hepl.authapi.presentation.controller;

import be.hepl.authapi.application.dto.request.client.ClientLoginRequest;
import be.hepl.authapi.application.dto.request.challenge.VerifyChallengeRequest;
import be.hepl.authapi.application.usecase.auth.login.ChallengeLoginVerificationUseCase;
import be.hepl.authapi.application.usecase.auth.login.PasswordVerificationUseCase;
import be.hepl.authapi.application.usecase.auth.login.SendChallengeIfVerifiedUseCase;
import be.hepl.authapi.domain.model.challenge.ChallengeType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/client/login")
public class ClientLoginController {

    private final PasswordVerificationUseCase passwordVerificationUseCase;

    private final ChallengeLoginVerificationUseCase challengeLoginVerificationUseCase;

    private final SendChallengeIfVerifiedUseCase sendChallengeIfVerifiedUseCase;


    public ClientLoginController(PasswordVerificationUseCase authUseCase,
                                 SendChallengeIfVerifiedUseCase sendChallengeIfVerifiedUseCase,
                                 ChallengeLoginVerificationUseCase challengeLoginVerificationUseCase)
    {

        this.passwordVerificationUseCase = authUseCase;
        this.sendChallengeIfVerifiedUseCase = sendChallengeIfVerifiedUseCase;
        this.challengeLoginVerificationUseCase = challengeLoginVerificationUseCase;
    }

    @PostMapping("/email")
    public ResponseEntity<String> emailAuthentication(@RequestBody ClientLoginRequest clientLoginRequest) {
        passwordVerificationUseCase.verify(clientLoginRequest);

        sendChallengeIfVerifiedUseCase.send(clientLoginRequest.email(), ChallengeType.EMAIL);

        return ResponseEntity.status(HttpStatus.OK).body("The challenge has been sent");
    }

    @PostMapping("/phone")
    public ResponseEntity<String> smsAuthentication(@RequestBody ClientLoginRequest clientLoginRequest) {

        passwordVerificationUseCase.verify(clientLoginRequest);

        sendChallengeIfVerifiedUseCase.send(clientLoginRequest.email(), ChallengeType.SMS);

        return ResponseEntity.status(HttpStatus.OK).body("The challenge has been sent");
    }



    @PostMapping({"/phone/challenge", "/email/challenge"})
    public ResponseEntity<String> verifyChallenge(@RequestBody VerifyChallengeRequest request)
    {
        String token = challengeLoginVerificationUseCase.verify(request.challenge(), request.email());

        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

}
