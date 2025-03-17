package be.hepl.authapi.presentation.controller;

import be.hepl.authapi.application.dto.request.JwtAuthenticationRequest;
import be.hepl.authapi.application.usecase.auth.TokenVerificationUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class JwtAuthenticationController {

    private final TokenVerificationUseCase tokenVerificationUseCase;

    public JwtAuthenticationController(TokenVerificationUseCase tokenVerificationUseCase) {
        this.tokenVerificationUseCase = tokenVerificationUseCase;
    }

    @PostMapping("/verify-token")
    public ResponseEntity<Map<String, Object>> verifyToken(@RequestBody JwtAuthenticationRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(tokenVerificationUseCase.verify(request.token()));
    }
}
