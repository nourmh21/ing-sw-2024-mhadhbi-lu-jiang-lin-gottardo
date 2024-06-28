package it.polimi.ingsw.main;



import it.polimi.ingsw.network.rmi.server.RMIServerImpl;
import it.polimi.ingsw.network.socket.server.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ServerApp {

    private static final int port = 49257;

    public static void main(String[] args) {

        String hostname = "127.0.0.1";
        int port_RMI = 1099;

        if (args.length >= 1) {
            try {
                hostname = args[0];
            }catch (NumberFormatException e){
                errorClose();
            }
        }
        if (args.length >= 2) {
            try {
                port_RMI = Integer.parseInt(args[1]);
            }catch (NumberFormatException e){
                errorClose();
            }
        }

        try {
            System.setProperty("java.rmi.server.hostname", hostname);
            RMIServerImpl obj =new RMIServerImpl();
            Registry registry = LocateRegistry.createRegistry(port_RMI);
            registry.rebind("Server",obj);

            // Creare un ScheduledExecutorService per eseguire checkClients ogni 5 secondi
            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            scheduler.scheduleAtFixedRate(() -> {
                try {
                    obj.checkClients();
                } catch (Exception e) {
                    System.err.println("ServerApp exception: " + e);
                }
                }, 0, 5, TimeUnit.SECONDS);


        } catch (Exception e) {
            System.err.println("ServerApp exception: " + e);
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

    public static void errorClose(){
        System.out.println("ERROR: invalid argument(s)");
        System.exit(0);
    }
}

