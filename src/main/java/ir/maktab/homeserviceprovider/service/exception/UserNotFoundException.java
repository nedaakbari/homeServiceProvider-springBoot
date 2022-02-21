package ir.maktab.homeserviceprovider.service.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() {
        super("user not found");
    }

}
