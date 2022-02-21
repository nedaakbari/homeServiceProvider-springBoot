package ir.maktab.homeserviceprovider.service.exception;

public class ExpertNotFoundException extends RuntimeException{
    public ExpertNotFoundException() {
    }

    public ExpertNotFoundException(String message) {
        super(message);
    }
}
