package it.polimi.ingsw.network.rmi.server;

import it.polimi.ingsw.controller.server.GameController;
import it.polimi.ingsw.message.enums.LocationType;
import it.polimi.ingsw.message.general.ChatMessage;

import it.polimi.ingsw.network.Client;
import it.polimi.ingsw.network.rmi.client.RMIClientInter;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RMIServerImpl extends UnicastRemoteObject implements RMIServer {
    Map<RMIClientInter, Client> clientMap = new HashMap<>();
    public RMIServerImpl() throws RemoteException {}

    @Override
    public void checkClients() throws RemoteException{
        List<RMIClientInter> disconnectedClient=new ArrayList<>();
        for (RMIClientInter client : clientMap.keySet()) {
            try {
                client.ping();
            } catch (RemoteException e) {
                // The client did not respond to the ping, so we assume it is disconnected
                GameController.getInstance().clientLeave(clientMap.get(client));
                disconnectedClient.add(client);
            }
        }
        for (RMIClientInter client : disconnectedClient){
            clientMap.remove(client);
        }
    }

    @Override
    public void registerClient(RMIClientInter rmiClient) throws RemoteException {
        Client client = new RMIClient( rmiClient);
        clientMap.put(rmiClient,client);
        GameController.getInstance().getClientManager().addClient(client);
    }

    @Override
    public void access(String nickname, String pwd, boolean isRegistered, RMIClientInter rmiClient) throws RemoteException {
        GameController.getInstance().access(clientMap.get(rmiClient), nickname,pwd,isRegistered);
    }

    @Override
    public void REQ_LOBBIES(RMIClientInter rmiClient) throws RemoteException {
        GameController.getInstance().giveLobbies(clientMap.get(rmiClient));
    }

    @Override
    public void JOIN_LOBBY(RMIClientInter rmiClient, int idLobby) throws RemoteException {
        GameController.getInstance().joinLobby(clientMap.get(rmiClient), idLobby);
    }

    @Override
    public void CREATE_LOBBY(RMIClientInter rmiClient, int numOfPlayer) throws RemoteException {
        GameController.getInstance().createLobby(clientMap.get(rmiClient), numOfPlayer);
    }

    @Override
    public void playInitCard(Integer idCard, boolean isBackSide, RMIClientInter rmiClient) throws RemoteException {
        GameController.getInstance().playInitCard(clientMap.get(rmiClient), idCard, isBackSide);
    }

    @Override
    public void choosePersonalGoal(Integer idCard, RMIClientInter rmiClient) throws RemoteException {
        GameController.getInstance().setPersonalGoal(clientMap.get(rmiClient), idCard);
    }

    @Override
    public void playCard(Integer idCard, boolean isBackSide, int[] position, RMIClientInter rmiClient) throws RemoteException {
        GameController.getInstance().playCard(clientMap.get(rmiClient), idCard, position, isBackSide);
    }

    @Override
    public void drawCard(LocationType location, Integer idCard, RMIClientInter rmiClient) throws RemoteException {
        GameController.getInstance().drawCard(clientMap.get(rmiClient), location, idCard);
    }

    @Override
    public void chat(ChatMessage message) throws RemoteException {
        GameController.getInstance().chatManage( message);
    }

}
