package it.polimi.ingsw.view.GUI.controllers;

import it.polimi.ingsw.network.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;

public class ForConnection {

    private String ip_address = "";

    @FXML
    private ToggleGroup connectionType;  //选择框从scene builder来的

    @FXML
    private Label error;

    @FXML
    private RadioButton rmi;

    @FXML
    private RadioButton socket;

    @FXML
    private Button submit;

    @FXML
    private TextField ip;

    @FXML
    void getRMI(ActionEvent event) {
        if(rmi.isSelected() && ip_address.isEmpty()){
            ip.requestFocus();
        }
    }

    @FXML
    void getSocket(ActionEvent event) {
        if(socket.isSelected() && ip_address.isEmpty()){
            ip.requestFocus();
        }
    }

    @FXML
    void getIP(ActionEvent event) {
        if(Client.checkIPValidity(ip.getText())){
            error.setText("");
            submit.requestFocus();
        }else{
            error.setText("Invalid ip_address!");
            ip.setText("");
        }
    }

    @FXML
    void trySubmit(ActionEvent event) throws IOException {
        ip_address = ip.getText();
        if ((rmi.isSelected() || socket.isSelected()) && Client.checkIPValidity(ip.getText())){

            if (socket.isSelected()) {
                Client.trySocketConnection(ip_address);
            } else if (rmi.isSelected())
                Client.tryRMIConnection(ip_address);
        } else{
            error.setText("Invalid IP or connection type unselected!");
        }
    }

}