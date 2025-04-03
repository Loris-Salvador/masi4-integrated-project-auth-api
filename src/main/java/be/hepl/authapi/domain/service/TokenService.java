package be.hepl.authapi.domain.service;

import be.hepl.authapi.domain.model.token.Role;
import be.hepl.authapi.domain.model.token.Token;

import java.util.Map;

/// <comments>
/// Interface utilisée par les implémentations qui permettent d'interagir avec les tokens
/// </comments>
public interface TokenService {
     Token generateTokens(String id, Role role);

     Map<String, Object> verifyJwtSignature(String jwtToken);

     String refresh(String refreshToken);
}
