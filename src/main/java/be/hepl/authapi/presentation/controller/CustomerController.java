package be.hepl.authapi.presentation.controller;

import be.hepl.authapi.application.dto.request.CustomerCreateRequest;
import be.hepl.authapi.application.dto.request.CustomerLoginRequest;
import be.hepl.authapi.application.dto.request.ChallengeRequest;
import be.hepl.authapi.application.dto.request.VerifyChallengeRequest;
import be.hepl.authapi.application.dto.response.CustomerCreateResponse;
import be.hepl.authapi.application.usecase.SendChallengeUseCase;
import be.hepl.authapi.application.usecase.customer.CreateCustomerUseCase;
import be.hepl.authapi.application.usecase.customer.CustomerLoginMethodVerificationCase;
import be.hepl.authapi.application.usecase.customer.CustomerLoginUseCase;
import be.hepl.authapi.application.usecase.customer.CustomerLoginVerificationUseCase;
import be.hepl.authapi.domain.model.challenge.ChallengeType;
import be.hepl.authapi.domain.model.token.Token;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Customer")
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerLoginUseCase customerLoginUseCase;

    private final CustomerLoginVerificationUseCase customerLoginVerificationUseCase;

    private final CreateCustomerUseCase createCustomerUseCase;

    private final SendChallengeUseCase sendChallengeUseCase;

    private final CustomerLoginMethodVerificationCase challengeVerificationSignUpUseCase;

    public CustomerController(CustomerLoginUseCase customerLoginUseCase,
                              CustomerLoginVerificationUseCase loginVerificationUseCase,
                              CreateCustomerUseCase createCustomerUseCase,
                              SendChallengeUseCase sendChallengeUseCase,
                              CustomerLoginMethodVerificationCase challengeVerificationSignUpUseCase)
    {
        this.customerLoginUseCase = customerLoginUseCase;
        this.customerLoginVerificationUseCase = loginVerificationUseCase;
        this.createCustomerUseCase = createCustomerUseCase;
        this.sendChallengeUseCase = sendChallengeUseCase;
        this.challengeVerificationSignUpUseCase = challengeVerificationSignUpUseCase;
    }

    @PostMapping("/login/email")
    @Operation(description = "Étape 1 du login par email ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Challenge envoyé par email avec succès voir /api/customer/login/email/challenge pour confirmer le challenge"),
    })
    public ResponseEntity<String> emailLogin(@RequestBody CustomerLoginRequest customerLoginRequest) {
        customerLoginUseCase.verify(customerLoginRequest, ChallengeType.EMAIL);

        return ResponseEntity.status(HttpStatus.OK).body("The challenge has been sent");
    }


    @PostMapping("/login/phone")
    @Operation(description = "Étape 1 du login par téléphone ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Challenge envoyé par SMS avec succès - voir /api/customer/login/phone/challenge pour confirmer le challenge"),
    })
    public ResponseEntity<String> phoneLogin(@RequestBody CustomerLoginRequest customerLoginRequest) {
        customerLoginUseCase.verify(customerLoginRequest, ChallengeType.SMS);

        return ResponseEntity.status(HttpStatus.OK).body("The challenge has been sent");
    }


    @PostMapping({"/login/phone/challenge", "/login/email/challenge"})
    @Operation(description = "Étape 2 du login par email/sms grace au challenge reçu par email/SMS")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authentification réussie et token reçu"),
    })
    public ResponseEntity<Token> verifyChallenge(@RequestBody VerifyChallengeRequest request)
    {
        Token token = customerLoginVerificationUseCase.verify(request.challenge(), request.email());

        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    @PostMapping("/signup")
    @Operation(description = "Création d'un client - le telephone et l'email n'est pas vérifié a la création c'est à faire par après voir /api/customer/verify/email et /api/customer/verify/phone")
    public ResponseEntity<CustomerCreateResponse> signup(@Valid @RequestBody CustomerCreateRequest customerCreateRequest)
    {
        CustomerCreateResponse response = createCustomerUseCase.create(customerCreateRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/verify/email")
    @Operation(description = "Étape 1 de la vérification de l'email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Challenge envoyé par email avec succès - voir /api/customer/verify/email/challenge pour confirmer le challenge"),
    })
    public ResponseEntity<String> sendChallengeByEmail(@RequestBody ChallengeRequest request)
    {
        sendChallengeUseCase.send(request.email(), ChallengeType.EMAIL);

        return ResponseEntity.status(HttpStatus.OK).body("The challenge has been sent");
    }


    @PostMapping("/verify/email/challenge")
    @Operation(description = "Étape 2 de la vérification de l'email avec l'envoie du challenge reçu par email")
    public ResponseEntity<String> verifyEmail(@RequestBody VerifyChallengeRequest request)
    {
        challengeVerificationSignUpUseCase.verify(request.challenge(), request.email(), ChallengeType.EMAIL);

        return ResponseEntity.status(HttpStatus.OK).body("Email verified");
    }

    @PostMapping("/verify/phone")
    @Operation(description = "Étape 1 de la vérification du telephone")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Challenge envoyé par SMS avec succès - voir /api/customer/verify/phone/challenge pour confirmer le challenge"),
    })
    public ResponseEntity<String> sendChallengeBySMS(@RequestBody ChallengeRequest request)
    {
        sendChallengeUseCase.send(request.email(), ChallengeType.SMS);

        return ResponseEntity.status(HttpStatus.OK).body("The challenge has been sent");
    }

    @PostMapping("/verify/phone/challenge")
    @Operation(description = "Étape 2 de la vérification du telephone avec l'envoie du challenge reçu par SMS")
    public ResponseEntity<String> verifyPhoneNumber(@RequestBody VerifyChallengeRequest request)
    {
        challengeVerificationSignUpUseCase.verify(request.challenge(), request.email(), ChallengeType.SMS);

        return ResponseEntity.status(HttpStatus.OK).body("Phone number verified");
    }
}
