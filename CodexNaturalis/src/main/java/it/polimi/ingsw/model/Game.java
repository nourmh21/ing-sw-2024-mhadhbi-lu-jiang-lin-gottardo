package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enums.CardType;
import it.polimi.ingsw.model.exceptions.TooFewPlayersException;
import it.polimi.ingsw.model.enums.State;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private final int idGame;
    private State gameState;
    private int numOfPlayer; //indicates the number of player in the game
    private final List<Player> players; //is the list of player
    private Player currentPlayer; //indicates the current player of the game
    private final List<Integer> commonGoals; //in contains the common goals for all players
    private Desk desk;
    private String winner;
    private boolean isLastTurn;
    //private Chat chat;
    private int numOfConnectedPlayers;   //num of player connect in the game


    /***
     * the class constructor
     */
    public Game(){

        desk = new Desk();
        players = new ArrayList<>();
        commonGoals = new ArrayList<>();

        commonGoals.add(desk.pickOneCard(CardType.OBJECTIVE));
        commonGoals.add(desk.pickOneCard(CardType.OBJECTIVE));

        //chat = new Chat();

        Random random=new Random();            //genera codice alfanumerici (è presente un file dove memorizza...)
        idGame = random.nextInt(1000);

        gameState = State.STARTING;
        isLastTurn=false;
        winner="";

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
     * @throws  TooFewPlayersException when only one player is connected
     */
    public void addPlayers(Player p) {
        if (numOfPlayer<4){
            players.add(p);
            numOfPlayer++;
        }
        else {
            throw new TooFewPlayersException();
        }

    }


    /**
     * @return player's list of the game
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * @param p indicated the player disconnect
     * @throws TooFewPlayersException when in the game remains one player
     */
    public void disconnect(Player p) throws TooFewPlayersException {
        p.setDisconnected();
        numOfConnectedPlayers -= 1;
        if (numOfConnectedPlayers == 1)
            throw new TooFewPlayersException();
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
      * @param player defines the next current player
      */
    public void setCurrentPlayer(Player player) {
            this.currentPlayer = player;
    }

    /**
     * @return the game's chat
     */
    /*public Chat getChat(){

        return chat;
    }*/

    /**
     * set isLastTurn true when is20 is true
     */
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

    /**
     * check max points in the game
     * @return winner indicates the player who has the most points
     */
    public String checkMaxPoint(){
        int max = 20;
        for (Player p:players) {
            if ((p.getPoint() >= max)) {
                max = p.getPoint();
                winner = p.getNickName();
            }
        }
        return winner;

    }


    /**
     * return the common Goal Cards of the current game
     * */
    public List<Integer> getCommonGoals() {
        return commonGoals;
    }



}
