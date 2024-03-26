package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import it.polimi.ingsw.model.enums.Symbol;
import it.polimi.ingsw.model.enums.CardType;

/**
 * Desk is a class that contains:
 *    4 deck of Card, one for each card type
 *    2 displayed card list,
 *    1 card that represent the first card of resource card deck
 *    1 card that represent the first card of gold card deck
 *    some methods to pick card, to update the card list after picking (in general it will be automatic)
 * @author Valeria Lu
 * @version 2024-03-24-17:30
 */

public class Desk {
    private List<Card> resourceCard;          //list of resource cards - resource card deck
    private List<Card> goldCard;              //list of gold cards - gold card deck
    private List<Card> initialCard;           //list of initial cards - initial card deck
    private List<Card> goalCard;              //list of goal cards - goal card deck
    private List<Card> displayedResourceCard; //list that will contain two face up resource card
    private List<Card> displayedGoldCard;     //list that will contain two face up gold card

    private Card nextResourceCard;            //the first card of resource card deck
    private Card nextGoldCard;                //the first card of gold card deck


    /*
    Questions:
       Java Code:
       Do I really need to type so much?
       This solution(create all cards at the beginning of the game) is a good or bad idea?
       Create 100 objects is not too much, right? Will it have any bad consequences? Will it spend a lot of time?
       (Should I divide this class in some smaller one? In class like ResourceCardDeck, GoldCardDeck,... or is it indifferent?)
       Is there any difference using switch and if/else ?
       UML:
       If I use dynamic type, like Card c = new GoldCard(); should I connected to Card class or to GoldCard?
       We decide to add a class account, it will not cause duplicate problems, right?
       We decide to memorize the username and the password, we want to check the pwd at login, is better using a database or just a file?
       ------
       Need to ask in slack: if one deck finished their Card, should I pick from another deck?
    */


    //the class constructor - incomplete
    public Desk(){
        resourceCard = new ArrayList<>(40);
        goldCard = new ArrayList<>(40);
        initialCard = new ArrayList<>(6);
        goalCard = new ArrayList<>(16);
        displayedResourceCard = new ArrayList<>(2);
        displayedGoldCard = new ArrayList<>(2);


        Card c1 = new Card(Symbol.FUNGI,0,Symbol.FUNGI, Symbol.FUNGI, Symbol.EMPTY, Symbol.HIDDEN, 0,false, CardType.RESOURCE);
        Card c2 = new Card(Symbol.FUNGI,0,Symbol.FUNGI, Symbol.EMPTY, Symbol.HIDDEN, Symbol.FUNGI, 0,false, CardType.RESOURCE);
        Card c3 = new Card(Symbol.FUNGI,0,Symbol.EMPTY, Symbol.HIDDEN, Symbol.FUNGI, Symbol.FUNGI, 0,false, CardType.RESOURCE);
        Card c4 = new Card(Symbol.FUNGI,0,Symbol.HIDDEN, Symbol.FUNGI, Symbol.FUNGI, Symbol.EMPTY, 0,false, CardType.RESOURCE);
        Card c5 = new Card(Symbol.FUNGI,0,Symbol.EMPTY, Symbol.FUNGI, Symbol.HIDDEN, Symbol.EMPTY, 1,false, CardType.RESOURCE);
        Card c6 = new Card(Symbol.FUNGI,0,Symbol.HIDDEN, Symbol.EMPTY, Symbol.EMPTY, Symbol.FUNGI, 1,false, CardType.RESOURCE);
        Card c7 = new Card(Symbol.FUNGI,0,Symbol.FUNGI, Symbol.HIDDEN, Symbol.EMPTY, Symbol.EMPTY, 1,false, CardType.RESOURCE);
        Card c8 = new Card(Symbol.FUNGI,0,Symbol.FUNGI, Symbol.INSECT, Symbol.HIDDEN, Symbol.PARCHMENT, 0,false, CardType.RESOURCE);
        Card c9 = new Card(Symbol.FUNGI,0,Symbol.INK_BOTTLE, Symbol.FUNGI, Symbol.ANIMAL, Symbol.HIDDEN, 0,false, CardType.RESOURCE);
        Card c10 = new Card(Symbol.FUNGI,0,Symbol.HIDDEN, Symbol.FEATHER, Symbol.FUNGI, Symbol.PLANT, 0,false, CardType.RESOURCE);

        resourceCard.add(c1);
        resourceCard.add(c2);
        resourceCard.add(c3);
        resourceCard.add(c4);
        resourceCard.add(c5);
        resourceCard.add(c6);
        resourceCard.add(c7);
        resourceCard.add(c8);
        resourceCard.add(c9);
        resourceCard.add(c10);


        Card c11 = new Card(Symbol.PLANT,0,Symbol.PLANT, Symbol.PLANT,  Symbol.EMPTY, Symbol.HIDDEN, 0,false, CardType.RESOURCE);
        Card c12 = new Card(Symbol.PLANT,0,Symbol.PLANT, Symbol.EMPTY,  Symbol.HIDDEN, Symbol.PLANT, 0,false, CardType.RESOURCE);
        Card c13 = new Card(Symbol.PLANT,0,Symbol.EMPTY, Symbol.HIDDEN, Symbol.PLANT, Symbol.PLANT, 0,false, CardType.RESOURCE);
        Card c14 = new Card(Symbol.PLANT,0,Symbol.HIDDEN, Symbol.PLANT, Symbol.PLANT, Symbol.EMPTY, 0,false, CardType.RESOURCE);
        Card c15 = new Card(Symbol.PLANT,0,Symbol.EMPTY, Symbol.EMPTY, Symbol.PLANT, Symbol.HIDDEN, 1,false, CardType.RESOURCE);
        Card c16 = new Card(Symbol.PLANT,0,Symbol.EMPTY, Symbol.EMPTY, Symbol.HIDDEN, Symbol.PLANT, 1, false, CardType.RESOURCE);
        Card c17 = new Card(Symbol.PLANT,0,Symbol.HIDDEN, Symbol.PLANT, Symbol.EMPTY, Symbol.EMPTY, 1,false, CardType.RESOURCE);
        Card c18 = new Card(Symbol.PLANT,0,Symbol.FUNGI, Symbol.PLANT, Symbol.INK_BOTTLE, Symbol.HIDDEN, 0,false, CardType.RESOURCE);
        Card c19 = new Card(Symbol.PLANT,0,Symbol.PARCHMENT, Symbol.HIDDEN, Symbol.ANIMAL, Symbol.PLANT, 0,false, CardType.RESOURCE);
        Card c20 = new Card(Symbol.PLANT,0,Symbol.HIDDEN, Symbol.INSECT, Symbol.PLANT, Symbol.FEATHER, 0,false, CardType.RESOURCE);

        resourceCard.add(c11);
        resourceCard.add(c12);
        resourceCard.add(c13);
        resourceCard.add(c14);
        resourceCard.add(c15);
        resourceCard.add(c16);
        resourceCard.add(c17);
        resourceCard.add(c18);
        resourceCard.add(c19);
        resourceCard.add(c20);


        Card c21 = new Card(Symbol.ANIMAL,0,Symbol.ANIMAL, Symbol.ANIMAL,  Symbol.HIDDEN, Symbol.EMPTY, 0,false, CardType.RESOURCE);
        Card c22 = new Card(Symbol.ANIMAL,0,Symbol.EMPTY, Symbol.ANIMAL, Symbol.ANIMAL, Symbol.HIDDEN, 0,false, CardType.RESOURCE);
        Card c23 = new Card(Symbol.ANIMAL,0,Symbol.ANIMAL, Symbol.HIDDEN, Symbol.EMPTY, Symbol.ANIMAL, 0,false, CardType.RESOURCE);
        Card c24 = new Card(Symbol.ANIMAL,0,Symbol.HIDDEN, Symbol.EMPTY, Symbol.ANIMAL, Symbol.ANIMAL, 0,false, CardType.RESOURCE);
        Card c25 = new Card(Symbol.ANIMAL,0,Symbol.EMPTY, Symbol.ANIMAL, Symbol.HIDDEN, Symbol.EMPTY, 1,false, CardType.RESOURCE);
        Card c26 = new Card(Symbol.ANIMAL,0,Symbol.EMPTY, Symbol.HIDDEN, Symbol.ANIMAL, Symbol.EMPTY, 1, false, CardType.RESOURCE);
        Card c27 = new Card(Symbol.ANIMAL,0,Symbol.HIDDEN, Symbol.EMPTY, Symbol.EMPTY, Symbol.ANIMAL, 1,false, CardType.RESOURCE);
        Card c28 = new Card(Symbol.ANIMAL,0,Symbol.PLANT, Symbol.ANIMAL, Symbol.PARCHMENT, Symbol.HIDDEN, 0,false, CardType.RESOURCE);
        Card c29 = new Card(Symbol.ANIMAL,0,Symbol.FEATHER, Symbol.HIDDEN, Symbol.FUNGI, Symbol.ANIMAL, 0,false, CardType.RESOURCE);
        Card c30 = new Card(Symbol.ANIMAL,0,Symbol.HIDDEN, Symbol.INSECT, Symbol.ANIMAL, Symbol.INK_BOTTLE, 0,false, CardType.RESOURCE);

        resourceCard.add(c21);
        resourceCard.add(c22);
        resourceCard.add(c23);
        resourceCard.add(c24);
        resourceCard.add(c25);
        resourceCard.add(c26);
        resourceCard.add(c27);
        resourceCard.add(c28);
        resourceCard.add(c29);
        resourceCard.add(c30);


        Card c31 = new Card(Symbol.INSECT,0,Symbol.INSECT, Symbol.INSECT, Symbol.HIDDEN, Symbol.EMPTY, 0,false, CardType.RESOURCE);
        Card c32 = new Card(Symbol.INSECT,0,Symbol.EMPTY, Symbol.INSECT, Symbol.INSECT, Symbol.HIDDEN, 0,false, CardType.RESOURCE);
        Card c33 = new Card(Symbol.INSECT,0,Symbol.INSECT, Symbol.HIDDEN, Symbol.EMPTY, Symbol.INSECT, 0,false, CardType.RESOURCE);
        Card c34 = new Card(Symbol.INSECT,0,Symbol.HIDDEN, Symbol.EMPTY, Symbol.INSECT, Symbol.INSECT, 0,false, CardType.RESOURCE);
        Card c35 = new Card(Symbol.INSECT,0,Symbol.EMPTY, Symbol.EMPTY, Symbol.INSECT, Symbol.HIDDEN, 1,false, CardType.RESOURCE);
        Card c36 = new Card(Symbol.INSECT,0,Symbol.INSECT, Symbol.HIDDEN, Symbol.EMPTY, Symbol.EMPTY, 1, false, CardType.RESOURCE);
        Card c37 = new Card(Symbol.INSECT,0,Symbol.HIDDEN, Symbol.INSECT, Symbol.EMPTY, Symbol.EMPTY, 1,false, CardType.RESOURCE);
        Card c38 = new Card(Symbol.INSECT,0,Symbol.INSECT, Symbol.PLANT, Symbol.HIDDEN, Symbol.INK_BOTTLE, 0,false, CardType.RESOURCE);
        Card c39 = new Card(Symbol.INSECT,0,Symbol.PARCHMENT, Symbol.INSECT, Symbol.FUNGI, Symbol.HIDDEN, 0,false, CardType.RESOURCE);
        Card c40 = new Card(Symbol.INSECT,0,Symbol.HIDDEN, Symbol.FEATHER, Symbol.INSECT, Symbol.ANIMAL, 0,false, CardType.RESOURCE);

        resourceCard.add(c31);
        resourceCard.add(c32);
        resourceCard.add(c33);
        resourceCard.add(c34);
        resourceCard.add(c35);
        resourceCard.add(c36);
        resourceCard.add(c37);
        resourceCard.add(c38);
        resourceCard.add(c39);
        resourceCard.add(c40);

        Card c41 = new GoldCard(Symbol.FUNGI,0,Symbol.EMPTY, Symbol.INK_BOTTLE, Symbol.EMPTY, Symbol.HIDDEN, 1,false, CardType.GOLD,
                Symbol.INK_BOTTLE, 2,1,0,0);
        Card c42 = new GoldCard(Symbol.FUNGI,0,Symbol.PARCHMENT, Symbol.EMPTY, Symbol.HIDDEN, Symbol.EMPTY, 1,false, CardType.GOLD,
                Symbol.PARCHMENT, 2,0,0,1);
        Card c43 = new GoldCard(Symbol.FUNGI,0,Symbol.HIDDEN, Symbol.EMPTY, Symbol.FEATHER, Symbol.EMPTY, 1,false, CardType.GOLD,
                Symbol.FEATHER, 2,0,1,0);
        Card c44 = new GoldCard(Symbol.FUNGI,0,Symbol.EMPTY, Symbol.EMPTY, Symbol.HIDDEN, Symbol.EMPTY, 2,false, CardType.GOLD,
                Symbol.COVERED_ANGLE, 3,1,0,0);
        Card c45 = new GoldCard(Symbol.FUNGI,0,Symbol.EMPTY, Symbol.EMPTY, Symbol.EMPTY, Symbol.HIDDEN, 2,false, CardType.GOLD,
                Symbol.COVERED_ANGLE, 3,0,1,0);
        Card c46 = new GoldCard(Symbol.FUNGI,0,Symbol.EMPTY, Symbol.INK_BOTTLE, Symbol.EMPTY, Symbol.HIDDEN, 0,false, CardType.GOLD,
                Symbol.INK_BOTTLE,0,0,0,0 );
        Card c47 = new GoldCard(Symbol.FUNGI,0,Symbol.EMPTY, Symbol.INK_BOTTLE, Symbol.EMPTY, Symbol.HIDDEN, 0,false, CardType.GOLD,
                Symbol.INK_BOTTLE, 0,0,0,0);
        Card c48 = new GoldCard(Symbol.FUNGI,0,Symbol.EMPTY, Symbol.INK_BOTTLE, Symbol.EMPTY, Symbol.HIDDEN, 0,false, CardType.GOLD,
                Symbol.INK_BOTTLE, 0,0,0,0);
        Card c49 = new GoldCard(Symbol.FUNGI,0,Symbol.EMPTY, Symbol.INK_BOTTLE, Symbol.EMPTY, Symbol.HIDDEN, 0,false, CardType.GOLD,
                Symbol.INK_BOTTLE, 0,0,0,0);

        // to be completed


        Collections.shuffle(resourceCard);
        Collections.shuffle(goldCard);
        Collections.shuffle(initialCard);
        Collections.shuffle(goalCard);

        updateDisplayedRCard();
        updateDisplayedGCard();

        updateNextRCard();
        updateNextGCard();
    }


    //method that picks one card from the right deck according the type passed as parameter
    public Card pickOneCard(CardType type){
        Card c = null;
        switch (type){
            case RESOURCE:
                if (!resourceCard.isEmpty()){
                    c = resourceCard.get(0);
                    resourceCard.remove(0);   // ? Is it ok? Does it create some problems?
                }
                break;
            case GOLD:
                if (!goalCard.isEmpty()){
                    c = goldCard.get(0);
                    goalCard.remove(0);   // ? Is it ok? Does it create some problems?
                }
                break;
            case INITIAL:
                c = initialCard.get(0);
                initialCard.remove(0);
                break;
            case GOAL:
                c = goalCard.get(0);
                goalCard.remove(0);
                break;
        }
        return c;
    }


    //method that fills the list of face up resource cards with resource card if the list size is less than 2 and resource card deck is not empty
    public void updateDisplayedRCard(){
        while (displayedResourceCard.size() < 2 && !resourceCard.isEmpty()){
            displayedResourceCard.add(pickOneCard(CardType.RESOURCE));
        }
    }


    //method that fills the list of face up gold cards with resource card if the list size is less than 2 and gold card deck is not empty
    public void updateDisplayedGCard(){
        while(displayedGoldCard.size() < 2 && !goalCard.isEmpty()){
            displayedGoldCard.add(pickOneCard(CardType.GOLD));
        }
    }


    //method that returns the current face up resource cards
    public List<Card> getDisplayedRCard(){
        return displayedResourceCard;
    }


    //method that returns the current face up gold cards
    public List<Card> getDisplayedGCard(){
        return displayedGoldCard;
    }


    //method that picks one resource card from face up cards according the index of position i, with automatic refill
    public Card pickOneDisplayedRCard(int i){
        Card c = null;
        if( i == 0){
            c = displayedResourceCard.get(i);
            displayedResourceCard.remove(i);
        }else if( i == 1 ){
            c = displayedResourceCard.get(i);
            displayedResourceCard.remove(i);
        }
        updateDisplayedRCard();
        return c;
    }


    //method that picks one gold card from face up cards according the index of position i, with automatic refill
    public Card pickOneDisplayedGCard(int i){
        Card c = null;
        if( i == 0){
            c = displayedGoldCard.get(i);
            displayedGoldCard.remove(i);
        }else if( i == 1 ){
            c = displayedResourceCard.get(i);
            displayedGoldCard.remove(i);
        }
        updateDisplayedGCard();
        return c;
    }


    //method that updates the first card of resource card deck, if it was used
    public void updateNextRCard(){
        if (!resourceCard.isEmpty()){
            nextResourceCard = pickOneCard(CardType.RESOURCE);
        }else{
            nextResourceCard = null;
        }
    }


    //method that updates the first card of gold card deck, if it was used
    public void updateNextGCard(){
        if (!goalCard.isEmpty()){
            nextGoldCard = pickOneCard(CardType.GOLD);
        }else{
            nextGoldCard = null;
        }

    }


    //method that picks the first card of resource card deck, with automatic refill
    public Card pickNextRCard(){
        Card c = nextResourceCard;
        updateNextRCard();
        return c;
    }


    //method that picks the first card of gold card deck, with automatic refill
    public Card pickNextGCard(){
        Card c = nextGoldCard;
        updateNextGCard();
        return c;
    }

}

// I haven't checked yet if there are any exceptional cases


