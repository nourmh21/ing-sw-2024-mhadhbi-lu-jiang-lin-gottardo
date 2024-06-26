package it.polimi.ingsw.network.rmi.client;

import it.polimi.ingsw.message.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * The RMIClientInterface  represents a client in a network communication.
 * It extends the {@link Remote} interface and provides a method for updating the client with a {@link Message}.
 */

public interface RMIClientInterface extends Remote {
    void ping() throws RemoteException;
    void executeMessage(Message message)throws RemoteException;

}
