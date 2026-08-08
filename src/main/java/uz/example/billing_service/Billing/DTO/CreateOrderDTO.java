package uz.example.billing_service.Billing.DTO;

import jakarta.validation.constraints.*;
import uz.example.billing_service.Billing.Enums.OrderStatusEnum;

public final record CreateOrderDTO(
        @NotNull(message = "Status is required")
        OrderStatusEnum status,

        @NotNull(message = "Amount is required")
        @Positive(message = "Amount should be positive")
        Long totalAmount,

        @NotBlank(message = "Currency is required")
        String currency,

        @Size(max = 500)
        String comment
) {
}
