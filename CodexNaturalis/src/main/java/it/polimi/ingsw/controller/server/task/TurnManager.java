package it.polimi.ingsw.controller.server.task;

import it.polimi.ingsw.controller.server.GameController;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.enums.GameState;

import java.util.List;

/**
 * The TurnManager is one of Runnable executed for the progression of a game, called to execute by {@link DrawCard}
 * It manages the change of turn and checks if end game condition is fulfilled
 */
public class TurnManager implements Runnable{
    private Game game;

    private Player lastPlayedPlayer;

    private int numOfPlayer;

    private List<Player> players;

    /**
     * Constructor
     * @param game the {@link Game} to be updated
     */
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


    /**
     * Updates the turn
     * @param j index of the last player
     */
    private void updateTurn(int j){
        if ( j < numOfPlayer-1){
            game.setCurrentPlayer(players.get(j+1));
        }else if (j == numOfPlayer-1){
            game.setCurrentPlayer(players.get(0));
        }
    }


    /**
     * @param playerList list of all players
     * @return true if one player has point >= 20 or if both gold and resource decks are empty, false otherwise
     */
    private boolean checkEndTrigger(List<Player> playerList){
        return ((playerList.stream()
                .parallel()
                .anyMatch(player -> player.getPoint() >= 20))
                || game.getDesk().isEmptyBothRGDeck());
    }


}
