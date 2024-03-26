package it.polimi.ingsw.Exception;

public class NotInProgressException extends RuntimeException{
    public NotInProgressException() {
        super("it can't in progress");
    }
}
