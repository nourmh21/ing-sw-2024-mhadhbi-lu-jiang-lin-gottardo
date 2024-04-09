package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enums.CardType;
import it.polimi.ingsw.model.exceptions.InvalidNumOfPlayerException;
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
    private List<Integer> commonGoals; //in contains the common goals for players
    private Desk desk;
    private boolean isLastTurn; //
    private String winner; //it contains the winner of the game
    //private Chat chat; //is a chat of the game
    private int connectedPlayers;   //num of player connected



    public Game(){

        desk = new Desk();
        players = new ArrayList<>();
        commonGoals = new ArrayList<>();

        commonGoals.add(desk.pickOneCard(CardType.GOAL));
        commonGoals.add(desk.pickOneCard(CardType.GOAL));

        //chat = new Chat();

        Random random=new Random();
        idGame = random.nextInt(1000);

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
        if ((numOfPlayer>1)){
            gameState=State.IN_PROGRESS;
        }
        if ((numOfPlayer==1)){
            gameState=State.WAITING;
            //TIMER

            //set unique player as winner after timer
            for (int i=0; i<numOfPlayer; i++) {
                if (players.get(i).isConnected()){
                    winner = players.get(i).getNickName();
                }
            }
            gameState = State.FINISHED;

            // se durante il conteggio del timer rientra un giocatore come faccio a implementarla

        }

    }

    /**
     * @param p indicates the new player in addition*/
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

    public void disconnect(Player p) {

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
      * @param player defines the next current player */
    public void setCurrentPlayer(Player player) {
            this.currentPlayer = player;
    }

    /**
     * @return the game's chat
     * */

    /*public Chat getChat(){

        return chat;
    }*/

    /**check if it's the last turn*/
    public void setIsLastTurn() {
        if (is20()){
            isLastTurn=true;
        }
    }

    public boolean getIsLastTurn(){
        return isLastTurn;
    }

    /**
     * control if one of player has 20 points
     *
     * @return p player that have >=20 points
     */
    public boolean is20(){

        for (int i=0; i<numOfPlayer; i++){
            if (((players.get(i)).getPoint()>=20)){
                break;
            }
        }

        return true;
    }

    /**check max points in the game*/
    public String checkMax(Player p){
        int i=0;
        int max=-1;
        if (isLastTurn) {

            for (i = 0; i < numOfPlayer; i++) {
                if ((p.getPoint() > max)) {
                    max = p.getPoint();
                }
            }
        }
        winner = p.getNickName();
        gameState=State.FINISHED;
        return winner;

    }



    /**
     *return the common Goal Cards of the current game*/
    public List<Integer> getCommonGoals() {
        return commonGoals;
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
