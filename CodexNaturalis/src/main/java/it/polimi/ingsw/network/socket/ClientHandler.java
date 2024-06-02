package it.polimi.ingsw.network.socket;


import it.polimi.ingsw.controller.server.GameController;
import it.polimi.ingsw.message.Message;
import it.polimi.ingsw.message.enums.NotifyType;
import it.polimi.ingsw.message.notify.NotifyMessage;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static it.polimi.ingsw.message.enums.MessageType.HEARTBEAT;

public class ClientHandler extends Thread{

    private final Socket clientSocket;

    private OutputStream output;
    private InputStream input;
    private ScheduledExecutorService scheduler;

    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private long lastHeartbeatTime;



    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
        lastHeartbeatTime = System.currentTimeMillis();
        scheduler = Executors.newScheduledThreadPool(1);
    }


    @Override
    public void run() {


        try {
            input = clientSocket.getInputStream();
            output = clientSocket.getOutputStream();

            ois = new ObjectInputStream(input);
            oos = new ObjectOutputStream(output);

            Message response = new NotifyMessage(NotifyType.CONNECTED);
            oos.writeObject(response);

            scheduler.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    long currentTime = System.currentTimeMillis();
                    long heartbeatInterval = currentTime - lastHeartbeatTime;

                    if (heartbeatInterval > 30 * 1000) {
                        try {
                            close();
                        } catch (IOException e) {
                           e.printStackTrace();
                        }
                    }
                }
            }, 15, 15, TimeUnit.SECONDS);


            while (!this.isInterrupted()){
                try {
                    Message message = (Message) ois.readObject();
                    if (message.getType() == HEARTBEAT)
                        lastHeartbeatTime = System.currentTimeMillis();
                    else
                        GameController.getInstance().messageHandler(message,oos);
                }catch (SocketException|EOFException e) {
                    System.out.println("Client disconnected");
                    close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

        }catch (SocketException e){
            //System.out.println("Client disconnected");

        }catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println(" ");
        }

    }

    private void close() throws IOException {
        //In theory fixed
        GameController.getInstance().socketClientLeave(oos);
        ois.close();
        oos.close();
        input.close();
        output.close();
        this.interrupt();
    }



}





