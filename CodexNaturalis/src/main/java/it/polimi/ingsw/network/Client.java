package it.polimi.ingsw.network;

import it.polimi.ingsw.message.enums.ErrorType;
import it.polimi.ingsw.message.enums.NotifyType;
import it.polimi.ingsw.observer.Observer;

import java.util.List;

public abstract class Client {
    private String nickname;

    public Client(){
        nickname = null;
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
    }

    public String getNickname(){
        return nickname;
    }

    public abstract Observer getObserver();

    public abstract void informError(ErrorType errorType);

    public abstract void informActionResult(NotifyType notifyType);

    public abstract void informLobbyList(List<Integer[]> lobbies);

}
