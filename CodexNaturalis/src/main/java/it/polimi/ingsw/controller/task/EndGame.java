package it.polimi.ingsw.controller.task;

import it.polimi.ingsw.controller.GameController;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.enums.GameState;

public class EndGame implements Runnable{
    private Game game;

    public EndGame(Game game){
        this.game = game;
    }

    @Override
    public void run() {

        if (game.getGameState() == GameState.ENDING){

            game.checkMaxPoint();

            game.setGameState(GameState.FINISHED);

            for (Player p: game.getPlayers()) {
                GameController.getInstance().removeUserInGame(p.getNickname());
            }
        }

    }

}

