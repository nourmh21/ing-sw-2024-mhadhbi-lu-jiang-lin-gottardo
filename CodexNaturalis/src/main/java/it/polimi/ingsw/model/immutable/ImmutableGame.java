package it.polimi.ingsw.model.immutable;

import it.polimi.ingsw.controller.server.GameController;
import it.polimi.ingsw.model.Card;
import it.polimi.ingsw.model.Game;
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
    private final int idGame;
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


    public ImmutableGame(Game game){
        idGame = game.getIdGame();
        numOfPlayer = game.getNumOfPlayer();
        gameState = game.getGameState();
        commonGoals = game.getCommonGoals();
        isLastRound = game.getIsLastRound();
        if (game.getPlayers() != null){
            players = game.getPlayersNickname();
        }else {
            players = null;
        }

        if (game.getCurrentPlayer() != null)
            currentPlayer = game.getCurrentPlayer().getNickname();
        else
            currentPlayer = null;

        if (game.getDesk().getNextResourceCard()!= null){
            firstRCardKingdom = ((Card)(GameController.getInstance().getCard(game.getDesk().getNextResourceCard())))
                            .getKingdom();
        }else{
            firstRCardKingdom = null;
        }

        if (game.getDesk().getNextGoldCard()!= null){
            firstGCardKingdom = ((Card)GameController.getInstance().
                    getCard(game.getDesk().getNextGoldCard())).getKingdom();
        }else {
            firstGCardKingdom = null;
        }

        if (game.getDesk().getDisplayedRCards() != null)
            displayedRCards = game.getDesk().getDisplayedRCards();
        else
            displayedRCards = null;


        if (game.getDesk().getDisplayedGCards() != null)
            displayedGCards = game.getDesk().getDisplayedGCards();
        else
            displayedGCards = null;
    }

    public int getIdGame() {
        return idGame;
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
