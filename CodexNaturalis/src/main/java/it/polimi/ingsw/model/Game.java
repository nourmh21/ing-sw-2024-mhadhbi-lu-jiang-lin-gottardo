package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enums.CardType;
import it.polimi.ingsw.model.exceptions.InvalidNumOfConnectedPlayer;
import it.polimi.ingsw.model.enums.State;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private final int idGame;
    private State gameState;
    private int numOfPlayer;  //indicates the number of player in the game
    private List<Player> players; //is the list of player
    private Player currentPlayer; //indicates the current player of the game
    private final List<Integer> commonGoals; //in contains the common goals for all players
    private Desk desk;
    private List<Player> winners;
    public List<Player> possibleWinners;
    private boolean isLastTurn;
    private int numOfConnectedPlayers;   //num of player connect in the game
    public String firstPlayer;


    /***
     * the class constructor
     */
    public Game(int numOfPlayer){

        desk = new Desk();
        players = new ArrayList<>();
        winners = new ArrayList<>();
        commonGoals = new ArrayList<>();

        commonGoals.add(desk.pickOneCard(CardType.OBJECTIVE));
        commonGoals.add(desk.pickOneCard(CardType.OBJECTIVE));

        this.numOfPlayer = numOfPlayer;
        numOfConnectedPlayers = 0;

        Random random=new Random();            //genera codice alfanumerici (è presente un file dove memorizza...)
        idGame = random.nextInt(1000000);

        gameState = State.STARTING;
        isLastTurn=false;
        firstPlayer="";

    }


    /**
    *@return the unique code of game generated random
    * */
    public int getIdGame() {
        return idGame;
    }


    /**
     * @return gameState that indicates what state the game is in
     */
    public State getGameState() {
        return gameState;
    }


    /**
     *sets game state
     *@param gameState indicate the present state of the game
     */
    public void setGameState(State gameState) {
       this.gameState=gameState;

    }


    /**
     * add the new player in the player list
     * @param p indicates the new player in addition
     * @throws InvalidNumOfConnectedPlayer when only one player is connected
     */
    public void addPlayers(Player p) {
        players.add(p);
        numOfPlayer++;
        numOfConnectedPlayers++;

    }


    /**
     * @return player's list of the game
     */
    public List<Player> getPlayers() {
        return players;
    }


    /**
     * @return the size of player list
     */
    public int getPlayersSize(){
        return players.size();
    }


    /**
     * @param p indicated the player disconnect
     * @throws InvalidNumOfConnectedPlayer when in the game remains one player
     */
    public void disconnect(Player p) throws InvalidNumOfConnectedPlayer {
        p.setDisconnected();
        numOfConnectedPlayers -= 1;
        if (numOfConnectedPlayers == 1)
            throw new InvalidNumOfConnectedPlayer();
    }


    /**
     * @param p indicates the player who reconnects
     */
    public void reconnect(Player p){
        p.setConnected();
        numOfConnectedPlayers += 1;

    }


    /**
     * @return the current player
     * */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }


     /**
      * @param p defines the next current player
      */
    public void setCurrentPlayer(Player p) {
            this.currentPlayer = p;
    }


    /**
     * set isLastTurn true
     */
    public void setIsLastTurn() {
            isLastTurn=true;
    }


    /**
     * @return if is last turn
     */
    public boolean getIsLastTurn(){
        return isLastTurn;
    }


    /**
     * find a player or some players whose have max point
     * @return the list of possible winner
     */
    public List<Player> checkMaxPoint(){
        int max = 20;

        for (Player p:players){
            if ((p.getPoint() >= max) && (p.isConnected()))
                max = p.getPoint();
        }

        for (Player p:players){
            if ((p.getPoint() == max) && (p.isConnected())){
                possibleWinners.add(p);
            }
        }
        return possibleWinners;

    }


    /**
     * find final winner in the game
     * @return the final winners list
     */
    public List<Player> checkExtraPoint(){
        int maxExtraPoint=0;

        for (Player p:possibleWinners){
            if (p.getPoint() >= maxExtraPoint)
                maxExtraPoint = p.getPoint();
        }

        for (Player p:possibleWinners){
            if (p.getPoint() == maxExtraPoint)
                winners.add(p);

        }
        return winners;
    };


    /**
     * @return the common Goal Cards id
     */
    public List<Integer> getCommonGoals() {
        return commonGoals;
    }


    /**
     * @return the first player of the game
     */
    public String getFirstPlayer(){
        return firstPlayer;
    }


    public void setFirstPlayer(String name){
        this.firstPlayer=name;

    }


    /**
     * @return the number of players that is connected
     */
    public int getNumOfConnectedPlayers(){
        return numOfConnectedPlayers;
    }


    /**
     * @return the number of player in the game
     */
    public int getNumOfPlayer(){
        return numOfPlayer;
    }



}
