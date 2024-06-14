package it.polimi.ingsw.controller.client;

import it.polimi.ingsw.message.enums.LocationType;
import it.polimi.ingsw.message.general.ChatMessage;

/**
 * The ClientAction interface provides methods to notify server about a client side action
 * Class that implements this: {@link it.polimi.ingsw.network.socket.client.SocketAction}, {@link it.polimi.ingsw.network.rmi.RMIAction}
 */
public interface ClientAction {

    /**
     * Notifies the login or the registration action
     * @param nickname the nickname entered
     * @param pwd the password entered
     * @param isRegistered true if it is login, false if it is registration
     */
    public void access(String nickname, String pwd, boolean isRegistered);

    /**
     * Notifies the request of available lobbies
     */
    public void reqLobbies();

    /**
     * Notifies the choice of lobby action
     * @param idLobby the id of lobby
     */
    public void joinLobby(Integer idLobby);

    /**
     * Notifies the lobby creation action
     * @param numOfPlayer the number of player for new lobby
     */
    public void createLobby(int numOfPlayer);

    /**
     * Notifies the initial card play action
     * @param idCard the id of card
     * @param isBackSide the side of card
     */
    public void playInitCard(Integer idCard, boolean isBackSide);

    /**
     * Notifies the personal objective(goal) card choice action
     * @param idCard the id of card
     */
    public void choosePersonalGoal(Integer idCard);

    /**
     * Notifies a card play action
     * @param idCard the id of card
     * @param isBackSide the side of card
     * @param position the position that client wants to play
     */
    public void playCard(Integer idCard, boolean isBackSide, int[] position);

    /**
     * Notifies a card draw action
     * @param location the {@link LocationType}
     * @param idCard the id of card
     */
    public void drawCard(LocationType location, Integer idCard);

    /**
     * Notifies a chat action
     * @param message the {@link ChatMessage}
     */
    public void chat(ChatMessage message);

}
