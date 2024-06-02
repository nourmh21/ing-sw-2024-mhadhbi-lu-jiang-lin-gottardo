package it.polimi.ingsw.view.gui.controllers;

import it.polimi.ingsw.controller.client.ClientController;
import it.polimi.ingsw.view.gui.GUIApplication;
import it.polimi.ingsw.view.gui.enums.SceneType;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ForJoinMode {

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    void askLobbies(MouseEvent event) {
        ClientController.getInstance().getClientAction().reqLobbies();
        deactivateButtonClick();
    }

    @FXML
    void switchToLobbyCreation(MouseEvent event) {
        ((GUIApplication) (ClientController.getInstance().getView())).switchPage(SceneType.LOBBY_CREATION);
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }

    private void deactivateButtonClick(){
        button1.setCursor(Cursor.WAIT);
        button2.setCursor(Cursor.WAIT);
        button1.setOnMouseClicked(event -> {});
        button2.setOnMouseClicked(event -> {});
    }

}
