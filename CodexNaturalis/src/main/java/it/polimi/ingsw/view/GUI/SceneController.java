package it.polimi.ingsw.view.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class SceneController {

    @FXML
    private ToggleGroup ConnectionType;

    @FXML
    private Label correctIPLabel;

    @FXML
    private Label errorIPLabel;

    @FXML
    private RadioButton rmi;

    @FXML
    private RadioButton socket;

    @FXML
    private Button submit;

    @FXML
    private TextField tFIP;

    @FXML
    void getConnectionType(ActionEvent event) {

    }

    @FXML
    void switchToLogin(ActionEvent event) {

    }

    @FXML
    void tfIP(ActionEvent event) {

    }

}
