package it.polimi.ingsw.controller.server;

import it.polimi.ingsw.message.general.ChatMessage;
import it.polimi.ingsw.network.Client;
import it.polimi.ingsw.controller.server.task.*;
import it.polimi.ingsw.message.enums.ErrorType;
import it.polimi.ingsw.message.enums.LocationType;
import it.polimi.ingsw.model.Card;
import it.polimi.ingsw.model.CardList;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Lobby;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The GameController is the class that manages all user actions by executing the corresponding method or task
 */
public class GameController {

    private static GameController instance;
    private ClientManager clientManager;
    private final List<Lobby> availableLobby;
    private final Set<String> loggedInUsers;
    private ConcurrentHashMap<String, Game> usersInGame;
    private ExecutorService executor;
    private Set<Integer> usedLobbyIds;
    private Random random;
    private final CardList cardList;

    /**
     * Constructor that inits:
     * a list of users logged in, a list of users in game, a list of ids used for lobby, a list of all cards...
     */
    public GameController(){
        clientManager = new ClientManager();
        loggedInUsers = new HashSet<>();
        usersInGame = new ConcurrentHashMap<>();
        availableLobby = new ArrayList<>();
        executor = Executors.newCachedThreadPool();
        usedLobbyIds = new HashSet<>();
        random = new Random();
        cardList = new CardList();
    }


    /**
     * Executes the task for login/registration
     * @param client user who made the action
     * @param nickname nickname of that user
     * @param pwd password
     * @param isRegistered true if already registered, false otherwise
     */
    public void access(Client client, String nickname, String pwd, boolean isRegistered){
        executor.execute(new Access(client, nickname, pwd, isRegistered));
    }


    /**
     * Notifies the client about all available lobbies
     * @param client user who made the action
     */
    public synchronized void giveLobbies(Client client){
        if (isLoggedIn(client.getNickname())){
            List<Integer[]> lobbies = availableLobby.stream()
                    .parallel()
                    .map(lobby -> new Integer[]{lobby.getIdLobby(),lobby.getNumOfPlayer(),lobby.getObservers().size()})
                    .toList();
            client.informLobbyList(lobbies);
        }
    }


    /**
     * Adds client in the request lobby and notifies that client if the request lobby is not present
     * @param client user who made the action
     * @param idLobby id of request lobby
     */
    public synchronized void joinLobby(Client client, Integer idLobby) {
        if (isLoggedIn(client.getNickname())){
            boolean find = false;
            for (Lobby lobby: availableLobby) {
                if (lobby.getIdLobby() == idLobby){
                    lobby.addObserver(client.getObserver());
                    lobby.addNewPlayer(client.getNickname());
                    find = true;
                    break;
                }
            }
            if (!find){
                client.informError(ErrorType.LOBBY_ID_NOT_FOUND);
                giveLobbies(client);
            }
        }
    }


    /**
     * Creates a lobby
     * @param client user who made the action
     * @param numOfPlayer number of player of the new lobby
     */
    public synchronized void createLobby(Client client, int numOfPlayer){
        if (isLoggedIn(client.getNickname())){
            Lobby lobby = new Lobby(numOfPlayer,getRandomLobbyId());
            availableLobby.add(lobby);
            lobby.addObserver(client.getObserver());
            lobby.addNewPlayer(client.getNickname());
        }
    }


    /**
     * Executes the task that manages the play of the initial card
     * @param client user who made the action
     * @param idCard id of the initial card
     * @param isBackSide true if client wants to play back side of the card, false otherwise
     */
    public void playInitCard(Client client, Integer idCard, boolean isBackSide){
        if (isInGame(client.getNickname()))
            executor.execute(new PlayInitialCard(client, usersInGame.get(client.getNickname()),
                (Card) getCard(idCard), isBackSide));
    }


    /**
     * Executes the task that manages the choice of the objective(goal) card
     * @param client user who made the action
     * @param idCard id of the objective(goal) card
     */
    public void setPersonalGoal(Client client, Integer idCard){
        if (isInGame(client.getNickname()))
            executor.execute(new SetPersonalGoal(client, usersInGame.get(client.getNickname()), idCard));
    }


    /**
     * Executes the task that manages the play of a card
     * @param client user who made the action
     * @param idCard id of card
     * @param position position that client want to place
     * @param isBackSide true if client wants to play back side of the card, false otherwise
     */
    public void playCard(Client client, Integer idCard, int[] position, boolean isBackSide){
        if (isInGame(client.getNickname()))
            executor.execute(new PlayCard(client, usersInGame.get(client.getNickname()), (Card)getCard(idCard), position,
                isBackSide));
    }


    /**
     * Executes the task that manages the draw of a card
     * @param client user who made the action
     * @param location location where the client drew the card
     * @param idCard id of card
     */
    public void drawCard(Client client, LocationType location, Integer idCard){
        if (isInGame(client.getNickname())){
            if ((location == LocationType.DISPLAYED_RESOURCE_LIST) || (location == LocationType.DISPLAYED_GOLD_LIST))
                executor.execute(new DrawCard(client, usersInGame.get(client.getNickname()),location,
                        idCard));
            else
                executor.execute(new DrawCard(client, usersInGame.get(client.getNickname()), location));
        }
    }


    /**
     * Executes the task that manages the disconnection of a user in game
     * @param client user who left the game
     */
    public void disconnectPlayer(Client client){
        if (isInGame(client.getNickname()))
            executor.execute(new PlayerDisconnect(client, usersInGame.get(client.getNickname())));
    }


    /**
     * Executes the task that manages chat message
     * @param message massage that contains all chat information
     */
    public void chatManage(ChatMessage message){
        if (isInGame(message.getSender()))
            executor.execute(new ChatManager(usersInGame.get(message.getSender()),message));
    }


    public boolean isLoggedIn(String nickname){
        return loggedInUsers.contains(nickname);
    }


    public boolean isInGame(String nickname){
        return usersInGame.containsKey(nickname);
    }


    /**
     * Remove lobby from the list of available lobbies
     * @param lobby lobby to be removed
     */
    public synchronized void removeLobby(Lobby lobby){
        availableLobby.remove(lobby);
        usedLobbyIds.remove(lobby.getIdLobby());
    }


    public void addNewUserInGame(String nickname, Game game){
        usersInGame.put(nickname,game);
    }


    public void removeUserInGame(String nickname){
        usersInGame.remove(nickname);
    }


    public void addNewUserLoggedIn(String nickname){
        loggedInUsers.add(nickname);
    }


    public void removeLoggedInUser(String nickname){
        loggedInUsers.remove(nickname);
    }


    public void executeTask(Runnable runnable){
        executor.execute(runnable);
    }


    /**
     * @param idCard id of card
     * @return a card by idCard
     */
    public Object getCard(Integer idCard){
        return cardList.getCard(idCard);
    }


    /**
     * Manages the client leaving
     * @param client user who made the action
     */
    public void clientLeave(Client client){
        clientManager.removeClient(client);
        if (client.getNickname() != null){
            String nickname = client.getNickname();
            removeLoggedInUser(client.getNickname());
            removePlayerFromLobby(nickname);
            disconnectPlayer(client);
        }
    }


    /**
     * Removes a user from the lobby if is inside one
     * @param nickname nickname of user
     */
    private synchronized void removePlayerFromLobby(String nickname){
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


    /**
     * Random an id of lobby not used in the list of available lobbies
     */
    public int getRandomLobbyId(){
        int n = random.nextInt(2000);
        while (usedLobbyIds.contains(n)){
            n = random.nextInt(2000);
        }
        usedLobbyIds.add(n);
        return n;
    }


    public ClientManager getClientManager(){
        return clientManager;
    }


    public synchronized static GameController getInstance(){
        if (instance == null)
            instance = new GameController();
        return instance;
    }

}

