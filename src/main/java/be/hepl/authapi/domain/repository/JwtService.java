package be.hepl.authapi.domain.repository;

import be.hepl.authapi.domain.model.Role;

import java.util.Map;

public interface JwtService {
     String generateToken(String id, Role role, Map<String, Object> extraClaims);

     Map<String, Object> verifyJwtSignature(String jwtToken);
}
