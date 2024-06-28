package it.polimi.ingsw.model.immutable;

import it.polimi.ingsw.model.enums.GameState;
import it.polimi.ingsw.model.enums.Symbol;

import java.io.Serializable;
import java.util.List;

/**
 * The ImmutableGame is an immutable class that implements {@link Serializable} interface
 * It is used in the communication between server and client
 * It contains all information about a game status
 */
public class ImmutableGame implements Serializable {
    private final int numOfPlayer;
    private final GameState gameState;
    private final List<Integer> commonGoals;
    private final boolean isLastRound;
    private final List<String> players;
    private final String currentPlayer;

    private final Symbol firstRCardKingdom;
    private final Symbol firstGCardKingdom;

    private final List<Integer> displayedRCards;
    private final List<Integer> displayedGCards;


    public ImmutableGame(int numOfPlayer, GameState gameState, List<Integer> commonGoals, boolean isLastRound, List<String> players,
                         String currentPlayer, Symbol firstRCardKingdom, Symbol firstGCardKingdom, List<Integer> displayedRCards, List<Integer> displayedGCards) {
        this.numOfPlayer = numOfPlayer;
        this.gameState = gameState;
        this.commonGoals = commonGoals;
        this.isLastRound = isLastRound;
        this.players = players;
        this.currentPlayer = currentPlayer;
        this.firstRCardKingdom = firstRCardKingdom;
        this.firstGCardKingdom = firstGCardKingdom;
        this.displayedRCards = displayedRCards;
        this.displayedGCards = displayedGCards;
    }


    public int getNumOfPlayer() {
        return numOfPlayer;
    }


    public GameState getGameState() {
        return gameState;
    }


    public List<Integer> getCommonGoals() {
        return commonGoals;
    }

    public boolean isLastRound() {
        return isLastRound;
    }

    public List<String> getPlayers() {
        return players;
    }


    public String getCurrentPlayer() {
        return currentPlayer;
    }


    public Symbol getFirstRCardKingdom() {
        return firstRCardKingdom;
    }


    public Symbol getFirstGCardKingdom() {
        return firstGCardKingdom;
    }


    public List<Integer> getDisplayedRCards() {
        return displayedRCards;
    }


    public List<Integer> getDisplayedGCards() {
        return displayedGCards;
    }
}
