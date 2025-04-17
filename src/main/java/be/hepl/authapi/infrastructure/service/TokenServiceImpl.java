package be.hepl.authapi.infrastructure.service;

import be.hepl.authapi.domain.exception.JwtExpiredException;
import be.hepl.authapi.domain.exception.JwtInvalidSignatureException;
import be.hepl.authapi.domain.model.token.Token;
import be.hepl.authapi.domain.model.token.Role;
import be.hepl.authapi.application.service.TokenService;
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
public class TokenServiceImpl implements TokenService {

    @Value("${jwt.access_token_secret_key}")
    private String accessTokenSecretKey;

    @Value("${jwt.access_token_expiration}")
    private long accessTokenExpiration;

    public Token generateTokens(String id, Role role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role.getValue());

        String accessToken = Jwts.builder()
                                .setClaims(claims)
                                .setSubject(id)
                                .setIssuedAt(new Date())
                                .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpiration))
                                .signWith(Keys.hmacShaKeyFor(accessTokenSecretKey.getBytes()), SignatureAlgorithm.HS256)
                                .compact();

        return new Token(accessToken);
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

}
