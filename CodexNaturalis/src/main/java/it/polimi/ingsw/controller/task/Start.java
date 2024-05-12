package it.polimi.ingsw.controller.task;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.enums.GameState;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Start implements Runnable{
    private Game game;
    private List<Player> players;
    public Start(Game game){
        this.game = game;
        players = game.getPlayers();
    }

    @Override
    public void run() {

        //random a player
        int random = new Random().nextInt(game.getNumOfPlayer());
        Player firstPlayer = players.get(random);

        //resort the player list in order to make that player become the first in the list
        while (players.get(0) != firstPlayer){
            Collections.rotate(players,-1);
        }

        game.setGameState(GameState.PLAY_CARD);
        //set first player in the list the current player
        game.setCurrentPlayer(players.get(0));

    }


}
