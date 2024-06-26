package it.polimi.ingsw.main;


import it.polimi.ingsw.controller.client.ClientController;
import it.polimi.ingsw.network.rmi.client.RMIAction;
import it.polimi.ingsw.network.rmi.client.RMIClientImpl;
import it.polimi.ingsw.network.rmi.server.RMIServerInterface;
import it.polimi.ingsw.network.socket.client.SocketAction;
import it.polimi.ingsw.network.socket.client.HeartbeatSender;
import it.polimi.ingsw.network.socket.client.MessageReader;
import it.polimi.ingsw.view.gui.GUIApplication;
import it.polimi.ingsw.view.tui.TUI;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class ClientApp {

    /**
     * The main entry point of the client application.
     * @param args the command-line arguments
     */
    public static void main( String[] args ) {
        boolean isTUI = false;

        for (String arg : args) {
            if (arg.equals("--tui")) {
                isTUI = true;
                break;
            }
        }

        if (isTUI){
            TUI tui = new TUI();
            ClientController.getInstance().setView(tui);
            tui.askServerIP();
        }else {
            GUIApplication gui = new GUIApplication();
            ClientController.getInstance().setView(gui);
            gui.launch();
        }
    }


    /**
     * Sets up the socket client by connecting to the socket server located at the specified IP and port.
     * @param ip the IP address of the socket server.
     */
    public static void trySocketConnection(String ip){
        try {
            Socket socket = new Socket(ip, 49257);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ClientController.getInstance().setClientAction(new SocketAction(oos));
            new HeartbeatSender(oos, socket).start();
            new MessageReader(ois, socket).start();
        }catch (IOException e) {
            ClientController.getInstance().getView().showConnectionError();
        }
    }


    /**
     * Sets up the RMI client by connecting to the RMI registry located at the specified IP and port.
     * Throws exceptions if the IP address or port is invalid or if the RMI registry is not bound.
     * @param ip the IP address of the RMI registry.
     * @throws RemoteException if a remote communication error occurs.
     * @throws NotBoundException if an attempt is made to lookup in the registry a name that has no associated binding.
     */
    public static void tryRMIConnection(String ip) throws RemoteException , NotBoundException {
        try {
                RMIClientImpl client= new RMIClientImpl();
                // Getting the registry.
                Registry registry = LocateRegistry.getRegistry(ip, 1099);
                // Looking up the registry for the remote object
                RMIServerInterface stub = (RMIServerInterface) registry.lookup("Server");
                ClientController.getInstance().setClientAction(new RMIAction( stub,client));
                stub.registerClient(client);
        } catch (Exception e) {
                System.err.println("ClientApp exception: " + e);
                ClientController.getInstance().getView().showConnectionError();
        }
    }


    /**
     * check if the provided IP address is valid.
     */
    public static boolean checkIPValidity(String ip){
        if (ip.isEmpty()) {
            return false;
        }
        String[] parts = ip.split("\\.");
        if (parts.length != 4) {
            return false;
        }
        for (String eachPart:parts) {
            try {
                if ((Integer.parseInt(eachPart)<0)||(Integer.parseInt(eachPart)>255))
                    return false;
            }catch (NumberFormatException e){
                return false;
            }
        }
        return true;
    }


}
