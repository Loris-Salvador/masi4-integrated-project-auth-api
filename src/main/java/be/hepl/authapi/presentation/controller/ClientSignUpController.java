package be.hepl.authapi.presentation.controller;

import be.hepl.authapi.application.dto.request.ChallengeRequest;
import be.hepl.authapi.application.dto.request.ClientCreateRequest;
import be.hepl.authapi.application.dto.request.SendEmailRequest;
import be.hepl.authapi.application.dto.response.ClientCreateResponse;
import be.hepl.authapi.application.usecase.ChallengeType;
import be.hepl.authapi.application.usecase.SendChallengeUseCase;
import be.hepl.authapi.application.usecase.signup.ChallengeSignUpVerificationUseCase;
import be.hepl.authapi.application.usecase.signup.CreateClientUseCase;
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

    private final ChallengeSignUpVerificationUseCase challengeSignUpVerificationUseCase;

    public ClientSignUpController(CreateClientUseCase createClientUseCase,
                                  SendChallengeUseCase sendChallengeUseCase,
                                  ChallengeSignUpVerificationUseCase challengeSignUpVerificationUseCase)
    {
        this.createClientUseCase = createClientUseCase;
        this.sendChallengeUseCase = sendChallengeUseCase;
        this.challengeSignUpVerificationUseCase = challengeSignUpVerificationUseCase;
    }

    @PostMapping()
    public ResponseEntity<ClientCreateResponse> signup(@Valid @RequestBody ClientCreateRequest clientCreateRequest)
    {
        ClientCreateResponse response = createClientUseCase.create(clientCreateRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/email")
    public ResponseEntity<String> sendChallengeByEmail(@RequestBody SendEmailRequest request)
    {
        sendChallengeUseCase.send(request.email(), ChallengeType.EMAIL);

        return ResponseEntity.status(HttpStatus.OK).body("The challenge has been sent");
    }

    @PostMapping("/phone")
    public ResponseEntity<String> sendChallengeBySMS(@RequestBody SendEmailRequest request)
    {
        sendChallengeUseCase.send(request.email(), ChallengeType.SMS);

        return ResponseEntity.status(HttpStatus.OK).body("The challenge has been sent");
    }

    @PostMapping("/email/challenge")
    public ResponseEntity<String> verifyEmail(@RequestBody ChallengeRequest request)
    {
        challengeSignUpVerificationUseCase.verify(request.challenge(), request.email(), ChallengeType.EMAIL);

        return ResponseEntity.status(HttpStatus.OK).body("Email verified");
    }

    @PostMapping("/phone/challenge")
    public ResponseEntity<String> verifyPhoneNumber(@RequestBody ChallengeRequest request)
    {
        challengeSignUpVerificationUseCase.verify(request.challenge(), request.email(), ChallengeType.SMS);

        return ResponseEntity.status(HttpStatus.OK).body("Phone number verified");
    }


}
