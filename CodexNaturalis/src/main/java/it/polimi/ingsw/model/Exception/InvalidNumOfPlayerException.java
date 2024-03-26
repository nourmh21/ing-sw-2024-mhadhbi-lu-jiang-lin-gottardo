package it.polimi.ingsw.model.Exception;

/**IvalidNumOfPlayer defines an exception when there is a number that fails to start the game */

public class InvalidNumOfPlayerException extends RuntimeException {
public InvalidNumOfPlayerException(){
    super("max of players");
}

}
