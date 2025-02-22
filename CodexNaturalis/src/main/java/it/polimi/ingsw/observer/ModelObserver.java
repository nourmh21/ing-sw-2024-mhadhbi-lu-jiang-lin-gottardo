package it.polimi.ingsw.observer;

import it.polimi.ingsw.message.general.ChatMessage;
import it.polimi.ingsw.model.immutable.ImmutableEndGameInfo;
import it.polimi.ingsw.model.immutable.ImmutableGame;
import it.polimi.ingsw.model.immutable.ImmutableLobby;
import it.polimi.ingsw.model.immutable.ImmutablePlayer;

import java.util.List;


/**
 * A class can implement the ModelObserver interface when it wants to be informed of changes in observable objects.
 * It is implemented by {@link SocketObserver} and {@link RMIObserver}
 */
public interface ModelObserver {
    public String getNickname();

    public void send_lobby_status(ImmutableLobby lobby);

    public void send_game_status(ImmutableGame game);

    public void send_player_status(ImmutablePlayer player);

    public void send_two_personal_goal(Integer[] personalGoals);

    public void send_player_handCard(List<Integer> handCards);

    public void send_end_game_info(ImmutableEndGameInfo endGameInfo);

    public void send_player_disconnected(String nickname);

    public void send_chat(ChatMessage message);

}
