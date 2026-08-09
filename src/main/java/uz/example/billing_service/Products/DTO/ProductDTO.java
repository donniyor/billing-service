package uz.example.billing_service.Products.DTO;

import java.time.Instant;

import uz.example.billing_service.Products.Entities.Product;

public record ProductDTO(
    Long id,
    String name,
    String description,
    Long price,
    String currency,
    Instant createdAt,
    Instant updatedAt,
    Instant deletedAt
) {

    public static ProductDTO fromEntity(Product product) {
        return new ProductDTO(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getPrice(),
            product.getCurrency(),
            product.getCreatedAt(),
            product.getUpdatedAt(),
            product.getDeletedAt()
        );
    } 
}
