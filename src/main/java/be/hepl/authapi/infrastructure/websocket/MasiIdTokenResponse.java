package be.hepl.authapi.infrastructure.websocket;

import be.hepl.authapi.domain.model.masiid.MasiIdLoginStatus;
import be.hepl.authapi.domain.model.token.Token;

public record MasiIdTokenResponse(
        MasiIdLoginStatus status,
        Token token
) {
}
