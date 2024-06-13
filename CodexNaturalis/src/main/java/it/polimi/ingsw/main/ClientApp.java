package it.polimi.ingsw.main;


import it.polimi.ingsw.controller.client.ClientController;
import it.polimi.ingsw.controller.client.SocketAction;
import it.polimi.ingsw.network.socket.HeartbeatSender;
import it.polimi.ingsw.network.socket.MessageReader;
import it.polimi.ingsw.view.gui.GUIApplication;
import it.polimi.ingsw.view.tui.TUI;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class ClientApp {

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


    //to be completed by Nourhane
    public static void tryRMIConnection(String ip){

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
