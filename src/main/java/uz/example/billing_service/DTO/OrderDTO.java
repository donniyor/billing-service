package uz.example.billing_service.DTO;

public record OrderDTO(Short status, Long totalAmount, String currency, String comment) {}
