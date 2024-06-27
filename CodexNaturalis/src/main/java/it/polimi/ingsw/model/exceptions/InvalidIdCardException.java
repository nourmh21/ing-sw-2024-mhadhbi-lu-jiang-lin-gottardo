package it.polimi.ingsw.model.exceptions;

/**
 * The InvalidIdCardException is an exception that is thrown when Idcard does not match the type of card used by the method.
 * It is a subclass of the RuntimeException class.
 */

@SuppressWarnings("ALL")
public class InvalidIdCardException extends RuntimeException {
    /**
     * Constructs a new {@code InvalidIdCardException} with the specified error message.
     *
     * @param errorMessage the error message describing the exception
     */

    public InvalidIdCardException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Constructs a new {@code InvalidIdCardException} with no error message.
     */

    public InvalidIdCardException() {
    }

}
