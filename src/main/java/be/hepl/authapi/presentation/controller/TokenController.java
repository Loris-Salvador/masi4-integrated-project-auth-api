package be.hepl.authapi.presentation.controller;

import be.hepl.authapi.application.dto.request.TokenAuthenticationRequest;
import be.hepl.authapi.application.usecase.TokenVerificationUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "Token")
@RestController
@RequestMapping("/api")
public class TokenController {

    private final TokenVerificationUseCase tokenVerificationUseCase;

    public TokenController(TokenVerificationUseCase tokenVerificationUseCase)
    {
        this.tokenVerificationUseCase = tokenVerificationUseCase;
    }

    @PostMapping("/verify-token")
    @Operation(description = "Vérification d'un token d'authentification créé sur cette API")
    public ResponseEntity<Map<String, Object>> verifyToken(@RequestBody TokenAuthenticationRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(tokenVerificationUseCase.verify(request.token()));
    }
}
