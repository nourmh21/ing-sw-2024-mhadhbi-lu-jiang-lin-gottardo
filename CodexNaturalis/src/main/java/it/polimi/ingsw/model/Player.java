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

    public String getNickName() {
        return nickName;
    }

    //method that returns player's turn
    public int getPosition(){
        return position;
    }

    //method that returns player's point
    public int getPoint(){
        return point;
    }

    //method that update player's point
    public void updatePoint(int newPoint){
        point = point + newPoint;
    }

    //method that returns player's color
    public Color getPlayerColor() {
        return playerColor;
    }

    //method that returns player's personal goal
    public Integer getPersonalGoal() {
        return personalGoal;
    }

    //method that return the connection of player
    public boolean isConnected() {

        return isConnected;

    }

    //method that disconnect player from game
    public void setDisconnected(){
        isConnected = false;
    }

    //method that reconnect player from game
    public void setConnected(){
        isConnected = true;
    }
}
