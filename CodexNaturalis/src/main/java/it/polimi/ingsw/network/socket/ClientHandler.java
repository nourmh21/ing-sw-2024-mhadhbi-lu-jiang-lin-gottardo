package it.polimi.ingsw.network.socket;


import it.polimi.ingsw.controller.server.GameController;
import it.polimi.ingsw.message.Message;
import it.polimi.ingsw.message.enums.MessageType;
import it.polimi.ingsw.message.enums.NotifyType;
import it.polimi.ingsw.message.notify.NotifyMessage;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ClientHandler extends Thread{

    private final Socket clientSocket;

    private OutputStream output;
    private InputStream input;
    private ScheduledExecutorService scheduler;

    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private long lastReceivedTime;



    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
        lastReceivedTime = System.currentTimeMillis();
        scheduler = Executors.newScheduledThreadPool(1);
    }


    @Override
    public void run() {

        try {
            input = clientSocket.getInputStream();
            output = clientSocket.getOutputStream();

            ois = new ObjectInputStream(input);
            oos = new ObjectOutputStream(output);

            oos.writeObject(new NotifyMessage(NotifyType.CONNECTED));

            scheduler.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    long currentTime = System.currentTimeMillis();
                    long timeInterval = currentTime - lastReceivedTime;
                    if (timeInterval > 10 * 1000)
                        close();
                }
            }, 5, 5, TimeUnit.SECONDS);


            while (!this.isInterrupted()){
                try {
                    Message message = (Message) ois.readObject();
                    lastReceivedTime = System.currentTimeMillis();
                    if (message.getType() != MessageType.HEARTBEAT)
                        GameController.getInstance().messageHandler(message,oos);
                }catch (SocketException|EOFException e) {
                    System.out.println("Client disconnected");
                    close();
                }
            }

        }catch (SocketException e){
            close();
        }catch (IOException|ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void close(){
        //In theory fixed
        GameController.getInstance().socketClientLeave(oos);
        try {
            ois.close();
            oos.close();
            input.close();
            output.close();
        } catch (IOException ignored) {}
        this.interrupt();
    }



}





