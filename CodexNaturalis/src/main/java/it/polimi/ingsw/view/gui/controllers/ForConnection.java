package it.polimi.ingsw.view.gui.controllers;

import it.polimi.ingsw.main.ClientApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Font;

import java.io.IOException;

public class ForConnection {

    @FXML
    private Label title;

    @FXML
    private ToggleGroup connectionType;

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

    private String ip_address = "";


    @FXML
    void initialize(){
        Font algerian40 = Font.loadFont(getClass().getResourceAsStream("/font/Alger.ttf"),40);
        title.setFont(algerian40);
    }

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
        if(ClientApp.checkIPValidity(ip.getText())){
            error.setText("");
            submit.requestFocus();
        }else{
            error.setText("Invalid ip_address!");
            ip.setText("");
        }
    }

    /**
     * try to connect to server with rmi or socket
     * @param event
     * @throws IOException
     */
    @FXML
    void trySubmit(ActionEvent event) throws IOException {
        ip_address = ip.getText();
        if ((rmi.isSelected() || socket.isSelected()) && ClientApp.checkIPValidity(ip.getText())){

            if (socket.isSelected()) {
                ClientApp.trySocketConnection(ip_address);
            } else if (rmi.isSelected())
                ClientApp.tryRMIConnection(ip_address);
        } else{
            error.setText("Invalid IP or connection type unselected!");
        }
    }

}