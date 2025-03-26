package be.hepl.authapi.presentation.controller;

import be.hepl.authapi.application.dto.request.challenge.VerifyChallengeRequest;
import be.hepl.authapi.application.dto.request.client.ClientCreateRequest;
import be.hepl.authapi.application.dto.request.challenge.SendChallengeRequest;
import be.hepl.authapi.application.dto.response.ClientCreateResponse;
import be.hepl.authapi.domain.model.challenge.ChallengeType;
import be.hepl.authapi.application.usecase.auth.SendChallengeUseCase;
import be.hepl.authapi.application.usecase.auth.signup.ChallengeVerificationSignUpUseCase;
import be.hepl.authapi.application.usecase.auth.signup.CreateClientUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client/signup")
public class ClientSignUpController {

    private final CreateClientUseCase createClientUseCase;

    private final SendChallengeUseCase sendChallengeUseCase;

    private final ChallengeVerificationSignUpUseCase challengeVerificationSignUpUseCase;

    public ClientSignUpController(CreateClientUseCase createClientUseCase,
                                  SendChallengeUseCase sendChallengeUseCase,
                                  ChallengeVerificationSignUpUseCase challengeVerificationSignUpUseCase)
    {
        this.createClientUseCase = createClientUseCase;
        this.sendChallengeUseCase = sendChallengeUseCase;
        this.challengeVerificationSignUpUseCase = challengeVerificationSignUpUseCase;
    }

    @PostMapping()
    public ResponseEntity<ClientCreateResponse> signup(@Valid @RequestBody ClientCreateRequest clientCreateRequest)
    {
        ClientCreateResponse response = createClientUseCase.create(clientCreateRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/email")
    public ResponseEntity<String> sendChallengeByEmail(@RequestBody SendChallengeRequest request)
    {
        sendChallengeUseCase.send(request.email(), ChallengeType.EMAIL);

        return ResponseEntity.status(HttpStatus.OK).body("The challenge has been sent");
    }

    @PostMapping("/phone")
    public ResponseEntity<String> sendChallengeBySMS(@RequestBody SendChallengeRequest request)
    {
        sendChallengeUseCase.send(request.email(), ChallengeType.SMS);

        return ResponseEntity.status(HttpStatus.OK).body("The challenge has been sent");
    }

    @PostMapping("/email/verify")
    public ResponseEntity<String> verifyEmail(@RequestBody VerifyChallengeRequest request)
    {
        challengeVerificationSignUpUseCase.verify(request.challenge(), request.email(), ChallengeType.EMAIL);

        return ResponseEntity.status(HttpStatus.OK).body("Email verified");
    }

    @PostMapping("/phone/verify")
    public ResponseEntity<String> verifyPhoneNumber(@RequestBody VerifyChallengeRequest request)
    {
        challengeVerificationSignUpUseCase.verify(request.challenge(), request.email(), ChallengeType.SMS);

        return ResponseEntity.status(HttpStatus.OK).body("Phone number verified");
    }


}
