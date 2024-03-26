package it.polimi.ingsw.model;

import it.polimi.ingsw.Exception.InvalidNumOfPlayerException;
import it.polimi.ingsw.Exception.NotInProgressException;
import it.polimi.ingsw.model.enums.State;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private int idGame;
    private State gameState;  //indicates the status of the game
    private int numOfPlayer; //indicates the number of player in the game
    private List<Player> players; //is the list of player
    private Player currentPlayer; //indicates the current player of the game
    private List<GoalCard> commonGoals; //in contains the common goals for players
    private Desk desk;
    private boolean isLastTurn; //
    private String winner; //it contains the winner of the game
    private Chat chat; //is a chat of the game



    public Game(){
        players = new List<>();
        commonGoals = new List<>();

        desk = new Desk();
        chat = new Chat();

        Random random=new Random();
        idGame=random.nextInt(500);

        gameState = State.STARTING;
        isLastTurn=false;
        winner="";
    }

   /**
    *@return the unique code of game*/
    public int getIdGame() {

        return idGame;
    }

    /**
     * @return gameState that indicates what state the game is in
     * */
    public State getGameState() {

        return gameState;
    }

    /**
     *sets game state
     *@param gameState indicate the state of the game*/
    public void setGameState(State gameState) {
        if (gameState.equals(State.IN_PROGRESS) && (numOfPlayer==1)){
            throw new NotInProgressException();
        }
        else {
            this.gameState=gameState;
        }

    }

    /**
     * @param player indicates the new player in addition*/
    public void addPlayers(Player p) {
        if (numOfPlayer<4){
            players.add(p);
            numOfPlayer++;
        }
        else {
            throw new InvalidNumOfPlayerException();
        }

    }


    /**
     * @return player's list of the game
     * */
    public List<Player> getPlayers() {

        return players;
    }

    public void disconnect() {

    }

    public void reconnected(Player p){


    }

    /**
     * @return the current player
     * */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

     /**
      * @param Player defines the next current player */
    public void setCurrentPlayer(Player) {
        this.currentPlayer = Player;

    }

    /**
     * @return the game's chat
     * */
    public Chat getChat(){

        return chat;
    }

    /**check if it's the last turn*/
    public void isLastTurn() {
        if ((!isLastTurn)){

        }
    }

    /** throw an exception when the numOfPlayer is 1*/
    public Game(int numOfPlayer) throws InvalidNumOfPlayerException{
        if (numOfPlayer ==1) {
            throw new InvalidNumOfPlayerException();
        }
        desk = null;
        this.numOfPlayer=numOfPlayer;
        gameState = State.FINISHED;
        winner = "";
    }



}
