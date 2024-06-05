package it.polimi.ingsw.controller.server;

import it.polimi.ingsw.controller.server.task.*;
import it.polimi.ingsw.message.Message;
import it.polimi.ingsw.message.enums.ErrorType;
import it.polimi.ingsw.message.enums.LocationType;
import it.polimi.ingsw.message.enums.MessageType;
import it.polimi.ingsw.message.enums.NotifyType;
import it.polimi.ingsw.message.error.ErrorMessage;
import it.polimi.ingsw.message.general.*;
import it.polimi.ingsw.message.notify.NotifyMessage;
import it.polimi.ingsw.model.Card;
import it.polimi.ingsw.model.CardList;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Lobby;
import it.polimi.ingsw.observer.SocketObserver;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class GameController {

    private static GameController instance;
    private final CardList cardList;

    private Optional<Lobby> currentLobby;
    private List<Lobby> availableLobby;

    private ConcurrentHashMap<ObjectOutputStream, String> loggedInUsers;

    private ConcurrentHashMap<String, Game> usersInGame;

    public ExecutorService executor;

    private Set<Integer> usedLibbyIds;
    private Random random;


    public GameController(){
        cardList = new CardList();
        currentLobby = Optional.empty();
        loggedInUsers = new ConcurrentHashMap<>();
        usersInGame = new ConcurrentHashMap<>();
        availableLobby = new ArrayList<>();
        executor = Executors.newCachedThreadPool();
        usedLibbyIds = new HashSet<>();
        random = new Random();
    }


    public void messageHandler(Message message, ObjectOutputStream oos){
        if(message.getType() == MessageType.ACCESS){
            AccessMessage accessMessage = (AccessMessage) message;
            if (accessMessage.isRegistered() && loggedInUsers.containsValue(accessMessage.getNickname())){
                writeErrorMessage(oos,ErrorType.ACCOUNT_ALREADY_LOGGED);
            }else{
                executor.execute(new Access(accessMessage.getNickname(),accessMessage.getPwd(),accessMessage.isRegistered(),oos));
            }
        }else {
            if (loggedInUsers.containsKey(oos)){
                String nickname = loggedInUsers.get(oos);
                switch (message.getType()){
                    case REQ_LOBBIES:
                        giveLobbies(nickname, oos);
                        break;
                    case JOIN_LOBBY:
                        joinLobby(nickname,oos,((JoinLobbyeMessage)message).getIdLobby());
                        break;
                    case CREATE_LOBBY:
                        CreateLobbyMessage createLobbyMessage = (CreateLobbyMessage) message;
                        createLobby(createLobbyMessage.getNumOfPlayer(),loggedInUsers.get(oos),oos);
                        break;
                    case PLAY_INITIAL_CARD:
                        PlayInitCardMessage playInitCardMessage = (PlayInitCardMessage) message;
                        executor.execute(new PlayInitialCard(usersInGame.get(nickname),nickname,
                                (Card) getCard(playInitCardMessage.getIdCard()),playInitCardMessage.isBackSide()));
                        break;
                    case PERSONAL_GOAL_CHOOSE:
                        executor.execute(new SetPersonalGoal(usersInGame.get(nickname),nickname,((PersonalGoalChooseMessage)message).getIdCard(),oos));
                        break;
                    case PLAY_CARD:
                        PlayCardMessage playCardMessage = (PlayCardMessage) message;
                        executor.execute(new PlayCard(usersInGame.get(nickname),nickname,
                                playCardMessage.getPosition(), playCardMessage.isBackSide(), (Card)getCard(playCardMessage.getIdCard()),oos));
                        break;
                    case DRAW_CARD:
                        DrawCardMessage drawCardMessage = (DrawCardMessage) message;
                        LocationType location = drawCardMessage.getLocation();
                        if ((location == LocationType.DISPLAYED_RESOURCE_LIST) || (location == LocationType.DISPLAYED_GOLD_LIST))
                            executor.execute(new DrawCard(usersInGame.get(nickname),nickname,location,
                                    drawCardMessage.getIdCard(),oos));
                        else
                            executor.execute(new DrawCard(usersInGame.get(nickname),nickname,location,oos));
                        break;
                    default:
                        break;
                }
            }
        }

    }


    public static void writeErrorMessage(ObjectOutputStream oos, ErrorType type){
        try {
            oos.reset();
            oos.writeObject(new ErrorMessage(type));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public synchronized void removeLobby(Lobby lobby){
        availableLobby.remove(lobby);
        usedLibbyIds.remove(lobby.getIdLobby());
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


    public void executeTask(Runnable task){
        executor.execute(task);
    }


    public void giveLobbies(String nickname, ObjectOutputStream oos){
        List<Integer[]> lobbies = availableLobby.stream()
                .parallel()
                .map(lobby -> new Integer[]{lobby.getIdLobby(),lobby.getNumOfPlayer(),lobby.getObservers().size()})
                .toList();
        try {
            oos.writeObject(new NotifyMessage(NotifyType.LOBBY_LIST, lobbies));
        } catch (IOException e) {

        }
    }


    public synchronized void joinLobby(String nickname, ObjectOutputStream oos, Integer idLobby) {
        boolean find = false;
        for (Lobby lobby: availableLobby) {
            if (lobby.getIdLobby() == idLobby){
                lobby.addObserver(new SocketObserver(nickname,oos));
                lobby.addNewPlayer(nickname);
                find = true;
                break;
            }
        }
        if (!find){
            writeErrorMessage(oos,ErrorType.LOBBY_ID_NOT_FOUND);
            giveLobbies(nickname,oos);
        }
    }


    public void createLobby(int numOfPlayer, String nickname, ObjectOutputStream oos){
        Lobby lobby = new Lobby(numOfPlayer,getRandomLobbyId());
        availableLobby.add(lobby);
        lobby.addObserver(new SocketObserver(nickname,oos));
        lobby.addNewPlayer(nickname);
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
            removePlayerFromLobby(nickname);
            if (usersInGame.containsKey(nickname)){
                executeTask(new PlayerDisconnect(nickname, usersInGame.get(nickname), oos));
            }
            loggedInUsers.remove(oos);
        }
    }


    public void removePlayerFromLobby(String nickname){
        boolean find = false;
        for (Lobby lobby:availableLobby) {
            for (String playerNickname: lobby.getPlayers()) {
                if (playerNickname.equals(nickname)){
                    lobby.removePlayer(nickname);
                    find = true;
                    break;
                }
            }
            if (find)
                break;
        }
    }


    public int getRandomLobbyId(){
        int n = random.nextInt(2000);
        while (usedLibbyIds.contains(n)){
            n = random.nextInt(2000);
        }
        usedLibbyIds.add(n);
        return n;
    }


    /**
     * @visible for test
     * @return usersInGame
     */
    public ConcurrentHashMap<String, Game> getUsersInGame(){
        return usersInGame;
    }


    /**
     * @visible for test
     * @return loggedInUsers
     */
    public ConcurrentHashMap<ObjectOutputStream, String> getLoggedInUsers(){
        return loggedInUsers;
    }


    /**
     * @visible for test
     * @return availableLobby
     */
    public List<Lobby> getAvailableLobby(){
        return availableLobby;
    }


}

