package it.polimi.ingsw.model;
import it.polimi.ingsw.model.enums.Color;

/**
 * Player is a class that contains information of each player
 * -nickName: player's name
 * -position: player's turn during game
 * -point: points acquired during game
 * -goalPoint: points acquired from objective cards
 * -playerColor: player's color
 * -personalGoal: objective that each player has to realize in order to acquire points at the end of game
 * -isConnected: verify if the player is connected
 */
public class Player{
    private String nickName;
    private int position;
    private int point;
    private int goalPoint;
    private Color playerColor;
    private Integer personalGoal;
    private boolean isConnected;
    private PlayerBoard board;

    public Player(String nickName, int position, Color color){
        this.nickName = nickName;
        this.position = position;
        point = 0;
        goalPoint = 0;
        personalGoal = null;
        playerColor = color;
        isConnected = true;
        board = new PlayerBoard();
    }


    /**
     * @return player's Nickname
     */
    public String getNickName() {
        return nickName;
    }


    /**
     * @return player's turn
     */
    public int getPosition(){
        return position;
    }


    /**
     * @return player's game point
     */
    public int getPoint(){
        return point;
    }

    /**
     * @return player's goalPoint
     */
    public int getGoalPoint() {
        return goalPoint;
    }

    /**
     * update player's goalPoint
     * @param goalPoint player's goalPoint
     */
    public void addGoalPoint(int goalPoint) {
        this.goalPoint += goalPoint;
    }

    /** set player's point
     * @param point player's point
     */
    public void setPoint(int point) {
        this.point = point;
    }

    /**
     * Update player's point
     * @param newPoint new points to be added
     */
    public void updatePoint(int newPoint){
        point = point + newPoint;
    }


    /**
     * @return player's color
     */
    public Color getPlayerColor() {
        return playerColor;
    }


    /**
     * @return player's personal goal card id
     */
    public Integer getPersonalGoal() {
        return personalGoal;
    }


    /**
     * @return true if player's connected, otherwise false
     */
    public boolean isConnected() {

        return isConnected;

    }


    /**
     * Set player disconnected from the game
     */
    public void setDisconnected(){
        isConnected = false;
    }


    /**
     * Set player connected to the game
     */
    public void setConnected(){
        isConnected = true;
    }
}
