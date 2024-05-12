package it.polimi.ingsw.network.socket;

import it.polimi.ingsw.message.enums.MessageType;
import it.polimi.ingsw.message.HeartbeatMessage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.SocketException;


public class SendHeartbeat extends Thread{

    private ObjectOutputStream oos;
    private long lastSendTime;

    public SendHeartbeat(ObjectOutputStream oos){
        this.oos = oos;
        lastSendTime = System.currentTimeMillis();
    }


    @Override
    public void run() {
        try {
            while (true){
                //there are decisions to be made here
                Thread.sleep(1*1000);
                if ((System.currentTimeMillis() - lastSendTime) >= 3*1000){
                    oos.writeObject(new HeartbeatMessage(MessageType.HEARTBEAT));
                    lastSendTime = System.currentTimeMillis();
                }

            }

        } catch (SocketException e){
            System.out.println("Server crashed, try later");
        }catch (InterruptedException e) {
            throw new RuntimeException(e);
        }catch (IOException e){
            e.printStackTrace();
        }
    }



}
