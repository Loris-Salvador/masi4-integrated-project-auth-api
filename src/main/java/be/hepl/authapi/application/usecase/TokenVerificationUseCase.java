package be.hepl.authapi.application.usecase;

import be.hepl.authapi.domain.service.TokenService;
import org.springframework.stereotype.Component;
import java.util.Map;

/// <comments>
/// Use case utilisé lors de la vérification de token
/// </comments>
@Component
public class TokenVerificationUseCase {

    private final TokenService tokenService;

    public TokenVerificationUseCase(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    public Map<String, Object> verify(String token)
    {
        return tokenService.verifyJwtSignature(token);
    }
}
