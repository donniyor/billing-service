package uz.example.billing_service.Products.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CreateProductDTO(
    @NotNull(message = "Name is required")
    String name,

    @Size(max = 500)
    String description,

    @NotNull(message = "Price is required")
    @Positive(message = "Price should be positive")
    Long price,

    @NotBlank(message = "Currency is required")
    @Size(min = 0, max = 3)
    String currency
) {
}
