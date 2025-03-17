package be.hepl.authapi.presentation.controller;

import be.hepl.authapi.application.dto.request.JwtAuthenticationRequest;
import be.hepl.authapi.application.dto.request.JwtRefreshRequest;
import be.hepl.authapi.application.usecase.auth.RefreshTokenUseCase;
import be.hepl.authapi.application.usecase.auth.TokenVerificationUseCase;
import be.hepl.authapi.domain.model.Jwt;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class JwtAuthenticationController {

    private final TokenVerificationUseCase tokenVerificationUseCase;

    private final RefreshTokenUseCase refreshTokenUseCase;

    public JwtAuthenticationController(TokenVerificationUseCase tokenVerificationUseCase,
                                       RefreshTokenUseCase refreshTokenUseCase)
    {
        this.tokenVerificationUseCase = tokenVerificationUseCase;
        this.refreshTokenUseCase = refreshTokenUseCase;
    }

    @PostMapping("/verify-token")
    public ResponseEntity<Map<String, Object>> verifyToken(@RequestBody JwtAuthenticationRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(tokenVerificationUseCase.verify(request.token()));
    }

    @PostMapping("refresh-token")
    public ResponseEntity<Jwt> refreshToken(@RequestBody JwtRefreshRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(refreshTokenUseCase.refresh(request.refreshToken()));
    }
}
