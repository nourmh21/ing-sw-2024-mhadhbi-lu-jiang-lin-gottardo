package it.polimi.ingsw.network.rmi.client;

import it.polimi.ingsw.network.MessageExecutor;
import it.polimi.ingsw.message.Message;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIClientImpl extends UnicastRemoteObject implements RMIClientInterface {

    private MessageExecutor messageExecutor;

    public RMIClientImpl() throws RemoteException {

        messageExecutor = new MessageExecutor();
    }


    @Override
    public void ping() throws RemoteException {}

    @Override
    public void executeMessage(Message message) throws RemoteException {
        messageExecutor.execute(message);
    }


}
