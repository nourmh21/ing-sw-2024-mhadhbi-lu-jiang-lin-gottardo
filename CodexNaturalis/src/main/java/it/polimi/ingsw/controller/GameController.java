package it.polimi.ingsw.controller;


import it.polimi.ingsw.message.Message;
import it.polimi.ingsw.message.ReqNewGameInfoMessage;

import it.polimi.ingsw.model.CardList;
import it.polimi.ingsw.model.Game;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class GameController {

    private static GameController instance;
    private final CardList cardList;

    private Optional<Lobby> currentLobby;


    private ConcurrentHashMap<ObjectOutputStream, String> loggedInUsers;
    //

    private ConcurrentHashMap<String, Game> usersInGame;

    private ExecutorService executor;


    public GameController(){
        cardList = new CardList();
        currentLobby = Optional.empty();
        //loggedInUsers = new ArrayList<>();
        loggedInUsers = new ConcurrentHashMap<>();
        usersInGame = new ConcurrentHashMap<>();
        executor = Executors.newCachedThreadPool();
    }



    public void messageHandler(Message message, ObjectOutputStream oos){
        switch (message.getType()){
            case ACCESS:
                //executor.execute(new Access());
                break;
            case JOIN_GAME:
                //is this one is logged in
                //joinGame();
                break;
            case NEW_GAME_INFO:
                //createLobby();
            case PLAY_INITIAL_CARD:
                //executor.execute(new PlayInitialCard());
                break;
            case PERSONAL_GOAL_CHOOSE:
                //executor.execute(new SetPersonalGoal());
                break;
            case PLAY_CARD:
                //executor.execute(new PlayCard());
                break;
            case DRAW_CARD:
                //executor.execute(new DrawGoals());
                break;
            default:
                break;
        }
    }


    public synchronized Optional<Lobby> getCurrentLobby() {
        return currentLobby;
    }


    public synchronized void removeFromCurrentLobby(){
        currentLobby = Optional.empty();
    }


    public void addNewUserInGame(String nickname, Game game){
        usersInGame.put(nickname,game);
    }


    public void removeUserInGame(String nickname){
        usersInGame.remove(nickname);
    }


    public void addNewUserLoggedIn(ObjectOutputStream oos, String nickname){
        loggedInUsers.put(oos, nickname);
    }


    public void removeUserLoggedIn(ObjectOutputStream oos){
        loggedInUsers.remove(oos);
    }


    public void executeTask(Runnable task){
        executor.execute(task);
    }


    public synchronized void joinGame(String nickname, ObjectOutputStream oos) {
        if (currentLobby.isPresent()){
            currentLobby.get().addNewPlayer(nickname);
        }else{
            Message message = new ReqNewGameInfoMessage();
            try {
                oos.writeObject(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public synchronized void createLobby(int numOfPlayer, String nickname){
        if (!currentLobby.isPresent()){
            currentLobby = Optional.of(new Lobby(numOfPlayer));
            currentLobby.get().addNewPlayer(nickname);
        }else {
            currentLobby.get().addNewPlayer(nickname);
            //notify someone before he created a lobby a moment ago
        }
        //synchronize problem should be resolved
        //if another user founds himself in the same situation,
        //and choose the number of player before this one,
        //it will create some problems
    }


    public synchronized static GameController getInstance(){
        if (instance == null)
            instance = new GameController();
        return instance;
    }

    public Object getCard(Integer idCard){
        return cardList.getCard(idCard);
    }

}

