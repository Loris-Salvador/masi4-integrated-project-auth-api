package be.hepl.authapi.application.service;

import be.hepl.authapi.domain.model.jwt.Jwt;
import be.hepl.authapi.domain.model.jwt.Role;

import java.util.Map;

/// <comments>
/// Interface utilisée par les implémentations qui permettent d'interagir avec les tokens
/// </comments>
public interface JwtService {
     Jwt generateTokens(String id, Role role, Map<String, Object> extraClaims);

     Map<String, Object> verifyJwtSignature(String jwtToken);

     Jwt refresh(String refreshToken);
}
