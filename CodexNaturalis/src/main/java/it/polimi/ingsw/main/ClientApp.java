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
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class ClientApp {

    public static void main( String[] args ) {
        boolean isTUI = false;
        for (String arg : args) {
            if (arg.equals("--tui")) {
                isTUI = true;
                break;
            }
        }
        //da togliere
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


    public static void tryRMIConnection(String ip){

        RMIClientImpl client= null;
        try {
                client = new RMIClientImpl();
                // Getting the registry.
                Registry registry = LocateRegistry.getRegistry(ip, 1099);
                // Looking up the registry for the remote object
                RMIServerInterface stub = (RMIServerInterface) registry.lookup("Server");
                ClientController.getInstance().setClientAction(new RMIAction( stub,client));
                stub.registerClient(client);
        } catch (Exception e) {
                System.err.println("ClientApp exception: " + e.toString());
                ClientController.getInstance().getView().showConnectionError();
        }
    }


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
