package be.hepl.authapi.domain.service;

import be.hepl.authapi.domain.model.jwt.Role;
import be.hepl.authapi.infrastructure.service.model.MasiIdServiceLoginResponse;
import jakarta.validation.constraints.NotNull;

public interface MasiIdService {
        String UserConnection(String phone, Role role);
}
