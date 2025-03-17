package be.hepl.authapi.application.usecase.auth;

import be.hepl.authapi.domain.repository.JwtService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TokenVerificationUseCase {

    private final JwtService jwtService;

    public TokenVerificationUseCase(final JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public Map<String, Object> verify(String token)
    {
        return jwtService.verifyJwtSignature(token);
    }
}
