package ir.maktab.homeserviceprovider.service.exception;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException() {
        super("customer not found");
    }

}
