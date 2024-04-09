package it.polimi.ingsw.model.exceptions;

/**IvalidNumOfPlayer defines an exception when there is a number that fails to start the game */

public class TooFewPlayersException extends RuntimeException {
    public TooFewPlayersException(){
        super("Only one player is connected");
    }

}
