package it.polimi.ingsw.model.exceptions;

public class NotInProgressException extends RuntimeException{
    public NotInProgressException() {
        super("it can't in progress");
    }
}
