package it.polimi.ingsw.network.rmi.client;

import it.polimi.ingsw.controller.client.ClientAction;
import it.polimi.ingsw.message.enums.LocationType;
import it.polimi.ingsw.message.general.ChatMessage;
import it.polimi.ingsw.network.rmi.server.RMIServerInterface;

import java.rmi.RemoteException;

//to be completed by Nourhane
public class RMIAction implements ClientAction {
    private final RMIClientInterface rmiClient;
    private final RMIServerInterface stub;
    public RMIAction(RMIServerInterface server , RMIClientInterface rmiClient) {
        this.stub = server;
        this.rmiClient = rmiClient;
    }
    @Override
    public void access(String nickname, String pwd, boolean isRegistered) {
        try {
            stub.access(nickname,pwd,isRegistered,rmiClient);
        }catch (IllegalArgumentException e){
                System.out.println("error");
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void reqLobbies() {
        try {

            stub.REQ_LOBBIES(rmiClient);

        }catch (IllegalArgumentException e){
            System.out.println("error");
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void joinLobby(Integer idLobby) {
        try {

            stub.JOIN_LOBBY(rmiClient,idLobby);

        }catch (IllegalArgumentException e){
            System.out.println("error");
        } catch (RemoteException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void createLobby(int numOfPlayer) {
        try {

            stub.CREATE_LOBBY(rmiClient,numOfPlayer);

        }catch (IllegalArgumentException e){
            System.out.println("error");
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void playInitCard(Integer idCard, boolean isBackSide) {


        try {

                stub.playInitCard(idCard,isBackSide,rmiClient);

        }catch (IllegalArgumentException e){
                System.out.println("error");
        } catch (RemoteException e) {
                e.printStackTrace();
        }

    }

    @Override
    public void choosePersonalGoal(Integer idCard) {

        try {

                stub.choosePersonalGoal(idCard,rmiClient);

        }catch (IllegalArgumentException e){
                System.out.println("error");
        } catch (RemoteException e) {
                e.printStackTrace();
        }

    }

    @Override
    public void playCard(Integer idCard, boolean isBackSide, int[] position) {

        try {

                stub.playCard(idCard,isBackSide,position,rmiClient);

        }catch (IllegalArgumentException e){
                System.out.println("error");
        } catch (RemoteException e) {
                e.printStackTrace();
        }

    }

    @Override
    public void drawCard(LocationType location, Integer idCard) {

      try {

            stub.drawCard(location,idCard,rmiClient);

      }catch (IllegalArgumentException e){
            System.out.println("error");
      } catch (RemoteException e) {
            e.printStackTrace();
      }

    }




    @Override
    public void chat(ChatMessage message) {
        try {
            stub.chat(message);
        }catch (IllegalArgumentException e){
            System.out.println("error");
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

}
