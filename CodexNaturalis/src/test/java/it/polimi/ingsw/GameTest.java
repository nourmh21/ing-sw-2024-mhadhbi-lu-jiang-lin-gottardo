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
    int numOfPlayer;
    private Player p1;
    private Player p2;
    private Player p3;


    @Before
    public void setGame(){
        game = new Game();
        p1=new Player("Rossi", 1, Color.RED);
        p2=new Player("Neri", 2, Color.YELLOW);
        p3=new Player ("Verdi", 3, Color.GREEN);

    }

    @Test
    public void AddPlayers_gameAlreadyStarted_StateGameWillBeInProgress(){
        game.addPlayers(p1);
        assertEquals(p1.getPosition(), game.getPlayers().size());

        game.addPlayers(p2);
        game.addPlayers(p3);
        assertEquals(p3.getPosition(), game.getPlayers().size());
    }




    @Test
    public void CheckMaxPoint_ExistingPlayerPointOver20_GiveWinner(){
        String name;
        int point1 =p1.getPoint();
        int point2=p1.getPoint();
        int point3=p3.getPoint();

        if ((point1>point2) && (point1>point3)){
            assertEquals("Rossi", game.checkMaxPoint());
        }else if ((point2>point1) && (point2>point3)){
                assertEquals("Neri", game.checkMaxPoint());
        }
        if ((point3>point1) && (point3>point2)){
            assertEquals("Verdi", p3.getNickName());
        }

    }

    @Test (expected = TooFewPlayersException.class)
    public void disconnect_InvalidNumOfPlayer_throwTooFewPlayersException(){
        p1.setDisconnected();
        assertFalse(p1.isConnected());
        p2.setDisconnected();
        assertFalse(p2.isConnected());
    }

    @Test
    public void reconnect_ForJoinAgainGame(){
        p1.setConnected();
        assertTrue(p1.isConnected());
        assertEquals(numOfPlayer, game.getPlayers().size()-1);
    }



}
