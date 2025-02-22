package it.polimi.ingsw.model;

import it.polimi.ingsw.controller.server.GameController;

import it.polimi.ingsw.model.immutable.ImmutableLobby;
import it.polimi.ingsw.observer.ModelObservable;
import it.polimi.ingsw.observer.ModelObserver;

import java.util.ArrayList;
import java.util.List;


public class Lobby extends ModelObservable {
    int idLobby;
    int numOfPlayer;
    private List<String> players;

    public Lobby(int numOfPlayer, int idLobby){
        this.idLobby = idLobby;
        this.numOfPlayer = numOfPlayer;
        players = new ArrayList<>();
    }


    /**
     * add new player in the lobby
     * @param nickname indicate the player's name added in the lobby
     */
    public void addNewPlayer(String nickname){

        players.add(nickname);

        notify_lobby_status(new ImmutableLobby(players, numOfPlayer));


        if (players.size() == numOfPlayer){
            //remove that lobby from controller
            GameController.getInstance().removeLobby(this);
            //create the game
            Game game = new Game(numOfPlayer);
            //add all players in the new game and in the list of player in game
            game.setGameObservers(observers);
            for (String name: players) {
                game.addPlayers(name);
                GameController.getInstance().addNewUserInGame(name, game);
            }

        }

    }

    /**
     * remove player from lobby
     * @param nickname is the nickname of player removed from lobby
     */
    public void removePlayer(String nickname){
        ModelObserver observer = null;
        for (ModelObserver o: observers) {
            if (o.getNickname().equals(nickname)){
                observer = o;
                break;
            }
        }
        removeObserver(observer);
        players.remove(nickname);
        if(!getObservers().isEmpty()){
            notify_lobby_status(new ImmutableLobby(players, numOfPlayer));
        }else {
            GameController.getInstance().removeLobby(this);
        }
    }

    public List<String> getPlayers() {
        return players;
    }

    public int getNumOfPlayer(){
        return numOfPlayer;
    }

    public int getIdLobby(){
        return idLobby;
    }
}
