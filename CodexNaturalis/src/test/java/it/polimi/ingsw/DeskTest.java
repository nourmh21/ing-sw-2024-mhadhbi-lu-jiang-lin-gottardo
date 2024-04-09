package it.polimi.ingsw;

import it.polimi.ingsw.model.Desk;
import it.polimi.ingsw.model.enums.CardType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DeskTest {

    Desk desk = null;


    @Before
    public void setUp()
    {
        desk = new Desk();
    }

    @Test(expected = NullPointerException.class)
    public void pickOneCard_EmptyResourceCardDeck_throwsNullPointerException(){
        for (int i = 0; i < 37; i++){
            desk.pickOneCard(CardType.RESOURCE);
        }
        desk.pickOneCard(CardType.RESOURCE);
    }

    @Test(expected = NullPointerException.class)
    public void pickOneCard_EmptyGoldCardDeck_throwsNullPointerException(){
        for (int i = 0; i < 37; i++){
            desk.pickOneCard(CardType.GOLD);
        }
        desk.pickOneCard(CardType.GOLD);
    }


    @Test(expected = NullPointerException.class)
    public void pickOneCard_EmptyGoalCardDeck_throwsNullPointerException(){
        for (int i = 0; i < 16; i++){
            desk.pickOneCard(CardType.GOAL);
        }
        desk.pickOneCard(CardType.GOAL);
    }


    @Test(expected = NullPointerException.class)
    public void pickOneCard_EmptyInitialDeck_throwsNullPointerException(){
        for (int i = 0; i < 6; i++){
            desk.pickOneCard(CardType.INITIAL);
        }
        desk.pickOneCard(CardType.INITIAL);
    }



}
