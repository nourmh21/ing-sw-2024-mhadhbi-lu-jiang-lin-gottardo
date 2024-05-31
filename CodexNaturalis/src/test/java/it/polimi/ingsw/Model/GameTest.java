package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enums.Color;
import it.polimi.ingsw.model.exceptions.InvalidNumOfConnectedPlayer;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


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
        possibleWinners = new ArrayList<>();
        winners = new ArrayList<>();
        p1 = new Player("Rossi", game, Color.BLUE);
        p2 = new Player("Neri", game, Color.YELLOW);
        p3 = new Player("Verdi", game, Color.BLUE);


    }


    @Test
    public void AddPlayers_NoPlayers_ReallyAdded(){
        game.addPlayers("Rossi");
        assertNotEquals(0, game.getPlayersSize());
    }


    @Test
    public void checkMaxPoint_GetMaxPointOfPlayers_ShouldReturnPossibleWinners(){
        int max=20;


        game.addPlayers(p1.getNickname());
        players.add(p1);
        /*
        p1.setPoint(25);
        game.addPlayers(p2.getNickname());
        players.add(p2);
        p2.setPoint(22);
        game.addPlayers(p3.getNickname());
        players.add(p3);
        p3.setPoint(25);*/


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
        assertEquals(possibleWinners.get(0).getNickname(), "Rossi");
        assertEquals(possibleWinners.get(1).getNickname(), "Verdi");

    }


    @Test
    public void checkExtraPoint_GetMaxExtraPoint_ShouldReturnFinalWinners(){
        game.addPlayers(p1.getNickname());
        possibleWinners.add(p1);
        p1.addGoalPoint(6);

        game.addPlayers(p3.getNickname());
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
        assertEquals(winners.get(0).getNickname(), "Rossi");
        assertEquals(winners.get(1).getNickname(), "Verdi");


    }

    @Test (expected = InvalidNumOfConnectedPlayer.class)
    public void disconnect_InvalidNumOfPlayer_throwInvalidNumOfConnectedPlayer() throws InvalidNumOfConnectedPlayer {
        game.addPlayers(p1.getNickname());
        game.addPlayers(p2.getNickname());
        game.addPlayers(p3.getNickname());
        game.disconnect(p1);
        game.disconnect(p2);
    }

    @Test
    public void reconnect_ForJoinAgainGame(){
        game.addPlayers(p1.getNickname());
        assertEquals(game.getNumOfConnectedPlayers(), game.getPlayersSize());
    }



}
