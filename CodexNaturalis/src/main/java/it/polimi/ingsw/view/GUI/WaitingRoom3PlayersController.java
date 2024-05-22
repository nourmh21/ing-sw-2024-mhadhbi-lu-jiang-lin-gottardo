package it.polimi.ingsw.view.GUI;

import it.polimi.ingsw.model.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

public class WaitingRoom3PlayersController {

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

    public void setImages(List<Player> playersInGame){
        Image[] playersImages = new Image[playersInGame.size()];
        for(int i = 0; i < playersInGame.size(); i++){
            playersImages[i] = new Image(getClass().getResourceAsStream("/img/utils/" + playersInGame.get(i).getPlayerColor() + ".png"));
        }
        imgFirstPlayer.setImage(playersImages[0]);
        imgSecondPlayer.setImage(playersImages[1]);
        imgThirdPlayer.setImage(playersImages[2]);
    }

    public void setNicknames(List<Player> playersInGame){
        nickFirstPlayer.setText(playersInGame.get(0).getNickname());
        nickSecondPlayer.setText(playersInGame.get(1).getNickname());
        nickThirdPlayer.setText(playersInGame.get(2).getNickname());
    }
}
