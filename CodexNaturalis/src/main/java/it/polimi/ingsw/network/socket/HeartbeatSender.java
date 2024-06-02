package it.polimi.ingsw.network.socket;

import it.polimi.ingsw.controller.client.ClientController;
import it.polimi.ingsw.message.general.HeartbeatMessage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;


public class HeartbeatSender extends Thread{

    private ObjectOutputStream oos;
    private Socket socket;
    private long lastSendTime;

    public HeartbeatSender(ObjectOutputStream oos, Socket socket){
        this.oos = oos;
        this.socket = socket;
        lastSendTime = System.currentTimeMillis();
    }

    @Override
    public void run() {
        while (!this.isInterrupted()){
            if ((System.currentTimeMillis() - lastSendTime) >= 5*1000){
                try {
                    try {
                        oos.writeObject(new HeartbeatMessage());
                        lastSendTime = System.currentTimeMillis();
                    }catch (SocketException e){
                        ClientController.getInstance().getView().showConnectionError();
                        this.interrupt();
                        socket.close();
                    }
                }catch (IOException ignored){}
            }
        }
    }


}
