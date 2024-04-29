package it.polimi.ingsw.modelView;

import it.polimi.ingsw.model.*;

import java.io.Serializable;
import java.util.List;

public class GameView implements Serializable {
    private Desk desk;

    private Integer numOfConnectedPlayers;

    private final List<PlayerView> players;

    private List<Integer> commonGoals;

    private final PlayerView currentPlayer;


    /**
     * control if game not exists yet, it creates one
     * @param game
     */
    public GameView(Game game) {
        if (game == null) {
            this.desk = null;
            this.players = null;
            this.currentPlayer = null;
            this.commonGoals = null;
            this.numOfConnectedPlayers = null;
        } else {
            this.desk = game.getDesk();
            this.players = new game.getPlayers();
            this.currentPlayer = game.getCurrentPlayer();
            this.numOfConnectedPlayers = game.getNumOfConnectedPlayers();
            this.commonGoals = new getCommonGoals();
        }

    }


    /**
     * @return the list of PlayerView
     */

    public List<PlayerView> getPlayers() {
        return players;
    }


    /**
     * @return the id's list of commonObjectiveCard
     */
    public List<Integer> getCommonGoals() {
        return commonGoals;
    }


    /**
     * @return PlayerView of the current player of the game
     */
    public PlayerView getCurrentPlayer() {
        return currentPlayer;
    }


    /**
     * @return number of connetced player
     */
    public int getNumOfConnectedPlayer() {
        return numOfConnectedPlayers;
    }

    public GameView(Desk desk) {
        if (desk != null)
            this.desk = desk.getDeskView;
        else {
            this.desk = null;
            this.players = null;
            this.currentPlayer = null;
            this.numOfConnectedPlayers = null;
            this.commonGoals = null;
        }
    }

    /**
     * @param players
     */
    public GameView(Player players){
        if (players != null){
            this.players = new PlayerView();
            ...
        }
        else{
            this.players = null;
            this.currentPlayer = null;
            this.numOfConnectedPlayers = null;
            this.commonGoals = null;
        }
    }



}










