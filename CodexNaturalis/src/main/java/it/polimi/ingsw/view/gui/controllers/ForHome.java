package it.polimi.ingsw.view.gui.controllers;

import it.polimi.ingsw.controller.client.ClientController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;

public class ForHome {

    @FXML
    private Button play;

    @FXML
    void playGame(ActionEvent event) throws IOException {
        ClientController.getInstance().getClientAction().joinGame();
    }

}