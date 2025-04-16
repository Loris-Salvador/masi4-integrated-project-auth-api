package be.hepl.authapi.application.service;

import be.hepl.authapi.domain.model.token.Role;
import be.hepl.authapi.domain.model.token.Token;

import java.util.Map;

public interface TokenService {
     Token generateTokens(String id, Role role);

     Map<String, Object> verifyJwtSignature(String jwtToken);
}
