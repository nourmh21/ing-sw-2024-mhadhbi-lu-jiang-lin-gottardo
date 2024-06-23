package it.polimi.ingsw.model;


import it.polimi.ingsw.model.enums.GameState;
import it.polimi.ingsw.model.exceptions.InvalidNumOfConnectedPlayer;
import it.polimi.ingsw.model.exceptions.InvalidNumOfHandCardsException;
import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.message.general.ChatMessage;


import java.util.Arrays;

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
    public void AddPlayers_checkPlayers_ReallyAdded(){
        assertNotEquals(0, game.getPlayersSize());
    }



    @Test
    public void checkMaxPoint_GetMaxPointOfPlayers_ShouldReturnPossibleWinners(){
        p1 = game.getPlayers().get(0);
        p1.updatePoint(21);

        p2 = game.getPlayers().get(1);
        p2.updatePoint(25);

        p3 = game.getPlayers().get(2);
        p3.updatePoint(25);

        game.checkMaxPoint();

        assertEquals(2, game.possibleWinners.size());
    }


    @Test
    public void checkMaxPoint_GetMaxPoint_ShouldReturnOnePlayer(){
        p1 = game.getPlayers().get(0);
        p1.updatePoint(21);

        p2 = game.getPlayers().get(1);
        p2.updatePoint(21);

        p3 = game.getPlayers().get(2);
        p3.updatePoint(25);

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



    @Test
    public void removeHandCard_checkHandCard_ReallyRemoved(){
        game.getPlayers().get(0).addCardToHandCards(63);
        game.getPlayers().get(0).addCardToHandCards(54);
        game.getPlayers().get(0).addCardToHandCards(21);

        game.getPlayers().get(0).removeHandCard(game.getPlayers().get(0).getHandCards().get(1));
        assertEquals(game.getPlayers().get(0).getHandCards().size(), 2);
    }


    @Test
    public void setPersonalGoal_checkPersonalGoal_ReallySet(){
        game.getPlayers().get(1).setPersonalGoal(94);
        assertNotNull(game.getPlayers().get(1).getPersonalGoal());
    }

    @Test
    public void addNewMessage_checkMessage_ReallyAdded(){
        String sender = "firstsend";
        String content = "Ciao";
        String recipient = "A";
        ChatMessage message = new ChatMessage(sender, recipient, content);
        game.addNewChat(message);

        assertTrue(game.getChatHistory()!=null);

    }


    @Test
    public void getDesk_checkGameDesk_ReallyGet(){
        assertNotNull(game.getDesk());
    }


    @Test
    public void setGameState_checkState_ReallyChange(){
        game.setGameState(GameState.PLAY_CARD);
        assertTrue(game.getGameState()==GameState.PLAY_CARD);
    }


    @Test
    public void setCurrentPlayer_checkPlayer_ReallyChange(){
        game.setCurrentPlayer(p1);
        assertEquals(game.getCurrentPlayer(), p1);
    }


    @Test
    public void setCommonGoals_checkCommonGoals_ReallySet(){
        game.setCommonGoals(84);
        game.setCommonGoals(86);

        assertEquals(game.getCommonGoals().size(), 2);
    }


    @Test
    public void setIsLastRound_checkIsLastRound_isReallyLast(){
        game.setIsLastRound();

        assertTrue(game.getIsLastRound());
    }

    @Test
    public void setInitialPossibleGoals_checkPossibleGoalsForPlayer_PlayerReallyReceived(){
        Integer[] id = {92,93};
        game.getPlayers().get(0).setInitialPossibleGoals(id);

        assertNotNull(game.getPlayers().getFirst().getInitialPossibleGoals());
    }



}