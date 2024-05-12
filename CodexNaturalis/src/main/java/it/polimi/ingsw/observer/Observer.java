package it.polimi.ingsw.observer;

import it.polimi.ingsw.controller.ImmutableLobby;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.immutable.ImmutableEndGameInfo;
import it.polimi.ingsw.model.immutable.ImmutableGame;
import it.polimi.ingsw.model.immutable.ImmutablePlayer;

import java.util.List;

public interface Observer {

    public String getNickname();
    public void send_lobby_status(ImmutableLobby lobby);
    public void send_game_status(ImmutableGame game);
    public void send_player_status(ImmutablePlayer player);
    public void send_two_personal_goal(Integer[] personalGoals);
    public void send_player_handCard(List<Integer> handCards);
    public void send_end_game_info(ImmutableEndGameInfo endGameInfo);

}
