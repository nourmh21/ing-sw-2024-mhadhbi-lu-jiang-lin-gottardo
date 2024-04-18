package it.polimi.ingsw.Model;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.enums.CardType;
import it.polimi.ingsw.model.enums.GoalType;
import it.polimi.ingsw.model.enums.Symbol;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class PlayerBoardTest {
    PlayerBoard playerBoard = null;
    @Before
    public void setUp() throws FileNotFoundException{
        playerBoard = new PlayerBoard();
        Card c97 = new InitialCard(97, Symbol.EMPTY, Symbol.FUNGI, Symbol.PLANT, Symbol.ANIMAL, Symbol.INSECT, 0, CardType.INITIAL, 0, 1, 0, 2, Symbol.EMPTY, Symbol.PLANT, Symbol.EMPTY, Symbol.INSECT);
        Card c7 = new Card(7, Symbol.FUNGI,Symbol.FUNGI, Symbol.HIDDEN, Symbol.EMPTY, Symbol.EMPTY, 1, CardType.RESOURCE);
        Card c17 = new Card(17, Symbol.PLANT, Symbol.HIDDEN, Symbol.PLANT, Symbol.EMPTY, Symbol.EMPTY, 1, CardType.RESOURCE);
        Card c14 = new Card(14, Symbol.PLANT, Symbol.HIDDEN, Symbol.PLANT, Symbol.PLANT, Symbol.EMPTY, 0, CardType.RESOURCE);
        Card c23 = new Card(23, Symbol.ANIMAL, Symbol.ANIMAL, Symbol.HIDDEN, Symbol.EMPTY, Symbol.ANIMAL, 0, CardType.RESOURCE);
        Card c22 = new Card(22, Symbol.ANIMAL, Symbol.EMPTY, Symbol.ANIMAL, Symbol.ANIMAL, Symbol.HIDDEN, 0, CardType.RESOURCE);
        Card c31 = new Card(31, Symbol.INSECT, Symbol.INSECT, Symbol.INSECT, Symbol.HIDDEN, Symbol.EMPTY, 0, CardType.RESOURCE);
        Card c39 = new Card(39, Symbol.INSECT, Symbol.PARCHMENT, Symbol.INSECT, Symbol.FUNGI, Symbol.HIDDEN, 0, CardType.RESOURCE);
        Card c32 = new Card(32, Symbol.INSECT, Symbol.EMPTY, Symbol.INSECT, Symbol.INSECT, Symbol.HIDDEN, 0, CardType.RESOURCE);
        Card c75 = new GoldCard(75, Symbol.INSECT, Symbol.EMPTY, Symbol.EMPTY, Symbol.HIDDEN, Symbol.EMPTY, 2, CardType.GOLD, Symbol.COVERED_ANGLE, 0,1,0,2);
        ObjectiveCard c88 = new ObjectiveCard(88, 3, GoalType.VVB);
    }

    @Test(expected = NullPointerException.class)
    public void placeCard__throwsNullPointerException(){

    }
    public void placeInitCard_E_throwsNullPointerException(){

    }
    public void calculateGoalPoint__throwsNullPointerException(){

    }
    public void addcardtoHandcards__throwsNullPointerException(){

    }





}
