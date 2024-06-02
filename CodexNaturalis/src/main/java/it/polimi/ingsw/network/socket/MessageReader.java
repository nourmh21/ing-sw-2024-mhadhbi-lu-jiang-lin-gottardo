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

    public MessageReader(ObjectInputStream ois, Socket socket){
        this.ois = ois;
        this.socket = socket;
    }

    @Override
    public void run() {
        while (!this.isInterrupted()){
            try {
                try {
                    Message message = (Message) ois.readUnshared();
                    ClientController.getInstance().messageHandler(message);
                }catch (SocketException | EOFException e){
                    this.interrupt();
                    socket.close();
                }
            }catch (IOException | ClassNotFoundException e){
                e.printStackTrace();
            }

        }
    }
}
