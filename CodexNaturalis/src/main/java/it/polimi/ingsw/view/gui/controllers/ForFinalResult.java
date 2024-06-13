package it.polimi.ingsw.view.gui.controllers;

import it.polimi.ingsw.controller.client.ClientController;
import it.polimi.ingsw.model.immutable.ImmutableEndGameInfo;
import it.polimi.ingsw.model.immutable.ImmutablePlayer;
import it.polimi.ingsw.view.gui.GUIApplication;
import it.polimi.ingsw.view.gui.enums.PageType;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;

public class ForFinalResult {

    @FXML
    private Label title;

    @FXML
    private Label goalScore1;

    @FXML
    private Label goalScore2;

    @FXML
    private Label goalScore3;

    @FXML
    private Label goalScore4;

    @FXML
    private Label player1;

    @FXML
    private Label player2;

    @FXML
    private Label player3;

    @FXML
    private Label player4;

    @FXML
    private Label totScore1;

    @FXML
    private Label totScore2;

    @FXML
    private Label totScore3;

    @FXML
    private Label totScore4;

    @FXML
    private Pane winner1;

    @FXML
    private Pane winner2;

    @FXML
    private Pane winner3;

    @FXML
    private Pane winner4;


    @FXML
    void initialize(){
        Font algerian48 = Font.loadFont(getClass().getResourceAsStream("/font/Alger.ttf"),48);
        title.setFont(algerian48);
    }

    /**
     * show final results of each player based on the scores in ascending order
     * @param finalResult
     */
    public void fillFinalResult(ImmutableEndGameInfo finalResult){
        GUIApplication gui = ((GUIApplication) ClientController.getInstance().getView());
        List<String> players = gui.getPlayers().stream()
                .map(ImmutablePlayer::getNickname)
                .toList();
        HashMap<String,int[]> results = finalResult.getFinalResult();
        List<String> winners = finalResult.getWinners();

        if (players.size() > 1){
            player1.setText(players.get(0));
            goalScore1.setText(String.valueOf((results.get(players.get(0)))[1]));
            totScore1.setText(String.valueOf((results.get(players.get(0)))[0]));
            player2.setText(players.get(1));
            goalScore2.setText(String.valueOf((results.get(players.get(1)))[1]));
            totScore2.setText(String.valueOf((results.get(players.get(1)))[0]));
            if (players.size() > 2){
                player3.setText(players.get(2));
                goalScore3.setText(String.valueOf((results.get(players.get(2)))[1]));
                totScore3.setText(String.valueOf((results.get(players.get(2)))[0]));
                if(players.size() > 3){
                    player4.setText(players.get(3));
                    goalScore4.setText(String.valueOf((results.get(players.get(3)))[1]));
                    totScore4.setText(String.valueOf((results.get(players.get(3)))[0]));
                }else {
                    player4.setText("");
                }
            }else {
                player3.setText("");
                player4.setText("");
            }
        }

        if (winners.contains(player1.getText()))
            setWinnerStyle(winner1, player1, goalScore1, totScore1);
        if (winners.contains(player2.getText()))
            setWinnerStyle(winner2, player2, goalScore2, totScore2);
        if (winners.contains(player3.getText()))
            setWinnerStyle(winner3, player3, goalScore3, totScore3);
        if (winners.contains(player4.getText()))
            setWinnerStyle(winner4, player4, goalScore4, totScore4);

    }

    /**
     * switch to home page
     * @param event
     */
    @FXML
    void goToHomePage(MouseEvent event) {
        GUIApplication gui = ((GUIApplication)ClientController.getInstance().getView());
        gui.switchPage(PageType.HOME);
        gui.removeAllLastGameInfo();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }


    private void setWinnerStyle(Pane pane, Label text1, Label text2, Label text3){
        pane.setStyle("-fx-background-color: #e6dfd7");
        text1.setStyle("-fx-text-fill: #5b5958");
        text2.setStyle("-fx-text-fill: #5b5958");
        text3.setStyle("-fx-text-fill: #5b5958");
    }

}

