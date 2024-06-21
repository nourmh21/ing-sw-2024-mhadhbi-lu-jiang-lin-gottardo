package it.polimi.ingsw.network.rmi.client;

import it.polimi.ingsw.message.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIClientInter extends Remote {
    void ping() throws RemoteException;
    void executMessage(Message message)throws RemoteException;
}
