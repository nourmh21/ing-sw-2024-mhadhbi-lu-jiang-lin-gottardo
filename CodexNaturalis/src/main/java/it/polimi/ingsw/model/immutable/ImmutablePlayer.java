package it.polimi.ingsw.model.immutable;

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

    public ImmutablePlayer(String nickname, Color color, int point, Integer initialCard, List<Integer> boardCards, boolean isPersonalGoalChosen,
                           List<Integer> x, List<Integer> y, List<Boolean> isBackSide, List<int[]> permissiblePosition, List<Symbol> handCardKingdoms,
                           List<CardType> handCardTypes, int[] symbolList) {
        this.nickname = nickname;
        this.color = color;
        this.point = point;
        this.initialCard = initialCard;
        this.boardCards = boardCards;
        this.isPersonalGoalChosen = isPersonalGoalChosen;
        this.x = x;
        this.y = y;
        this.isBackSide = isBackSide;
        this.permissiblePosition = permissiblePosition;
        this.handCardKingdoms = handCardKingdoms;
        this.handCardTypes = handCardTypes;
        this.symbolList = symbolList;
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

    public Integer getInitialCard() {
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

    public int[] getSymbolList() {
        return symbolList;
    }

    ;
}

