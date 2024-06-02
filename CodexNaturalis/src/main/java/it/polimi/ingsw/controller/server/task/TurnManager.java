package it.polimi.ingsw.controller.server.task;

import it.polimi.ingsw.controller.server.GameController;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.enums.GameState;

import java.util.List;

public class TurnManager implements Runnable{
    private Game game;

    private Player lastPlayedPlayer;

    private int numOfPlayer;

    private List<Player> players;

    public TurnManager(Game game){
        this.game = game;
        lastPlayedPlayer = game.getCurrentPlayer();
        numOfPlayer = game.getNumOfPlayer();
        players = game.getPlayers();
    }

    @Override
    public void run() {
        if (game.getGameState() == GameState.TURN_MANAGE){

            for (int i = 0; i < numOfPlayer; i++) {
                if (players.get(i) == lastPlayedPlayer){
                    //check if is end of round
                    if (i == numOfPlayer - 1) {
                        //if game is in the last round
                        if (game.getIsLastRound()) {
                            game.setGameState(GameState.ENDING);
                        }else{
                            //check if the next round will be the last one
                            if (checkEndTrigger(players))
                                game.setIsLastRound();
                        }
                    }

                    if (game.getGameState() == GameState.ENDING)
                        GameController.getInstance().executeTask(new EndGame(game));
                    else{
                        //update who's the next to do turn
                        game.setGameState(GameState.PLAY_CARD);
                        updateTurn(i);
                    }
                    break;
                }
            }
        }
    }

    private void updateTurn(int j){
        if ( j < numOfPlayer-1){
            game.setCurrentPlayer(players.get(j+1));
        }else if (j == numOfPlayer-1){
            game.setCurrentPlayer(players.get(0));
        }
    }

    private boolean checkEndTrigger(List<Player> playerList){
        return ((playerList.stream()
                .parallel()
                .anyMatch(player -> player.getPoint() >= 20))
                || game.getDesk().isEmptyBothRGDeck());
    }


}
