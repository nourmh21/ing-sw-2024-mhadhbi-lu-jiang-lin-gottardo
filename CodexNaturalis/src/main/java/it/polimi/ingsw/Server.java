package it.polimi.ingsw;

import it.polimi.ingsw.model.Card;
import it.polimi.ingsw.model.GoldCard;
import it.polimi.ingsw.model.InitialCard;
import it.polimi.ingsw.model.enums.CardType;
import it.polimi.ingsw.model.enums.Symbol;

import java.util.List;

public class Server {



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
    InitialCard c81= new InitialCard(81,Symbol.EMPTY,Symbol.EMPTY,Symbol.ANIMAL,Symbol.FUNGI,Symbol.EMPTY,0,false,CardType.INITIAL,1,0,0,0);

    public Card getC(int i){

        return c48;
    }
    public InitialCard getIC(int i){

        return c81;
    }

}
