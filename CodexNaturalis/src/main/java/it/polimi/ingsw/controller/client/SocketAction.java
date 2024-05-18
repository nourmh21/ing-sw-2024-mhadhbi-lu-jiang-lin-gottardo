package it.polimi.ingsw.controller.client;

import it.polimi.ingsw.controller.client.ClientAction;
import it.polimi.ingsw.controller.client.ClientController;
import it.polimi.ingsw.message.enums.LocationType;
import it.polimi.ingsw.message.general.*;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.SocketException;

public class SocketAction implements ClientAction {
    private ObjectOutputStream oos;
    public SocketAction(ObjectOutputStream oos){
        this.oos = oos;
    }

    @Override
    public void access(String nickname, String pwd, boolean isRegistered) {
        try {
            oos.writeObject(new AccessMessage(nickname,pwd,isRegistered));
        }catch (SocketException exception){
            ClientController.getInstance().getView().showServerOffline();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void joinGame() {
        try {
            oos.writeObject(new JoinGameMessage());
        }catch (SocketException exception){
            ClientController.getInstance().getView().showServerOffline();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void newGame(int numOfPlayer) {
        try {
            oos.writeObject(new NewGameInfoMessage(numOfPlayer));
        }catch (SocketException exception){
            ClientController.getInstance().getView().showServerOffline();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void playInitCard(Integer idCard, boolean isBackSide) {
        try {
            oos.writeObject(new PlayInitCardMessage(idCard,isBackSide));
            oos.flush();
        }catch (SocketException exception){
            ClientController.getInstance().getView().showServerOffline();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void choosePersonalGoal(Integer idCard) {
        try {
            oos.writeObject(new PersonalGoalChooseMessage(idCard));
        }catch (SocketException exception){
            ClientController.getInstance().getView().showServerOffline();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void playCard(Integer idCard, boolean isBackSide, int[] position) {
        try {
            oos.writeObject(new PlayCardMessage(idCard,isBackSide,position));
        }catch (SocketException exception){
            ClientController.getInstance().getView().showServerOffline();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void drawCard(LocationType location, Integer idCard) {
        try {
            oos.reset();
            if (idCard != null)
                oos.writeObject(new DrawCardMessage(location,idCard));
            else
                oos.writeObject(new DrawCardMessage(location));
        }catch (SocketException exception){
            ClientController.getInstance().getView().showServerOffline();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
