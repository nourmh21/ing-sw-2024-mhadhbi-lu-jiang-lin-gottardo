package it.polimi.ingsw.network.rmi.server;

import it.polimi.ingsw.message.enums.LocationType;
import it.polimi.ingsw.message.general.ChatMessage;
import it.polimi.ingsw.network.rmi.client.RMIClientInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Remote interface for the RMI server defining available methods for clients.
 */
public interface RMIServerInterface extends Remote {

    /**
     * Checks the status of connected clients.
     *
     * @throws RemoteException Thrown if there are issues in remote communication.
     */
    void checkClients()throws RemoteException;

    /**
     * Registers a specific client to receive notifications.
     *
     * @param rmiClient The client to register.
     * @throws RemoteException Thrown if there are issues in remote communication.
     */
    void registerClient(RMIClientInterface rmiClient)throws RemoteException;

    /**
     * Handles client access using nickname, password, and registration status.
     *
     * @param nickname     The client's nickname.
     * @param pwd          The client's password.
     * @param isRegistered The registration status of the client.
     * @param rmiClient    The RMI client requesting access.
     * @throws RemoteException Thrown if there are issues in remote communication.
     */
    void access(String nickname, String pwd, boolean isRegistered, RMIClientInterface rmiClient)throws RemoteException;

    /**
     * Sends a request for information about available lobbies to a client.
     *
     * @param rmiClient The RMI client requesting lobby information.
     * @throws RemoteException Thrown if there are issues in remote communication.
     */
    void  REQ_LOBBIES(RMIClientInterface rmiClient)throws RemoteException;

    /**
     * Allows a client to join a specific lobby.
     *
     * @param rmiClient The RMI client wanting to join the lobby.
     * @param idLobby   The ID of the lobby to join.
     * @throws RemoteException Thrown if there are issues in remote communication.
     */
    void JOIN_LOBBY(RMIClientInterface rmiClient, int idLobby)throws RemoteException;

    /**
     * Allows a client to create a new lobby.
     *
     * @param rmiClient   The RMI client creating the lobby.
     * @param numOfPlayer The number of players in the lobby.
     * @throws RemoteException Thrown if there are issues in remote communication.
     */
    void CREATE_LOBBY(RMIClientInterface rmiClient, int numOfPlayer)throws RemoteException;

    /**
     * Allows a client to play an initial card.
     *
     * @param idCard     The ID of the card to play.
     * @param isBackSide True if the card is played facing down, false otherwise.
     * @param rmiClient  The RMI client playing the card.
     * @throws RemoteException Thrown if there are issues in remote communication.
     */
    void playInitCard(Integer idCard, boolean isBackSide, RMIClientInterface rmiClient) throws RemoteException;

    /**
     * Allows a client to choose a personal goal.
     *
     * @param idCard    The ID of the personal goal card.
     * @param rmiClient The RMI client choosing the goal.
     * @throws RemoteException Thrown if there are issues in remote communication.
     */
    void choosePersonalGoal(Integer idCard, RMIClientInterface rmiClient) throws RemoteException;

    /**
     * Allows a client to play a card.
     *
     * @param idCard     The ID of the card to play.
     * @param isBackSide True if the card is played facing down, false otherwise.
     * @param position   The position to play the card.
     * @param rmiClient  The RMI client playing the card.
     * @throws RemoteException Thrown if there are issues in remote communication.
     */
    void playCard(Integer idCard, boolean isBackSide, int[] position, RMIClientInterface rmiClient)throws RemoteException;

    /**
     * Allows a client to draw a card .
     *
     * @param location   The type of location from which to draw the card.
     * @param idCard     The ID of the card to draw.
     * @param rmiClient  The RMI client drawing the card.
     * @throws RemoteException Thrown if there are issues in remote communication.
     */
    void drawCard(LocationType location, Integer idCard, RMIClientInterface rmiClient)throws RemoteException;

    /**
     * Allows a client to send a message in the chat.
     *
     * @param message The message to send in the chat.
     * @throws RemoteException Thrown if there are issues in remote communication.
     */
    void chat (ChatMessage message)throws RemoteException;

}
