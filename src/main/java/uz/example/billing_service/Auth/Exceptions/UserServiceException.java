package uz.example.billing_service.Auth.Exceptions;

public class UserServiceException extends RuntimeException {
    public UserServiceException(String errorMessage) {
        super(errorMessage);
    }

    public static UserServiceException sameEmailError() {
        return new UserServiceException("User with same email is already exists");
    } 

    public static UserServiceException invalideEmailOrPassword() {
        return new UserServiceException("Invalid username or password");
    }
}
