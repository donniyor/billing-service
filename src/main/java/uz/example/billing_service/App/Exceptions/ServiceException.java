package uz.example.billing_service.App.Exceptions;

import uz.example.billing_service.Billing.Services.OrderService;

public final class ServiceException extends RuntimeException {
    public ServiceException(String error) {
        super(error);
    }

    public static ServiceException negativeOrZeroValue() {
        return new ServiceException("The value can not be zero or negative");
    }

    public static ServiceException limitExceeded() {
        return new ServiceException("Page size cannot exceed " + OrderService.MAX_LIMIT);
    }

    public static ServiceException invalidPagination() {
        return new ServiceException("Page must be >= 0 and size must be > 0");
    }
}
