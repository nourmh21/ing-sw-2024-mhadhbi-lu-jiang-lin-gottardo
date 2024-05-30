package it.polimi.ingsw.controller.server;


import it.polimi.ingsw.controller.server.task.*;
import it.polimi.ingsw.message.Message;
import it.polimi.ingsw.message.enums.ErrorType;
import it.polimi.ingsw.message.enums.LocationType;
import it.polimi.ingsw.message.error.ErrorMessage;
import it.polimi.ingsw.message.general.*;

import it.polimi.ingsw.model.Card;
import it.polimi.ingsw.model.CardList;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Lobby;
import it.polimi.ingsw.observer.SocketObserver;

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
                AccessMessage accessMessage = (AccessMessage) message;
                if (accessMessage.isRegistered() && loggedInUsers.containsValue(accessMessage.getNickname())){
                    try {
                        oos.reset();
                        oos.writeObject(new ErrorMessage(ErrorType.ACCOUNT_ALREADY_LOGGED));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    executor.execute(new Access(accessMessage.getNickname(),accessMessage.getPwd(),accessMessage.isRegistered(),oos));
                }
                break;
            case JOIN_GAME:
                if (loggedInUsers.containsKey(oos))
                    joinGame(loggedInUsers.get(oos), oos);
                break;
            case NEW_GAME_INFO:
                if (loggedInUsers.containsKey(oos)){
                    NewGameInfoMessage newGameInfoMessage = (NewGameInfoMessage) message;
                    createLobby(newGameInfoMessage.getNumOfPlayer(),loggedInUsers.get(oos),oos);
                }
                break;
            case PLAY_INITIAL_CARD:
                if (loggedInUsers.containsKey(oos)){
                    PlayInitCardMessage playInitCardMessage = (PlayInitCardMessage) message;
                    String nickname = loggedInUsers.get(oos);
                    executor.execute(new PlayInitialCard(usersInGame.get(nickname),nickname,
                            (Card) getCard(playInitCardMessage.getIdCard()),playInitCardMessage.isBackSide()));
                }
                break;
            case PERSONAL_GOAL_CHOOSE:
                if (loggedInUsers.containsKey(oos)){
                    String nickname = loggedInUsers.get(oos);
                    executor.execute(new SetPersonalGoal(usersInGame.get(nickname),nickname,((PersonalGoalChooseMessage)message).getIdCard(),oos));
                }
                break;
            case PLAY_CARD:
                if (loggedInUsers.containsKey(oos)){
                    String nickname = loggedInUsers.get(oos);
                    PlayCardMessage playCardMessage = (PlayCardMessage) message;
                    executor.execute(new PlayCard(usersInGame.get(nickname),nickname,
                            playCardMessage.getPosition(), playCardMessage.isBackSide(), (Card)getCard(playCardMessage.getIdCard()),oos));
                }
                break;
            case DRAW_CARD:
                if (loggedInUsers.containsKey(oos)) {
                    String nickname = loggedInUsers.get(oos);
                    DrawCardMessage drawCardMessage = (DrawCardMessage) message;
                    LocationType location = drawCardMessage.getLocation();
                    if ((location == LocationType.DISPLAYED_RESOURCE_LIST) || (location == LocationType.DISPLAYED_GOLD_LIST))
                        executor.execute(new DrawCard(usersInGame.get(nickname),nickname,location,
                                drawCardMessage.getIdCard(),oos));
                    else
                        executor.execute(new DrawCard(usersInGame.get(nickname),nickname,location,oos));
                }
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
            currentLobby.get().addObserver(new SocketObserver(nickname, oos));
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


    public synchronized void createLobby(int numOfPlayer, String nickname, ObjectOutputStream oos){
        if (currentLobby.isEmpty()){
            currentLobby = Optional.of(new Lobby(numOfPlayer));
            //
            currentLobby.get().addObserver(new SocketObserver(nickname, oos));
            currentLobby.get().addNewPlayer(nickname);
        }else {
            currentLobby.get().addObserver(new SocketObserver(nickname, oos));
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

    public void socketClientLeave(ObjectOutputStream oos){
        if (loggedInUsers.containsKey(oos)){
            String nickname = loggedInUsers.get(oos);
            if (currentLobby.isPresent()){
                if (currentLobby.get().getPlayers().contains(nickname))
                    currentLobby.get().removePlayer(nickname);
            }
            //
            usersInGame.remove(nickname);
            loggedInUsers.remove(oos);
        }

    }
}

