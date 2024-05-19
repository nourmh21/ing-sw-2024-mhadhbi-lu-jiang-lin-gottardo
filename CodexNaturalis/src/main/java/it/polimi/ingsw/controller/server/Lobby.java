package it.polimi.ingsw.controller.server;

import it.polimi.ingsw.controller.server.GameController;
import it.polimi.ingsw.controller.server.ImmutableLobby;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.observer.Observable;
import it.polimi.ingsw.observer.Observer;

import java.util.ArrayList;
import java.util.List;


public class Lobby extends Observable {
    int numOfPlayer;
    private List<String> players;

    public Lobby(int numOfPlayer){
        this.numOfPlayer = numOfPlayer;
        players = new ArrayList<>();
    }


    public void addNewPlayer(String nickname){

        players.add(nickname);

        notify_lobby_status(new ImmutableLobby(this));


        if (players.size() == numOfPlayer){
            //remove that lobby from controller
            GameController.getInstance().removeFromCurrentLobby();
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


    public void removePlayer(String nickname){
        Observer observer = null;
        for (Observer o:observers) {
            if (o.getNickname().equals(nickname)){
                observer = o;
                break;
            }
        }
        removeObserver(observer);
        players.remove(nickname);
        if(!getObservers().isEmpty()){
            notify_lobby_status(new ImmutableLobby(this));
        }
        if (players.isEmpty())
            GameController.getInstance().removeFromCurrentLobby();
    }


    public List<String> getPlayers() {
        return players;
    }


    public int getNumOfPlayer(){
        return numOfPlayer;
    }
}
