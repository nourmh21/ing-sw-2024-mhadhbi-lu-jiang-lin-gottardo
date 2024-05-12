package it.polimi.ingsw.observer;

import it.polimi.ingsw.controller.ImmutableLobby;
import it.polimi.ingsw.controller.Lobby;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.immutable.ImmutableEndGameInfo;
import it.polimi.ingsw.model.immutable.ImmutableGame;
import it.polimi.ingsw.model.immutable.ImmutablePlayer;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {
    protected List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer){
        observers.add(observer);
    }

    public void removeObserver(Observer observer){
        observers.add(observer);
    }


    public void notify_lobby_status(Lobby lobby){
        for (Observer o: observers) {
            o.send_lobby_status(new ImmutableLobby(lobby));
        }
    }


    public void notify_game_status(Game game){
        for (Observer o:observers) {
            o.send_game_status(new ImmutableGame(game));
        }
    }


    public void notify_final_result(Game game){
        for (Observer o:observers) {
            o.send_end_game_info(new ImmutableEndGameInfo(game));
        }
    }


    public void notify_player_status(Player player){
        for (Observer o:observers) {
            o.send_player_status(new ImmutablePlayer(player, player.getBoard()));
        }
    }


    public void notify_two_personal_goals(Player player){
        for (Observer o:observers) {
            if (o.getNickname().equals(player.getNickname())){
                o.send_two_personal_goal(player.getInitialPossibleGoals());
                break;
            }
        }
    }


    public void notify_hand_cards(Player player){
        for (Observer o:observers) {
            if ((o.getNickname().equals(player.getNickname()))){
                o.send_player_handCard(player.getHandCards());
                break;
            }
        }
    }







}
