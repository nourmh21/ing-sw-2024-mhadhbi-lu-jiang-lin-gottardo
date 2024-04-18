package it.polimi.ingsw;

import it.polimi.ingsw.model.Card;
import it.polimi.ingsw.model.GoldCard;
import it.polimi.ingsw.model.InitialCard;
import it.polimi.ingsw.model.ObjectiveCard;
import it.polimi.ingsw.model.enums.CardType;
import it.polimi.ingsw.model.enums.GoalType;
import it.polimi.ingsw.model.enums.Symbol;

import java.util.List;

public class Server {

    // RESOURCE CARDS
    Card c1 = new Card(1, Symbol.FUNGI,Symbol.FUNGI, Symbol.FUNGI, Symbol.EMPTY, Symbol.HIDDEN, 0, CardType.RESOURCE);
    Card c2 = new Card(2, Symbol.FUNGI,Symbol.FUNGI, Symbol.EMPTY, Symbol.HIDDEN, Symbol.FUNGI, 0, CardType.RESOURCE);
    Card c3 = new Card(3, Symbol.FUNGI,Symbol.EMPTY, Symbol.HIDDEN, Symbol.FUNGI, Symbol.FUNGI, 0,CardType.RESOURCE);
    Card c4 = new Card(4, Symbol.FUNGI,Symbol.HIDDEN, Symbol.FUNGI, Symbol.FUNGI, Symbol.EMPTY, 0, CardType.RESOURCE);
    Card c5 = new Card(5, Symbol.FUNGI,Symbol.EMPTY, Symbol.FUNGI, Symbol.HIDDEN, Symbol.EMPTY, 1, CardType.RESOURCE);
    Card c6 = new Card(6, Symbol.FUNGI,Symbol.HIDDEN, Symbol.EMPTY, Symbol.EMPTY, Symbol.FUNGI, 1, CardType.RESOURCE);
    Card c7 = new Card(7, Symbol.FUNGI,Symbol.FUNGI, Symbol.HIDDEN, Symbol.EMPTY, Symbol.EMPTY, 1, CardType.RESOURCE);
    Card c8 = new Card(8, Symbol.FUNGI,Symbol.FUNGI, Symbol.INSECT, Symbol.HIDDEN, Symbol.PARCHMENT, 0, CardType.RESOURCE);
    Card c9 = new Card(9, Symbol.FUNGI,Symbol.INK_BOTTLE, Symbol.FUNGI, Symbol.ANIMAL, Symbol.HIDDEN, 0, CardType.RESOURCE);
    Card c10 = new Card(10, Symbol.FUNGI,Symbol.HIDDEN, Symbol.FEATHER, Symbol.FUNGI, Symbol.PLANT, 0, CardType.RESOURCE);

    Card c11 = new Card(11, Symbol.PLANT, Symbol.PLANT, Symbol.PLANT,  Symbol.EMPTY, Symbol.HIDDEN, 0, CardType.RESOURCE);
    Card c12 = new Card(12, Symbol.PLANT, Symbol.PLANT, Symbol.EMPTY,  Symbol.HIDDEN, Symbol.PLANT, 0, CardType.RESOURCE);
    Card c13 = new Card(13, Symbol.PLANT, Symbol.EMPTY, Symbol.HIDDEN, Symbol.PLANT, Symbol.PLANT, 0, CardType.RESOURCE);
    Card c14 = new Card(14, Symbol.PLANT, Symbol.HIDDEN, Symbol.PLANT, Symbol.PLANT, Symbol.EMPTY, 0, CardType.RESOURCE);
    Card c15 = new Card(15, Symbol.PLANT, Symbol.EMPTY, Symbol.EMPTY, Symbol.PLANT, Symbol.HIDDEN, 1, CardType.RESOURCE);
    Card c16 = new Card(16, Symbol.PLANT, Symbol.EMPTY, Symbol.EMPTY, Symbol.HIDDEN, Symbol.PLANT, 1, CardType.RESOURCE);
    Card c17 = new Card(17, Symbol.PLANT, Symbol.HIDDEN, Symbol.PLANT, Symbol.EMPTY, Symbol.EMPTY, 1, CardType.RESOURCE);
    Card c18 = new Card(18, Symbol.PLANT, Symbol.FUNGI, Symbol.PLANT, Symbol.INK_BOTTLE, Symbol.HIDDEN, 0, CardType.RESOURCE);
    Card c19 = new Card(19, Symbol.PLANT, Symbol.PARCHMENT, Symbol.HIDDEN, Symbol.ANIMAL, Symbol.PLANT, 0, CardType.RESOURCE);
    Card c20 = new Card(20, Symbol.PLANT, Symbol.HIDDEN, Symbol.INSECT, Symbol.PLANT, Symbol.FEATHER, 0, CardType.RESOURCE);

    Card c21 = new Card(21, Symbol.ANIMAL, Symbol.ANIMAL, Symbol.ANIMAL,  Symbol.HIDDEN, Symbol.EMPTY, 0, CardType.RESOURCE);
    Card c22 = new Card(22, Symbol.ANIMAL, Symbol.EMPTY, Symbol.ANIMAL, Symbol.ANIMAL, Symbol.HIDDEN, 0, CardType.RESOURCE);
    Card c23 = new Card(23, Symbol.ANIMAL, Symbol.ANIMAL, Symbol.HIDDEN, Symbol.EMPTY, Symbol.ANIMAL, 0, CardType.RESOURCE);
    Card c24 = new Card(24, Symbol.ANIMAL, Symbol.HIDDEN, Symbol.EMPTY, Symbol.ANIMAL, Symbol.ANIMAL, 0, CardType.RESOURCE);
    Card c25 = new Card(25, Symbol.ANIMAL, Symbol.EMPTY, Symbol.ANIMAL, Symbol.HIDDEN, Symbol.EMPTY, 1, CardType.RESOURCE);
    Card c26 = new Card(26, Symbol.ANIMAL, Symbol.EMPTY, Symbol.HIDDEN, Symbol.ANIMAL, Symbol.EMPTY, 1,  CardType.RESOURCE);
    Card c27 = new Card(27, Symbol.ANIMAL, Symbol.HIDDEN, Symbol.EMPTY, Symbol.EMPTY, Symbol.ANIMAL, 1, CardType.RESOURCE);
    Card c28 = new Card(28, Symbol.ANIMAL, Symbol.PLANT, Symbol.ANIMAL, Symbol.PARCHMENT, Symbol.HIDDEN, 0, CardType.RESOURCE);
    Card c29 = new Card(29, Symbol.ANIMAL, Symbol.FEATHER, Symbol.HIDDEN, Symbol.FUNGI, Symbol.ANIMAL, 0, CardType.RESOURCE);
    Card c30 = new Card(30, Symbol.ANIMAL, Symbol.HIDDEN, Symbol.INSECT, Symbol.ANIMAL, Symbol.INK_BOTTLE, 0, CardType.RESOURCE);


    Card c31 = new Card(31, Symbol.INSECT, Symbol.INSECT, Symbol.INSECT, Symbol.HIDDEN, Symbol.EMPTY, 0, CardType.RESOURCE);
    Card c32 = new Card(32, Symbol.INSECT, Symbol.EMPTY, Symbol.INSECT, Symbol.INSECT, Symbol.HIDDEN, 0, CardType.RESOURCE);
    Card c33 = new Card(33, Symbol.INSECT, Symbol.INSECT, Symbol.HIDDEN, Symbol.EMPTY, Symbol.INSECT, 0, CardType.RESOURCE);
    Card c34 = new Card(34, Symbol.INSECT, Symbol.HIDDEN, Symbol.EMPTY, Symbol.INSECT, Symbol.INSECT, 0, CardType.RESOURCE);
    Card c35 = new Card(35, Symbol.INSECT, Symbol.EMPTY, Symbol.EMPTY, Symbol.INSECT, Symbol.HIDDEN, 1, CardType.RESOURCE);
    Card c36 = new Card(36, Symbol.INSECT, Symbol.INSECT, Symbol.HIDDEN, Symbol.EMPTY, Symbol.EMPTY, 1,  CardType.RESOURCE);
    Card c37 = new Card(37, Symbol.INSECT, Symbol.HIDDEN, Symbol.INSECT, Symbol.EMPTY, Symbol.EMPTY, 1, CardType.RESOURCE);
    Card c38 = new Card(38, Symbol.INSECT, Symbol.INSECT, Symbol.PLANT, Symbol.HIDDEN, Symbol.INK_BOTTLE, 0, CardType.RESOURCE);
    Card c39 = new Card(39, Symbol.INSECT, Symbol.PARCHMENT, Symbol.INSECT, Symbol.FUNGI, Symbol.HIDDEN, 0, CardType.RESOURCE);
    Card c40 = new Card(40, Symbol.INSECT, Symbol.HIDDEN, Symbol.FEATHER, Symbol.INSECT, Symbol.ANIMAL, 0, CardType.RESOURCE);


    // GOLD CARDS
    Card c41 = new GoldCard(41, Symbol.FUNGI, Symbol.HIDDEN, Symbol.EMPTY, Symbol.FEATHER, Symbol.EMPTY, 1, CardType.GOLD,
            Symbol.FEATHER, 2,0,1,0);
    Card c42 = new GoldCard(42, Symbol.FUNGI, Symbol.EMPTY, Symbol.INK_BOTTLE, Symbol.EMPTY, Symbol.HIDDEN, 1, CardType.GOLD,
            Symbol.INK_BOTTLE, 2,1,0,0);
    Card c43 = new GoldCard(43, Symbol.FUNGI, Symbol.PARCHMENT, Symbol.EMPTY, Symbol.HIDDEN, Symbol.EMPTY, 1, CardType.GOLD,
            Symbol.PARCHMENT, 2,0,0,1);
    Card c44 = new GoldCard(44, Symbol.FUNGI, Symbol.EMPTY, Symbol.EMPTY, Symbol.EMPTY, Symbol.HIDDEN, 2, CardType.GOLD,
            Symbol.COVERED_ANGLE, 3,0,1,0);
    Card c45 = new GoldCard(45, Symbol.FUNGI, Symbol.EMPTY, Symbol.EMPTY, Symbol.HIDDEN, Symbol.EMPTY, 2, CardType.GOLD,
            Symbol.COVERED_ANGLE, 3,1,0,0);
    Card c46 = new GoldCard(46, Symbol.FUNGI, Symbol.EMPTY, Symbol.HIDDEN, Symbol.EMPTY, Symbol.EMPTY, 2, CardType.GOLD,
            Symbol.INK_BOTTLE,3,0,0,1 );
    Card c47 = new GoldCard(47, Symbol.FUNGI, Symbol.EMPTY, Symbol.HIDDEN, Symbol.HIDDEN, Symbol.INK_BOTTLE, 3, CardType.GOLD,
            Symbol.EMPTY, 3,0,0,0);
    Card c48 = new GoldCard(48, Symbol.FUNGI, Symbol.FEATHER, Symbol.EMPTY, Symbol.HIDDEN, Symbol.HIDDEN, 3, CardType.GOLD,
            Symbol.EMPTY, 3,0,0,0);
    Card c49 = new GoldCard(49, Symbol.FUNGI, Symbol.EMPTY, Symbol.PARCHMENT, Symbol.EMPTY, Symbol.HIDDEN, 3, CardType.GOLD,
            Symbol.EMPTY, 0,0,0,0);
    Card c50 = new GoldCard(50, Symbol.FUNGI, Symbol.EMPTY, Symbol.HIDDEN, Symbol.HIDDEN, Symbol.EMPTY, 5, CardType.GOLD,
            Symbol.EMPTY, 5,0,0,0);

    Card c51 = new GoldCard(51, Symbol.PLANT, Symbol.FEATHER, Symbol.EMPTY, Symbol.HIDDEN, Symbol.EMPTY, 1, CardType.GOLD,
            Symbol.FEATHER, 0,2,0,1);
    Card c52 = new GoldCard(52, Symbol.PLANT, Symbol.EMPTY, Symbol.PARCHMENT, Symbol.EMPTY, Symbol.HIDDEN, 1, CardType.GOLD,
            Symbol.PARCHMENT, 1,2,0,0);
    Card c53 = new GoldCard(53, Symbol.PLANT, Symbol.EMPTY, Symbol.HIDDEN, Symbol.EMPTY, Symbol.INK_BOTTLE, 1, CardType.GOLD,
            Symbol.INK_BOTTLE, 0,2,1,0);
    Card c54 = new GoldCard(54, Symbol.PLANT, Symbol.HIDDEN, Symbol.EMPTY, Symbol.EMPTY, Symbol.EMPTY, 2, CardType.GOLD,
            Symbol.COVERED_ANGLE, 0,3,0,1);
    Card c55 = new GoldCard(55, Symbol.PLANT, Symbol.EMPTY, Symbol.EMPTY, Symbol.HIDDEN, Symbol.EMPTY, 2, CardType.GOLD,
            Symbol.COVERED_ANGLE, 0,3,1,0);
    Card c56 = new GoldCard(56, Symbol.PLANT, Symbol.EMPTY, Symbol.HIDDEN, Symbol.EMPTY, Symbol.EMPTY, 2, CardType.GOLD,
            Symbol.COVERED_ANGLE, 1,3,0,0);
    Card c57 = new GoldCard(57, Symbol.PLANT, Symbol.EMPTY, Symbol.HIDDEN, Symbol.HIDDEN, Symbol.FEATHER, 3, CardType.GOLD,
            Symbol.EMPTY, 0,3,0,0);
    Card c58 = new GoldCard(58, Symbol.PLANT, Symbol.PARCHMENT, Symbol.EMPTY, Symbol.HIDDEN, Symbol.HIDDEN, 3, CardType.GOLD,
            Symbol.EMPTY, 0,3,0,0);
    Card c59 = new GoldCard(59, Symbol.PLANT, Symbol.HIDDEN, Symbol.INK_BOTTLE, Symbol.EMPTY, Symbol.HIDDEN, 3, CardType.GOLD,
            Symbol.EMPTY, 0,3,0,0);
    Card c60 = new GoldCard(60, Symbol.PLANT, Symbol.EMPTY, Symbol.EMPTY, Symbol.HIDDEN, Symbol.HIDDEN, 5, CardType.GOLD,
            Symbol.EMPTY, 0,5,0,0);

    Card c61 = new GoldCard(61, Symbol.ANIMAL, Symbol.INK_BOTTLE, Symbol.EMPTY, Symbol.HIDDEN, Symbol.EMPTY, 1, CardType.GOLD,
            Symbol.INK_BOTTLE, 0,0,2,1);
    Card c62 = new GoldCard(62, Symbol.ANIMAL, Symbol.HIDDEN, Symbol.EMPTY, Symbol.PARCHMENT, Symbol.EMPTY, 1, CardType.GOLD,
            Symbol.PARCHMENT, 0,1,2,0);
    Card c63 = new GoldCard(63, Symbol.ANIMAL, Symbol.EMPTY, Symbol.HIDDEN, Symbol.EMPTY, Symbol.FEATHER, 2, CardType.GOLD,
            Symbol.FEATHER, 1,0,2,0);
    Card c64 = new GoldCard(64, Symbol.ANIMAL, Symbol.EMPTY, Symbol.EMPTY, Symbol.EMPTY, Symbol.HIDDEN, 2, CardType.GOLD,
            Symbol.COVERED_ANGLE, 0,0,3,1);
    Card c65 = new GoldCard(65, Symbol.ANIMAL, Symbol.EMPTY, Symbol.HIDDEN, Symbol.EMPTY, Symbol.EMPTY, 2, CardType.GOLD,
            Symbol.COVERED_ANGLE, 1,0,3,0);
    Card c66 = new GoldCard(66, Symbol.ANIMAL, Symbol.HIDDEN, Symbol.EMPTY, Symbol.EMPTY, Symbol.EMPTY, 2, CardType.GOLD,
            Symbol.COVERED_ANGLE, 0,1,3,0);
    Card c67 = new GoldCard(67, Symbol.ANIMAL, Symbol.EMPTY, Symbol.HIDDEN, Symbol.HIDDEN, Symbol.PARCHMENT, 3, CardType.GOLD,
            Symbol.EMPTY, 0,5,0,0);
    Card c68 = new GoldCard(68, Symbol.ANIMAL, Symbol.EMPTY, Symbol.INK_BOTTLE, Symbol.HIDDEN, Symbol.HIDDEN, 3, CardType.GOLD,
            Symbol.EMPTY, 0,0,3,0);
    Card c69 = new GoldCard(69, Symbol.ANIMAL, Symbol.HIDDEN, Symbol.EMPTY, Symbol.FEATHER, Symbol.HIDDEN, 3, CardType.GOLD,
            Symbol.EMPTY, 0,0,3,0);
    Card c70 = new GoldCard(70, Symbol.ANIMAL, Symbol.HIDDEN, Symbol.EMPTY, Symbol.EMPTY, Symbol.HIDDEN, 5, CardType.GOLD,
            Symbol.EMPTY, 0,0,5,0);

    Card c71 = new GoldCard(71, Symbol.INSECT, Symbol.EMPTY, Symbol.FEATHER, Symbol.EMPTY, Symbol.HIDDEN, 1, CardType.GOLD,
            Symbol.FEATHER, 0,1,0,2);
    Card c72 = new GoldCard(72, Symbol.INSECT, Symbol.EMPTY, Symbol.HIDDEN, Symbol.EMPTY, Symbol.PARCHMENT, 1, CardType.GOLD,
            Symbol.PARCHMENT, 0,0,1,2);
    Card c73 = new GoldCard(73, Symbol.INSECT, Symbol.HIDDEN, Symbol.EMPTY, Symbol.INK_BOTTLE, Symbol.EMPTY, 1, CardType.GOLD,
            Symbol.INK_BOTTLE, 1,0,0,2);
    Card c74 = new GoldCard(74, Symbol.INSECT, Symbol.EMPTY, Symbol.EMPTY, Symbol.EMPTY, Symbol.HIDDEN, 2, CardType.GOLD,
            Symbol.COVERED_ANGLE, 0,0,1,3);
    Card c75 = new GoldCard(75, Symbol.INSECT, Symbol.EMPTY, Symbol.EMPTY, Symbol.HIDDEN, Symbol.EMPTY, 2, CardType.GOLD,
            Symbol.COVERED_ANGLE, 0,1,0,2);
    Card c76 = new GoldCard(76, Symbol.INSECT, Symbol.EMPTY, Symbol.HIDDEN, Symbol.EMPTY, Symbol.EMPTY, 2, CardType.GOLD,
            Symbol.COVERED_ANGLE, 1,0,0,2);
    Card c77 = new GoldCard(77, Symbol.INSECT, Symbol.INK_BOTTLE, Symbol.HIDDEN, Symbol.HIDDEN, Symbol.EMPTY, 3, CardType.GOLD,
            Symbol.EMPTY, 0,0,0,3);
    Card c78 = new GoldCard(78, Symbol.INSECT, Symbol.EMPTY, Symbol.PARCHMENT, Symbol.HIDDEN, Symbol.HIDDEN, 3, CardType.GOLD,
            Symbol.EMPTY, 0,0,0,3);
    Card c79 = new GoldCard(79, Symbol.INSECT, Symbol.EMPTY, Symbol.EMPTY, Symbol.HIDDEN, Symbol.FEATHER, 3, CardType.GOLD,
            Symbol.EMPTY, 0,0,0,3);
    Card c80 = new GoldCard(80, Symbol.INSECT, Symbol.EMPTY, Symbol.EMPTY, Symbol.HIDDEN, Symbol.HIDDEN, 5, CardType.GOLD,
            Symbol.EMPTY, 0,0,0,5);


    //OBJECTIVE CARDS
    ObjectiveCard c81 = new ObjectiveCard(81, 2, GoalType.REDG);
    ObjectiveCard c82 = new ObjectiveCard(82, 2, GoalType.GREEND);
    ObjectiveCard c83 = new ObjectiveCard(83, 2, GoalType.BLUEG);
    ObjectiveCard c84 = new ObjectiveCard(84, 2, GoalType.VIOLAD);
    ObjectiveCard c85 = new ObjectiveCard(85, 3, GoalType.RRG);
    ObjectiveCard c86 = new ObjectiveCard(86, 3, GoalType.GGV);
    ObjectiveCard c87 = new ObjectiveCard(87, 3, GoalType.BBR);
    ObjectiveCard c88 = new ObjectiveCard(88, 3, GoalType.VVB);
    ObjectiveCard c89 = new ObjectiveCard(89, 2, GoalType.FFF);
    ObjectiveCard c90 = new ObjectiveCard(90, 2, GoalType.PPP);
    ObjectiveCard c91 = new ObjectiveCard(91, 2, GoalType.AAA);
    ObjectiveCard c92 = new ObjectiveCard(92, 2, GoalType.III);
    ObjectiveCard c93 = new ObjectiveCard(93, 3, GoalType.BFP);
    ObjectiveCard c94 = new ObjectiveCard(94, 2, GoalType.PP);
    ObjectiveCard c95 = new ObjectiveCard(95, 2, GoalType.BB);
    ObjectiveCard c96 = new ObjectiveCard(96, 2, GoalType.FF);


    //INITIAL CARDS

    Card c97 = new InitialCard(97, Symbol.EMPTY, Symbol.FUNGI, Symbol.PLANT, Symbol.ANIMAL, Symbol.INSECT, 0, CardType.INITIAL, 0, 1, 0, 2, Symbol.EMPTY, Symbol.PLANT, Symbol.EMPTY, Symbol.INSECT);
    Card c98 = new InitialCard(98, Symbol.EMPTY, Symbol.PLANT, Symbol.ANIMAL, Symbol.INSECT, Symbol.FUNGI, 0, CardType.INITIAL, 2, 0, 1, 0, Symbol.ANIMAL, Symbol.EMPTY, Symbol.FUNGI, Symbol.EMPTY);
    Card c99 = new InitialCard(99, Symbol.EMPTY, Symbol.INSECT, Symbol.ANIMAL, Symbol.PLANT, Symbol.FUNGI, 0, CardType.INITIAL, 1, 1, 0, 0, Symbol.EMPTY, Symbol.EMPTY, Symbol.EMPTY, Symbol.EMPTY);
    Card c100 = new InitialCard(100, Symbol.EMPTY, Symbol.PLANT, Symbol.INSECT, Symbol.FUNGI, Symbol.ANIMAL, 0, CardType.INITIAL, 0, 0, 1, 1,Symbol.EMPTY, Symbol.EMPTY, Symbol.EMPTY, Symbol.EMPTY);
    Card c101 = new InitialCard(101, Symbol.EMPTY, Symbol.INSECT, Symbol.FUNGI, Symbol.ANIMAL, Symbol.PLANT, 0, CardType.INITIAL, 0, 1, 1, 1, Symbol.EMPTY, Symbol.EMPTY, Symbol.HIDDEN, Symbol.HIDDEN);
    Card C102 = new InitialCard(102, Symbol.EMPTY, Symbol.FUNGI, Symbol.ANIMAL, Symbol.INSECT, Symbol.PLANT, 0, CardType.INITIAL, 1, 1, 1, 0, Symbol.EMPTY, Symbol.EMPTY, Symbol.HIDDEN, Symbol.HIDDEN);


    public Card getC(int i){
        return c1;
    }

}
