package it.polimi.ingsw.controller.task;

import it.polimi.ingsw.message.ErrorMessage;
import it.polimi.ingsw.message.Message;
import it.polimi.ingsw.model.Card;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.PlayerBoard;
import it.polimi.ingsw.model.enums.GameState;

import java.io.IOException;
import java.io.ObjectOutputStream;
import static it.polimi.ingsw.message.enums.ErrorType.INVALID_POSITION;

public class PlayCard implements Runnable{

    private Game game;
    private String nickname;
    private int x;
    private int y;

    private boolean isBackSide;
    private Card card;
    private ObjectOutputStream oos;

    public PlayCard(Game game, String nickname, int x, int y, boolean isBackSide, Card card, ObjectOutputStream oos){
        this.game = game;
        this.nickname = nickname;
        this.x = x;
        this.y = y;
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
            int[] position= {x,y};

            if (!board.getAvailablePosition().contains(position)){
                //
                Message message = new ErrorMessage(INVALID_POSITION);
                try {
                    oos.writeObject(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                game.getCurrentPlayer().updatePoint(board.placeCard(card, isBackSide, x, y));
                game.setGameState(GameState.DRAW_CARD);
            }
        }

    }

    private boolean checkPlayCondition(){
        return game.getGameState() == GameState.PLAY_CARD &&
                game.getCurrentPlayer().getNickname().equals(nickname);
    }

}
