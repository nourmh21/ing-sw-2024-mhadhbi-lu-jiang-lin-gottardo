package it.polimi.ingsw.main;



import it.polimi.ingsw.network.rmi.server.RMIServerImpl;
import it.polimi.ingsw.network.socket.server.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ServerApp {

    private static final int port = 49257;
    private static final int port_RMI= 1099;
    public static void main(String[] args) {
        try {
            var ref = new Object() {
                RMIServerImpl obj = null;
            };
            ref.obj = new RMIServerImpl();

            try {
                Registry registry = LocateRegistry.createRegistry(port_RMI);
                registry.rebind("Server", ref.obj);
                //da controllare
                System.setProperty("java.rmi.server.hostname", "127.0.0.1");


                // Creare un ScheduledExecutorService per eseguire checkClients ogni 5 secondi
                ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
                scheduler.scheduleAtFixedRate(() -> {
                    try {
                        ref.obj.checkClients();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }, 0, 5, TimeUnit.SECONDS);


            } catch (Exception e) {
                System.err.println("Server exception: " + e.toString());
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.err.println("Cannot start RMI.");
        }


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

