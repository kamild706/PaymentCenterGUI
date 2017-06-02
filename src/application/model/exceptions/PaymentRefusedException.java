package application.model.exceptions;


public class PaymentRefusedException extends Exception {

    public PaymentRefusedException() {

    }

    public PaymentRefusedException(String message) {
        super(message);
    }

    public PaymentRefusedException(String message, Throwable cause) {
        super(message, cause);
    }

    public PaymentRefusedException(Throwable cause) {
        super(cause);
    }

    public PaymentRefusedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
