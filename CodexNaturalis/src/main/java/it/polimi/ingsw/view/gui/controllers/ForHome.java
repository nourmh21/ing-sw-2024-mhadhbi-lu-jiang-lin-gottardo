package it.polimi.ingsw.view.gui.controllers;

import it.polimi.ingsw.controller.client.ClientController;
import it.polimi.ingsw.view.gui.GUIApplication;
import it.polimi.ingsw.view.gui.enums.SceneType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;

import java.io.IOException;

public class ForHome {

    @FXML
    private Button play;

    /**
     * try to play a match
     * @param event
     * @throws IOException
     */
    @FXML
    void playGame(ActionEvent event){
        ((GUIApplication) (ClientController.getInstance().getView())).showUsefulStage(SceneType.JOIN_MODE,null,null,null);
        play.setOnMouseClicked(event1 -> {});
        play.setCursor(Cursor.DEFAULT);
    }



}