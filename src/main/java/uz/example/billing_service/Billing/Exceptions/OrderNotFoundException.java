package uz.example.billing_service.Billing.Exceptions;

public final class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(Long id) {
        super("Order not found with id: " + id);
    }
}
