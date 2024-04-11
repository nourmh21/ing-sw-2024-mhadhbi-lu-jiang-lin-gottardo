package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import it.polimi.ingsw.model.enums.CardType;

/**
 * Desk is a class that contains:
 *    4 deck of Card, one for each card type
 *    2 displayed card list,
 *    1 card that represent the first card of resource card deck
 *    1 card that represent the first card of gold card deck
 *    some methods to pick card, to update the card list after picking (it will be automatic)
 * Note: there are only card ids here, therefore only integers and not card objects
 * Status: almost complete
 * @author Valeria Lu
 * @version 2024-04-10-20:00
 * */

public class Desk {
    private List<Integer> resourceCardDeck;          //list of resource cards - resource card deck
    private List<Integer> goldCardDeck;              //list of gold cards - gold card deck
    private List<Integer> initialCardDeck;           //list of initial cards - initial card deck
    private List<Integer> objectiveCardDeck;         //list of goal cards - goal card deck
    private List<Integer> displayedResourceCards;    //list that will contain two face up resource card
    private List<Integer> displayedGoldCards;        //list that will contain two face up gold card
    private Integer nextResourceCard;                //the first card of resource card deck
    private Integer nextGoldCard;                    //the first card of gold card deck


    /**
     * The class constructor
     */
    public Desk(){
        resourceCardDeck = new ArrayList<>(40);
        goldCardDeck = new ArrayList<>(40);
        initialCardDeck = new ArrayList<>(6);
        objectiveCardDeck = new ArrayList<>(16);
        displayedResourceCards = new ArrayList<>(2);
        displayedGoldCards = new ArrayList<>(2);

        for (int i = 1; i <= 40; i++){
            resourceCardDeck.add(i);
        }
        for (int i = 41; i <= 80; i++){
            goldCardDeck.add(i);
        }
        for (int i = 81; i <= 96; i++){
            objectiveCardDeck.add(i);
        }
        for (int i = 97; i <= 102; i++){
            initialCardDeck.add(i);
        }

        Collections.shuffle(resourceCardDeck);
        Collections.shuffle(goldCardDeck);
        Collections.shuffle(initialCardDeck);
        Collections.shuffle(objectiveCardDeck);

        updateDisplayedRCard();
        updateDisplayedGCard();

        updateNextRCard();
        updateNextGCard();
    }


    /**
     * Pick one card id from the right deck
     * @param type the type of card that one wants to pick
     * @return a card id of the required type
     * @throws NullPointerException in case that the required card deck is empty
     */
    public Integer pickOneCard(CardType type) throws NullPointerException{
        Integer idCard = null;
        switch (type){
            case RESOURCE:
                if (!resourceCardDeck.isEmpty()) {
                    idCard = resourceCardDeck.get(0);
                    resourceCardDeck.remove(0);
                }else{
                    throw new NullPointerException();
                }
                break;
            case GOLD:
                if (!goldCardDeck.isEmpty()){
                    idCard = goldCardDeck.get(0);
                    goldCardDeck.remove(0);
                }else{
                    throw new NullPointerException();
                }
                break;
            case INITIAL:
                if(!initialCardDeck.isEmpty()){
                    idCard = initialCardDeck.get(0);
                    initialCardDeck.remove(0);
                }else{
                    throw new NullPointerException();
                }
                break;
            case OBJECTIVE:
                if (!objectiveCardDeck.isEmpty()){
                    idCard = objectiveCardDeck.get(0);
                    objectiveCardDeck.remove(0);
                }else{
                    throw new NullPointerException();
                }
                break;
        }
        return idCard;
    }


    /**
     * Fill the list of displayed resource card with resource card
     */
    private void updateDisplayedRCard(){
        while (displayedResourceCards.size() < 2 && !resourceCardDeck.isEmpty()){
            displayedResourceCards.add(pickOneCard(CardType.RESOURCE));
        }
    }


    /**
     * Fill the list of displayed gold card with gold card
     */
    private void updateDisplayedGCard(){
        while(displayedGoldCards.size() < 2 && !goldCardDeck.isEmpty()){
            displayedGoldCards.add(pickOneCard(CardType.GOLD));
        }
    }


    /**
     * @return the current list of displayed resource card id
     */
    public List<Integer> getDisplayedRCards(){
        return displayedResourceCards;
    }


    /**
     * @return the current list of displayed gold card id
     */
    public List<Integer> getDisplayedGCards(){
        return displayedGoldCards;
    }


    /**
     * Pick one resource card from displayed resource card list, with automatic refill after picking
     * @param i the position of the card in the arraylist
     * @return a resource card id
     */
    public Integer pickOneDisplayedRCard(int i){
        Integer c = null;
        if( i == 0){
            c = displayedResourceCards.get(i);
            displayedResourceCards.remove(i);
        }else if( i == 1 ){
            c = displayedResourceCards.get(i);
            displayedResourceCards.remove(i);
        }
        updateDisplayedRCard();
        return c;
    }


    /**
     * Pick one gold card from displayed gold card list, with automatic refill after picking
     * @param i the position of the card in the arraylist
     * @return a gold card id
     */
    public Integer pickOneDisplayedGCard(int i){
        Integer c = null;
        if( i == 0){
            c = displayedGoldCards.get(i);
            displayedGoldCards.remove(i);
        }else if( i == 1 ){
            c = displayedResourceCards.get(i);
            displayedGoldCards.remove(i);
        }
        updateDisplayedGCard();
        return c;
    }


    /**
     * Update the first card of resource card deck (when it was used)
     */
    private void updateNextRCard(){
        nextResourceCard = pickOneCard(CardType.RESOURCE);
    }


    /**
     * Update the first card of gold card deck (when it was used)
     */
    private void updateNextGCard(){
        nextGoldCard = pickOneCard(CardType.GOLD);
    }


    /**
     * Pick the first card of resource card deck, with automatic refill
     * @return a resource card id
     */
    public Integer pickNextRCard(){
        Integer c = nextResourceCard;
        updateNextRCard();
        return c;
    }


    /**
     * Pick the first card of gold card deck, with automatic refill
     * @return a gold card id
     */
    public Integer pickNextGCard(){
        Integer c = nextGoldCard;
        updateNextGCard();
        return c;
    }

}



