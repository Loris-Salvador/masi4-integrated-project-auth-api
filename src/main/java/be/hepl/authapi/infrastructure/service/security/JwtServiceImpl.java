package be.hepl.authapi.infrastructure.service.security;

import be.hepl.authapi.domain.model.Role;
import be.hepl.authapi.domain.repository.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expiration; // 1 heure

    public String generateToken(String id, Role role, Map<String, Object> extraClaims) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role.getValue());
        claims.putAll(extraClaims);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(id)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public  boolean verifyJwtSignature(String jwtToken) {
        try {
            Key key = Keys.hmacShaKeyFor(secretKey.getBytes());

            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwtToken)
                    .getBody();

            System.out.println("JWT is valid. Payload: " + claims);
            return true;
        } catch (SignatureException e) {
            System.out.println("Invalid JWT signature: " + e.getMessage());
            return false;
        }
    }
}
