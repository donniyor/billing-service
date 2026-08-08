package uz.example.billing_service.Billing.DTO;

import java.time.Instant;

import uz.example.billing_service.Billing.Entities.Order;

public final record OrderDTO(
        Long id,
        Short status,
        Long totalAmount,
        String currency,
        String comment,
        Instant createdAt,
        Instant updatedAt
    ) {

    public static OrderDTO fromEnity(Order entity) {
        return new OrderDTO(
                entity.getId(),
                entity.getStatus(),
                entity.getTotalAmount(),
                entity.getCurrency(),
                entity.getComment(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
