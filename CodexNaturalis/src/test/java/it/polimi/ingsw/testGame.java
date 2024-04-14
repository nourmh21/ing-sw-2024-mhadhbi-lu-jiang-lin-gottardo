package it.polimi.ingsw;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.enums.Color;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for class Game
 */
public class testGame {

    private Game game;
    int numOfPlayer=3;
    private Player p1;
    private Player p2;
    private Player p3;


    @Before
    public void setGame(){
        game = new Game();
        p1 = new Player("A", 1, Color.BLACK);
        p2 = new Player ("B", 2, Color.RED);
        p3 = new Player("C", 3, Color.YELLOW);


    }

    @Test
    public void testAddPlayers(){
        game.addPlayers(p1);
        assertEquals(p1, game.getPlayers());

        game.addPlayers(p2);
        game.addPlayers(p3);
        assertEquals(p3, game.getPlayers());

    }

    @Test
    public void testCheckMaxPoint(){
        String name;
        name = p1.getNickName();
        game.checkMaxPoint()= name;
        assertEquals("A", game.checkMaxPoint());
    }

}
