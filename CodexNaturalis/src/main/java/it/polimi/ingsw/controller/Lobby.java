package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.observer.Observable;

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
        //addObserver();
        //
        notify_lobby_status(this);

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
            /*
            try {
                game.setInitialCard();
            }catch (EmptyDeckException e){
                //in theory, it is impossible to happen
            }*/

        }

    }


    public List<String> getPlayers() {
        return players;
    }

    public int getNumOfPlayer(){
        return numOfPlayer;
    }
}
