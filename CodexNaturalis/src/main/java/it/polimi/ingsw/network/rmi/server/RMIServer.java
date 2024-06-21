package it.polimi.ingsw.network.rmi.server;

import it.polimi.ingsw.message.enums.LocationType;
import it.polimi.ingsw.message.general.ChatMessage;
import it.polimi.ingsw.network.rmi.client.RMIClientInter;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIServer extends Remote {
    void checkClients()throws RemoteException;
    void registerClient(RMIClientInter rmiClient)throws RemoteException;
    void access(String nickname, String pwd, boolean isRegistered, RMIClientInter rmiClient)throws RemoteException;
    void  REQ_LOBBIES(RMIClientInter rmiClient)throws RemoteException;
    void JOIN_LOBBY(RMIClientInter rmiClient, int idLobby)throws RemoteException;
    void CREATE_LOBBY(RMIClientInter rmiClient, int numOfPlayer)throws RemoteException;
    void playInitCard(Integer idCard, boolean isBackSide, RMIClientInter rmiClient) throws RemoteException;
    void choosePersonalGoal(Integer idCard, RMIClientInter rmiClient) throws RemoteException;
    void playCard(Integer idCard, boolean isBackSide, int[] position, RMIClientInter rmiClient)throws RemoteException;
    void drawCard(LocationType location, Integer idCard, RMIClientInter rmiClient)throws RemoteException;
    void chat (ChatMessage message)throws RemoteException;

}
