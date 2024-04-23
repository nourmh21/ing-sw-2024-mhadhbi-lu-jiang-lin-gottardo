package it.polimi.ingsw.model;
import it.polimi.ingsw.model.enums.Color;
import it.polimi.ingsw.model.exceptions.InvalidNumOfHandCardsException;

import java.util.ArrayList;
import java.util.List;

/**
 * Player is a class that contains information of each player
 * -nickName: player's name
 * -position: player's turn during game
 * -point: points acquired during game
 * -goalPoint: points acquired from objective cards
 * -playerColor: player's color
 * -personalGoal: objective that each player has to realize in order to acquire points at the end of game
 * -isConnected: verify if the player is connected
 * -handCards define list of card in hand of player.
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
    private List<Integer> handCards;

    public Player(String nickName, int position, Color color){
        this.nickName = nickName;
        this.position = position;
        handCards = new ArrayList<>();
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


    /**
     * @return list of Hand cards
     */
    public List<Integer> getHandCards() {
        return handCards;
    }


    /**
     * add card to hand
     * @param idCard id of the card
     * @throws InvalidNumOfHandCardsException when the player already has three cards in the hand
     */
    public void addCardToHandCards(int idCard) throws InvalidNumOfHandCardsException {
        if (handCards.size() == 3) {
            throw new InvalidNumOfHandCardsException();
        }
        try {
            handCards.add(idCard);
        }catch (NumberFormatException e){}
    }

    /**
     * @return player's board
     */
    public  PlayerBoard getBoard(){
        return board;
    }
}
