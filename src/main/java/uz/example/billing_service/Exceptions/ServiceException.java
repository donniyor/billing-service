package uz.example.billing_service.Exceptions;

public class ServiceException extends RuntimeException {
    public ServiceException(String error) {
        super(error);
    }

    public static ServiceException negativeOrZeroValue() {
        return new ServiceException("The value can not be zero or negative");
    }
}
