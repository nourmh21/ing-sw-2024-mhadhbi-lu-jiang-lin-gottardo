package it.polimi.ingsw.network.socket.client;

import it.polimi.ingsw.message.general.HeartbeatMessage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

/**
 * The HeartbeatSender is a thread that sends heartbeat messages periodically
 */
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
        while (!this.isInterrupted() && !socket.isClosed()){
            if ((System.currentTimeMillis() - lastSendTime) >= 5*1000){
                try {
                    try {
                        oos.writeObject(new HeartbeatMessage());
                        lastSendTime = System.currentTimeMillis();
                    }catch (SocketException e){
                        socket.close();
                        this.interrupt();
                    }
                }catch (IOException ignored){}
            }
        }
    }


}
