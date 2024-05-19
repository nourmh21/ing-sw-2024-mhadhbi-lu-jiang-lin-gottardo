package it.polimi.ingsw.view.GUI;

import it.polimi.ingsw.model.Player;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.util.List;

public class waitingRoom1PlayerController {

    @FXML
    private ImageView imgFirstPlayer;

    @FXML
    private Text nickFirstPlayer;

    public void setImages(List<Player> playersInGame){
        imgFirstPlayer.setImage(new Image(getClass().getResourceAsStream("/img/utils/" + playersInGame.get(0).getPlayerColor() + ".png")));
    }

    public void setNicknames(List<Player> playersInGame){
        nickFirstPlayer.setText(playersInGame.get(0).getNickname());
    }
}
