package it.polimi.ingsw.controller.server.task;

import it.polimi.ingsw.message.error.ErrorMessage;
import it.polimi.ingsw.model.Card;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GoldCard;
import it.polimi.ingsw.model.PlayerBoard;
import it.polimi.ingsw.model.enums.CardType;
import it.polimi.ingsw.model.enums.GameState;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;

import static it.polimi.ingsw.message.enums.ErrorType.GOLD_CARD_CONDITION_NOT_RESPECTED;

public class PlayCard implements Runnable{

    private Game game;
    private String nickname;
    private int[] position;

    private boolean isBackSide;
    private Card card;
    private ObjectOutputStream oos;

    public PlayCard(Game game, String nickname, int[] position, boolean isBackSide, Card card, ObjectOutputStream oos){
        this.game = game;
        this.nickname = nickname;
        this.position = position;
        this.isBackSide = isBackSide;
        this.card = card;
        this.oos = oos;
    }

    @Override
    public void run() {
        //basic check
        if (checkPlayCondition()){
            PlayerBoard board = game.getCurrentPlayer().getBoard();
            try {
                //play position check
                if (isPositionValid(board.getAvailablePosition(),position)){
                    //if card is gold card, check that card play condition is fulfilled
                    if (!isCardConditionFulfilled(board)){
                        oos.writeObject(new ErrorMessage(GOLD_CARD_CONDITION_NOT_RESPECTED));
                    }else {
                        game.getCurrentPlayer().removeHandCard(card.getIdCard());
                        game.getCurrentPlayer().updatePoint(board.placeCard(card, isBackSide, position[0], position[1]));
                        game.setGameState(GameState.DRAW_CARD);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    private boolean checkPlayCondition(){
        return game.getGameState() == GameState.PLAY_CARD &&
                game.getCurrentPlayer().getNickname().equals(nickname);
    }


    private boolean isPositionValid(List<int[]> playerPositions, int[] position){
        for (int[] p:playerPositions) {
            if (Arrays.equals(p,position))
                return true;
        }
        return false;
    }


    private boolean isCardConditionFulfilled(PlayerBoard board){
        if (card.getType() == CardType.GOLD){
            GoldCard goldCard = (GoldCard) card;
            return board.checkGoldCardCondition(goldCard);
        }
        return true;
    }
}
