package it.polimi.ingsw.model.immutable;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * The ImmutableEndGameInfo is an immutable class that implements {@link Serializable} interface
 * It is used in the communication between server and client
 * It contains all information about final result of a game
 */
public class ImmutableEndGameInfo implements Serializable {
    private final HashMap<String, int[]> finalResult;
    private final List<String> winners;

    public ImmutableEndGameInfo(HashMap<String,int[]> finalResult, List<String> winners){
        this.finalResult = finalResult;
        this.winners = winners;
    }

    public HashMap<String, int[]> getFinalResult() {
        return finalResult;
    }

    public List<String> getWinners() {
        return winners;
    }

}
