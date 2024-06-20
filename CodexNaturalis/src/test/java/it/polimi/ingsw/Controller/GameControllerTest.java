package it.polimi.ingsw.Controller;

import it.polimi.ingsw.controller.server.GameController;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Lobby;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.enums.Color;
import org.junit.Before;
import org.junit.Test;


import java.io.*;
import static org.junit.Assert.*;


public class GameControllerTest {

    private GameController gameController;
    private Game game;
    Player p1;
    Player p2;
    Player p3;

    




    @Before
    public void setUpController() throws IOException {
        gameController = new GameController();
        game = new Game(3);
        p1 = new Player("Alice", game, Color.RED);
        p2 = new Player("Bob", game, Color.YELLOW);
        p3 = new Player("Neri", game, Color.BLUE);
        //client.setNickname(p1.getNickname());


    }


    @Test
    public void getRandomLobbyId(){
        int i1=  gameController.getRandomLobbyId();
        int i2 = gameController.getRandomLobbyId();

        assertNotEquals(i1, i2);

    }



    @Test
    public void addNewUserLoggedIn_checkLoggedUser_ReallyAdded(){
        gameController.addNewUserLoggedIn(p1.getNickname());
        gameController.addNewUserLoggedIn(p2.getNickname());

        assertTrue(gameController.isLoggedIn(p1.getNickname()));
        assertTrue(gameController.isLoggedIn(p2.getNickname()));
        assertFalse(gameController.isLoggedIn(p3.getNickname()));
    }


    @Test
    public void removeLoggedInUser(){
        gameController.addNewUserLoggedIn(p1.getNickname());
        gameController.addNewUserLoggedIn(p2.getNickname());
        gameController.addNewUserLoggedIn(p3.getNickname());

        gameController.removeLoggedInUser(p2.getNickname());
        assertFalse(gameController.isLoggedIn(p2.getNickname()));

    }



     @Test
     public void addNewUserInGame_checkUser_ReallyAdded(){
        String name = p1.getNickname();
        //String name2 = p2.getNickname();
        gameController.addNewUserInGame(name, game);
        assertTrue(gameController.isInGame(name));
        assertTrue(gameController.getUserInGame().containsKey(name));


     }


    @Test
    public void removeUserInGame_checkUser_ReallyMoved() throws IOException{
        String name1 = p1.getNickname();
        String name2 = p2.getNickname();
        String name3 = p3.getNickname();

        gameController.addNewUserInGame(name1, game);
        gameController.addNewUserInGame(name2, game);
        gameController.addNewUserInGame(name3, game);

        gameController.removeUserInGame(name1);

        assertFalse(gameController.getUserInGame().containsKey(name1));
    }
    

    @Test
    public void checkRemoveLobby_Reallyremoved(){
        Lobby l1 = new Lobby(3, gameController.getRandomLobbyId());
        Lobby l2 = new Lobby (2, gameController.getRandomLobbyId());

        gameController.getAvailableLobby().add(l1);
        gameController.getAvailableLobby().add(l2);
        gameController.removeLobby(l1);

        assertFalse(gameController.getAvailableLobby().contains(l1));

    }







}
