package it.polimi.ingsw.controller.server.task;

import it.polimi.ingsw.network.Client;
import it.polimi.ingsw.model.Card;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GoldCard;
import it.polimi.ingsw.model.PlayerBoard;
import it.polimi.ingsw.model.enums.CardType;
import it.polimi.ingsw.model.enums.GameState;

import java.util.Arrays;
import java.util.List;

import static it.polimi.ingsw.message.enums.ErrorType.GOLD_CARD_CONDITION_NOT_RESPECTED;

public class PlayCard implements Runnable{

    private Client client;
    private Game game;
    private int[] position;
    private boolean isBackSide;
    private Card card;


    public PlayCard(Client client, Game game, Card card, int[] position, boolean isBackSide){
        this.client = client;
        this.game = game;
        this.position = position;
        this.isBackSide = isBackSide;
        this.card = card;
    }

    @Override
    public void run() {
        //basic check
        if (checkPlayCondition()){
            PlayerBoard board = game.getCurrentPlayer().getBoard();
            //play position check
            if (isPositionValid(board.getAvailablePosition(),position)){
                //if card is gold card, check that card play condition is fulfilled
                if (!isCardConditionFulfilled(board)){
                    client.informError(GOLD_CARD_CONDITION_NOT_RESPECTED);
                }else {
                    game.getCurrentPlayer().removeHandCard(card.getIdCard());
                    game.getCurrentPlayer().updatePoint(board.placeCard(card, isBackSide, position[0], position[1]));
                    game.setGameState(GameState.DRAW_CARD);
                }
            }
        }

    }


    private boolean checkPlayCondition(){
        return game.getGameState() == GameState.PLAY_CARD &&
                game.getCurrentPlayer().getNickname().equals(client.getNickname());
    }


    private boolean isPositionValid(List<int[]> playerPositions, int[] position){
        for (int[] p:playerPositions) {
            if (Arrays.equals(p,position))
                return true;
        }
        return false;
    }


    private boolean isCardConditionFulfilled(PlayerBoard board){
        if (card.getType() == CardType.GOLD && !isBackSide){
            GoldCard goldCard = (GoldCard) card;
            return board.checkGoldCardCondition(goldCard);
        }
        return true;
    }
}
