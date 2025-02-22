package it.polimi.ingsw.network.socket.server;


import it.polimi.ingsw.network.Client;
import it.polimi.ingsw.message.Message;
import it.polimi.ingsw.message.enums.ErrorType;
import it.polimi.ingsw.message.enums.NotifyType;
import it.polimi.ingsw.message.error.ErrorMessage;
import it.polimi.ingsw.message.notify.NotifyMessage;
import it.polimi.ingsw.observer.ModelObserver;
import it.polimi.ingsw.observer.SocketObserver;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * The SocketClient class is a subtype of {@link Client}
 * It represents client connected with socket
 */
public class SocketClient extends Client {
    private final ObjectOutputStream oos;

    /**
     * Constructor
     * @param oos the output stream of client
     */
    public SocketClient(ObjectOutputStream oos){
        this.oos = oos;
    }

    @Override
    public ModelObserver getObserver() {
        return new SocketObserver(getNickname(),this.oos);
    }

    @Override
    public void informError(ErrorType errorType) {
        sendMessage(new ErrorMessage(errorType));
    }

    @Override
    public void informActionResult(NotifyType notifyType) {
        sendMessage(new NotifyMessage(notifyType));
    }

    @Override
    public void informLobbyList(List<Integer[]> lobbies) {
        sendMessage(new NotifyMessage(NotifyType.LOBBY_LIST, lobbies));
    }

    /**
     * Sends message to client
     * @param message the {@link Message} to be sent
     */
    private void sendMessage(Message message){
        try {
            oos.reset();
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
