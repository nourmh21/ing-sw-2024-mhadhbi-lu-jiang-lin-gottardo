package it.polimi.ingsw.network.rmi.server;

import it.polimi.ingsw.message.enums.ErrorType;
import it.polimi.ingsw.message.enums.NotifyType;
import it.polimi.ingsw.message.error.ErrorMessage;
import it.polimi.ingsw.message.notify.NotifyMessage;
import it.polimi.ingsw.network.Client;
import it.polimi.ingsw.network.rmi.client.RMIClientInterface;
import it.polimi.ingsw.observer.ModelObserver;
import it.polimi.ingsw.observer.RMIObserver;

import java.rmi.RemoteException;
import java.util.List;

public class RMIClient extends Client {
    private final RMIClientInterface rmiClient;

    public RMIClient(RMIClientInterface rmiClient)  {

        this.rmiClient= rmiClient;
        try {
            rmiClient.executMessage(new NotifyMessage(NotifyType.CONNECTED));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ModelObserver getObserver() {
        return  new RMIObserver(getNickname(),this.rmiClient);
    }

    @Override
    public void informError(ErrorType errorType) {
        try {
            rmiClient.executMessage(new ErrorMessage(errorType));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void informActionResult(NotifyType notifyType) {
        try {
            rmiClient.executMessage(new NotifyMessage(notifyType));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void informLobbyList(List<Integer[]> lobbies) {
        try {
            rmiClient.executMessage(new NotifyMessage(NotifyType.LOBBY_LIST, lobbies));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
