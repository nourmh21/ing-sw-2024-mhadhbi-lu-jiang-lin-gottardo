package it.polimi.ingsw.view.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

public class For4PlayersLobby {

    @FXML
    private ImageView imgFirstPlayer;
    @FXML
    private ImageView imgSecondPlayer;
    @FXML
    private ImageView imgThirdPlayer;
    @FXML
    private ImageView imgFourthPlayer;

    @FXML
    private Label nickFirstPlayer;
    @FXML
    private Label nickSecondPlayer;
    @FXML
    private Label nickThirdPlayer;
    @FXML
    private Label nickFourthPlayer;

    private List<String> players;
    Image playerIcon = new Image(getClass().getResourceAsStream("/img/utils/playersIcon.png"));

    public void updateStatus(List<String> newStatus){
        if(players == null){
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

    private void updatePlayer4(){
        imgFourthPlayer.setImage(playerIcon);
        nickFourthPlayer.setText(players.get(3));
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
        }else if (players.size() == 4){
            updatePlayer1();
            updatePlayer2();
            updatePlayer3();
            updatePlayer4();
        }
    }

    private void removeALl(){
        imgFirstPlayer.setImage(null);
        nickFirstPlayer.setText("");
        imgSecondPlayer.setImage(null);
        nickSecondPlayer.setText("");
        imgThirdPlayer.setImage(null);
        nickThirdPlayer.setText("");
    }

}
