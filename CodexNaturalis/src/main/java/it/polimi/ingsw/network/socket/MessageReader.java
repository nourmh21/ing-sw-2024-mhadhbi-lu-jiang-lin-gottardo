package it.polimi.ingsw.network.socket;

import it.polimi.ingsw.controller.client.ClientController;
import it.polimi.ingsw.message.Message;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;

public class MessageReader extends Thread{

    private final ObjectInputStream ois;
    private final Socket socket;

    private MessageExecutor messageExecutor;


    public MessageReader(ObjectInputStream ois, Socket socket){
        this.ois = ois;
        this.socket = socket;
        messageExecutor = new MessageExecutor();
    }

    @Override
    public void run() {
        while (!this.isInterrupted() && !socket.isClosed()){
            try {
                try {
                    Message message = (Message) ois.readUnshared();
                    messageExecutor.execute(message);
                }catch (SocketException | EOFException e){
                    ClientController.getInstance().setDisconnected();
                    ClientController.getInstance().getView().showConnectionError();
                    this.interrupt();
                    socket.close();
                }
            }catch (IOException | ClassNotFoundException e){
                e.printStackTrace();
            }

        }
    }
}
