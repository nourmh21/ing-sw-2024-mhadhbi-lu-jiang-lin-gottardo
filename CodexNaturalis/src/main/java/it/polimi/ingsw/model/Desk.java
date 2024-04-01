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
 * */

public class Desk {
    private List<Card> resourceCard;          //list of resource cards - resource card deck
    private List<Card> goldCard;              //list of gold cards - gold card deck
    private List<Card> initialCard;           //list of initial cards - initial card deck
    private List<GoalCard> goalCard;              //list of goal cards - goal card deck
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
       Can I return a primitive type's value only using local variables?
       UML:
       If I use dynamic type, like Card c = new GoldCard(); should I connected only to Card class and also to GoldCard?
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


        Card c1 = new Card(1, Symbol.FUNGI,Symbol.FUNGI, Symbol.FUNGI, Symbol.EMPTY, Symbol.HIDDEN, 0,false, CardType.RESOURCE);
        Card c2 = new Card(2, Symbol.FUNGI,Symbol.FUNGI, Symbol.EMPTY, Symbol.HIDDEN, Symbol.FUNGI, 0,false, CardType.RESOURCE);
        Card c3 = new Card(3, Symbol.FUNGI,Symbol.EMPTY, Symbol.HIDDEN, Symbol.FUNGI, Symbol.FUNGI, 0,false, CardType.RESOURCE);
        Card c4 = new Card(4, Symbol.FUNGI,Symbol.HIDDEN, Symbol.FUNGI, Symbol.FUNGI, Symbol.EMPTY, 0,false, CardType.RESOURCE);
        Card c5 = new Card(5, Symbol.FUNGI,Symbol.EMPTY, Symbol.FUNGI, Symbol.HIDDEN, Symbol.EMPTY, 1,false, CardType.RESOURCE);
        Card c6 = new Card(6, Symbol.FUNGI,Symbol.HIDDEN, Symbol.EMPTY, Symbol.EMPTY, Symbol.FUNGI, 1,false, CardType.RESOURCE);
        Card c7 = new Card(7, Symbol.FUNGI,Symbol.FUNGI, Symbol.HIDDEN, Symbol.EMPTY, Symbol.EMPTY, 1,false, CardType.RESOURCE);
        Card c8 = new Card(8, Symbol.FUNGI,Symbol.FUNGI, Symbol.INSECT, Symbol.HIDDEN, Symbol.PARCHMENT, 0,false, CardType.RESOURCE);
        Card c9 = new Card(9, Symbol.FUNGI,Symbol.INK_BOTTLE, Symbol.FUNGI, Symbol.ANIMAL, Symbol.HIDDEN, 0,false, CardType.RESOURCE);
        Card c10 = new Card(10, Symbol.FUNGI,Symbol.HIDDEN, Symbol.FEATHER, Symbol.FUNGI, Symbol.PLANT, 0,false, CardType.RESOURCE);

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


        Card c11 = new Card(11, Symbol.PLANT, Symbol.PLANT, Symbol.PLANT,  Symbol.EMPTY, Symbol.HIDDEN, 0,false, CardType.RESOURCE);
        Card c12 = new Card(12, Symbol.PLANT, Symbol.PLANT, Symbol.EMPTY,  Symbol.HIDDEN, Symbol.PLANT, 0,false, CardType.RESOURCE);
        Card c13 = new Card(13, Symbol.PLANT, Symbol.EMPTY, Symbol.HIDDEN, Symbol.PLANT, Symbol.PLANT, 0,false, CardType.RESOURCE);
        Card c14 = new Card(14, Symbol.PLANT, Symbol.HIDDEN, Symbol.PLANT, Symbol.PLANT, Symbol.EMPTY, 0,false, CardType.RESOURCE);
        Card c15 = new Card(15, Symbol.PLANT, Symbol.EMPTY, Symbol.EMPTY, Symbol.PLANT, Symbol.HIDDEN, 1,false, CardType.RESOURCE);
        Card c16 = new Card(16, Symbol.PLANT, Symbol.EMPTY, Symbol.EMPTY, Symbol.HIDDEN, Symbol.PLANT, 1, false, CardType.RESOURCE);
        Card c17 = new Card(17, Symbol.PLANT, Symbol.HIDDEN, Symbol.PLANT, Symbol.EMPTY, Symbol.EMPTY, 1,false, CardType.RESOURCE);
        Card c18 = new Card(18, Symbol.PLANT, Symbol.FUNGI, Symbol.PLANT, Symbol.INK_BOTTLE, Symbol.HIDDEN, 0,false, CardType.RESOURCE);
        Card c19 = new Card(19, Symbol.PLANT, Symbol.PARCHMENT, Symbol.HIDDEN, Symbol.ANIMAL, Symbol.PLANT, 0,false, CardType.RESOURCE);
        Card c20 = new Card(20, Symbol.PLANT, Symbol.HIDDEN, Symbol.INSECT, Symbol.PLANT, Symbol.FEATHER, 0,false, CardType.RESOURCE);

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


        Card c21 = new Card(21, Symbol.ANIMAL, Symbol.ANIMAL, Symbol.ANIMAL,  Symbol.HIDDEN, Symbol.EMPTY, 0,false, CardType.RESOURCE);
        Card c22 = new Card(22, Symbol.ANIMAL, Symbol.EMPTY, Symbol.ANIMAL, Symbol.ANIMAL, Symbol.HIDDEN, 0,false, CardType.RESOURCE);
        Card c23 = new Card(23, Symbol.ANIMAL, Symbol.ANIMAL, Symbol.HIDDEN, Symbol.EMPTY, Symbol.ANIMAL, 0,false, CardType.RESOURCE);
        Card c24 = new Card(24, Symbol.ANIMAL, Symbol.HIDDEN, Symbol.EMPTY, Symbol.ANIMAL, Symbol.ANIMAL, 0,false, CardType.RESOURCE);
        Card c25 = new Card(25, Symbol.ANIMAL, Symbol.EMPTY, Symbol.ANIMAL, Symbol.HIDDEN, Symbol.EMPTY, 1,false, CardType.RESOURCE);
        Card c26 = new Card(26, Symbol.ANIMAL, Symbol.EMPTY, Symbol.HIDDEN, Symbol.ANIMAL, Symbol.EMPTY, 1, false, CardType.RESOURCE);
        Card c27 = new Card(27, Symbol.ANIMAL, Symbol.HIDDEN, Symbol.EMPTY, Symbol.EMPTY, Symbol.ANIMAL, 1,false, CardType.RESOURCE);
        Card c28 = new Card(28, Symbol.ANIMAL, Symbol.PLANT, Symbol.ANIMAL, Symbol.PARCHMENT, Symbol.HIDDEN, 0,false, CardType.RESOURCE);
        Card c29 = new Card(29, Symbol.ANIMAL, Symbol.FEATHER, Symbol.HIDDEN, Symbol.FUNGI, Symbol.ANIMAL, 0,false, CardType.RESOURCE);
        Card c30 = new Card(30, Symbol.ANIMAL, Symbol.HIDDEN, Symbol.INSECT, Symbol.ANIMAL, Symbol.INK_BOTTLE, 0,false, CardType.RESOURCE);

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


        Card c31 = new Card(31, Symbol.INSECT, Symbol.INSECT, Symbol.INSECT, Symbol.HIDDEN, Symbol.EMPTY, 0,false, CardType.RESOURCE);
        Card c32 = new Card(32, Symbol.INSECT, Symbol.EMPTY, Symbol.INSECT, Symbol.INSECT, Symbol.HIDDEN, 0,false, CardType.RESOURCE);
        Card c33 = new Card(33, Symbol.INSECT, Symbol.INSECT, Symbol.HIDDEN, Symbol.EMPTY, Symbol.INSECT, 0,false, CardType.RESOURCE);
        Card c34 = new Card(34, Symbol.INSECT, Symbol.HIDDEN, Symbol.EMPTY, Symbol.INSECT, Symbol.INSECT, 0,false, CardType.RESOURCE);
        Card c35 = new Card(35, Symbol.INSECT, Symbol.EMPTY, Symbol.EMPTY, Symbol.INSECT, Symbol.HIDDEN, 1,false, CardType.RESOURCE);
        Card c36 = new Card(36, Symbol.INSECT, Symbol.INSECT, Symbol.HIDDEN, Symbol.EMPTY, Symbol.EMPTY, 1, false, CardType.RESOURCE);
        Card c37 = new Card(37, Symbol.INSECT, Symbol.HIDDEN, Symbol.INSECT, Symbol.EMPTY, Symbol.EMPTY, 1,false, CardType.RESOURCE);
        Card c38 = new Card(38, Symbol.INSECT, Symbol.INSECT, Symbol.PLANT, Symbol.HIDDEN, Symbol.INK_BOTTLE, 0,false, CardType.RESOURCE);
        Card c39 = new Card(39, Symbol.INSECT, Symbol.PARCHMENT, Symbol.INSECT, Symbol.FUNGI, Symbol.HIDDEN, 0,false, CardType.RESOURCE);
        Card c40 = new Card(40, Symbol.INSECT, Symbol.HIDDEN, Symbol.FEATHER, Symbol.INSECT, Symbol.ANIMAL, 0,false, CardType.RESOURCE);

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

        Card c41 = new GoldCard(41, Symbol.FUNGI, Symbol.EMPTY, Symbol.INK_BOTTLE, Symbol.EMPTY, Symbol.HIDDEN, 1,false, CardType.GOLD,
                Symbol.INK_BOTTLE, 2,1,0,0);
        Card c42 = new GoldCard(42, Symbol.FUNGI, Symbol.PARCHMENT, Symbol.EMPTY, Symbol.HIDDEN, Symbol.EMPTY, 1,false, CardType.GOLD,
                Symbol.PARCHMENT, 2,0,0,1);
        Card c43 = new GoldCard(43, Symbol.FUNGI, Symbol.HIDDEN, Symbol.EMPTY, Symbol.FEATHER, Symbol.EMPTY, 1,false, CardType.GOLD,
                Symbol.FEATHER, 2,0,1,0);
        Card c44 = new GoldCard(44, Symbol.FUNGI, Symbol.EMPTY, Symbol.EMPTY, Symbol.HIDDEN, Symbol.EMPTY, 2,false, CardType.GOLD,
                Symbol.COVERED_ANGLE, 3,1,0,0);
        Card c45 = new GoldCard(45, Symbol.FUNGI, Symbol.EMPTY, Symbol.EMPTY, Symbol.EMPTY, Symbol.HIDDEN, 2,false, CardType.GOLD,
                Symbol.COVERED_ANGLE, 3,0,1,0);
        Card c46 = new GoldCard(46, Symbol.FUNGI, Symbol.EMPTY, Symbol.INK_BOTTLE, Symbol.EMPTY, Symbol.HIDDEN, 0,false, CardType.GOLD,
                Symbol.INK_BOTTLE,0,0,0,0 );
        Card c47 = new GoldCard(47, Symbol.FUNGI, Symbol.EMPTY, Symbol.INK_BOTTLE, Symbol.EMPTY, Symbol.HIDDEN, 0,false, CardType.GOLD,
                Symbol.INK_BOTTLE, 0,0,0,0);
        Card c48 = new GoldCard(48, Symbol.FUNGI, Symbol.EMPTY, Symbol.INK_BOTTLE, Symbol.EMPTY, Symbol.HIDDEN, 0,false, CardType.GOLD,
                Symbol.INK_BOTTLE, 0,0,0,0);
        Card c49 = new GoldCard(49, Symbol.FUNGI, Symbol.EMPTY, Symbol.INK_BOTTLE, Symbol.EMPTY, Symbol.HIDDEN, 0,false, CardType.GOLD,
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


    //method that picks one card from the right deck according the type passed as parameter: RESOURCE, GOLD or INITIAL
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
        }
        return c;
    }

    //method that pick one goal card
    public GoalCard pickOneGoalCard(){
        GoalCard c = goalCard.get(0);
        goalCard.remove(0);
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


