package be.hepl.authapi.presentation.controller;

import be.hepl.authapi.application.dto.request.TokenAuthenticationRequest;
import be.hepl.authapi.application.dto.request.TokenRefreshRequest;
import be.hepl.authapi.application.dto.response.TokenRefreshResponse;
import be.hepl.authapi.application.usecase.RefreshTokenUseCase;
import be.hepl.authapi.application.usecase.TokenVerificationUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/// <comments>
/// Controller appelé lors d'interaction avec des JWT
/// Exemple : le refresh
/// </comments>
@Tag(name = "Token")
@RestController
@RequestMapping("/api")
public class TokenController {

    private final TokenVerificationUseCase tokenVerificationUseCase;

    private final RefreshTokenUseCase refreshTokenUseCase;

    public TokenController(TokenVerificationUseCase tokenVerificationUseCase,
                           RefreshTokenUseCase refreshTokenUseCase)
    {
        this.tokenVerificationUseCase = tokenVerificationUseCase;
        this.refreshTokenUseCase = refreshTokenUseCase;
    }

    @PostMapping("/verify-token")
    public ResponseEntity<Map<String, Object>> verifyToken(@RequestBody TokenAuthenticationRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(tokenVerificationUseCase.verify(request.token()));
    }

    @PostMapping("refresh-token")
    public ResponseEntity<TokenRefreshResponse> refreshToken(@RequestBody TokenRefreshRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(refreshTokenUseCase.refresh(request.refreshToken()));
    }
}
