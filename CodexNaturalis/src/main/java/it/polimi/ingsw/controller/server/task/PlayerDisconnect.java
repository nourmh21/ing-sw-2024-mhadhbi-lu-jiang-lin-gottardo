package it.polimi.ingsw.controller.server.task;

import it.polimi.ingsw.network.Client;
import it.polimi.ingsw.controller.server.GameController;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.enums.GameState;

public class PlayerDisconnect implements Runnable{
    Client client;
    Game game;

    public PlayerDisconnect(Client client, Game game){
        this.client = client;
        this.game = game;
    }

    @Override
    public void run() {
        game.notify_game_interrupted(client.getNickname());
        game.getObservers().clear();
        game.setGameState(GameState.FINISHED);
        for (Player p: game.getPlayers()){
            GameController.getInstance().removeUserInGame(p.getNickname());
        }
    }
}
