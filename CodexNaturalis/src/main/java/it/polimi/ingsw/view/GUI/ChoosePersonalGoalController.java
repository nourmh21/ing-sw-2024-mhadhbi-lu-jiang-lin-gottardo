package it.polimi.ingsw.view.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

public class ChoosePersonalGoalController {

    Integer id;

    @FXML
    private Button SELECT;

    @FXML
    private Button personalGoal1;

    @FXML
    private Button personalGoal2;

    @FXML
    private ImageView personal1;

    @FXML
    private ImageView personal2;

    @FXML
    private Label errorPersonalGoalLabel;

    private boolean personalGoal1Selected = false;
    private boolean personalGoal2Selected = false;

    public void showPersonalGoal(List<Integer> personalGoal){
        Image im1 = new Image(getClass().getResourceAsStream("/img/cards/front/" + personalGoal.get(0) + ".png"));
        Image im2 = new Image(getClass().getResourceAsStream("/img/cards/front/" + personalGoal.get(1) + ".png" ));

        personal1.setImage(im1);
        personal2.setImage(im2);
    }

    @FXML
    public void initialize(){

        personalGoal1.setOnAction(event -> {
            personalGoal1Selected = true;
            personalGoal2Selected = false;
        });

        personalGoal2.setOnAction(event -> {
            personalGoal1Selected = false;
            personalGoal2Selected = true;
        });
    }

    public Integer getId(List<Integer> personalGoal){
        if(personalGoal1Selected){
            id = personalGoal.get(0);
        }
        else if(personalGoal2Selected){
            id = personalGoal.get(1);
        }
        return id;
    }

    @FXML
    void switchToPlayerBoard(ActionEvent event) {
        if(personalGoal1Selected || personalGoal2Selected){
            //switch to player board
        }
        else{
            errorPersonalGoalLabel.setText("Select a personal goal card!");
        }
    }

}
