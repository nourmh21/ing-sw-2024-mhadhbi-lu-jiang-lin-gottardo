package it.polimi.ingsw.Model;

import it.polimi.ingsw.model.Desk;
import it.polimi.ingsw.model.enums.CardType;
import it.polimi.ingsw.model.exceptions.EmptyDeckException;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DeskTest {

    Desk desk = null;


    @Before
    public void setUp()
    {
        desk = new Desk();
    }

    @Test(expected = EmptyDeckException.class)
    public void pickOneCard_EmptyResourceCardDeck_throwsNullPointerException(){
        for (int i = 0; i < 37; i++){
            desk.pickOneCard(CardType.RESOURCE);
        }
        desk.pickOneCard(CardType.RESOURCE);
    }

    @Test(expected = EmptyDeckException.class)
    public void pickOneCard_EmptyGoldCardDeck_throwsNullPointerException(){
        for (int i = 0; i < 37; i++){
            desk.pickOneCard(CardType.GOLD);
        }
        desk.pickOneCard(CardType.GOLD);
    }


    @Test(expected = EmptyDeckException.class)
    public void pickOneCard_EmptyGoalCardDeck_throwsNullPointerException(){
        for (int i = 0; i < 16; i++){
            desk.pickOneCard(CardType.OBJECTIVE);
        }
        desk.pickOneCard(CardType.OBJECTIVE);
    }


    @Test(expected = EmptyDeckException.class)
    public void pickOneCard_EmptyInitialDeck_throwsNullPointerException(){
        for (int i = 0; i < 6; i++){
            desk.pickOneCard(CardType.INITIAL);
        }
        desk.pickOneCard(CardType.INITIAL);
    }


    @Test
    public void pickDisplayedNextRCard_PickCard_ReallyOut(){
        List<Integer> testList = desk.getDisplayedRCards();
        int initialSize = testList.size();
        desk.pickOneDisplayedRCard(testList.getFirst());
        assertEquals(testList.size(), initialSize - 1);

    }

    @Test
    public void pickDisplayedNextGCard_PickCard_ReallyOut(){
        List<Integer> testList = desk.getDisplayedGCards();
        int initialSize = testList.size();
        desk.pickOneDisplayedGCard(testList.getFirst());
        assertEquals(testList.size(), initialSize - 1);

    }


    @Test
    public void pickDisplayedNextRCard_PickCardWithUpdate_ReallyUpdated(){
        List<Integer> testList = desk.getDisplayedRCards();
        desk.pickOneDisplayedRCard(testList.getFirst());
        int sizeBeforeUpdating = testList.size();
        desk.updateDisplayedRCard();
        assertEquals(testList.size(), sizeBeforeUpdating + 1);
    }


    @Test
    public void pickDisplayedNextGCard_PickCardWithUpdate_ReallyUpdated(){
        List<Integer> testList = desk.getDisplayedGCards();
        desk.pickOneDisplayedGCard(testList.getFirst());
        int sizeBeforeUpdating = testList.size();
        desk.updateDisplayedGCard();
        assertEquals(testList.size(), sizeBeforeUpdating + 1);
    }


    @Test
    public void updateNextRCard_PickTwoTimes_ShouldNotBeTheSame(){
        int card1 = desk.pickNextRCard();
        desk.updateNextRCard();
        int card2 = desk.pickNextRCard();
        assertNotEquals(card1, card2);
    }


    @Test
    public void updateNextGCard_PickTwoTimes_ShouldNotBeTheSame(){
        int card1 = desk.pickNextGCard();
        desk.updateNextGCard();
        int card2 = desk.pickNextGCard();
        assertNotEquals(card1, card2);
    }


    @Test
    public void pickNextRCard_PickCardWithoutUpdate_ShouldGiveUsNull(){
        desk.pickNextRCard();
        assertNull(desk.pickNextRCard());
    }


    @Test
    public void pickNextGCard_PickCardWithoutUpdate_ShouldGiveUsNull(){
        desk.pickNextGCard();
        assertNull(desk.pickNextGCard());
    }


    @Test(expected = EmptyDeckException.class)
    public void pickOneCard_CalledByUpdatingMethodWhenTheDeckIsEmpty_throwsNullPointerException(){
        for (int i = 0; i < 37; i++){
            desk.pickOneCard(CardType.RESOURCE);
        }
        desk.pickOneDisplayedRCard(desk.getDisplayedRCards().getFirst());
        desk.updateDisplayedRCard();
    }

}

