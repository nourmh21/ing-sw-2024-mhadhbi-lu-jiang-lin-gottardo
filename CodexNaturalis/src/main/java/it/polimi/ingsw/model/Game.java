package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enums.Color;
import it.polimi.ingsw.observer.Observable;
import it.polimi.ingsw.observer.Observer;
import it.polimi.ingsw.model.enums.CardType;
import it.polimi.ingsw.model.exceptions.EmptyDeckException;
import it.polimi.ingsw.model.exceptions.InvalidNumOfConnectedPlayer;
import it.polimi.ingsw.model.enums.GameState;
import it.polimi.ingsw.model.immutable.ImmutableEndGameInfo;
import it.polimi.ingsw.model.immutable.ImmutableGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game extends Observable{
    private final int idGame;
    private GameState gameState;
    private int numOfPlayer;      //indicates the number of player in the game
    private List<Player> players;  //is the list of player
    private final List<Integer> commonGoals; //in contains the common goals for all players
    private Desk desk;
    private List<Player> winners;
    public List<Player> possibleWinners;
    private boolean isLastRound;
    private int numOfConnectedPlayers;   //num of player connect in the game
    private Player currentPlayer; //indicates the current player of the game
    public Player firstPlayer;
    private int round;


    /***
     * the class constructor
     */
    public Game(int numOfPlayer) {
        desk = new Desk(this);
        players = new ArrayList<>();
        winners = new ArrayList<>();
        commonGoals = new ArrayList<>();
        this.numOfPlayer = numOfPlayer;
        numOfConnectedPlayers = 0;
        Random random=new Random();                //genera codice alfanumerici (è presente un file dove memorizza...)
        idGame = random.nextInt(1000000);
        gameState = GameState.SETUP_PHASE_1;
        isLastRound =false;
        //
        notify_game_status(this);
    }





    public void setGameObservers(List<Observer> observers){
        this.observers = observers;
    }


    public List<Observer> getGameObservers(){
        return observers;
    }


    public void setCommonGoals(Integer idCard){
        if (commonGoals.size() < 2)
            commonGoals.add(idCard);
        //
        if (commonGoals.size() == 2)
            notify_game_status(this);
    }


    /*
    public void setInitialCard() throws EmptyDeckException{
        for (Player p:players) {
            p.setInitialCard(desk.pickOneCard(CardType.INITIAL));
        }
    }

     */


    /**
    *@return the unique code of game generated random
    * */
    public int getIdGame() {
        return idGame;
    }


    /**
     * @return gameState that indicates what state the game is in
     */
    public GameState getGameState() {
        return gameState;
    }


    /**
     *sets game state
     *@param gameState indicate the present state of the game
     */
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
        //
        notify_game_status(this);
    }


    /**
     * add the new player in the player list
     * //@param p indicates the new player in addition
     * @throws InvalidNumOfConnectedPlayer when only one player is connected
     */
    public void addPlayers(String nickname) {
        players.add(new Player(nickname, this));
        numOfConnectedPlayers++;

        if (players.size() == numOfPlayer){
            notify_game_status(this);
        }
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
        //
        notify_game_status(this);
    }


    /**
     * set isLastRound true
     */
    public void setIsLastRound() {
        isLastRound = true;
        //
        notify_game_status(this);
    }


    /**
     * @return if is last turn
     */
    public boolean getIsLastRound(){
        return isLastRound;
    }


    /**
     * find a player or some players whose have max point
     * @return the list of possible winner
     */
    public void checkMaxPoint(){
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
        if (possibleWinners.size() > 1)
            checkExtraPoint();
        //
        notify_final_result(this);
    }


    public int getNumOfPossibleWinner(){
        return possibleWinners.size();
    }


    /**
     * find final winner in the game
     * @return the final winners list
     */
    private void checkExtraPoint(){
        int maxExtraPoint=0;
        for (Player p:possibleWinners){
            if (p.getPoint() >= maxExtraPoint)
                maxExtraPoint = p.getPoint();
        }
        for (Player p:possibleWinners){
            if (p.getPoint() == maxExtraPoint)
                winners.add(p);
        }

    }


    public List<Player> getWinners(){
        return winners;
    }


    /**
     * @return the common Goal Cards id
     */
    public List<Integer> getCommonGoals() {
        return commonGoals;
    }


    /**
     * @return the first player of the game
     */
    public Player getFirstPlayer(){
        return firstPlayer;
    }


    public void setFirstPlayer(Player player){
        firstPlayer = player;
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


    public Desk getDesk() {
        return desk;
    }


    public void updateRound(){
        round++;
    }


    /**
     * @return the color get by random
     */
    public Color randomColor(){
        Random random = new Random();
        return Color.values()[random.nextInt(Color.values().length)];
    }


}
