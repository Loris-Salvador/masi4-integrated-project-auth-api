package be.hepl.authapi.presentation.controller.customer;

import be.hepl.authapi.application.dto.request.CustomerLoginRequest;
import be.hepl.authapi.application.dto.request.VerifyChallengeRequest;
import be.hepl.authapi.application.usecase.customer.CustomerLoginVerificationUseCase;
import be.hepl.authapi.application.usecase.customer.CustomerLoginUseCase;
import be.hepl.authapi.domain.model.jwt.Jwt;
import be.hepl.authapi.domain.model.challenge.ChallengeType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/// <comments>
/// Controller appelé lors de quelconque login du client 1ere et 2eme phase
/// Exemple : un client envoie son login/password il reçoit un challenge par mail/sms et le renvoie ici aussi
/// </comments>
@RestController
@RequestMapping("api/customer/login")
public class CustomerLoginController {

    private final CustomerLoginUseCase customerLoginUseCase;

    private final CustomerLoginVerificationUseCase customerLoginVerificationUseCase;

    public CustomerLoginController(CustomerLoginUseCase loginUseCase,
                                   CustomerLoginVerificationUseCase customerLoginVerificationUseCase)
    {
        this.customerLoginUseCase = loginUseCase;
        this.customerLoginVerificationUseCase = customerLoginVerificationUseCase;
    }

    @PostMapping("/email")
    public ResponseEntity<String> emailLogin(@RequestBody CustomerLoginRequest customerLoginRequest) {
        customerLoginUseCase.verify(customerLoginRequest, ChallengeType.EMAIL);

        return ResponseEntity.status(HttpStatus.OK).body("The challenge has been sent");
    }

    @PostMapping("/phone")
    public ResponseEntity<String> phoneLogin(@RequestBody CustomerLoginRequest customerLoginRequest) {
        customerLoginUseCase.verify(customerLoginRequest, ChallengeType.SMS);

        return ResponseEntity.status(HttpStatus.OK).body("The challenge has been sent");
    }


    @PostMapping({"/phone/challenge", "/email/challenge"})
    public ResponseEntity<Jwt> verifyChallenge(@RequestBody VerifyChallengeRequest request)
    {
        Jwt token = customerLoginVerificationUseCase.verify(request.challenge(), request.email());

        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
}
