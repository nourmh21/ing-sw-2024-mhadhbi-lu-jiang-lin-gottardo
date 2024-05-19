package it.polimi.ingsw.model.immutable;

import it.polimi.ingsw.controller.server.GameController;
import it.polimi.ingsw.model.Card;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.PlayerBoard;
import it.polimi.ingsw.model.enums.Color;
import it.polimi.ingsw.model.enums.Symbol;

import java.io.Serializable;
import java.util.List;

public class ImmutablePlayer implements Serializable {
    private final String nickname;
    private final Color color;
    private final int point;
    private final Integer initialCard;

    private final List<Integer> boardCards;
    private final List<Symbol> topLeftAngle;
    private final List<Symbol> topRightAngle;
    private final List<Symbol> bottomLeftAngle;
    private final List<Symbol> bottomRightAngle;
    private final List<Symbol> cardKingdom;
    private final List<Integer> x;
    private final List<Integer> y;
    private final List<int[]> permissiblePosition;
    private final List<Symbol> handCardKingdoms;

    public ImmutablePlayer(Player player, PlayerBoard board){
        nickname = player.getNickname();
        color = player.getPlayerColor();
        point = player.getPoint();
        initialCard = player.getInitialCard();
        boardCards = board.getBoardCards();
        topLeftAngle = board.getTopLeftAngle();
        topRightAngle = board.getTopRightAngle();
        bottomLeftAngle = board.getBottomLeftAngle();
        bottomRightAngle = board.getBottomRightAngle();
        cardKingdom = board.getCardKingdom();
        x = board.getX();
        y = board.getY();
        permissiblePosition = board.getAvailablePosition();
        if (player.getHandCards() != null){
            handCardKingdoms = player.getHandCards().stream()
                    .map(integer -> (((Card)(GameController.getInstance().getCard(integer))).getKingdom()))
                    .toList();
        }else {
            handCardKingdoms = null;
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

    public List<Symbol> getTopLeftAngle() {
        return topLeftAngle;
    }

    public List<Symbol> getTopRightAngle() {
        return topRightAngle;
    }

    public List<Symbol> getBottomLeftAngle() {
        return bottomLeftAngle;
    }

    public List<Symbol> getBottomRightAngle() {
        return bottomRightAngle;
    }

    public List<Symbol> getCardKingdom() {
        return cardKingdom;
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
}

