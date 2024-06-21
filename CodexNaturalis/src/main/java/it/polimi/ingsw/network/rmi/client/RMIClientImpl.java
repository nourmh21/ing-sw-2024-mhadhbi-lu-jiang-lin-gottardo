package it.polimi.ingsw.network.rmi.client;

import it.polimi.ingsw.network.socket.client.MessageExecutor;
import it.polimi.ingsw.message.Message;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIClientImpl extends UnicastRemoteObject implements RMIClientInter {

    private MessageExecutor messageExecutor;

    public RMIClientImpl() throws RemoteException {

        messageExecutor = new MessageExecutor();
    }


    @Override
    public void ping() throws RemoteException {}

    @Override
    public void executMessage(Message message) throws RemoteException {
        messageExecutor.execute(message);
    }


}
