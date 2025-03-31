package be.hepl.authapi.presentation.controller.client;

import be.hepl.authapi.application.dto.request.ClientLoginRequest;
import be.hepl.authapi.application.dto.request.VerifyChallengeRequest;
import be.hepl.authapi.application.usecase.client.ClientLoginVerificationUseCase;
import be.hepl.authapi.application.usecase.client.ClientLoginUseCase;
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
@RequestMapping("api/client/login")
public class ClientLoginController {

    private final ClientLoginUseCase clientLoginUseCase;

    private final ClientLoginVerificationUseCase clientLoginVerificationUseCase;

    public ClientLoginController(ClientLoginUseCase loginUseCase,
                                 ClientLoginVerificationUseCase clientLoginVerificationUseCase)
    {
        this.clientLoginUseCase = loginUseCase;
        this.clientLoginVerificationUseCase = clientLoginVerificationUseCase;
    }

    @PostMapping("/email")
    public ResponseEntity<String> emailLogin(@RequestBody ClientLoginRequest clientLoginRequest) {
        clientLoginUseCase.verify(clientLoginRequest, ChallengeType.EMAIL);

        return ResponseEntity.status(HttpStatus.OK).body("The challenge has been sent");
    }

    @PostMapping("/phone")
    public ResponseEntity<String> phoneLogin(@RequestBody ClientLoginRequest clientLoginRequest) {
        clientLoginUseCase.verify(clientLoginRequest, ChallengeType.SMS);

        return ResponseEntity.status(HttpStatus.OK).body("The challenge has been sent");
    }


    @PostMapping({"/phone/challenge", "/email/challenge"})
    public ResponseEntity<Jwt> verifyChallenge(@RequestBody VerifyChallengeRequest request)
    {
        Jwt token = clientLoginVerificationUseCase.verify(request.challenge(), request.email());

        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
}
