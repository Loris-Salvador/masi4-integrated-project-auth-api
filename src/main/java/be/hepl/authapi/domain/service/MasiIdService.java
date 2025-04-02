package be.hepl.authapi.domain.service;

import be.hepl.authapi.domain.model.jwt.Role;

public interface MasiIdService {
        String UserConnection(String phone, Role role);
}
