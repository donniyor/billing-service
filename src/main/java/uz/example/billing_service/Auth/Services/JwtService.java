package uz.example.billing_service.Auth.Services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import uz.example.billing_service.Auth.Config.JwtConfig;
import uz.example.billing_service.Auth.Entities.User;
import uz.example.billing_service.Auth.Enum.UserRole;

@Service
public class JwtService {
    private final JwtConfig config;
    private final SecretKey key;

    public JwtService(JwtConfig config) {
        this.config = config;
        this.key = Keys.hmacShaKeyFor(config.secret().getBytes());
    }

    public String generateToken(User user) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + config.expiration());

        Map<String, Object> claims = new HashMap<>();

        claims.put("email", user.getEmail());
        claims.put("role", user.getRole().name());

        return Jwts.builder()
            .subject(user.getId().toString())
            .claims(claims)
            .issuedAt(now)
            .expiration(expiry)
            .signWith(key)
            .compact();
    }

    public Date extractExpirationDate(String token) {
        return parseClaims(token).getExpiration();
    }

    public String extractEmail(String token) {
        return parseClaims(token).get("email", String.class); 
    }

    public UserRole extractRole(String token) {
        return UserRole.fromString(parseClaims(token).get("role", String.class));
    }

    private Claims parseClaims(String token) {
        return Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }
}
