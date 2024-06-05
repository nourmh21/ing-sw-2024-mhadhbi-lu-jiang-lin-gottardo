package it.polimi.ingsw.Controller;

import it.polimi.ingsw.controller.server.GameController;
import it.polimi.ingsw.controller.server.task.Access;
import it.polimi.ingsw.message.Message;
import it.polimi.ingsw.message.enums.ErrorType;
import it.polimi.ingsw.message.enums.MessageType;
import it.polimi.ingsw.message.enums.NotifyType;
import it.polimi.ingsw.message.error.ErrorMessage;
import it.polimi.ingsw.message.general.AccessMessage;
import it.polimi.ingsw.model.CardList;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Lobby;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.enums.Color;
import it.polimi.ingsw.network.Client;
import it.polimi.ingsw.network.socket.ClientHandler;
import it.polimi.ingsw.network.socket.HeartbeatSender;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

//import static it.polimi.ingsw.network.Server.port;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GameControllertest {

    private GameController gameController;
    private Game game;
    private ObjectOutputStream oos;
    Player p1;
    Player p2;
    Player p3;





    @Before
    public void setUpController() throws IOException {
        gameController = new GameController();
        //oos = mock(ObjectOutputStream.class);
        game = new Game(3);
        p1 = new Player("Alice", game, Color.RED);
        p2 = new Player("Bob", game, Color.YELLOW);
        p3 = new Player("Neri", game, Color.BLUE);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        oos = new ObjectOutputStream(byteArrayOutputStream);
        /*gameController.addNewUserInGame("Rossi", game);
        gameController.addNewUserInGame("Verdi", game);
        gameController.addNewUserInGame("Neri", game);*/

        /*serverSocket = new ServerSocket(12345);
        clientSocket = new Socket("localhost", 12345);
        Socket socket  = serverSocket.accept();


        outputc = clientSocket.getOutputStream();
        inputc = clientSocket.getInputStream();
        coos = new ObjectOutputStream(outputc);
        cois = new ObjectInputStream(inputc);

        outputs = socket.getOutputStream();
        inputs = socket.getInputStream();
        soos = new ObjectOutputStream(outputs);
        sois = new ObjectInputStream(inputs);
        //new ClientHandler(socket).start();
        new HeartbeatSender(coos, clientSocket).start();*/

    }



    @Test
    public void checkUser_AddNewUserLoggeIn_ReallyAdded(){
        String name = p1.getNickname();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream outs = null;
        try{
            outs = new ObjectOutputStream(byteArrayOutputStream);
            gameController.addNewUserLoggedIn(outs, name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue(gameController.getLoggedInUsers().containsValue(name));

    }


     @Test
     public void checkUser_AddNewUserInGame_ReallyAdded(){
        String name = p1.getNickname();
        //String name2 = p2.getNickname();
        gameController.addNewUserInGame(name, game);
        assertTrue(gameController.getUsersInGame().containsKey(name));

     }


    @Test
    public void checkUser_RemoveUserInGame_ReallyMoved() throws IOException{
        String name1 = p1.getNickname();
        String name2 = p2.getNickname();
        String name3 = p3.getNickname();

        gameController.addNewUserInGame(name1, game);
        gameController.addNewUserInGame(name2, game);
        gameController.addNewUserInGame(name3, game);

        gameController.removeUserInGame(name1);

        assertFalse(gameController.getUsersInGame().containsKey(name1));
    }


    @Test
    public void testCreateLobby_ReallyCreated() {
        String name = p1.getNickname();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream outs = null;
        try {
            outs = new ObjectOutputStream(byteArrayOutputStream);
            gameController.createLobby(3, name, outs);

        } catch (IOException e) {
            e.printStackTrace();
        }
        assertFalse(gameController.getAvailableLobby().isEmpty());

    }



    @Test
    public void checkRemoveLobby_Reallyremoved(){
        Lobby l1 = new Lobby(3, gameController.getRandomLobbyId());
        Lobby l2 = new Lobby (2, gameController.getRandomLobbyId());

        gameController.getAvailableLobby().add(l1);
        gameController.getAvailableLobby().add(l2);
        gameController.removeLobby(l1);

        assertFalse(gameController.getAvailableLobby().contains(gameController.getAvailableLobby().contains(l1)));


    }

    @Test
    public void checkJoinLobby_ReallyJoined(){
        String name1 = p1.getNickname();
        String name2 = p2.getNickname();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream outs = null;

        try {
            outs = new ObjectOutputStream(byteArrayOutputStream);
            gameController.createLobby(2, name1, outs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int i = gameController.getAvailableLobby().getFirst().getIdLobby();
        gameController.joinLobby(name2, outs, i);
        assertEquals(gameController.getAvailableLobby().getFirst().getNumOfPlayer(), 2);
    }


    @Test
    public void checkRemovePlayerFromLobby_ReallyRemoved(){
        String name1 = p1.getNickname();
        String name2 = p2.getNickname();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream outs = null;

        try {
            outs = new ObjectOutputStream(byteArrayOutputStream);
            gameController.createLobby(2, name1, outs);
            gameController.joinLobby(name2, outs, gameController.getAvailableLobby().getFirst().getIdLobby());

        } catch (IOException e) {
            e.printStackTrace();
        }
        gameController.removePlayerFromLobby(name2);
        assertFalse(gameController.getAvailableLobby().getFirst().getPlayers().contains(name2));

    }


}
