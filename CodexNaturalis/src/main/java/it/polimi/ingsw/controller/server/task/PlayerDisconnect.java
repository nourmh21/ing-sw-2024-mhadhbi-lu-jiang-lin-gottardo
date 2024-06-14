package it.polimi.ingsw.controller.server.task;

import it.polimi.ingsw.network.Client;
import it.polimi.ingsw.controller.server.GameController;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.enums.GameState;

/**
 * The PlayerDisconnect is one of Runnable executed by {@link GameController}
 * It manages the disconnection of a player currently in game
 */
public class PlayerDisconnect implements Runnable{
    Client client;
    Game game;

    /**
     * Constructor
     * @param client user who made the action
     * @param game game tha user is in
     */
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
