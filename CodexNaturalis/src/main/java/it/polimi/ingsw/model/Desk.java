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
 *    some methods to pick card, to update the card list after picking (in general it will be automatic)
 * @author Valeria Lu
 * @version 2024-04-01-13:10
 * Status: need to be complete and refine
 * */

public class Desk {
    private List<Integer> resourceCardDeck;          //list of resource cards - resource card deck
    private List<Integer> goldCardDeck;              //list of gold cards - gold card deck
    private List<Integer> initialCardDeck;           //list of initial cards - initial card deck
    private List<Integer> goalCardDeck;              //list of goal cards - goal card deck
    private List<Integer> displayedResourceCards; //list that will contain two face up resource card
    private List<Integer> displayedGoldCards;     //list that will contain two face up gold card

    private Integer nextResourceCard;            //the first card of resource card deck
    private Integer nextGoldCard;                //the first card of gold card deck


    /*
    Questions:
       Java Code:


       UML:

       ------
       Need to ask in slack: if one deck finished their Card, should I pick from another deck?
    */


    //the class constructor
    public Desk(){
        resourceCardDeck = new ArrayList<>(40);
        goldCardDeck = new ArrayList<>(40);
        initialCardDeck = new ArrayList<>(6);
        goalCardDeck = new ArrayList<>(16);
        displayedResourceCards = new ArrayList<>(2);
        displayedGoldCards = new ArrayList<>(2);

        for (int i = 1; i <= 40; i++){
            resourceCardDeck.add(i);
        }
        for (int i = 41; i <= 80; i++){
            goldCardDeck.add(i);
        }
        for (int i = 81; i <= 86; i++){
            initialCardDeck.add(i);
        }
        for (int i = 87; i <= 102; i++){
            goalCardDeck.add(i);
        }


        Collections.shuffle(resourceCardDeck);
        Collections.shuffle(goldCardDeck);
        Collections.shuffle(initialCardDeck);
        Collections.shuffle(goalCardDeck);

        updateDisplayedRCard();
        updateDisplayedGCard();

        updateNextRCard();
        updateNextGCard();
    }



    //method that picks one card from the right deck according the type passed as parameter
    public Integer pickOneCard(CardType type){
        Integer c = null;
        switch (type){
            case RESOURCE:
                if (!resourceCardDeck.isEmpty()){
                    c = resourceCardDeck.get(0);
                    resourceCardDeck.remove(0);
                }
                break;
            case GOLD:
                if (!goalCardDeck.isEmpty()){
                    c = goldCardDeck.get(0);
                    goalCardDeck.remove(0);
                }
                break;
            case INITIAL:
                c = initialCardDeck.get(0);
                initialCardDeck.remove(0);
                break;
            case GOAL:
                c = goalCardDeck.get(0);
                goalCardDeck.remove(0);
                break;
        }
        return c;
    }


    //method that fills the list of face up resource cards with resource cards
    //       if the list size is less than 2 and resource card deck is not empty
    public void updateDisplayedRCard(){
        while (displayedResourceCards.size() < 2 && !resourceCardDeck.isEmpty()){
            displayedResourceCards.add(pickOneCard(CardType.RESOURCE));
        }
    }


    //method that fills the list of face up gold cards with gold card
    //       if the list size is less than 2 and gold card deck is not empty
    public void updateDisplayedGCard(){
        while(displayedGoldCards.size() < 2 && !goalCardDeck.isEmpty()){
            displayedGoldCards.add(pickOneCard(CardType.GOLD));
        }
    }


    //method that returns the current face up resource cards
    public List<Integer> getDisplayedRCard(){
        return displayedResourceCards;
    }


    //method that returns the current face up gold cards
    public List<Integer> getDisplayedGCard(){
        return displayedGoldCards;
    }


    //method that picks one resource card from face up cards according the index of position i, with automatic refill
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


    //method that picks one gold card from face up cards according the index of position i, with automatic refill
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


    //method that updates the first card of resource card deck, if it was used
    public void updateNextRCard(){
        if (!resourceCardDeck.isEmpty()){
            nextResourceCard = pickOneCard(CardType.RESOURCE);
        }else{
            nextResourceCard = null;
        }
    }


    //method that updates the first card of gold card deck, if it was used
    public void updateNextGCard(){
        if (!goalCardDeck.isEmpty()){
            nextGoldCard = pickOneCard(CardType.GOLD);
        }else{
            nextGoldCard = null;
        }

    }


    //method that picks the first card of resource card deck, with automatic refill
    public Integer pickNextRCard(){
        Integer c = nextResourceCard;
        updateNextRCard();
        return c;
    }


    //method that picks the first card of gold card deck, with automatic refill
    public Integer pickNextGCard(){
        Integer c = nextGoldCard;
        updateNextGCard();
        return c;
    }

    //method that check if one deck is empty or not
    public boolean isOneDeckEmpty(CardType type){
        switch (type){
            case RESOURCE:
                if (resourceCardDeck.isEmpty()){
                    return true;
                }else{
                    return false;
                }
            case GOLD:
                if (goldCardDeck.isEmpty()){
                    return true;
                }else{
                    return false;
                }
        }
        return false;
    }

}

// I haven't checked yet if there are any exceptional cases


