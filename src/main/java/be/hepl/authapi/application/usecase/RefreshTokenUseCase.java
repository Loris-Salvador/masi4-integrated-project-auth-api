package be.hepl.authapi.application.usecase;

import be.hepl.authapi.application.dto.response.TokenRefreshResponse;
import be.hepl.authapi.application.service.TokenService;
import org.springframework.stereotype.Component;

@Component
public class RefreshTokenUseCase {

    private final TokenService tokenService;

    public RefreshTokenUseCase(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    public TokenRefreshResponse refresh(String refreshToken) {
        String tokenRefresh = tokenService.refresh(refreshToken);

        return new TokenRefreshResponse(tokenRefresh);
    }
}
