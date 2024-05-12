package it.polimi.ingsw.controller;

import java.util.List;

public class ImmutableLobby {
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
