package it.polimi.ingsw.model.immutable;

import it.polimi.ingsw.controller.server.Lobby;

import java.io.Serializable;
import java.util.List;

public class ImmutableLobby implements Serializable {
    private final List<String> players;
    private final int numOfPlayer;
    public ImmutableLobby(Lobby lobby){
        players = lobby.getPlayers();
        numOfPlayer = lobby.getNumOfPlayer();
    }

    public List<String> getPlayers() {
        return players;
    }

    public int getNumOfPlayer() {
        return numOfPlayer;
    }
}
