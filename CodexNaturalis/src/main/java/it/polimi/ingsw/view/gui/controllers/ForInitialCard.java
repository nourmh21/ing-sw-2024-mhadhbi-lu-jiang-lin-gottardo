package it.polimi.ingsw.view.gui.controllers;

import it.polimi.ingsw.controller.client.ClientController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ForInitialCard {

    Integer idCard;

    @FXML
    private ImageView initialBack;


    @FXML
    private ImageView initialFront;


    @FXML
    private Button back;


    @FXML
    private Button front;


    public void showInitialCards(Integer initialCard){
        idCard = initialCard;
        Image im1 = new Image(getClass().getResourceAsStream("/img/cards/front/" + idCard + ".png"));
        Image im2 = new Image(getClass().getResourceAsStream("/img/cards/back/" + idCard + ".png" ));
        initialFront.setImage(im1);
        initialBack.setImage(im2);
    }


    /**
     * try to choose back side of initial card
     * @param event on mouse click
     */
    @FXML
    void SelectBackSide(MouseEvent event) {
        ClientController.getInstance().getClientAction().playInitCard(idCard,true);
    }


    /**
     * try to choose front side of initial card
     * @param event on mouse click
     */
    @FXML
    void SelectFrontSide(MouseEvent event) {
        ClientController.getInstance().getClientAction().playInitCard(idCard,false);
    }

}
