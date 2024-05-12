package it.polimi.ingsw.network;




import it.polimi.ingsw.network.socket.SendHeartbeat;
import it.polimi.ingsw.view.TUI;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;


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

        isTUI = true;

        if (isTUI){
            try {
                new TUI();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
            ;


    }


    public static void trySocketConnection(String ip){
        try {
            Socket socket = new Socket(ip, 49251);
            OutputStream output = socket.getOutputStream();
            InputStream input = socket.getInputStream();

            ObjectOutputStream oos = new ObjectOutputStream(output);
            ObjectInputStream ois = new ObjectInputStream(input);

            new SendHeartbeat(oos).start();

        } catch (SocketException e){
            System.out.println("Server crashed, try later");
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
