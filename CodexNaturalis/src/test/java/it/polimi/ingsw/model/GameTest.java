package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enums.Color;
import it.polimi.ingsw.model.exceptions.InvalidNumOfConnectedPlayer;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


/**
 * Tests for class Game
 */

public class GameTest {

    private Game game;
    private Player p1;
    private Player p2;
    private Player p3;


    @Before
    public void setGame(){
        game = new Game(3);
        game.addPlayers("Rossi");
        game.addPlayers("Verdi");
        game.addPlayers("Neri");


    }


    @Test
    public void AddPlayers_NoPlayers_ReallyAdded(){
        game.addPlayers("Rossi");
        assertNotEquals(0, game.getPlayersSize());
    }



    @Test
    public void checkMaxPoint_GetMaxPointOfPlayers_ShouldReturnPossibleWinners(){
        p1 = game.getPlayers().get(0);
        p1.updatePoint(21);
        p1.isConnected();

        p2 = game.getPlayers().get(1);
        p2.updatePoint(25);
        p2.isConnected();

        p3 = game.getPlayers().get(2);
        p3.updatePoint(25);
        p3.isConnected();

        game.checkMaxPoint();

        assertEquals(2, game.possibleWinners.size());
    }


    @Test
    public void checkMaxPoint_GetMaxPointofPlayer_ShouldReturnOnePlayer(){
        p1 = game.getPlayers().get(0);
        p1.updatePoint(21);
        p1.isConnected();

        p2 = game.getPlayers().get(1);
        p2.updatePoint(21);
        p2.isConnected();

        p3 = game.getPlayers().get(2);
        p3.updatePoint(25);
        p3.isConnected();

        game.checkMaxPoint();

        assertEquals(1, game.getWinners().size());
    }


    @Test
    public void checkExtraPoint_GetMaxExtraPoint_ShouldReturnFinalWinners(){

        p1 = game.getPlayers().get(0);
        p1.addGoalPoint(6);
        game.possibleWinners.add(p1);


        p2 = game.getPlayers().get(1);
        p2.addGoalPoint(6);
        game.possibleWinners.add(p2);

        game.checkExtraPoint();

        assertEquals(game.getWinners().size(), 2);

    }


    @Test (expected = InvalidNumOfConnectedPlayer.class)
    public void disconnect_InvalidNumOfPlayer_throwInvalidNumOfConnectedPlayer() throws InvalidNumOfConnectedPlayer {
        game.disconnect(game.getPlayers().get(0));
        game.disconnect(game.getPlayers().get(1));
    }
    


}