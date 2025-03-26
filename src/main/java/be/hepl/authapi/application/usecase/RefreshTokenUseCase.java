package be.hepl.authapi.application.usecase;

import be.hepl.authapi.domain.model.jwt.Jwt;
import be.hepl.authapi.application.service.JwtService;
import org.springframework.stereotype.Component;

@Component
public class RefreshTokenUseCase {

    private final JwtService jwtService;

    public RefreshTokenUseCase(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public Jwt refresh(String refreshToken) {
        return jwtService.refresh(refreshToken);
    }
}
