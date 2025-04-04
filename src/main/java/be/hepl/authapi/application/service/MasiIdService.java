package be.hepl.authapi.application.service;

import be.hepl.authapi.domain.model.token.Role;

public interface MasiIdService {
        void UserConnection(String phone, Role role);
}
