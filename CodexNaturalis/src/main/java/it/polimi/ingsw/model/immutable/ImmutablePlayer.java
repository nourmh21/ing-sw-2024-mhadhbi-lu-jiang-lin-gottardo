package it.polimi.ingsw.model.immutable;

import it.polimi.ingsw.controller.server.GameController;
import it.polimi.ingsw.model.Card;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.PlayerBoard;
import it.polimi.ingsw.model.enums.CardType;
import it.polimi.ingsw.model.enums.Color;
import it.polimi.ingsw.model.enums.Symbol;

import java.io.Serializable;
import java.util.List;

/**
 * The ImmutablePlayer is an immutable class that implements {@link Serializable} interface
 * It is used in the communication between server and client
 * It contains all information about a player status
 */
public class ImmutablePlayer implements Serializable {
    private final String nickname;
    private final Color color;
    private final int point;
    private final Integer initialCard;
    private final List<Integer> boardCards;
    private final boolean isPersonalGoalChosen;
    private final List<Integer> x;
    private final List<Integer> y;
    private final List<Boolean> isBackSide;
    private final List<int[]> permissiblePosition;
    private final List<Symbol> handCardKingdoms;
    private final List<CardType> handCardTypes;
    private final int[] symbolList;

    public ImmutablePlayer(Player player, PlayerBoard board){
        nickname = player.getNickname();
        color = player.getPlayerColor();
        point = player.getPoint();
        initialCard = player.getInitialCard();
        isBackSide = board.getCardSide();
        isPersonalGoalChosen = player.isPersonalGoalChosen();
        boardCards = board.getBoardCards();
        x = board.getX();
        y = board.getY();
        permissiblePosition = board.getAvailablePosition();
        symbolList = board.getSymbolList();
        if (player.getHandCards() != null){
            handCardKingdoms = player.getHandCards().stream()
                    .map(integer -> (((Card)(GameController.getInstance().getCard(integer))).getKingdom()))
                    .toList();
            handCardTypes = player.getHandCards().stream()
                    .map(integer -> (((Card)(GameController.getInstance().getCard(integer))).getType()))
                    .toList();
        }else {
            handCardKingdoms = null;
            handCardTypes = null;
        }
    }


    public String getNickname() {
        return nickname;
    }

    public Color getColor() {
        return color;
    }

    public int getPoint() {
        return point;
    }

    public Integer getInitialCard(){
        return initialCard;
    }

    public List<Integer> getBoardCards() {
        return boardCards;
    }

    public List<Integer> getX() {
        return x;
    }

    public List<Integer> getY() {
        return y;
    }

    public List<int[]> getPermissiblePosition() {
        return permissiblePosition;
    }

    public List<Symbol> getHandCardKingdoms() {
        return handCardKingdoms;
    }

    public List<CardType> getHandCardTypes() {
        return handCardTypes;
    }

    public boolean isPersonalGoalChosen() {
        return isPersonalGoalChosen;
    }

    public List<Boolean> getIsBackSide() {
        return isBackSide;
    }

    public int[] getSymbolList(){
        return symbolList;
    };
}

