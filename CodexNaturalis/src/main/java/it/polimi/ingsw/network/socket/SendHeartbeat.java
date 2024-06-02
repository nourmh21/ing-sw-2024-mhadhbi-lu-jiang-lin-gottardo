package it.polimi.ingsw.network.socket;

import it.polimi.ingsw.message.general.HeartbeatMessage;

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
                if ((System.currentTimeMillis() - lastSendTime) >= 15*1000){
                    oos.writeObject(new HeartbeatMessage());
                    lastSendTime = System.currentTimeMillis();
                }

            }

        } catch (SocketException e){
            //
        }catch (InterruptedException e) {
            throw new RuntimeException(e);
        }catch (IOException e){
            e.printStackTrace();
        }
    }



}
