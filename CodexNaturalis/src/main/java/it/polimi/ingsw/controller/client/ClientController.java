package it.polimi.ingsw.controller.client;

import it.polimi.ingsw.view.UserInterface;

/**
 * The ClientController class is used on client side to remember:
 * the client's {@link UserInterface} choice and {@link ClientAction}, and the current connection status
 */
public class ClientController {
    private static ClientController instance;
    private UserInterface view;
    private ClientAction clientAction;
    private boolean isConnected;


    public ClientController(){
        view = null;
        clientAction = null;
        isConnected = false;
    }


    public static ClientController getInstance(){
        if (instance == null)
            instance = new ClientController();
        return instance;
    }


    public void setView(UserInterface view) {
        this.view = view;
    }

    public UserInterface getView() {
        return view;
    }

    public void setClientAction(ClientAction clientAction) {
        this.clientAction = clientAction;
    }

    public ClientAction getClientAction() {
        return clientAction;
    }

    public void setConnected(){
        isConnected = true;
    }

    public void setDisconnected(){
        isConnected = false;
    }

    public boolean isConnected() {
        return isConnected;
    }
}
