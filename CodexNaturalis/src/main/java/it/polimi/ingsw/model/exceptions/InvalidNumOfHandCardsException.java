package it.polimi.ingsw.model.exceptions;

/**
 * The MaxiNumOfHandCardsReachedException is an exception that is thrown when number of cards is reached.
 * It is a subclass of the RuntimeException class.
 */

@SuppressWarnings("ALL")
public class InvalidNumOfHandCardsException extends RuntimeException {
    /**
     * Constructs a new {@code MaxiNumOfHandCardsReachedException} with the specified error message.
     *
     * @param errorMessage the error message describing the exception
     */

    public InvalidNumOfHandCardsException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Constructs a new {@code MaxiNumOfHandCardsReachedException} with no error message.
     */

    public InvalidNumOfHandCardsException() {

    }


}
