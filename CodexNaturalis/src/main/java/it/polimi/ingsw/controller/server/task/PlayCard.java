package it.polimi.ingsw.controller.server.task;

import it.polimi.ingsw.message.error.ErrorMessage;
import it.polimi.ingsw.message.Message;
import it.polimi.ingsw.model.Card;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.PlayerBoard;
import it.polimi.ingsw.model.enums.GameState;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static it.polimi.ingsw.message.enums.ErrorType.INVALID_POSITION;

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

    /*
    public PlayCard(Game game, String nickname, int x, int y, boolean isBackSide, Card card){
        this.game = game;
        this.nickname = nickname;
        this.x = x;
        this.y = y;
        this.isBackSide = isBackSide;
        this.card = card;
    }*/


    @Override
    public void run() {
        if (checkPlayCondition()){
            PlayerBoard board = game.getCurrentPlayer().getBoard();

            if (!isPositionValid(board.getAvailablePosition(),position)){
                try {
                    oos.writeObject(new ErrorMessage(INVALID_POSITION));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }else {
                game.getCurrentPlayer().updatePoint(board.placeCard(card, isBackSide, position[0], position[1]));
                game.setGameState(GameState.DRAW_CARD);
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
}
