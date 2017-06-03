package application.model.exceptions;

/**
 * Created by kamil on 6/3/17.
 */
public class CardNotFoundException extends Exception {

    public CardNotFoundException() {
    }

    public CardNotFoundException(String message) {
        super(message);
    }
}
