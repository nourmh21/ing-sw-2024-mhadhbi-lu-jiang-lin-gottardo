package it.polimi.ingsw.view.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

public class For3PlayersLobby {

    @FXML
    private ImageView imgFirstPlayer;
    @FXML
    private ImageView imgSecondPlayer;
    @FXML
    private ImageView imgThirdPlayer;

    @FXML
    private Label nickFirstPlayer;
    @FXML
    private Label nickSecondPlayer;
    @FXML
    private Label nickThirdPlayer;

    private List<String> players;
    Image playerIcon = new Image(getClass().getResourceAsStream("/img/playerIcon/beige.png"));

    /**
     * update lobby status
     * @param newStatus
     */
    public void updateStatus(List<String> newStatus){
        if (players == null){
            players = newStatus;
            update();
        } else{
            List<String> oldStatus = this.players;
            if (newStatus.size() < oldStatus.size()) {
                if (oldStatus.size() == 2) {
                    removeALl();
                }
            }
            players = newStatus;
            update();
        }

    }

    private void update(){
        if (players.size() == 1){
            updatePlayer1();
        }else  if (players.size() == 2){
            updatePlayer1();
            updatePlayer2();
        }else  if (players.size() == 3){
            updatePlayer1();
            updatePlayer2();
            updatePlayer3();
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

    private void updatePlayer3(){
        imgThirdPlayer.setImage(playerIcon);
        nickThirdPlayer.setText(players.get(2));
    }

    private void removeALl(){
        imgFirstPlayer.setImage(null);
        nickFirstPlayer.setText("");
        imgSecondPlayer.setImage(null);
        nickSecondPlayer.setText("");
    }


}
