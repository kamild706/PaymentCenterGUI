package application.model.exceptions;


public class FundsException extends Exception {

    public FundsException() {
    }

    public FundsException(String message) {
        super(message);
    }

    public FundsException(String message, Throwable cause) {
        super(message, cause);
    }

    public FundsException(Throwable cause) {
        super(cause);
    }

    public FundsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
