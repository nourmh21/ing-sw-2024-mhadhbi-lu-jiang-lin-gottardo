package it.polimi.ingsw.network.socket;


import it.polimi.ingsw.network.Client;
import it.polimi.ingsw.controller.server.GameController;
import it.polimi.ingsw.message.Message;
import it.polimi.ingsw.message.enums.LocationType;
import it.polimi.ingsw.message.enums.MessageType;
import it.polimi.ingsw.message.enums.NotifyType;
import it.polimi.ingsw.message.general.*;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ClientHandler extends Thread{

    private final Socket clientSocket;
    private final Client thisClient;
    private final ScheduledExecutorService scheduler;
    private final ObjectInputStream ois;
    private final ObjectOutputStream oos;
    private long lastReceivedTime;
    private boolean isCallToClose;


    public ClientHandler(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        lastReceivedTime = System.currentTimeMillis();
        scheduler = Executors.newScheduledThreadPool(1);
        ois = new ObjectInputStream(clientSocket.getInputStream());
        oos = new ObjectOutputStream(clientSocket.getOutputStream());
        thisClient = new SocketClient(oos);
        isCallToClose = false;
    }


    @Override
    public void run() {
        try {
            GameController.getInstance().getClientManager().addClient(thisClient);
            thisClient.informActionResult(NotifyType.CONNECTED);
            scheduler.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    long currentTime = System.currentTimeMillis();
                    long timeInterval = currentTime - lastReceivedTime;
                    if (timeInterval > 10 * 1000) {
                        //scheduler.close();
                        scheduler.shutdownNow();
                        close();
                    }
                }
            }, 5, 5, TimeUnit.SECONDS);


            while (!this.isInterrupted()){
                try {
                    Message message = (Message) ois.readObject();
                    lastReceivedTime = System.currentTimeMillis();
                    if (message.getType() != MessageType.HEARTBEAT)
                        messageHandler(message);
                }catch (SocketException|EOFException e) {
                    System.out.println("Client disconnected");
                    close();
                }
            }

        }catch (SocketException e){
            close();
        }catch (IOException|ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void messageHandler(Message message){
        switch (message.getType()){
            case ACCESS:
                AccessMessage accessMessage = (AccessMessage) message;
                GameController.getInstance().access(thisClient, accessMessage.getNickname(),accessMessage.getPwd(),accessMessage.isRegistered());
                break;
            case REQ_LOBBIES:
                GameController.getInstance().giveLobbies(thisClient);
                break;
            case JOIN_LOBBY:
                GameController.getInstance().joinLobby(thisClient, ((JoinLobbyeMessage)message).getIdLobby());
                break;
            case CREATE_LOBBY:
                GameController.getInstance().createLobby(thisClient, ((CreateLobbyMessage) message).getNumOfPlayer());
                break;
            case PLAY_INITIAL_CARD:
                PlayInitCardMessage playInitCardMessage = (PlayInitCardMessage) message;
                GameController.getInstance().playInitCard(thisClient, playInitCardMessage.getIdCard(), playInitCardMessage.isBackSide());
                break;
            case PERSONAL_GOAL_CHOOSE:
                GameController.getInstance().setPersonalGoal(thisClient, ((PersonalGoalChooseMessage)message).getIdCard());
                break;
            case PLAY_CARD:
                PlayCardMessage playCardMessage = (PlayCardMessage) message;
                GameController.getInstance().playCard(thisClient, playCardMessage.getIdCard(), playCardMessage.getPosition(),
                        playCardMessage.isBackSide());
                break;
            case DRAW_CARD:
                DrawCardMessage drawCardMessage = (DrawCardMessage) message;
                LocationType location = drawCardMessage.getLocation();
                GameController.getInstance().drawCard(thisClient, drawCardMessage.getLocation(), drawCardMessage.getIdCard());
            default:
                break;
        }


    }

    private void close(){
        if (!isCallToClose){
            isCallToClose = true;
            GameController.getInstance().socketClientLeave(thisClient);
            try {
                ois.close();
                oos.close();
                clientSocket.close();
            } catch (IOException ignored) {}
            this.interrupt();
        }

    }



}





