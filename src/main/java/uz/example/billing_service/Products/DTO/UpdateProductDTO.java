package uz.example.billing_service.Products.DTO;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record UpdateProductDTO(
    String name,

    @Size(max = 500)
    String description,

    @Positive(message = "Price should be positive")
    Long price
){
}
