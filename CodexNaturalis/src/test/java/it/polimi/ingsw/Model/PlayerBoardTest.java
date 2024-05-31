package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enums.CardType;
import it.polimi.ingsw.model.enums.GoalType;
import it.polimi.ingsw.model.enums.Symbol;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import static it.polimi.ingsw.model.enums.Symbol.COVERED_ANGLE;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class PlayerBoardTest {
    PlayerBoard playerBoard;


    @Before
    public void setUp() throws FileNotFoundException {
        playerBoard = new PlayerBoard();


    }

    @After
    public void teardown() {

    }

    @Test
    public void getSymbolsList_correctOutput() {
        Card c98 = new InitialCard(98, Symbol.EMPTY, Symbol.PLANT, Symbol.ANIMAL, Symbol.INSECT, Symbol.FUNGI, 0, CardType.INITIAL, 2, 0, 1, 0, Symbol.ANIMAL, Symbol.EMPTY, Symbol.FUNGI, Symbol.EMPTY);
        Card c6 = new Card(6, Symbol.FUNGI, Symbol.HIDDEN, Symbol.EMPTY, Symbol.EMPTY, Symbol.FUNGI, 1, CardType.RESOURCE);
        Card c21 = new Card(21, Symbol.ANIMAL, Symbol.ANIMAL, Symbol.ANIMAL, Symbol.HIDDEN, Symbol.EMPTY, 0, CardType.RESOURCE);
        Card c38 = new Card(38, Symbol.INSECT, Symbol.INSECT, Symbol.PLANT, Symbol.HIDDEN, Symbol.INK_BOTTLE, 0, CardType.RESOURCE);
        Card c33 = new Card(33, Symbol.INSECT, Symbol.INSECT, Symbol.HIDDEN, Symbol.EMPTY, Symbol.INSECT, 0, CardType.RESOURCE);
        Card c8 = new Card(8, Symbol.FUNGI, Symbol.FUNGI, Symbol.INSECT, Symbol.HIDDEN, Symbol.PARCHMENT, 0, CardType.RESOURCE);
        Card c10 = new Card(10, Symbol.FUNGI, Symbol.HIDDEN, Symbol.FEATHER, Symbol.FUNGI, Symbol.PLANT, 0, CardType.RESOURCE);
        Card c30 = new Card(30, Symbol.ANIMAL, Symbol.HIDDEN, Symbol.INSECT, Symbol.ANIMAL, Symbol.INK_BOTTLE, 0, CardType.RESOURCE);
        Card c22 = new Card(22, Symbol.ANIMAL, Symbol.EMPTY, Symbol.ANIMAL, Symbol.ANIMAL, Symbol.HIDDEN, 0, CardType.RESOURCE);
        Card c64 = new GoldCard(64, Symbol.ANIMAL, Symbol.EMPTY, Symbol.EMPTY, Symbol.EMPTY, Symbol.HIDDEN, 2, CardType.GOLD,
                COVERED_ANGLE, 0, 0, 3, 1);
        Card c3 = new Card(3, Symbol.FUNGI, Symbol.EMPTY, Symbol.HIDDEN, Symbol.FUNGI, Symbol.FUNGI, 0, CardType.RESOURCE);
        Card c40 = new Card(40, Symbol.INSECT, Symbol.HIDDEN, Symbol.FEATHER, Symbol.INSECT, Symbol.ANIMAL, 0, CardType.RESOURCE);
        Card c26 = new Card(26, Symbol.ANIMAL, Symbol.EMPTY, Symbol.HIDDEN, Symbol.ANIMAL, Symbol.EMPTY, 1, CardType.RESOURCE);
        Card c29 = new Card(29, Symbol.ANIMAL, Symbol.FEATHER, Symbol.HIDDEN, Symbol.FUNGI, Symbol.ANIMAL, 0, CardType.RESOURCE);
        Card c31 = new Card(31, Symbol.INSECT, Symbol.INSECT, Symbol.INSECT, Symbol.HIDDEN, Symbol.EMPTY, 0, CardType.RESOURCE);
        Card c39 = new Card(39, Symbol.INSECT, Symbol.PARCHMENT, Symbol.INSECT, Symbol.FUNGI, Symbol.HIDDEN, 0, CardType.RESOURCE);
        ObjectiveCard c81 = new ObjectiveCard(81, 2, GoalType.REDG);
        ObjectiveCard c88 = new ObjectiveCard(88, 3, GoalType.VVB);
        ObjectiveCard c89 = new ObjectiveCard(89, 2, GoalType.FFF);
        ObjectiveCard c91 = new ObjectiveCard(91, 2, GoalType.AAA);
        ObjectiveCard c92 = new ObjectiveCard(92, 2, GoalType.III);
        ObjectiveCard c93 = new ObjectiveCard(93, 3, GoalType.BFP);
        ObjectiveCard c94 = new ObjectiveCard(94, 2, GoalType.PP);
        ObjectiveCard c95 = new ObjectiveCard(95, 2, GoalType.BB);
        ObjectiveCard c96 = new ObjectiveCard(96, 2, GoalType.FF);

        playerBoard.placeInitCard((InitialCard) c98, false);

        playerBoard.placeCard(c6, false, 1, 1);
        playerBoard.placeCard(c21, true, 1, -1);
        playerBoard.placeCard(c38, false, -1, 1);
        playerBoard.placeCard(c33, false, -1, -1);
        playerBoard.placeCard(c8, false, 2, 2);
        playerBoard.placeCard(c10, false, 3, 3);
        playerBoard.placeCard(c30, false, -2, 2);
        playerBoard.placeCard(c22, false, -1, 3);
        playerBoard.placeCard(c64, false, 0, -2);
        playerBoard.placeCard(c3, false, 2, -2);
        playerBoard.placeCard(c40, false, 1, -3);
        playerBoard.placeCard(c26, false, 3, -3);
        playerBoard.placeCard(c29, false, 2, -4);
        playerBoard.placeCard(c31, false, 1, -5);
        playerBoard.placeCard(c39, false, 2, 0);


        int[] expected = {6, 2, 6, 5, 3, 2, 2};

        assertArrayEquals(expected, playerBoard.getSymbolList());

    }
    @Test
    public void getBoardCards_correctOutput() {
        Card c98 = new InitialCard(98, Symbol.EMPTY, Symbol.PLANT, Symbol.ANIMAL, Symbol.INSECT, Symbol.FUNGI, 0, CardType.INITIAL, 2, 0, 1, 0, Symbol.ANIMAL, Symbol.EMPTY, Symbol.FUNGI, Symbol.EMPTY);
        Card c6 = new Card(6, Symbol.FUNGI, Symbol.HIDDEN, Symbol.EMPTY, Symbol.EMPTY, Symbol.FUNGI, 1, CardType.RESOURCE);
        Card c21 = new Card(21, Symbol.ANIMAL, Symbol.ANIMAL, Symbol.ANIMAL, Symbol.HIDDEN, Symbol.EMPTY, 0, CardType.RESOURCE);
        Card c38 = new Card(38, Symbol.INSECT, Symbol.INSECT, Symbol.PLANT, Symbol.HIDDEN, Symbol.INK_BOTTLE, 0, CardType.RESOURCE);
        Card c33 = new Card(33, Symbol.INSECT, Symbol.INSECT, Symbol.HIDDEN, Symbol.EMPTY, Symbol.INSECT, 0, CardType.RESOURCE);
        Card c8 = new Card(8, Symbol.FUNGI, Symbol.FUNGI, Symbol.INSECT, Symbol.HIDDEN, Symbol.PARCHMENT, 0, CardType.RESOURCE);
        Card c10 = new Card(10, Symbol.FUNGI, Symbol.HIDDEN, Symbol.FEATHER, Symbol.FUNGI, Symbol.PLANT, 0, CardType.RESOURCE);
        Card c30 = new Card(30, Symbol.ANIMAL, Symbol.HIDDEN, Symbol.INSECT, Symbol.ANIMAL, Symbol.INK_BOTTLE, 0, CardType.RESOURCE);
        Card c22 = new Card(22, Symbol.ANIMAL, Symbol.EMPTY, Symbol.ANIMAL, Symbol.ANIMAL, Symbol.HIDDEN, 0, CardType.RESOURCE);
        Card c64 = new GoldCard(64, Symbol.ANIMAL, Symbol.EMPTY, Symbol.EMPTY, Symbol.EMPTY, Symbol.HIDDEN, 2, CardType.GOLD,
                COVERED_ANGLE, 0, 0, 3, 1);


        playerBoard.placeInitCard((InitialCard) c98, false);
        playerBoard.placeCard(c6, false, 1, 1);
        playerBoard.placeCard(c21, true, 1, -1);
        playerBoard.placeCard(c38, false, -1, 1);
        playerBoard.placeCard(c33, false, -1, -1);
        playerBoard.placeCard(c8, false, 2, 2);
        playerBoard.placeCard(c10, false, 3, 3);
        playerBoard.placeCard(c30, false, -2, 2);
        playerBoard.placeCard(c22, false, -1, 3);
        playerBoard.placeCard(c64, false, 0, -2);


        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(98, 6, 21, 38, 33,8,10,30,22,64));

        assertEquals(expected, playerBoard.getBoardCards());

    }
    @Test
    public void placeCard_correctInput_correctOutput() {
        Card c98 = new InitialCard(98, Symbol.EMPTY, Symbol.PLANT, Symbol.ANIMAL, Symbol.INSECT, Symbol.FUNGI, 0, CardType.INITIAL, 2, 0, 1, 0, Symbol.ANIMAL, Symbol.EMPTY, Symbol.FUNGI, Symbol.EMPTY);
        Card c6 = new Card(6, Symbol.FUNGI, Symbol.HIDDEN, Symbol.EMPTY, Symbol.EMPTY, Symbol.FUNGI, 1, CardType.RESOURCE);
        Card c21 = new Card(21, Symbol.ANIMAL, Symbol.ANIMAL, Symbol.ANIMAL, Symbol.HIDDEN, Symbol.EMPTY, 0, CardType.RESOURCE);
        Card c38 = new Card(38, Symbol.INSECT, Symbol.INSECT, Symbol.PLANT, Symbol.HIDDEN, Symbol.INK_BOTTLE, 0, CardType.RESOURCE);
        Card c33 = new Card(33, Symbol.INSECT, Symbol.INSECT, Symbol.HIDDEN, Symbol.EMPTY, Symbol.INSECT, 0, CardType.RESOURCE);
        Card c8 = new Card(8, Symbol.FUNGI, Symbol.FUNGI, Symbol.INSECT, Symbol.HIDDEN, Symbol.PARCHMENT, 0, CardType.RESOURCE);
        Card c10 = new Card(10, Symbol.FUNGI, Symbol.HIDDEN, Symbol.FEATHER, Symbol.FUNGI, Symbol.PLANT, 0, CardType.RESOURCE);
        Card c30 = new Card(30, Symbol.ANIMAL, Symbol.HIDDEN, Symbol.INSECT, Symbol.ANIMAL, Symbol.INK_BOTTLE, 0, CardType.RESOURCE);
        Card c22 = new Card(22, Symbol.ANIMAL, Symbol.EMPTY, Symbol.ANIMAL, Symbol.ANIMAL, Symbol.HIDDEN, 0, CardType.RESOURCE);
        Card c64 = new GoldCard(64, Symbol.ANIMAL, Symbol.EMPTY, Symbol.EMPTY, Symbol.EMPTY, Symbol.HIDDEN, 2, CardType.GOLD,
                Symbol.COVERED_ANGLE, 0, 0, 3, 1);
        playerBoard.placeCard(c6, false, 1, 1);
        playerBoard.placeCard(c21, true, 1, -1);
        playerBoard.placeCard(c38, false, -1, 1);
        playerBoard.placeCard(c33, false, -1, -1);
        playerBoard.placeCard(c8, false, 2, 2);
        playerBoard.placeCard(c10, false, 3, 3);
        playerBoard.placeCard(c30, false, -2, 2);
        playerBoard.placeCard(c22, false, -1, 3);

        int actual = playerBoard.placeCard(c64, false, 0, -2);
        int expected = 4;
        assertEquals(expected, actual);

    }





    @Test
    public void calculateGoalPoint_correctInput_correctOutput(){

        Card c98 = new InitialCard(98, Symbol.EMPTY, Symbol.PLANT, Symbol.ANIMAL, Symbol.INSECT, Symbol.FUNGI, 0, CardType.INITIAL, 2, 0, 1, 0, Symbol.ANIMAL, Symbol.EMPTY, Symbol.FUNGI, Symbol.EMPTY);
        Card c6 = new Card(6, Symbol.FUNGI,Symbol.HIDDEN, Symbol.EMPTY, Symbol.EMPTY, Symbol.FUNGI, 1, CardType.RESOURCE);
        Card c21 = new Card(21, Symbol.ANIMAL, Symbol.ANIMAL, Symbol.ANIMAL,  Symbol.HIDDEN, Symbol.EMPTY, 0, CardType.RESOURCE);
        Card c38 = new Card(38, Symbol.INSECT, Symbol.INSECT, Symbol.PLANT, Symbol.HIDDEN, Symbol.INK_BOTTLE, 0, CardType.RESOURCE);
        Card c33 = new Card(33, Symbol.INSECT, Symbol.INSECT, Symbol.HIDDEN, Symbol.EMPTY, Symbol.INSECT, 0, CardType.RESOURCE);
        Card c8 = new Card(8, Symbol.FUNGI,Symbol.FUNGI, Symbol.INSECT, Symbol.HIDDEN, Symbol.PARCHMENT, 0, CardType.RESOURCE);
        Card c10 = new Card(10, Symbol.FUNGI,Symbol.HIDDEN, Symbol.FEATHER, Symbol.FUNGI, Symbol.PLANT, 0, CardType.RESOURCE);
        Card c30 = new Card(30, Symbol.ANIMAL, Symbol.HIDDEN, Symbol.INSECT, Symbol.ANIMAL, Symbol.INK_BOTTLE, 0, CardType.RESOURCE);
        Card c22 = new Card(22, Symbol.ANIMAL, Symbol.EMPTY, Symbol.ANIMAL, Symbol.ANIMAL, Symbol.HIDDEN, 0, CardType.RESOURCE);
        Card c64 = new GoldCard(64, Symbol.ANIMAL, Symbol.EMPTY, Symbol.EMPTY, Symbol.EMPTY, Symbol.HIDDEN, 2, CardType.GOLD,
                Symbol.COVERED_ANGLE, 0,0,3,1);
        Card c3 = new Card(3, Symbol.FUNGI,Symbol.EMPTY, Symbol.HIDDEN, Symbol.FUNGI, Symbol.FUNGI, 0,CardType.RESOURCE);
        Card c40 = new Card(40, Symbol.INSECT, Symbol.HIDDEN, Symbol.FEATHER, Symbol.INSECT, Symbol.ANIMAL, 0, CardType.RESOURCE);
        Card c26 = new Card(26, Symbol.ANIMAL, Symbol.EMPTY, Symbol.HIDDEN, Symbol.ANIMAL, Symbol.EMPTY, 1,  CardType.RESOURCE);
        Card c29 = new Card(29, Symbol.ANIMAL, Symbol.FEATHER, Symbol.HIDDEN, Symbol.FUNGI, Symbol.ANIMAL, 0, CardType.RESOURCE);
        Card c31 = new Card(31, Symbol.INSECT, Symbol.INSECT, Symbol.INSECT, Symbol.HIDDEN, Symbol.EMPTY, 0, CardType.RESOURCE);
        Card c39 = new Card(39, Symbol.INSECT, Symbol.PARCHMENT, Symbol.INSECT, Symbol.FUNGI, Symbol.HIDDEN, 0, CardType.RESOURCE);


        playerBoard.placeInitCard((InitialCard) c98, false);

        playerBoard.placeCard(c6, false, 1, 1);
        playerBoard.placeCard(c21, true, 1, -1);
        playerBoard.placeCard(c38, false, -1, 1);
        playerBoard.placeCard(c33, false, -1, -1);
        playerBoard.placeCard(c8, false, 2, 2);
        playerBoard.placeCard(c10, false, 3, 3);
        playerBoard.placeCard(c30, false, -2, 2);
        playerBoard.placeCard(c22, false, -1, 3);
        playerBoard.placeCard(c64, false, 0, -2);
        playerBoard.placeCard(c3, false, 2, -2);
        playerBoard.placeCard(c40, false, 1, -3);
        playerBoard.placeCard(c26, false, 3, -3);
        playerBoard.placeCard(c29, false, 2, -4);
        playerBoard.placeCard(c31, false, 1, -5);
        playerBoard.placeCard(c39, false, 2, 0);

        ObjectiveCard c81 = new ObjectiveCard(81, 2, GoalType.REDG);
        ObjectiveCard c88 = new ObjectiveCard(88, 3, GoalType.VVB);
        ObjectiveCard c89 = new ObjectiveCard(89, 2, GoalType.FFF);
        ObjectiveCard c91 = new ObjectiveCard(91, 2, GoalType.AAA);
        ObjectiveCard c92 = new ObjectiveCard(92, 2, GoalType.III);
        ObjectiveCard c93 = new ObjectiveCard(93, 3, GoalType.BFP);
        ObjectiveCard c94 = new ObjectiveCard(94, 2, GoalType.PP);
        ObjectiveCard c95 = new ObjectiveCard(95, 2, GoalType.BB);
        ObjectiveCard c96 = new ObjectiveCard(96, 2, GoalType.FF);

        int expected1 = 2;
        int actual1 = playerBoard.calculateGoalPoint(c81);
        int expected2 = 6;
        int actual2 = playerBoard.calculateGoalPoint(c88);
        int expected3 = 4;
        int actual3 = playerBoard.calculateGoalPoint(c89);
        int expected4 = 4;
        int actual4 = playerBoard.calculateGoalPoint(c91);
        int expected5 = 2;
        int actual5 = playerBoard.calculateGoalPoint(c92);
        int expected6 = 6;
        int actual6 = playerBoard.calculateGoalPoint(c93);
        int expected7 = 2;
        int actual7 = playerBoard.calculateGoalPoint(c94);
        int expected8 = 2;
        int actual8 = playerBoard.calculateGoalPoint(c95);
        int expected9 = 2;
        int actual9 = playerBoard.calculateGoalPoint(c96);



        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
        assertEquals(expected3, actual3);
        assertEquals(expected4, actual4);
        assertEquals(expected5, actual5);
        assertEquals(expected6, actual6);
        assertEquals(expected7, actual7);
        assertEquals(expected8, actual8);
        assertEquals(expected9, actual9);
    }









}

