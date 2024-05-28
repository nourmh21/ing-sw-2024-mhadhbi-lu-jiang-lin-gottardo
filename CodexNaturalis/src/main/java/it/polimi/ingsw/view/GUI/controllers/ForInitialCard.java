package it.polimi.ingsw.view.gui.controllers;

import it.polimi.ingsw.controller.client.ClientController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ForInitialCard {

    Integer idCard;

    @FXML
    private ImageView initialBack1;


    @FXML
    private ImageView initialFront1;


    @FXML
    private Button back1;


    @FXML
    private Button front1;

    @FXML
    private Label error;

    public void showInitialCards(Integer initialCard){
        idCard = initialCard;
        Image im1 = new Image(getClass().getResourceAsStream("/img/cards/front/" + idCard + ".png"));
        Image im2 = new Image(getClass().getResourceAsStream("/img/cards/back/" + idCard + ".png" ));
        initialFront1.setImage(im1);
        initialBack1.setImage(im2);
    }

    @FXML
    void SelectBackSide(MouseEvent event) {
        ClientController.getInstance().getClientAction().playInitCard(idCard,true);
    }

    @FXML
    void SelectFrontSide(MouseEvent event) {
        ClientController.getInstance().getClientAction().playInitCard(idCard,false);
    }

}
