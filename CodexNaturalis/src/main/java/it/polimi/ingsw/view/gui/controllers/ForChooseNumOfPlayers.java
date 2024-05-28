package it.polimi.ingsw.view.gui.controllers;

import it.polimi.ingsw.controller.client.ClientController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;


import java.io.IOException;

public class ForChooseNumOfPlayers {


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
            ClientController.getInstance().getClientAction().newGame(2);

        } else if(three.isSelected()){
            error.setText("");
            ClientController.getInstance().getClientAction().newGame(3);

        } else if(four.isSelected()){
            error.setText("");
            ClientController.getInstance().getClientAction().newGame(4);

        } else{
            error.setText("Select the number of players!");
        }
    }

}
