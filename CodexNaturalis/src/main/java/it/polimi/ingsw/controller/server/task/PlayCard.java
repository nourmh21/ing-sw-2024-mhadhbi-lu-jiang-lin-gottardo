package it.polimi.ingsw.controller.server.task;

import it.polimi.ingsw.controller.server.GameController;
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

/**
 * The PlayCard is one of Runnable executed by {@link GameController}
 * It manages the play of a card
 */
public class PlayCard implements Runnable{

    private Client client;
    private Game game;
    private int[] position;
    private boolean isBackSide;
    private Card card;


    /**
     * Constructor
     * @param client the {@link Client} who made the action
     * @param game the {@link Game} in which client participates
     * @param card the {@link Card} that client want to play
     * @param position position[x,y] that client want to play
     * @param isBackSide side of card that client want to play
     */
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


    /**
     * @return true if it is a currently admitted action, false otherwise
     */
    private boolean checkPlayCondition(){
        return game.getGameState() == GameState.PLAY_CARD &&
                game.getCurrentPlayer().getNickname().equals(client.getNickname());
    }


    /**
     * @param playerPositions a player's list of permitted position
     * @param position position that want to play
     * @return true if list of permitted position contains position
     */
    private boolean isPositionValid(List<int[]> playerPositions, int[] position){
        for (int[] p:playerPositions) {
            if (Arrays.equals(p,position))
                return true;
        }
        return false;
    }


    /**
     * @param board the {@link PlayerBoard} where contains check condition method
     * @return true if card is not a gold card or else is backside
     * or else it is gold card and required condition is fulfilled, false otherwise
     */
    private boolean isCardConditionFulfilled(PlayerBoard board){
        if (card.getType() == CardType.GOLD && !isBackSide){
            GoldCard goldCard = (GoldCard) card;
            return board.checkGoldCardCondition(goldCard);
        }
        return true;
    }
}
