package it.polimi.ingsw.view.gui.controllers;

import it.polimi.ingsw.controller.client.ClientController;
import it.polimi.ingsw.view.gui.GUIApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ForPersonalGoal {


    @FXML
    private Button personalGoal1;

    @FXML
    private Button personalGoal2;

    @FXML
    private ImageView personal1;

    @FXML
    private ImageView personal2;

    private Integer[] goals;

    public void showPersonalGoal(Integer[] goals){
        this.goals = goals;
        Image im1 = new Image(getClass().getResourceAsStream("/img/cards/front/" + goals[0] + ".png"));
        Image im2 = new Image(getClass().getResourceAsStream("/img/cards/front/" + goals[1] + ".png" ));

        personal1.setImage(im1);
        personal2.setImage(im2);
    }

    /**
     * try to choose the first personal goal
     * @param event on mouse click
     */
    @FXML
    void tryGoal1(MouseEvent event) {
        ((GUIApplication)ClientController.getInstance().getView()).setPersonalGoal(goals[0]);
        ClientController.getInstance().getClientAction().choosePersonalGoal(goals[0]);
    }

    /**
     * try to choose the second personal goal
     * @param event on mouse click
     */
    @FXML
    void tryGoal2(MouseEvent event) {
        ((GUIApplication)ClientController.getInstance().getView()).setPersonalGoal(goals[1]);
        ClientController.getInstance().getClientAction().choosePersonalGoal(goals[1]);
    }

}
