
package it.polimi.ingsw.observer;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.immutable.ImmutableEndGameInfo;
import it.polimi.ingsw.model.immutable.ImmutableGame;
import it.polimi.ingsw.model.immutable.ImmutableLobby;
import it.polimi.ingsw.model.immutable.ImmutablePlayer;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {
    protected List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer){
        observers.add(observer);
    }

    public void removeObserver(Observer observer){
        observers.remove(observer);
    }


    public void notify_lobby_status(ImmutableLobby lobby){
        for (Observer o: observers) {
            o.send_lobby_status(lobby);
        }
    }


    public void notify_game_status(ImmutableGame game){
        for (Observer o:observers) {
            o.send_game_status(game);
        }
    }


    public void notify_final_result(ImmutableEndGameInfo info){
        for (Observer o:observers) {
            o.send_end_game_info(info);
        }
    }


    public void notify_player_status(ImmutablePlayer player){
        for (Observer o:observers) {
            o.send_player_status(player);
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



    public List<Observer> getObservers(){
        return observers;
    }




}
