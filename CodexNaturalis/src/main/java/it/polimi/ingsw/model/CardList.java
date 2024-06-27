package it.polimi.ingsw.model;


import it.polimi.ingsw.model.enums.CardType;
import it.polimi.ingsw.model.enums.GoalType;
import it.polimi.ingsw.model.enums.Symbol;

import java.util.ArrayList;
import java.util.List;

/**
 * The CardList class represents a list of cards)
 */
public final class CardList {
    private final List<Object> cardList;

    /**
     * the constructor creates an empty ArrayList of cards. Each game card is then added through the add() method
     */
    public CardList() {
        cardList = new ArrayList<>();

        // RESOURCE CARDS
        cardList.add(new Card(1, Symbol.FUNGI, Symbol.FUNGI, Symbol.EMPTY, Symbol.HIDDEN, Symbol.FUNGI, 0, CardType.RESOURCE));
        cardList.add(new Card(2, Symbol.FUNGI, Symbol.FUNGI, Symbol.FUNGI, Symbol.EMPTY, Symbol.HIDDEN, 0, CardType.RESOURCE));
        cardList.add(new Card(3, Symbol.FUNGI, Symbol.EMPTY, Symbol.HIDDEN, Symbol.FUNGI, Symbol.FUNGI, 0, CardType.RESOURCE));
        cardList.add(new Card(4, Symbol.FUNGI, Symbol.HIDDEN, Symbol.FUNGI, Symbol.FUNGI, Symbol.EMPTY, 0, CardType.RESOURCE));
        cardList.add(new Card(5, Symbol.FUNGI, Symbol.HIDDEN, Symbol.FEATHER, Symbol.FUNGI, Symbol.PLANT, 0, CardType.RESOURCE));
        cardList.add(new Card(6, Symbol.FUNGI, Symbol.INK_BOTTLE, Symbol.FUNGI, Symbol.ANIMAL, Symbol.HIDDEN, 0, CardType.RESOURCE));
        cardList.add(new Card(7, Symbol.FUNGI, Symbol.FUNGI, Symbol.INSECT, Symbol.EMPTY, Symbol.PARCHMENT, 0, CardType.RESOURCE));
        cardList.add(new Card(8, Symbol.FUNGI, Symbol.EMPTY, Symbol.FUNGI, Symbol.HIDDEN, Symbol.EMPTY, 1, CardType.RESOURCE));
        cardList.add(new Card(9, Symbol.FUNGI, Symbol.FUNGI, Symbol.HIDDEN, Symbol.EMPTY, Symbol.EMPTY, 1, CardType.RESOURCE));
        cardList.add(new Card(10, Symbol.FUNGI, Symbol.HIDDEN, Symbol.EMPTY, Symbol.EMPTY, Symbol.FUNGI, 1, CardType.RESOURCE));

        cardList.add(new Card(11, Symbol.PLANT, Symbol.PLANT, Symbol.EMPTY, Symbol.HIDDEN, Symbol.PLANT, 0, CardType.RESOURCE));
        cardList.add(new Card(12, Symbol.PLANT, Symbol.PLANT, Symbol.PLANT, Symbol.EMPTY, Symbol.HIDDEN, 0, CardType.RESOURCE));
        cardList.add(new Card(13, Symbol.PLANT, Symbol.EMPTY, Symbol.HIDDEN, Symbol.PLANT, Symbol.PLANT, 0, CardType.RESOURCE));
        cardList.add(new Card(14, Symbol.PLANT, Symbol.HIDDEN, Symbol.PLANT, Symbol.PLANT, Symbol.EMPTY, 0, CardType.RESOURCE));
        cardList.add(new Card(15, Symbol.PLANT, Symbol.HIDDEN, Symbol.INSECT, Symbol.PLANT, Symbol.FEATHER, 0, CardType.RESOURCE));
        cardList.add(new Card(16, Symbol.PLANT, Symbol.FUNGI, Symbol.PLANT, Symbol.INK_BOTTLE, Symbol.HIDDEN, 0, CardType.RESOURCE));
        cardList.add(new Card(17, Symbol.PLANT, Symbol.PARCHMENT, Symbol.HIDDEN, Symbol.ANIMAL, Symbol.PLANT, 0, CardType.RESOURCE));
        cardList.add(new Card(18, Symbol.PLANT, Symbol.EMPTY, Symbol.EMPTY, Symbol.HIDDEN, Symbol.PLANT, 1, CardType.RESOURCE));
        cardList.add(new Card(19, Symbol.PLANT, Symbol.EMPTY, Symbol.EMPTY, Symbol.PLANT, Symbol.HIDDEN, 1, CardType.RESOURCE));
        cardList.add(new Card(20, Symbol.PLANT, Symbol.HIDDEN, Symbol.PLANT, Symbol.EMPTY, Symbol.EMPTY, 1, CardType.RESOURCE));

        cardList.add(new Card(21, Symbol.ANIMAL, Symbol.ANIMAL, Symbol.ANIMAL, Symbol.HIDDEN, Symbol.EMPTY, 0, CardType.RESOURCE));
        cardList.add(new Card(22, Symbol.ANIMAL, Symbol.HIDDEN, Symbol.EMPTY, Symbol.ANIMAL, Symbol.ANIMAL, 0, CardType.RESOURCE));
        cardList.add(new Card(23, Symbol.ANIMAL, Symbol.ANIMAL, Symbol.HIDDEN, Symbol.EMPTY, Symbol.ANIMAL, 0, CardType.RESOURCE));
        cardList.add(new Card(24, Symbol.ANIMAL, Symbol.EMPTY, Symbol.ANIMAL, Symbol.ANIMAL, Symbol.HIDDEN, 0, CardType.RESOURCE));
        cardList.add(new Card(25, Symbol.ANIMAL, Symbol.HIDDEN, Symbol.INSECT, Symbol.ANIMAL, Symbol.INK_BOTTLE, 0, CardType.RESOURCE));
        cardList.add(new Card(26, Symbol.ANIMAL, Symbol.PLANT, Symbol.ANIMAL, Symbol.PARCHMENT, Symbol.HIDDEN, 0, CardType.RESOURCE));
        cardList.add(new Card(27, Symbol.ANIMAL, Symbol.FEATHER, Symbol.HIDDEN, Symbol.FUNGI, Symbol.ANIMAL, 0, CardType.RESOURCE));
        cardList.add(new Card(28, Symbol.ANIMAL, Symbol.HIDDEN, Symbol.EMPTY, Symbol.EMPTY, Symbol.ANIMAL, 1, CardType.RESOURCE));
        cardList.add(new Card(29, Symbol.ANIMAL, Symbol.EMPTY, Symbol.HIDDEN, Symbol.ANIMAL, Symbol.EMPTY, 1, CardType.RESOURCE));
        cardList.add(new Card(30, Symbol.ANIMAL, Symbol.EMPTY, Symbol.ANIMAL, Symbol.HIDDEN, Symbol.EMPTY, 1, CardType.RESOURCE));

        cardList.add(new Card(31, Symbol.INSECT, Symbol.INSECT, Symbol.INSECT, Symbol.HIDDEN, Symbol.EMPTY, 0, CardType.RESOURCE));
        cardList.add(new Card(32, Symbol.INSECT, Symbol.HIDDEN, Symbol.EMPTY, Symbol.INSECT, Symbol.INSECT, 0, CardType.RESOURCE));
        cardList.add(new Card(33, Symbol.INSECT, Symbol.INSECT, Symbol.HIDDEN, Symbol.EMPTY, Symbol.INSECT, 0, CardType.RESOURCE));
        cardList.add(new Card(34, Symbol.INSECT, Symbol.EMPTY, Symbol.INSECT, Symbol.INSECT, Symbol.HIDDEN, 0, CardType.RESOURCE));
        cardList.add(new Card(35, Symbol.INSECT, Symbol.HIDDEN, Symbol.FEATHER, Symbol.INSECT, Symbol.ANIMAL, 0, CardType.RESOURCE));
        cardList.add(new Card(36, Symbol.INSECT, Symbol.PARCHMENT, Symbol.INSECT, Symbol.FUNGI, Symbol.HIDDEN, 0, CardType.RESOURCE));
        cardList.add(new Card(37, Symbol.INSECT, Symbol.INSECT, Symbol.PLANT, Symbol.HIDDEN, Symbol.INK_BOTTLE, 0, CardType.RESOURCE));
        cardList.add(new Card(38, Symbol.INSECT, Symbol.INSECT, Symbol.HIDDEN, Symbol.EMPTY, Symbol.EMPTY, 1, CardType.RESOURCE));
        cardList.add(new Card(39, Symbol.INSECT, Symbol.EMPTY, Symbol.EMPTY, Symbol.INSECT, Symbol.HIDDEN, 1, CardType.RESOURCE));
        cardList.add(new Card(40, Symbol.INSECT, Symbol.HIDDEN, Symbol.INSECT, Symbol.EMPTY, Symbol.EMPTY, 1, CardType.RESOURCE));

        // GOLD CARDS
        cardList.add(new GoldCard(41, Symbol.FUNGI, Symbol.HIDDEN, Symbol.EMPTY, Symbol.FEATHER, Symbol.EMPTY, 1, CardType.GOLD,
                Symbol.FEATHER, 2, 0, 1, 0));
        cardList.add(new GoldCard(42, Symbol.FUNGI, Symbol.EMPTY, Symbol.INK_BOTTLE, Symbol.EMPTY, Symbol.HIDDEN, 1, CardType.GOLD,
                Symbol.INK_BOTTLE, 2, 1, 0, 0));
        cardList.add(new GoldCard(43, Symbol.FUNGI, Symbol.PARCHMENT, Symbol.EMPTY, Symbol.HIDDEN, Symbol.EMPTY, 1, CardType.GOLD,
                Symbol.PARCHMENT, 2, 0, 0, 1));
        cardList.add(new GoldCard(44, Symbol.FUNGI, Symbol.EMPTY, Symbol.EMPTY, Symbol.EMPTY, Symbol.HIDDEN, 2, CardType.GOLD,
                Symbol.COVERED_ANGLE, 3, 0, 1, 0));
        cardList.add(new GoldCard(45, Symbol.FUNGI, Symbol.EMPTY, Symbol.EMPTY, Symbol.HIDDEN, Symbol.EMPTY, 2, CardType.GOLD,
                Symbol.COVERED_ANGLE, 3, 1, 0, 0));
        cardList.add(new GoldCard(46, Symbol.FUNGI, Symbol.EMPTY, Symbol.HIDDEN, Symbol.EMPTY, Symbol.EMPTY, 2, CardType.GOLD,
                Symbol.COVERED_ANGLE, 3, 0, 0, 1));
        cardList.add(new GoldCard(47, Symbol.FUNGI, Symbol.EMPTY, Symbol.HIDDEN, Symbol.HIDDEN, Symbol.INK_BOTTLE, 3, CardType.GOLD,
                Symbol.EMPTY, 3, 0, 0, 0));
        cardList.add(new GoldCard(48, Symbol.FUNGI, Symbol.FEATHER, Symbol.EMPTY, Symbol.HIDDEN, Symbol.HIDDEN, 3, CardType.GOLD,
                Symbol.EMPTY, 3, 0, 0, 0));
        cardList.add(new GoldCard(49, Symbol.FUNGI, Symbol.HIDDEN, Symbol.PARCHMENT, Symbol.EMPTY, Symbol.HIDDEN, 3, CardType.GOLD,
                Symbol.EMPTY, 3, 0, 0, 0));
        cardList.add(new GoldCard(50, Symbol.FUNGI, Symbol.EMPTY, Symbol.HIDDEN, Symbol.HIDDEN, Symbol.EMPTY, 5, CardType.GOLD,
                Symbol.EMPTY, 5, 0, 0, 0));

        cardList.add(new GoldCard(51, Symbol.PLANT, Symbol.FEATHER, Symbol.EMPTY, Symbol.HIDDEN, Symbol.EMPTY, 1, CardType.GOLD,
                Symbol.FEATHER, 0, 2, 0, 1));
        cardList.add(new GoldCard(52, Symbol.PLANT, Symbol.EMPTY, Symbol.PARCHMENT, Symbol.EMPTY, Symbol.HIDDEN, 1, CardType.GOLD,
                Symbol.PARCHMENT, 1, 2, 0, 0));
        cardList.add(new GoldCard(53, Symbol.PLANT, Symbol.EMPTY, Symbol.HIDDEN, Symbol.EMPTY, Symbol.INK_BOTTLE, 1, CardType.GOLD,
                Symbol.INK_BOTTLE, 0, 2, 1, 0));
        cardList.add(new GoldCard(54, Symbol.PLANT, Symbol.HIDDEN, Symbol.EMPTY, Symbol.EMPTY, Symbol.EMPTY, 2, CardType.GOLD,
                Symbol.COVERED_ANGLE, 0, 3, 0, 1));
        cardList.add(new GoldCard(55, Symbol.PLANT, Symbol.EMPTY, Symbol.EMPTY, Symbol.HIDDEN, Symbol.EMPTY, 2, CardType.GOLD,
                Symbol.COVERED_ANGLE, 0, 3, 1, 0));
        cardList.add(new GoldCard(56, Symbol.PLANT, Symbol.EMPTY, Symbol.HIDDEN, Symbol.EMPTY, Symbol.EMPTY, 2, CardType.GOLD,
                Symbol.COVERED_ANGLE, 1, 3, 0, 0));
        cardList.add(new GoldCard(57, Symbol.PLANT, Symbol.EMPTY, Symbol.HIDDEN, Symbol.HIDDEN, Symbol.FEATHER, 3, CardType.GOLD,
                Symbol.EMPTY, 0, 3, 0, 0));
        cardList.add(new GoldCard(58, Symbol.PLANT, Symbol.PARCHMENT, Symbol.EMPTY, Symbol.HIDDEN, Symbol.HIDDEN, 3, CardType.GOLD,
                Symbol.EMPTY, 0, 3, 0, 0));
        cardList.add(new GoldCard(59, Symbol.PLANT, Symbol.HIDDEN, Symbol.INK_BOTTLE, Symbol.EMPTY, Symbol.HIDDEN, 3, CardType.GOLD,
                Symbol.EMPTY, 0, 3, 0, 0));
        cardList.add(new GoldCard(60, Symbol.PLANT, Symbol.EMPTY, Symbol.EMPTY, Symbol.HIDDEN, Symbol.HIDDEN, 5, CardType.GOLD,
                Symbol.EMPTY, 0, 5, 0, 0));

        cardList.add(new GoldCard(61, Symbol.ANIMAL, Symbol.INK_BOTTLE, Symbol.EMPTY, Symbol.HIDDEN, Symbol.EMPTY, 1, CardType.GOLD,
                Symbol.INK_BOTTLE, 0, 0, 2, 1));
        cardList.add(new GoldCard(62, Symbol.ANIMAL, Symbol.HIDDEN, Symbol.EMPTY, Symbol.PARCHMENT, Symbol.EMPTY, 1, CardType.GOLD,
                Symbol.PARCHMENT, 0, 1, 2, 0));
        cardList.add(new GoldCard(63, Symbol.ANIMAL, Symbol.EMPTY, Symbol.HIDDEN, Symbol.EMPTY, Symbol.FEATHER, 1, CardType.GOLD,
                Symbol.FEATHER, 1, 0, 2, 0));
        cardList.add(new GoldCard(64, Symbol.ANIMAL, Symbol.EMPTY, Symbol.EMPTY, Symbol.EMPTY, Symbol.HIDDEN, 2, CardType.GOLD,
                Symbol.COVERED_ANGLE, 0, 0, 3, 1));
        cardList.add(new GoldCard(65, Symbol.ANIMAL, Symbol.EMPTY, Symbol.HIDDEN, Symbol.EMPTY, Symbol.EMPTY, 2, CardType.GOLD,
                Symbol.COVERED_ANGLE, 1, 0, 3, 0));
        cardList.add(new GoldCard(66, Symbol.ANIMAL, Symbol.HIDDEN, Symbol.EMPTY, Symbol.EMPTY, Symbol.EMPTY, 2, CardType.GOLD,
                Symbol.COVERED_ANGLE, 0, 1, 3, 0));
        cardList.add(new GoldCard(67, Symbol.ANIMAL, Symbol.EMPTY, Symbol.HIDDEN, Symbol.HIDDEN, Symbol.PARCHMENT, 3, CardType.GOLD,
                Symbol.EMPTY, 0, 0, 3, 0));
        cardList.add(new GoldCard(68, Symbol.ANIMAL, Symbol.EMPTY, Symbol.INK_BOTTLE, Symbol.HIDDEN, Symbol.HIDDEN, 3, CardType.GOLD,
                Symbol.EMPTY, 0, 0, 3, 0));
        cardList.add(new GoldCard(69, Symbol.ANIMAL, Symbol.HIDDEN, Symbol.EMPTY, Symbol.FEATHER, Symbol.HIDDEN, 3, CardType.GOLD,
                Symbol.EMPTY, 0, 0, 3, 0));
        cardList.add(new GoldCard(70, Symbol.ANIMAL, Symbol.HIDDEN, Symbol.EMPTY, Symbol.EMPTY, Symbol.HIDDEN, 5, CardType.GOLD,
                Symbol.EMPTY, 0, 0, 5, 0));

        cardList.add(new GoldCard(71, Symbol.INSECT, Symbol.EMPTY, Symbol.FEATHER, Symbol.EMPTY, Symbol.HIDDEN, 1, CardType.GOLD,
                Symbol.FEATHER, 0, 1, 0, 2));
        cardList.add(new GoldCard(72, Symbol.INSECT, Symbol.EMPTY, Symbol.HIDDEN, Symbol.EMPTY, Symbol.PARCHMENT, 1, CardType.GOLD,
                Symbol.PARCHMENT, 0, 0, 1, 2));
        cardList.add(new GoldCard(73, Symbol.INSECT, Symbol.HIDDEN, Symbol.EMPTY, Symbol.INK_BOTTLE, Symbol.EMPTY, 1, CardType.GOLD,
                Symbol.INK_BOTTLE, 1, 0, 0, 2));
        cardList.add(new GoldCard(74, Symbol.INSECT, Symbol.EMPTY, Symbol.EMPTY, Symbol.EMPTY, Symbol.HIDDEN, 2, CardType.GOLD,
                Symbol.COVERED_ANGLE, 0, 0, 1, 3));
        cardList.add(new GoldCard(75, Symbol.INSECT, Symbol.EMPTY, Symbol.EMPTY, Symbol.HIDDEN, Symbol.EMPTY, 2, CardType.GOLD,
                Symbol.COVERED_ANGLE, 0, 1, 0, 3));
        cardList.add(new GoldCard(76, Symbol.INSECT, Symbol.EMPTY, Symbol.HIDDEN, Symbol.EMPTY, Symbol.EMPTY, 2, CardType.GOLD,
                Symbol.COVERED_ANGLE, 1, 0, 0, 3));
        cardList.add(new GoldCard(77, Symbol.INSECT, Symbol.INK_BOTTLE, Symbol.HIDDEN, Symbol.HIDDEN, Symbol.EMPTY, 3, CardType.GOLD,
                Symbol.EMPTY, 0, 0, 0, 3));
        cardList.add(new GoldCard(78, Symbol.INSECT, Symbol.EMPTY, Symbol.PARCHMENT, Symbol.HIDDEN, Symbol.HIDDEN, 3, CardType.GOLD,
                Symbol.EMPTY, 0, 0, 0, 3));
        cardList.add(new GoldCard(79, Symbol.INSECT, Symbol.HIDDEN, Symbol.HIDDEN, Symbol.EMPTY, Symbol.FEATHER, 3, CardType.GOLD,
                Symbol.EMPTY, 0, 0, 0, 3));
        cardList.add(new GoldCard(80, Symbol.INSECT, Symbol.EMPTY, Symbol.EMPTY, Symbol.HIDDEN, Symbol.HIDDEN, 5, CardType.GOLD,
                Symbol.EMPTY, 0, 0, 0, 5));

        //OBJECTIVE CARDS
        cardList.add(new ObjectiveCard(81, 2, GoalType.REDG));
        cardList.add(new ObjectiveCard(82, 2, GoalType.GREEND));
        cardList.add(new ObjectiveCard(83, 2, GoalType.BLUEG));
        cardList.add(new ObjectiveCard(84, 2, GoalType.VIOLAD));
        cardList.add(new ObjectiveCard(85, 3, GoalType.RRG));
        cardList.add(new ObjectiveCard(86, 3, GoalType.GGV));
        cardList.add(new ObjectiveCard(87, 3, GoalType.BBR));
        cardList.add(new ObjectiveCard(88, 3, GoalType.VVB));
        cardList.add(new ObjectiveCard(89, 2, GoalType.FFF));
        cardList.add(new ObjectiveCard(90, 2, GoalType.PPP));
        cardList.add(new ObjectiveCard(91, 2, GoalType.AAA));
        cardList.add(new ObjectiveCard(92, 2, GoalType.III));
        cardList.add(new ObjectiveCard(93, 3, GoalType.BFP));
        cardList.add(new ObjectiveCard(94, 2, GoalType.PP));
        cardList.add(new ObjectiveCard(95, 2, GoalType.BB));
        cardList.add(new ObjectiveCard(96, 2, GoalType.FF));

        //INITIAL CARDS
        cardList.add(new InitialCard(97, Symbol.EMPTY, Symbol.FUNGI, Symbol.PLANT, Symbol.ANIMAL, Symbol.INSECT, 0, CardType.INITIAL, 0, 1, 0, 2, Symbol.EMPTY, Symbol.PLANT, Symbol.EMPTY, Symbol.INSECT));
        cardList.add(new InitialCard(98, Symbol.EMPTY, Symbol.PLANT, Symbol.ANIMAL, Symbol.INSECT, Symbol.FUNGI, 0, CardType.INITIAL, 2, 0, 1, 0, Symbol.ANIMAL, Symbol.EMPTY, Symbol.FUNGI, Symbol.EMPTY));
        cardList.add(new InitialCard(99, Symbol.EMPTY, Symbol.INSECT, Symbol.ANIMAL, Symbol.PLANT, Symbol.FUNGI, 0, CardType.INITIAL, 1, 1, 0, 0, Symbol.EMPTY, Symbol.EMPTY, Symbol.EMPTY, Symbol.EMPTY));
        cardList.add(new InitialCard(100, Symbol.EMPTY, Symbol.PLANT, Symbol.INSECT, Symbol.FUNGI, Symbol.ANIMAL, 0, CardType.INITIAL, 0, 0, 1, 1, Symbol.EMPTY, Symbol.EMPTY, Symbol.EMPTY, Symbol.EMPTY));
        cardList.add(new InitialCard(101, Symbol.EMPTY, Symbol.INSECT, Symbol.FUNGI, Symbol.ANIMAL, Symbol.PLANT, 0, CardType.INITIAL, 0, 1, 1, 1, Symbol.EMPTY, Symbol.EMPTY, Symbol.HIDDEN, Symbol.HIDDEN));
        cardList.add(new InitialCard(102, Symbol.EMPTY, Symbol.FUNGI, Symbol.ANIMAL, Symbol.INSECT, Symbol.PLANT, 0, CardType.INITIAL, 1, 1, 1, 0, Symbol.EMPTY, Symbol.EMPTY, Symbol.HIDDEN, Symbol.HIDDEN));
    }

    /**
     * getter for the Card object
     *
     * @param id id of the card that needs to be returned
     * @return Card object
     */
    public Object getCard(int id) {
        return cardList.get(id - 1);
    }
}
