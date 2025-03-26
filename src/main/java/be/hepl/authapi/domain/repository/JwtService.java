package be.hepl.authapi.domain.repository;

import be.hepl.authapi.domain.model.jwt.Jwt;
import be.hepl.authapi.domain.model.jwt.Role;

import java.util.Map;

public interface JwtService {
     Jwt generateTokens(String id, Role role, Map<String, Object> extraClaims);

     Map<String, Object> verifyJwtSignature(String jwtToken);

     Jwt refresh(String refreshToken);
}
