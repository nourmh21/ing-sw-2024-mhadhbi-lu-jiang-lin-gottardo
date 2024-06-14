package it.polimi.ingsw.main;



import it.polimi.ingsw.network.socket.server.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {
    //如果抛不出来说明端口被占了
    private static final int port = 49257;
    public static void main(String[] args){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    new ClientHandler(socket).start();
                } catch (IOException ignored) {
                }
            }
        } catch (IOException e) {
            //e.printStackTrace();
        }

    }






}

