package it.polimi.ingsw.view.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

public class For2PlayersLobby {

    @FXML
    private ImageView imgFirstPlayer;
    @FXML
    private ImageView imgSecondPlayer;

    @FXML
    private Label nickFirstPlayer;
    @FXML
    private Label nickSecondPlayer;

    private List<String> players;

    Image playerIcon = new Image(getClass().getResourceAsStream("/img/utils/playersIcon.png"));

    /**
     * update lobby status
     * @param newStatus
     */
    public void updateStatus(List<String> newStatus){
        players = newStatus;
        if (players.size() == 1){
            updatePlayer1();
        }else  if (players.size() == 2){
            updatePlayer1();
            updatePlayer2();
        }
    }

    private void updatePlayer1(){
        imgFirstPlayer.setImage(playerIcon);
        nickFirstPlayer.setText(players.get(0));
    }

    private void updatePlayer2(){
        imgSecondPlayer.setImage(playerIcon);
        nickSecondPlayer.setText(players.get(1));
    }


}
