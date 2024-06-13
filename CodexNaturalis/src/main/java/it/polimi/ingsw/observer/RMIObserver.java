package it.polimi.ingsw.observer;

import it.polimi.ingsw.message.general.ChatMessage;
import it.polimi.ingsw.model.immutable.ImmutableEndGameInfo;
import it.polimi.ingsw.model.immutable.ImmutableGame;
import it.polimi.ingsw.model.immutable.ImmutableLobby;
import it.polimi.ingsw.model.immutable.ImmutablePlayer;

import java.util.List;

//to be completed by Nourhane
public class RMIObserver implements Observer{
    @Override
    public String getNickname() {
        return null;
    }

    @Override
    public void send_lobby_status(ImmutableLobby lobby) {

    }

    @Override
    public void send_game_status(ImmutableGame game) {

    }

    @Override
    public void send_player_status(ImmutablePlayer player) {

    }

    @Override
    public void send_two_personal_goal(Integer[] personalGoals) {

    }

    @Override
    public void send_player_handCard(List<Integer> handCards) {

    }

    @Override
    public void send_end_game_info(ImmutableEndGameInfo endGameInfo) {

    }

    @Override
    public void send_player_disconnected(String nickname) {

    }

    @Override
    public void send_chat(ChatMessage message) {

    }
}
