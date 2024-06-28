package it.polimi.ingsw.model.immutable;

import java.io.Serializable;
import java.util.List;

/**
 * The ImmutableLobby is an immutable class that implements {@link Serializable} interface
 * It is used in the communication between server and client
 * It contains all information about a lobby
 */
public class ImmutableLobby implements Serializable {
    private final List<String> players;
    private final int numOfPlayer;

    public ImmutableLobby(List<String> players, int numOfPlayer) {
        this.players = players;
        this.numOfPlayer = numOfPlayer;
    }

    public List<String> getPlayers() {
        return players;
    }

    public int getNumOfPlayer() {
        return numOfPlayer;
    }
}
