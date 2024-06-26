package it.polimi.ingsw.network.rmi.client;

import it.polimi.ingsw.network.MessageExecutor;
import it.polimi.ingsw.message.Message;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * The RMIClientImpl class is an implementation of the {@link RMIClientInterface } interface.
 * It extends the {@link UnicastRemoteObject} class .
 */

public class RMIClientImpl extends UnicastRemoteObject implements RMIClientInterface {

    private final MessageExecutor messageExecutor;


    public RMIClientImpl() throws RemoteException {
        messageExecutor = new MessageExecutor();
    }

    /**
     * server can invoke periodically to check if the client is still connected.
     * @throws RemoteException if a remote communication error occurs
     */
    @Override
    public void ping() throws RemoteException {}

    /**
     * update the client with a {@link Message}
     * @throws RemoteException if a remote communication error occurs
     */
    @Override
    public void executeMessage(Message message) throws RemoteException {
        messageExecutor.execute(message);
    }


}
