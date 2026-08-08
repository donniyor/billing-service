package uz.example.billing_service.Auth.DTO;

import java.util.Date;

public record LoginResponseDTO(
    String token,
    Date expiresAt
) {
}
