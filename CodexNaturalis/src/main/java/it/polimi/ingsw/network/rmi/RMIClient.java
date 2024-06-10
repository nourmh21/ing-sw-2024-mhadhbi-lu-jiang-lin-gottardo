package it.polimi.ingsw.network.rmi;

import it.polimi.ingsw.message.enums.ErrorType;
import it.polimi.ingsw.message.enums.NotifyType;
import it.polimi.ingsw.network.Client;
import it.polimi.ingsw.observer.Observer;

import java.util.List;

public class RMIClient extends Client {

    @Override
    public Observer getObserver() {
        return null;
    }

    @Override
    public void informError(ErrorType errorType) {}

    @Override
    public void informActionResult(NotifyType notifyType) {}

    @Override
    public void informLobbyList(List<Integer[]> lobbies) {}

}
