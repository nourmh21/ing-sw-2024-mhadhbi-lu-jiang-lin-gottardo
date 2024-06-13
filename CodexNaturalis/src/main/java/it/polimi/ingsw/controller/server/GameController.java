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


    public void access(Client client, String nickname, String pwd, boolean isRegistered){
        executor.execute(new Access(client, nickname, pwd, isRegistered));
    }


    public void giveLobbies(Client client){
        if (isLoggedIn(client.getNickname())){
            List<Integer[]> lobbies = availableLobby.stream()
                    .parallel()
                    .map(lobby -> new Integer[]{lobby.getIdLobby(),lobby.getNumOfPlayer(),lobby.getObservers().size()})
                    .toList();
            client.informLobbyList(lobbies);
        }
    }


    public void joinLobby(Client client, Integer idLobby) {
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


    public void createLobby(Client client, int numOfPlayer){
        if (isLoggedIn(client.getNickname())){
            Lobby lobby = new Lobby(numOfPlayer,getRandomLobbyId());
            availableLobby.add(lobby);
            lobby.addObserver(client.getObserver());
            lobby.addNewPlayer(client.getNickname());
        }
    }


    public void playInitCard(Client client, Integer idCard, boolean isBackSide){
        if (isInGame(client.getNickname()))
            executor.execute(new PlayInitialCard(client, usersInGame.get(client.getNickname()),
                (Card) getCard(idCard), isBackSide));
    }


    public void setPersonalGoal(Client client, Integer idCard){
        if (isInGame(client.getNickname()))
            executor.execute(new SetPersonalGoal(client, usersInGame.get(client.getNickname()), idCard));
    }


    public void playCard(Client client, Integer idCard, int[] position, boolean isBackSide){
        if (isInGame(client.getNickname()))
            executor.execute(new PlayCard(client, usersInGame.get(client.getNickname()), (Card)getCard(idCard), position,
                isBackSide));
    }


    public void drawCard(Client client, LocationType location, Integer idCard){
        if (isInGame(client.getNickname())){
            if ((location == LocationType.DISPLAYED_RESOURCE_LIST) || (location == LocationType.DISPLAYED_GOLD_LIST))
                executor.execute(new DrawCard(client, usersInGame.get(client.getNickname()),location,
                        idCard));
            else
                executor.execute(new DrawCard(client, usersInGame.get(client.getNickname()), location));
        }
    }


    public void disconnectPlayer(Client client){
        if (isInGame(client.getNickname()))
            executor.execute(new PlayerDisconnect(client, usersInGame.get(client.getNickname())));
    }

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


    public void removeLobby(Lobby lobby){
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


    public Object getCard(Integer idCard){
        return cardList.getCard(idCard);
    }


    public void socketClientLeave(Client client){
        clientManager.removeClient(client);
        if (client.getNickname() != null){
            String nickname = client.getNickname();
            removeLoggedInUser(client.getNickname());
            removePlayerFromLobby(nickname);
            disconnectPlayer(client);
        }
    }


    private void removePlayerFromLobby(String nickname){
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

