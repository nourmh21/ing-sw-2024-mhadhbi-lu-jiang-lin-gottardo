package it.polimi.ingsw.controller.server.task;

import it.polimi.ingsw.controller.server.GameController;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.enums.GameState;

import java.io.ObjectOutputStream;

public class PlayerDisconnect implements Runnable{
    Game game;
    String nickname;
    ObjectOutputStream oos;

    public PlayerDisconnect(String nickname, Game game, ObjectOutputStream oos){
        this.game = game;
        this.nickname = nickname;
        this.oos = oos;
    }

    @Override
    public void run() {
        game.notify_game_interrupted(nickname);
        game.getObservers().clear();
        game.setGameState(GameState.FINISHED);
        for (Player p: game.getPlayers()){
            GameController.getInstance().removeUserInGame(p.getNickname());
        }
    }
}
