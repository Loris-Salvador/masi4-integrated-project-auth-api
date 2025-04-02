package be.hepl.authapi.application.usecase;

import be.hepl.authapi.application.dto.response.RefreshTokenResponse;
import be.hepl.authapi.domain.model.jwt.Jwt;
import be.hepl.authapi.domain.service.JwtService;
import org.springframework.stereotype.Component;

/// <comments>
/// Use case utilisé lors de la demande de refresh token
/// </comments>
@Component
public class RefreshTokenUseCase {

    private final JwtService jwtService;

    public RefreshTokenUseCase(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public RefreshTokenResponse refresh(String refreshToken) {
        String tokenRefresh = jwtService.refresh(refreshToken);

        return new RefreshTokenResponse(tokenRefresh);
    }
}
