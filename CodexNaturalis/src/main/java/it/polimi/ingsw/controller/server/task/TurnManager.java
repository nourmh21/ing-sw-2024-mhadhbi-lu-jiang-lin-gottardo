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

            //to be fixed because more probably we don't do resilience to connection
            for (int i = 0; i < numOfPlayer; i++) {
                if (players.get(i) == lastPlayedPlayer){
                    //记得告诉做view的人让他们如果收到points更新到20让他们标出来(TUI)or改个颜色(GUI)
                    //我这边只有一个倒数最后一个round结束了才会去改成现为最后一个round
                    //check if is end of round
                    if (i == numOfPlayer - 1 ||
                            ((numOfPlayer >= 3) && (i == numOfPlayer - 2) && (!players.get(i + 1).isConnected())) ||
                            ((numOfPlayer == 4) && (i == numOfPlayer - 3) && (!players.get(i + 1).isConnected())
                                    && (!players.get(i + 2).isConnected()))
                    ) {
                        //if game is in the last round
                        if (game.getIsLastRound()) {
                            game.setGameState(GameState.ENDING);
                        }else{
                            //check if the next round will be the last one
                            if (checkEndTrigger(players))
                                game.setIsLastRound();
                            //game.updateRound();
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
        Player last = game.getCurrentPlayer();
        while (last == game.getCurrentPlayer()){
            if ( j < numOfPlayer-1){
                if (players.get(j++).isConnected()){
                    game.setCurrentPlayer(players.get(j++));
                }else {
                    j++;
                }
            }else if (j == numOfPlayer-1){
                j = 0;
                if (players.get(j).isConnected()){
                    game.setCurrentPlayer(players.get(j));
                }else {
                    j++;
                }
            }

        }
    }


    private boolean checkEndTrigger(List<Player> playerList){
        return ((playerList.stream()
                .parallel()
                .anyMatch(player -> player.getPoint() >= 20))
                || game.getDesk().isEmptyBothRGDeck());
    }


}
