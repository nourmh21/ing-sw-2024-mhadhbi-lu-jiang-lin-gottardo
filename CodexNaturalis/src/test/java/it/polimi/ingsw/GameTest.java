package it.polimi.ingsw;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.enums.Color;

import it.polimi.ingsw.model.exceptions.TooFewPlayersException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for class Game
 */
public class GameTest {

    private Game game;
    int numOfPlayer=3;
    private Player p1;
    private Player p2;
    private Player p3;


    @Before
    public void setGame(){
        game = new Game();
        p1 = new Player("A", 1, Color.RED);
        p2 = new Player ("B", 2, Color.GREEN);
        p3 = new Player("C", 3, Color.YELLOW);
    }

    @Test
    public void AddPlayers_gameAlreadyStarted_throwTooPlayersException(){
        game.addPlayers(p1);
        assertEquals(p1.getPosition(), game.getPlayers().size());

        game.addPlayers(p2);
        game.addPlayers(p3);
        assertEquals(p3.getPosition(), game.getPlayers().size());

    }


    /*@Test
    public void Is20(){


    }*/


    @Test
    public void CheckMaxPoint_ExistingPlayerPointOver20_GiveWinner(){
        String name;
        name = p1.getNickName();
        name = game.checkMaxPoint();
        assertEquals("A", game.checkMaxPoint());

    }

    @Test
    public void disconnect_InvalidNumOfPlayer_throwTooFewPlayersException(){
        p1.setDisconnected();
        assertFalse(p1.isConnected());
        if (numOfPlayer>1){
            assertEquals(numOfPlayer, game.getPlayers().size());
        }
        else
            throw new TooFewPlayersException();


    }

    @Test
    public void reconnect(){
        p1.setConnected();
        assertTrue(p1.isConnected());
        assertEquals(numOfPlayer, game.getPlayers().size());
    }



}
