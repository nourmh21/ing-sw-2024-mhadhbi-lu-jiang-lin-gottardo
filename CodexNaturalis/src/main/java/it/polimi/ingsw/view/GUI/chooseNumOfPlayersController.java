package it.polimi.ingsw.view.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class chooseNumOfPlayersController {

    @FXML
    private Button create;

    @FXML
    private Label errorNLabel;

    @FXML
    private RadioButton four;

    @FXML
    private ToggleGroup nOfPlayers;

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

    @FXML
    void switchToLobby(ActionEvent event) {
        if(two.isSelected()){
            errorNLabel.setText("");
            //send message to server and switch to respective lobby
        }
        else if(three.isSelected()){
            errorNLabel.setText("");
            //send message to server and switch to respective lobby
        }
        else if(four.isSelected()){
            errorNLabel.setText("");
            //send message to server and switch to respective lobby
        }
        else{
            errorNLabel.setText("Select the number of players!");
        }
    }

}
