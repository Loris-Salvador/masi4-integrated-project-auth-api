package be.hepl.authapi.infrastructure.service.security;

import be.hepl.authapi.domain.exception.JwtExpiredException;
import be.hepl.authapi.domain.exception.JwtInvalidSignatureException;
import be.hepl.authapi.domain.model.Jwt;
import be.hepl.authapi.domain.model.Role;
import be.hepl.authapi.domain.repository.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
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

    @Value("${jwt.access_token_secret_key}")
    private String accessTokenSecretKey;

    @Value("${jwt.access_token_expiration}")
    private long accessTokenExpiration;

    @Value("${jwt.refresh_token_secret_key}")
    private String refreshTokenSecretKey;

    @Value("${jwt.refresh_token_expiration}")
    private long refreshTokenExpiration;

    public Jwt generateTokens(String id, Role role, Map<String, Object> extraClaims) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role.getValue());
        claims.putAll(extraClaims);

        String accessToken = Jwts.builder()
                                .setClaims(claims)
                                .setSubject(id)
                                .setIssuedAt(new Date())
                                .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpiration))
                                .signWith(Keys.hmacShaKeyFor(accessTokenSecretKey.getBytes()), SignatureAlgorithm.HS256)
                                .compact();

        Map<String, Object> refreshClaims = new HashMap<>();
        refreshClaims.put("sub", id);
        refreshClaims.put("type", "refresh_token");

        String refreshToken = Jwts.builder()
                .setClaims(refreshClaims)
                .setSubject(id)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpiration))
                .signWith(Keys.hmacShaKeyFor(refreshTokenSecretKey.getBytes()), SignatureAlgorithm.HS256)
                .compact();

        return new Jwt(accessToken, refreshToken);
    }

    public Map<String, Object> verifyJwtSignature(String jwtToken) {
        try {
            Key key = Keys.hmacShaKeyFor(accessTokenSecretKey.getBytes());

            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwtToken)
                    .getBody();

        } catch (SignatureException e) {
            throw new JwtInvalidSignatureException("Invalid signature");
        }
        catch (ExpiredJwtException e) {
            throw new JwtExpiredException("Token expired");
        }
    }

    @Override
    public Jwt refresh(String refreshToken) {
        try {
            Key key = Keys.hmacShaKeyFor(refreshTokenSecretKey.getBytes());

            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(refreshToken)
                    .getBody();

            String id = claims.getSubject();
            String role = (String) claims.get("role");


            Map<String, Object> extraClaims = new HashMap<>();
            extraClaims.put("role", role);

            String newAccessToken = Jwts.builder()
                    .setClaims(extraClaims)
                    .setSubject(id)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpiration)) // expiration courte (par exemple, 1 heure)
                    .signWith(Keys.hmacShaKeyFor(accessTokenSecretKey.getBytes()), SignatureAlgorithm.HS256)
                    .compact();

            String newRefreshToken = Jwts.builder()
                    .setClaims(extraClaims)
                    .setSubject(id)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpiration)) // expiration longue (par exemple, 30 jours)
                    .signWith(Keys.hmacShaKeyFor(refreshTokenSecretKey.getBytes()), SignatureAlgorithm.HS256)
                    .compact();

            return new Jwt(newAccessToken, newRefreshToken);

        } catch (SignatureException e) {
            throw new JwtInvalidSignatureException("Invalid signature");
        } catch (ExpiredJwtException e) {
            throw new JwtExpiredException("Token expired");
        }
    }

}
