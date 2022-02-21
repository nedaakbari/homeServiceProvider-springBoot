package ir.maktab.homeserviceprovider.service.exception;

public class NotFoundDta extends RuntimeException {
    private int errorCode;

    public NotFoundDta(String message) {
        super(message);
    }
}
