package it.polimi.ingsw.network;


import it.polimi.ingsw.model.CardList;
import it.polimi.ingsw.network.socket.ClientHandler;

import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int port = 49251;
    public static void main(String[] args){

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    new ClientHandler(socket).start();
                    System.out.println("Client connected");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            //e.printStackTrace();
        }

    }






}

