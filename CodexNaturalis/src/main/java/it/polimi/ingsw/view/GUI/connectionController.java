package it.polimi.ingsw.view.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class connectionController {

    String address;
    private Stage stage;
    private Scene scene;
    private AnchorPane root;

    @FXML
    private ToggleGroup ConnectionType;

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
    void getRMI(ActionEvent event) {
        if(rmi.isSelected()){
            //send message to server: connection with RMI
            //if successful
            tFIP.requestFocus();
        }
    }

    @FXML
    void getSOCKET(ActionEvent event) {
        if(socket.isSelected()){
            //send message to server: connection with SOCKET
            //if successful
            tFIP.requestFocus();
        }
    }

    @FXML
    void switchToLogin(ActionEvent event) throws IOException {
        address = tFIP.getText();
        if(!address.isEmpty() && !address.matches(".*[a-zA-Z].*") && (rmi.isSelected() || socket.isSelected())){
            root = FXMLLoader.load(getClass().getResource("/2Login.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();

            Image background = new Image(getClass().getResourceAsStream("/img/utils/background.png"));
            BackgroundImage backgroundImage = new BackgroundImage(
                    background, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT
            );

            root.setBackground(new Background(backgroundImage));
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else{
            errorIPLabel.setText("ERROR: Invalid IP or connection type not selected!");
        }
    }

    @FXML
    void tfIP(ActionEvent event) {
        address = tFIP.getText();
        //send address to server
        if(!address.isEmpty() && !address.matches(".*[a-zA-Z].*")){
            errorIPLabel.setText("");
            submit.requestFocus();
        }
        else{
            errorIPLabel.setText("ERROR: Invalid address!");
            tFIP.setText("");
        }
    }

    private void handleKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            Node source = (Node) event.getSource();
            source.fireEvent(new ActionEvent());
        }
    }

}
