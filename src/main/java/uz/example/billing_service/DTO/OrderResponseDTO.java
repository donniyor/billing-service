package uz.example.billing_service.DTO;

import java.time.Instant;

import uz.example.billing_service.Enums.OrderStatusEnum;

public record OrderResponseDTO(
        Long id,
        Short status,
        Long totalAmount,
        String currency,
        String comment,
        Instant createdAt,
        Instant updatedAt
    ) {
}
