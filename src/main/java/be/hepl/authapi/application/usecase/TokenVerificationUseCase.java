package be.hepl.authapi.application.usecase;

import be.hepl.authapi.application.service.JwtService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TokenVerificationUseCase {

    private final JwtService jwtService;

    public TokenVerificationUseCase(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public Map<String, Object> verify(String token)
    {
        return jwtService.verifyJwtSignature(token);
    }
}
