package it.polimi.ingsw.observer;

import it.polimi.ingsw.message.enums.NotifyType;
import it.polimi.ingsw.message.general.ChatMessage;
import it.polimi.ingsw.message.notify.NotifyMessage;
import it.polimi.ingsw.model.immutable.ImmutableEndGameInfo;
import it.polimi.ingsw.model.immutable.ImmutableGame;
import it.polimi.ingsw.model.immutable.ImmutableLobby;
import it.polimi.ingsw.model.immutable.ImmutablePlayer;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.SocketException;
import java.util.List;

public class SocketObserver implements Observer{
    String nickname;
    ObjectOutputStream oos;

    public SocketObserver(String nickname, ObjectOutputStream oos) {
        this.nickname = nickname;
        this.oos = oos;
    }


    public String getNickname() {
        return nickname;
    }


    @Override
    public void send_lobby_status(ImmutableLobby lobby) {
        try {
            oos.reset();
            oos.writeObject(new NotifyMessage(NotifyType.LOBBY_STATUS,lobby));
        }catch (SocketException exception){
            //
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void send_game_status(ImmutableGame game) {
        try {
            oos.reset();
            oos.writeObject(new NotifyMessage(NotifyType.GAME_STATUS,game));
        }catch (SocketException exception){
            //
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void send_player_status(ImmutablePlayer player) {
        try {
            oos.reset();
            oos.writeObject(new NotifyMessage(NotifyType.PLAYER_STATUS,player));
        }catch (SocketException exception){
            //
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void send_two_personal_goal(Integer[] personalGoals) {
        try {
            oos.writeObject(new NotifyMessage(NotifyType.PERSONAL_GOALS,personalGoals));
        }catch (SocketException exception){
            //
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void send_player_handCard(List<Integer> handCards) {
        try {
            oos.writeObject(new NotifyMessage(NotifyType.HAND_CARDS, handCards));
        }catch (SocketException exception){
            //
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void send_end_game_info(ImmutableEndGameInfo endGameInfo) {
        try {
            oos.writeObject(new NotifyMessage(NotifyType.END_GAME_INFO,endGameInfo));
        }catch (SocketException exception){
            //
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void send_player_disconnected(String nickname) {
        try {
            oos.writeObject(new NotifyMessage(NotifyType.PLAYER_DISCONNECTED,nickname));
        }catch (SocketException exception){
            //
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void send_chat(ChatMessage message) {
        try {
            oos.writeObject(message);
        }catch (SocketException exception){
            //
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ObjectOutputStream getOos() {
        return oos;
    }
}
