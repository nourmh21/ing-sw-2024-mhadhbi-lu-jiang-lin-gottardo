package it.polimi.ingsw.network;


import it.polimi.ingsw.controller.client.ClientController;
import it.polimi.ingsw.controller.client.SocketAction;
import it.polimi.ingsw.message.Message;
import it.polimi.ingsw.network.socket.SendHeartbeat;
import it.polimi.ingsw.view.gui.GUIApplication;
import it.polimi.ingsw.view.tui.TUI;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;



public class Client
{
    public static void main( String[] args ) {
        boolean isTUI = false;
        for (String arg : args) {
            if (arg.equals("--tui")) {
                isTUI = true;
                break;
            }
        }

        //for test
        isTUI = false;

        try {
            if (isTUI){
                TUI tui = new TUI();
                ClientController.getInstance().setView(tui);
                tui.start();
            }else {
                GUIApplication gui = new GUIApplication();
                ClientController.getInstance().setView(gui);
                gui.launch();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void trySocketConnection(String ip){

        try {
            Socket socket = new Socket(ip, 49257);
            OutputStream output = socket.getOutputStream();
            InputStream input = socket.getInputStream();

            ObjectOutputStream oos = new ObjectOutputStream(output);
            ObjectInputStream ois = new ObjectInputStream(input);

            new SendHeartbeat(oos).start();
            ClientController.getInstance().setClientAction(new SocketAction(oos));

            new Thread(()-> {
                try {
                    while (true){
                        Message message = (Message) ois.readUnshared();
                        ClientController.getInstance().messageHandler(message);
                    }
                }catch (SocketException e){
                    ClientController.getInstance().getView().showServerOffline();
                }catch (IOException |ClassNotFoundException e){
                    e.printStackTrace();
                }
            }).start();


        } catch (SocketException e){
            //ClientController.getInstance().getView().showServerOffline();
        } catch (IOException e) {
            e.printStackTrace();
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
            if ((Integer.parseInt(eachPart)<0)||(Integer.parseInt(eachPart)>255))
                return false;
        }
        return true;
    }


}
