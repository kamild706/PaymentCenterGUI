package application.model.exceptions;


public class AccountBalanceException extends FundsException {

    public AccountBalanceException() {
    }

    public AccountBalanceException(String message) {
        super(message);
    }

    public AccountBalanceException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountBalanceException(Throwable cause) {
        super(cause);
    }

    public AccountBalanceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
