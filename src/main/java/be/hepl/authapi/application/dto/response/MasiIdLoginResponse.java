package be.hepl.authapi.application.dto.response;

import be.hepl.authapi.domain.model.masiid.MasiIdLoginStatus;

public record MasiIdLoginResponse(
        MasiIdLoginStatus status,
        String message
) {
}
