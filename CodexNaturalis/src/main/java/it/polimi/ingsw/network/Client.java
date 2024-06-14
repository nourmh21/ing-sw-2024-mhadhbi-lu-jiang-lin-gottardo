package it.polimi.ingsw.network;

import it.polimi.ingsw.message.enums.ErrorType;
import it.polimi.ingsw.message.enums.NotifyType;
import it.polimi.ingsw.observer.ModelObserver;

import java.util.List;

/**
 * The Client abstract class is used to represents user's identity
 * It also provides methods to inform user about some action result/error
 */
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

    /**
     * @return an {@link ModelObserver}
     */
    public abstract ModelObserver getObserver();

    /**
     * Informs user about his/her action error
     * @param errorType {@link ErrorType}
     */
    public abstract void informError(ErrorType errorType);


    /**
     * Informs user about his/her action result
     * @param notifyType {@link NotifyType}
     */
    public abstract void informActionResult(NotifyType notifyType);


    /**
     * Informs user about the list of available lobbies
     * @param lobbies list of {@link it.polimi.ingsw.model.Lobby}
     */
    public abstract void informLobbyList(List<Integer[]> lobbies);

}
