package it.polimi.ingsw.model.Exception;

public class NotInProgressException extends RuntimeException{
    public NotInProgressException() {
        super("it can't in progress");
    }
}
