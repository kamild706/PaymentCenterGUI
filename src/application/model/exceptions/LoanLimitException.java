package application.model.exceptions;


public class LoanLimitException extends FundsException {

    public LoanLimitException() {
    }

    public LoanLimitException(String message) {
        super(message);
    }

    public LoanLimitException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoanLimitException(Throwable cause) {
        super(cause);
    }

    public LoanLimitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
