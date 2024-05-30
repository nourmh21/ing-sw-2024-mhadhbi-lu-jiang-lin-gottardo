package it.polimi.ingsw.model;
import it.polimi.ingsw.model.enums.CardType;
import it.polimi.ingsw.model.exceptions.EmptyDeckException;
import it.polimi.ingsw.model.immutable.ImmutablePlayer;
import it.polimi.ingsw.observer.Observable;
import it.polimi.ingsw.model.enums.Color;
import it.polimi.ingsw.model.exceptions.InvalidNumOfHandCardsException;

import java.util.ArrayList;
import java.util.List;

/**
 * Player is a class that contains information of each player
 * -nickname: player's name
 * -position: player's turn during game
 * -point: points acquired during game
 * -goalPoint: points acquired from objective cards
 * -playerColor: player's color
 * -personalGoal: objective that each player has to realize in order to acquire points at the end of game
 * -isConnected: verify if the player is connected
 * -handCards define list of card in hand of player.
 */
public class Player extends Observable {
    private final String nickname;
    private int point;
    private int goalPoint;
    private Color playerColor;
    private Integer personalGoal;

    private Integer[] initialPossibleGoals;

    private boolean isConnected;

    private boolean isPersonalGoalChosen;
    private PlayerBoard board;
    private List<Integer> handCards;

    private Integer initialCard;


    public Player(String nickname, Game game, Color color){
        this.nickname = nickname;
        isPersonalGoalChosen = false;
        handCards = new ArrayList<>();
        point = 0;
        goalPoint = 0;
        personalGoal = null;
        playerColor = color;
        isConnected = true;
        board = new PlayerBoard();
        initialPossibleGoals = new Integer[2];
        try {
            initialCard = game.getDesk().pickOneCard(CardType.INITIAL);
        } catch (EmptyDeckException e) {
            throw new RuntimeException(e);
        }
        observers = game.getGameObservers();
        //
        notify_player_status(new ImmutablePlayer(this,this.board));
    }


    public Integer getInitialCard(){
        return initialCard;
    }


    public void setPersonalGoal(Integer idCard){
        personalGoal = idCard;
        setPersonalGoalChosen();
    }

    public void setPersonalGoalChosen() {
        isPersonalGoalChosen = true;
        notify_player_status(new ImmutablePlayer(this, this.getBoard()));
    }


    public boolean isPersonalGoalChosen() {
        return isPersonalGoalChosen;
    }

    public void setInitialPossibleGoals(Integer[] goals){
        initialPossibleGoals = goals;
        //
        notify_two_personal_goals(this);
    }


    public Integer[] getInitialPossibleGoals(){
        return initialPossibleGoals;
    }


    /**
     * @return player's Nickname
     */
    public String getNickname() {
        return nickname;
    }



    /**
     * @return player's game point
     */
    public int getPoint(){
        return point;
    }



    /**
     * Update player's point
     * @param newPoint new points to be added
     */
    public void updatePoint(int newPoint){
        point = point + newPoint;
        //
        notify_player_status(new ImmutablePlayer(this,this.board));
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
        updatePoint(goalPoint);
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
    public void addCardToHandCards(Integer idCard){
        handCards.add(idCard);
        //
        if (handCards.size() == 3){
            notify_hand_cards(this);
            notify_player_status(new ImmutablePlayer(this,this.board));
        }

    }


    public void removeHandCard(Integer idCard){
        handCards.remove(idCard);
        //
        notify_hand_cards(this);
        notify_player_status(new ImmutablePlayer(this,this.board));
    }


    public PlayerBoard getBoard() {
        return board;
    }


}
