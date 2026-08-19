package uz.example.billing_service.Auth.Config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Validated
@ConfigurationProperties(prefix = "jwt")
public record JwtConfig(
    // HMAC-SHA (RFC 7518) требует ключ >= 256 бит, то есть >= 32 байт
    @Size(min = 32, message = "jwt.secret must be at least 32 bytes (256 bits); check the JWT_SECRET env variable")
    String secret,

    @Positive(message = "jwt.expiration must be positive")
    long expiration
) {
}
