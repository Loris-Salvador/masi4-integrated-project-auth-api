package be.hepl.authapi.presentation.controller.customer;

import be.hepl.authapi.application.dto.request.SendChallengeRequest;
import be.hepl.authapi.application.dto.request.VerifyChallengeRequest;
import be.hepl.authapi.application.usecase.SendChallengeUseCase;
import be.hepl.authapi.application.usecase.customer.CustomerLoginMethodVerificationCase;
import be.hepl.authapi.domain.model.challenge.ChallengeType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/// <comments>
/// Controller appelé lors de la vérification d'une méthode de login d'un client
/// Exemple : un client veut activer 2FA mail ou 2FA SMS
/// </comments>
@RestController
@RequestMapping("/api/customer/verify")
public class CustomerVerifyLoginMethodController {

    private final SendChallengeUseCase sendChallengeUseCase;

    private final CustomerLoginMethodVerificationCase challengeVerificationSignUpUseCase;

    public CustomerVerifyLoginMethodController(SendChallengeUseCase sendChallengeUseCase,
                                               CustomerLoginMethodVerificationCase challengeVerificationSignUpUseCase)
    {
        this.sendChallengeUseCase = sendChallengeUseCase;
        this.challengeVerificationSignUpUseCase = challengeVerificationSignUpUseCase;
    }

    @PostMapping("/email")
    public ResponseEntity<String> sendChallengeByEmail(@RequestBody SendChallengeRequest request)
    {
        sendChallengeUseCase.send(request.email(), ChallengeType.EMAIL);

        return ResponseEntity.status(HttpStatus.OK).body("The challenge has been sent");
    }

    @PostMapping("/email/challenge")
    public ResponseEntity<String> verifyEmail(@RequestBody VerifyChallengeRequest request)
    {
        challengeVerificationSignUpUseCase.verify(request.challenge(), request.email(), ChallengeType.EMAIL);

        return ResponseEntity.status(HttpStatus.OK).body("Email verified");
    }

    @PostMapping("/phone")
    public ResponseEntity<String> sendChallengeBySMS(@RequestBody SendChallengeRequest request)
    {
        sendChallengeUseCase.send(request.email(), ChallengeType.SMS);

        return ResponseEntity.status(HttpStatus.OK).body("The challenge has been sent");
    }

    @PostMapping("/phone/challenge")
    public ResponseEntity<String> verifyPhoneNumber(@RequestBody VerifyChallengeRequest request)
    {
        challengeVerificationSignUpUseCase.verify(request.challenge(), request.email(), ChallengeType.SMS);

        return ResponseEntity.status(HttpStatus.OK).body("Phone number verified");
    }
}
