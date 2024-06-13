package it.polimi.ingsw.network.socket.client;

import it.polimi.ingsw.controller.client.ClientAction;
import it.polimi.ingsw.message.Message;
import it.polimi.ingsw.message.enums.LocationType;
import it.polimi.ingsw.message.general.*;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class SocketAction implements ClientAction {
    private ObjectOutputStream oos;

    public SocketAction(ObjectOutputStream oos){
        this.oos = oos;
    }

    @Override
    public void access(String nickname, String pwd, boolean isRegistered) {
        send(new AccessMessage(nickname,pwd,isRegistered));
    }

    @Override
    public void reqLobbies() {
        send(new ReqLobbiesMessage());
    }

    @Override
    public void joinLobby(Integer idLobby) {
        send(new JoinLobbyeMessage(idLobby));
    }

    @Override
    public void createLobby(int numOfPlayer) {
        send(new CreateLobbyMessage(numOfPlayer));
    }

    @Override
    public void playInitCard(Integer idCard, boolean isBackSide) {
        send(new PlayInitCardMessage(idCard,isBackSide));
    }

    @Override
    public void choosePersonalGoal(Integer idCard) {
        send(new PersonalGoalChooseMessage(idCard));
    }

    @Override
    public void playCard(Integer idCard, boolean isBackSide, int[] position) {
        send(new PlayCardMessage(idCard,isBackSide,position));
    }

    @Override
    public void drawCard(LocationType location, Integer idCard) {
        if (idCard != null)
            send(new DrawCardMessage(location,idCard));
        else
            send(new DrawCardMessage(location));
    }

    @Override
    public void chat(ChatMessage message) {
        send(message);
    }

    private void send(Message message){
        try {
            oos.writeObject(message);
        }catch (IOException ignored) {}
    }

}
