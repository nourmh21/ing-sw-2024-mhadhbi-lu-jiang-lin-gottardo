package it.polimi.ingsw.network.socket;


import it.polimi.ingsw.controller.GameController;
import it.polimi.ingsw.message.general.ConnectedMessage;
import it.polimi.ingsw.message.Message;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

import static it.polimi.ingsw.message.enums.MessageType.HEARTBEAT;

public class ClientHandler extends Thread{

    private final Socket clientSocket;

    private OutputStream output;
    private InputStream input;


    private ObjectInputStream ois;
    private ObjectOutputStream oos;



    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }


    @Override
    public void run() {


        try {

            output = clientSocket.getOutputStream();
            input = clientSocket.getInputStream();

            ois = new ObjectInputStream(input);
            oos = new ObjectOutputStream(output);

            Message response = new ConnectedMessage();
            oos.writeObject(response);


            while (!this.isInterrupted()){
                try {
                    Message message = (Message) ois.readObject();
                    if (message.getType() != HEARTBEAT)
                        //
                        GameController.getInstance().messageHandler(message,oos);

                }catch (SocketException e){
                    System.out.println("Client disconnected");
                    close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

        }catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println(" ");
        }

    }

    private void close() throws IOException {
        //??
        GameController.getInstance().removeUserLoggedIn(oos);
        ois.close();
        oos.close();
        input.close();
        output.close();
        this.interrupt();
    }



}





