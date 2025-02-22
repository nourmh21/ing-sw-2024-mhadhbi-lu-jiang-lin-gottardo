package it.polimi.ingsw.view.gui.controllers;

import it.polimi.ingsw.controller.client.ClientController;
import it.polimi.ingsw.view.gui.GUIApplication;
import it.polimi.ingsw.view.gui.enums.PageType;
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
     */
    @FXML
    void playGame(ActionEvent event){
        ((GUIApplication) (ClientController.getInstance().getView())).showAskStage(PageType.JOIN_MODE,null,null,null);
        play.setOnAction(event1 -> {});
        play.setCursor(Cursor.DEFAULT);
    }



}