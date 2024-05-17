package it.polimi.ingsw.view.GUI;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.awt.*;

public class PlayerBoardController {

    @FXML
    private ImageView handCard1;

    @FXML
    private ImageView handCard2;

    @FXML
    private ImageView handCard3;

    @FXML
    private HBox handCards;

    @FXML
    private ImageView personal_goal;



    public void showPersonalGoal(Integer id){
        Image im = new Image(getClass().getResourceAsStream("/img/cards/front/" + id + ".png"));
        personal_goal.setImage(im);
    }


    /**
     * it shows the possible position of the hand card clicked
     */
    public void showPossiblePosition() {


    }


    /**
     * get a card choose from desk
     */
    public void chooseCardFromDesk(){

    }

}
