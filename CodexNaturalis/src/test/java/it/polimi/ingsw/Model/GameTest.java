package it.polimi.ingsw.Model;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player;
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
    private List<Player> players;
    private List<Player> possibleWinners;
    private List<Player> winners;


    @Before
    public void setGame(){
        game = new Game(3);
        players = new ArrayList<>();
        p1 = new Player("Rossi", 1, Color.RED);
        p2 = new Player("Neri", 2, Color.YELLOW);
        p3 = new Player ("Verdi", 3, Color.GREEN);
        possibleWinners = new ArrayList<>();
        winners = new ArrayList<>();


    }


    @Test
    public void AddPlayers_NoPlayers_ReallyAdded(){
        game.addPlayers(p1);
        assertNotEquals(0, game.getPlayers().size());
    }


    @Test
    public void checkMaxPoint_GetMaxPointOfPlayers_ShouldReturnPossibleWinners(){
        int max=20;


        game.addPlayers(p1);
        players.add(p1);
        p1.setPoint(25);
        game.addPlayers(p2);
        players.add(p2);
        p2.setPoint(22);
        game.addPlayers(p3);
        players.add(p3);
        p3.setPoint(25);


        for (Player p: players){
            if (p.getPoint() >= max) {
                max = p.getPoint();
            }
        }


        for (Player p:players){
            if (p.getPoint() == max)
                possibleWinners.add(p);
        }

        assertEquals(possibleWinners.size(), 2);

    }


    @Test
    public void checkExtraPoint_GetMaxExtraPoint_ShouldReturnFinalWinners(){
        game.addPlayers(p1);
        possibleWinners.add(p1);
        p1.addGoalPoint(6);

        game.addPlayers(p3);
        possibleWinners.add(p3);
        p3.addGoalPoint(6);


        int maxExtra=0;
        for (Player p: possibleWinners){
            if (p.getGoalPoint() >= maxExtra) {
                maxExtra = p.getGoalPoint();
            }
        }

        for (Player p:possibleWinners){
            if (p.getGoalPoint() == maxExtra)
                winners.add(p);
        }

        assertEquals(winners.size(), 2);


    }

    @Test (expected = InvalidNumOfConnectedPlayer.class)
    public void disconnect_InvalidNumOfPlayer_throwTooFewPlayersException(){
        game.addPlayers(p1);
        game.addPlayers(p2);
        game.addPlayers(p3);
        game.disconnect(p1);
        game.disconnect(p2);
    }

    @Test
    public void reconnect_ForJoinAgainGame(){
        game.addPlayers(p1);
        assertEquals(game.getNumOfConnectedPlayers(), game.getPlayers().size());
    }



}
