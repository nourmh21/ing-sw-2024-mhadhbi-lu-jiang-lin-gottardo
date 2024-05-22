package it.polimi.ingsw.view.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class ChooseNumOfPlayersController {

    private Stage stage;
    private Scene scene;
    private AnchorPane root;

    @FXML
    private Button create;

    @FXML
    private Label errorNLabel;

    @FXML
    private RadioButton four;

    @FXML
    private ToggleGroup nOfPlayers;

    @FXML
    private RadioButton three;

    @FXML
    private RadioButton two;

    @FXML
    void getFour(ActionEvent event) {
        create.requestFocus();
    }

    @FXML
    void getThree(ActionEvent event) {
        create.requestFocus();
    }

    @FXML
    void getTwo(ActionEvent event) {
        create.requestFocus();
    }

    @FXML
    void switchToLobby(ActionEvent event) throws IOException {
        if(two.isSelected()){
            errorNLabel.setText("");
            root = FXMLLoader.load(getClass().getResource("/6.0WaitingRoom2Players.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();

            Image background = new Image(getClass().getResourceAsStream("/img/utils/background.png"));
            BackgroundImage backgroundImage = new BackgroundImage(
                    background, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT
            );

            root.setBackground(new Background(backgroundImage));
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            //send message to server and switch to respective lobby
        }
        else if(three.isSelected()){
            errorNLabel.setText("");
            root = FXMLLoader.load(getClass().getResource("/6.1WaitingRoom3Players.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();

            Image background = new Image(getClass().getResourceAsStream("/img/utils/background.png"));
            BackgroundImage backgroundImage = new BackgroundImage(
                    background, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT
            );

            root.setBackground(new Background(backgroundImage));
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            //send message to server and switch to respective lobby
        }
        else if(four.isSelected()){
            errorNLabel.setText("");
            root = FXMLLoader.load(getClass().getResource("/6.2WaitingRoom4Players.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();

            Image background = new Image(getClass().getResourceAsStream("/img/utils/background.png"));
            BackgroundImage backgroundImage = new BackgroundImage(
                    background, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT
            );

            root.setBackground(new Background(backgroundImage));
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            //send message to server and switch to respective lobby
        }
        else{
            errorNLabel.setText("Select the number of players!");
        }
    }

}
