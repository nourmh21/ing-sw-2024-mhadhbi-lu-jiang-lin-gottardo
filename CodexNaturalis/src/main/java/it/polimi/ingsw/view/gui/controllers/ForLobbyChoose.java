package it.polimi.ingsw.view.gui.controllers;

import it.polimi.ingsw.controller.client.ClientController;
import it.polimi.ingsw.view.gui.GUIApplication;
import it.polimi.ingsw.view.gui.enums.PageType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ForLobbyChoose {

    @FXML
    private Label title;

    @FXML
    private TextField idLobby;

    @FXML
    private ListView<String> lobbyList;

    @FXML
    private Button submit;

    @FXML
    private Button tryRefresh;

    private List<String> lobbies;

    @FXML
    private Button toCreate;

    @FXML
    private Button toHome;

    @FXML
    void initialize(){
        Font algerian48 = Font.loadFont(getClass().getResourceAsStream("/font/Alger.ttf"),48);
        title.setFont(algerian48);
    }

    @FXML
    void switchToCreateLobby(MouseEvent event) {
        ((GUIApplication) (ClientController.getInstance().getView())).switchPage(PageType.LOBBY_CREATION);
    }

    @FXML
    void switchToHome(MouseEvent event) {
        ((GUIApplication) (ClientController.getInstance().getView())).switchPage(PageType.HOME);
    }

    @FXML
    void tryRefresh(MouseEvent event) {
        ClientController.getInstance().getClientAction().reqLobbies();
    }

    @FXML
    void trySubmit(MouseEvent event) {
        String id = idLobby.getText();
        if (lobbies.contains(id)){
            ClientController.getInstance().getClientAction().joinLobby(Integer.valueOf(id));
            deactivateMouseClick();
        }else {
            idLobby.setStyle("-fx-border-color: red");
            idLobby.requestFocus();
        }

    }

    @FXML
    void tryInsert(ActionEvent event){
        submit.requestFocus();
    }


    @FXML
    void removeRedBorder(KeyEvent event) {
        idLobby.setStyle("-fx-border-color: rgb(128,128,128)");
    }


    public void showLobbies(List<Integer[]> newLobbiesStatus){
        lobbyList.requestFocus();
        lobbies = new ArrayList<>();
        if (newLobbiesStatus.isEmpty()){
            ObservableList<String> list = FXCollections.observableList(Collections.singletonList("No room was created"));
            lobbyList.setItems(list);
        }else {
            lobbies = newLobbiesStatus.stream()
                .parallel()
                .map(integers -> String.valueOf(integers[0]))
                .toList();
            List<String> stringFormat = newLobbiesStatus
                    .stream()
                    .parallel()
                    .map(integers -> (integers[0] + " [" + integers[1] + "]  currently in: " + integers[2]))
                    .toList();
            ObservableList<String> list = FXCollections.observableList(stringFormat);
            lobbyList.setItems(list);
        }
    }

    public void deactivateMouseClick(){
        deactivate(submit);
        deactivate(tryRefresh);
        deactivate(toHome);
        deactivate(toCreate);
    }

    private void deactivate(Button button){
        button.setCursor(Cursor.WAIT);
        button.setOnMouseClicked(event -> {});

    }

    public void reactivateMouseClick(){
        changeCursorInHand(submit);
        changeCursorInHand(tryRefresh);
        changeCursorInHand(toHome);
        changeCursorInHand(toCreate);
        submit.setOnMouseClicked(this::trySubmit);
        tryRefresh.setOnMouseClicked(this::tryRefresh);
        toHome.setOnMouseClicked(this::switchToHome);
        toCreate.setOnMouseClicked(this::switchToCreateLobby);
    }

    private void changeCursorInHand(Button button){
        button.setCursor(Cursor.HAND);
    }

    public void setLobbyListStyle(){
        lobbyList.setStyle("-fx-font-size: 16");
    }



}

