package it.polimi.ingsw.view.gui.controllers;

import it.polimi.ingsw.controller.client.ClientController;
import it.polimi.ingsw.view.gui.GUIApplication;
import it.polimi.ingsw.view.gui.enums.PageType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class ForLobbyCreation {


    @FXML
    private Button create;

    @FXML
    private Label error;

    @FXML
    private RadioButton four;

    @FXML
    private ToggleGroup numOfPlayers;

    @FXML
    private RadioButton three;

    @FXML
    private RadioButton two;

    @FXML
    void getFour(ActionEvent event) {
        create.requestFocus();
    }

    @FXML
    void getThree(ActionEvent event) {
        create.requestFocus();
    }

    @FXML
    void getTwo(ActionEvent event) {
        create.requestFocus();
    }

    /**
     * try to inform server about players' number for new game
     * @param event
     * @throws IOException
     */
    @FXML
    void submit(ActionEvent event) throws IOException {
        if(two.isSelected()){
            error.setText("");
            ClientController.getInstance().getClientAction().createLobby(2);

        } else if(three.isSelected()){
            error.setText("");
            ClientController.getInstance().getClientAction().createLobby(3);

        } else if(four.isSelected()){
            error.setText("");
            ClientController.getInstance().getClientAction().createLobby(4);

        } else{
            error.setText("Select the number of players!");
        }
    }

    @FXML
    void switchToHome(MouseEvent event) {
        ((GUIApplication) (ClientController.getInstance().getView())).switchPage(PageType.HOME);
    }

    @FXML
    void switchToLobbyChoose(MouseEvent event) {
        ((GUIApplication) (ClientController.getInstance().getView())).switchPage(PageType.LOBBY_CHOOSE);
        ClientController.getInstance().getClientAction().reqLobbies();
    }


}
