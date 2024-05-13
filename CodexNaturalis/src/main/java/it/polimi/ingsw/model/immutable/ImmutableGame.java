package it.polimi.ingsw.model.immutable;

import it.polimi.ingsw.controller.GameController;
import it.polimi.ingsw.model.Card;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.enums.GameState;
import it.polimi.ingsw.model.enums.Symbol;

import java.io.Serializable;
import java.util.IllegalFormatCodePointException;
import java.util.List;
import java.util.Optional;

public class ImmutableGame implements Serializable {

    private final int idGame;
    private final int numOfPlayer;
    private final GameState gameState;
    private final List<Integer> commonGoals;
    private final boolean isLastRound;
    private final List<String> players;
    private final String currentPlayer;

    private final Optional<Symbol> firstRCardKingdom;
    private final Optional<Symbol> firstGCardKingdom;

    private final Optional<List<Integer>> displayedRCards;
    private final Optional<List<Integer>> displayedGCards;


    public ImmutableGame(Game game){
        idGame = game.getIdGame();
        numOfPlayer = game.getNumOfPlayer();
        gameState = game.getGameState();
        commonGoals = game.getCommonGoals();
        isLastRound = game.getIsLastRound();
        if (game.getPlayers() != null){
            players = game.getPlayers().stream()
                    .map(Player::getNickname)
                    .toList();
        }else {
            players = null;
        }

        currentPlayer = game.getCurrentPlayer().getNickname();
        if (game.getDesk().getNextResourceCard()!= null){
            firstRCardKingdom = Optional.of(
                    ((Card)(GameController.getInstance().getCard(game.getDesk().getNextResourceCard())))
                            .getKingdom());
        }else{
            firstRCardKingdom = Optional.empty();
        }

        if (game.getDesk().getNextGoldCard()!= null){
            firstGCardKingdom = Optional.of(((Card)GameController.getInstance().
                    getCard(game.getDesk().getNextGoldCard())).getKingdom());
        }else {
            firstGCardKingdom = Optional.empty();
        }

        if (game.getDesk().getDisplayedRCards() != null)
            displayedRCards = Optional.of(game.getDesk().getDisplayedRCards());
        else
            displayedRCards = Optional.empty();


        if (game.getDesk().getDisplayedGCards() != null)
            displayedGCards = Optional.of(game.getDesk().getDisplayedGCards());
        else
            displayedGCards = Optional.empty();

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


    public Optional<Symbol> getFirstRCardKingdom() {
        return firstRCardKingdom;
    }


    public Optional<Symbol> getFirstGCardKingdom() {
        return firstGCardKingdom;
    }


    public Optional<List<Integer>> getDisplayedRCards() {
        return displayedRCards;
    }


    public Optional<List<Integer>> getDisplayedGCards() {
        return displayedGCards;
    }
}
