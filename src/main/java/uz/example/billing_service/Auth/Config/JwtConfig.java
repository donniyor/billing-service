package uz.example.billing_service.Auth.Config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
public record JwtConfig(
    String secret,
    long expiration
) {
}
