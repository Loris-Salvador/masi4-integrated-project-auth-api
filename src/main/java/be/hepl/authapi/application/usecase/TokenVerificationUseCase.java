package be.hepl.authapi.application.usecase;

import be.hepl.authapi.application.service.TokenService;
import org.springframework.stereotype.Component;
import java.util.Map;

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
