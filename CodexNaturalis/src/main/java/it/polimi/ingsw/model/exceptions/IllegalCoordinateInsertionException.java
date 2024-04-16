package it.polimi.ingsw.model.exceptions;
/**
 * The IllegalCoordinateInsertionException is an exception that is thrown when the position of card is invalid.
 * It is a subclass of the RuntimeException class.
 */
@SuppressWarnings("ALL")
public class IllegalCoordinateInsertionException extends RuntimeException{

        /**
         * Constructs a new {@code IllegalCoordinateInsertionException} with the specified error message.
         *
         *  @param errorMessage the error message describing the exception
         */

        public IllegalCoordinateInsertionException(String errorMessage) {
            super();
        }

        /**
         * Constructs a new {@code InvalidIdCardException} with no error message.
         */

        public IllegalCoordinateInsertionException() {
        }

    }