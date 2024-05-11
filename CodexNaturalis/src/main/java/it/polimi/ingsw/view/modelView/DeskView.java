package it.polimi.ingsw.view.modelView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.polimi.ingsw.model.Desk;
import it.polimi.ingsw.model.enums.CardType;
import it.polimi.ingsw.model.exceptions.EmptyDeckException;

/**
 * Desk is a class that contains:
 *    4 deck of Card, one for each card type
 *    2 displayed card list,
 *    1 card that represent the first card of resource card deck
 *    1 card that represent the first card of gold card deck
 *    some methods to pick a card
 * Note: there are only card ids here, therefore only integers and not card objects
 * Status: almost complete
 * @author Valeria Lu
 * @version 2024-04-19
 * */

public class DeskView {
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
    public DeskView(){
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

        updateNextRCard();
        updateNextGCard();

        updateDisplayedRCard();
        updateDisplayedGCard();

    }


    /**
     * Pick one card id from the right deck
     * @param type the type of card that one wants to pick
     * @return a card id of the required type
     */
    public Integer pickOneCard(CardType type){
        Integer idCard = null;
        switch (type){
            case RESOURCE:
                idCard = pick(resourceCardDeck);
                break;
            case GOLD:
                idCard = pick(goldCardDeck);
                break;
            case INITIAL:
                idCard = pick(initialCardDeck);
                break;
            case OBJECTIVE:
                idCard = pick(objectiveCardDeck);
                break;
        }
        return idCard;
    }


    /**
     * Support the pickOneCard method, in order to avoid repetitive code
     * @param list the list of card id
     * @return a card id
     * @throws EmptyDeckException if the list is empty
     */
    private Integer pick(List<Integer> list) throws EmptyDeckException {
        if (list.isEmpty())
            throw new EmptyDeckException();
        Integer idCard = list.get(0);
        list.remove(0);
        return idCard;
    }


    /**
     * Fill the list of displayed resource card with resource card
     */
    public void updateDisplayedRCard(){
        while (displayedResourceCards.size() < 2){
            displayedResourceCards.add(pickNextRCard());
            updateNextRCard();
        }
    }


    /**
     * Fill the list of displayed gold card with gold card
     */
    public void updateDisplayedGCard(){
        while(displayedGoldCards.size() < 2){
            displayedGoldCards.add(pickNextGCard());
            updateNextGCard();
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
     * Pick one resource card from displayed resource card list
     * @param idCard the id of the card that one wants to pick
     * @return a resource card id
     */
    public Integer pickOneDisplayedRCard(int idCard){
        Integer c = null;
        for (int i = 0; i < 2; i++){
            if(idCard == displayedResourceCards.get(i)){
                c = displayedResourceCards.get(i);
                displayedResourceCards.remove(i);
                break;
            }
        }
        return c;
    }


    /**
     * Pick one gold card from displayed gold card list
     * @param idCard the id of the card that one wants to pick
     * @return a gold card id
     */
    public Integer pickOneDisplayedGCard(int idCard){
        Integer c = null;
        for (int i = 0; i < 2; i++){
            if( idCard == displayedGoldCards.get(i)){
                c = displayedGoldCards.get(i);
                displayedGoldCards.remove(i);
                break;
            }
        }
        return c;
    }


    /**
     * Update the first card of resource card deck (when it was used)
     */
    public void updateNextRCard(){
        nextResourceCard = pickOneCard(CardType.RESOURCE);
    }


    /**
     * Update the first card of gold card deck (when it was used)
     */
    public void updateNextGCard(){
        nextGoldCard = pickOneCard(CardType.GOLD);
    }


    /**
     * Pick the first card of resource card deck
     * @return a resource card id
     */
    public Integer pickNextRCard(){
        Integer c = nextResourceCard;
        nextResourceCard = null;
        return c;
    }


    /**
     * Pick the first card of gold card deck
     * @return a gold card id
     */
    public Integer pickNextGCard(){
        Integer c = nextGoldCard;
        nextGoldCard = null;
        return c;
    }

    public boolean isEmptyBothRGDeck(){
        return resourceCardDeck.isEmpty() && goldCardDeck.isEmpty();
    }


}



