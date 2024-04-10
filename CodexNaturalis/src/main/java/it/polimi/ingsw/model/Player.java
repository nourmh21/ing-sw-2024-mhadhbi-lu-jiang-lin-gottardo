package it.polimi.ingsw.model;
import it.polimi.ingsw.model.enums.Color;

/**
 * Player is a class that contains information of each player
 * -nickName: player's name
 * -position: player's turn during game
 * -point: points acquired during game
 * -playerColor: player's color
 * -personalGoal: objective that each player has to realize in order to acquire points at the end of game
 * -isConnected: verify if the player is connected
 */
public class Player{
    private String nickName;
    private int position;
    private int point;
    private Color playerColor;
    private Integer personalGoal;
    private boolean isConnected;
    private PlayerBoard board;

    public Player(String nickName, int position, Color color){
        this.nickName = nickName;
        this.position = position;
        point = 0;
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
     * Update player's point
     * @param newPoint the new points to be added
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
     * @return true if the player is connected, false otherwise
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
