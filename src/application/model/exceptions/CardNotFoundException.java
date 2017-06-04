package application.model.exceptions;

/**
 * Thrown when searched card is not found.
 */
public class CardNotFoundException extends Exception {

    public CardNotFoundException() {
    }

    public CardNotFoundException(String message) {
        super(message);
    }
}
