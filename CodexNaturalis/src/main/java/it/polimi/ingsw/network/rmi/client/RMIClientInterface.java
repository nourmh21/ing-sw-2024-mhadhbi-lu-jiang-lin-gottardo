package it.polimi.ingsw.network.rmi.client;

import it.polimi.ingsw.message.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIClientInterface extends Remote {
    void ping() throws RemoteException;
    void executeMessage(Message message)throws RemoteException;
}
