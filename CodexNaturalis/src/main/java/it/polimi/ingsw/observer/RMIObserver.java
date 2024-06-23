package it.polimi.ingsw.observer;

import it.polimi.ingsw.message.enums.NotifyType;
import it.polimi.ingsw.message.general.ChatMessage;
import it.polimi.ingsw.message.notify.NotifyMessage;
import it.polimi.ingsw.model.immutable.ImmutableEndGameInfo;
import it.polimi.ingsw.model.immutable.ImmutableGame;
import it.polimi.ingsw.model.immutable.ImmutableLobby;
import it.polimi.ingsw.model.immutable.ImmutablePlayer;
import it.polimi.ingsw.network.rmi.client.RMIClientInterface;

import java.rmi.RemoteException;
import java.util.List;

/**
 * The RMIObserver class implements {@link ModelObserver}
 * It represents observer connected with RMI
 */

public class RMIObserver implements ModelObserver {
    String nickname;
    RMIClientInterface rmiClient;

    public RMIObserver(String nickname, RMIClientInterface rmiClient) {
        this.nickname = nickname;
        this.rmiClient = rmiClient;
    }

    @Override
    public String getNickname() {
        return nickname;
    }

    //public RMIClientInter getRmiClient() {return rmiClient;}

    @Override
    public void send_lobby_status(ImmutableLobby lobby) {
        try {
                rmiClient.executeMessage(new NotifyMessage(NotifyType.LOBBY_STATUS,lobby));
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }

    }

    @Override
    public void send_game_status(ImmutableGame game) {
        try {
            rmiClient.executeMessage(new NotifyMessage(NotifyType.GAME_STATUS,game));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void send_player_status(ImmutablePlayer player) {
        try {
            rmiClient.executeMessage(new NotifyMessage(NotifyType.PLAYER_STATUS,player));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void send_two_personal_goal(Integer[] personalGoals) {
        try {
            rmiClient.executeMessage(new NotifyMessage(NotifyType.PERSONAL_GOALS,personalGoals));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void send_player_handCard(List<Integer> handCards) {
        try {
            rmiClient.executeMessage(new NotifyMessage(NotifyType.HAND_CARDS, handCards));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void send_end_game_info(ImmutableEndGameInfo endGameInfo) {
        try {
            rmiClient.executeMessage(new NotifyMessage(NotifyType.END_GAME_INFO,endGameInfo));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void send_player_disconnected(String nickname) {
        try {
            rmiClient.executeMessage(new NotifyMessage(NotifyType.PLAYER_DISCONNECTED,nickname));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void send_chat(ChatMessage message) {
        try {
            rmiClient.executeMessage(message);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
