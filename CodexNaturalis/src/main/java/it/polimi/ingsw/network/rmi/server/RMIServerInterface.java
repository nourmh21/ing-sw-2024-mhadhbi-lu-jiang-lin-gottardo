package it.polimi.ingsw.network.rmi.server;

import it.polimi.ingsw.message.enums.LocationType;
import it.polimi.ingsw.message.general.ChatMessage;
import it.polimi.ingsw.network.rmi.client.RMIClientInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIServerInterface extends Remote {
    void checkClients()throws RemoteException;
    void registerClient(RMIClientInterface rmiClient)throws RemoteException;
    void access(String nickname, String pwd, boolean isRegistered, RMIClientInterface rmiClient)throws RemoteException;
    void  REQ_LOBBIES(RMIClientInterface rmiClient)throws RemoteException;
    void JOIN_LOBBY(RMIClientInterface rmiClient, int idLobby)throws RemoteException;
    void CREATE_LOBBY(RMIClientInterface rmiClient, int numOfPlayer)throws RemoteException;
    void playInitCard(Integer idCard, boolean isBackSide, RMIClientInterface rmiClient) throws RemoteException;
    void choosePersonalGoal(Integer idCard, RMIClientInterface rmiClient) throws RemoteException;
    void playCard(Integer idCard, boolean isBackSide, int[] position, RMIClientInterface rmiClient)throws RemoteException;
    void drawCard(LocationType location, Integer idCard, RMIClientInterface rmiClient)throws RemoteException;
    void chat (ChatMessage message)throws RemoteException;

}
