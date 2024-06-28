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

/**
 * Main class that starts a server to handle RMI and Socket client connections.
 * The server uses RMI to expose a remote interface and Socket to accept connections from clients.
 * Uses an instance of RMIServerImpl to handle RMI operations and starts a thread for each Socket client
 * that connects, handled by the ClientHandler class.
 */
public class ServerApp {
    private static final int port = 49257;// Default Socket port

    /**
     * Main method that starts the server.
     * Configure hostname for RMI, initialize RMI server, manage Socket clients and related exceptions.
     *
     * @param args Command line arguments: [hostname] [RMI port]
     *            hostname: IP address of the host (optional, default: 127.0.0.1)
     *            RMI port: Port for RMI register (optional, default: 1099)
     */
    public static void main(String[] args) {
        String hostname = "127.0.0.1";// Default IP
        int port_RMI = 1099; // Default RMI port
        // Check if hostname and port arguments have been provided
        if (args.length >= 1) {
            hostname = args[0];
        }
        if (args.length >= 2) {
            try {
                port_RMI = Integer.parseInt(args[1]);
            }catch (NumberFormatException e){
                errorClose();
            }
        }

        try {
            // Set the hostname for the RMI server
            System.setProperty("java.rmi.server.hostname", hostname);

            RMIServerImpl obj =new RMIServerImpl();
            Registry registry = LocateRegistry.createRegistry(port_RMI);
            registry.rebind("Server",obj);

            // Create a ScheduledExecutorService to run checkClients every 5 seconds
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
            // Create a ServerSocket to accept connections from clients on the primary port
            ServerSocket serverSocket = new ServerSocket(port);
            // Main loop to accept incoming clients
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

