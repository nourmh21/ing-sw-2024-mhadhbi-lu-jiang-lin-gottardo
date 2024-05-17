package it.polimi.ingsw.view.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class startController {

    private boolean existsLobby;
    private Stage stage;
    private Scene scene;
    private AnchorPane root;

    @FXML
    private Button play;

    @FXML
    void switchToLobby(ActionEvent event) throws IOException {
        //check free Lobby
        if(false){
            //enter in lobby
        }
        else{
            root = FXMLLoader.load(getClass().getResource("/5ChoosePlayersNumber.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Image background = new Image(getClass().getResourceAsStream("/img/utils/background.png"));
            BackgroundImage backgroundImage = new BackgroundImage(
                    background, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT
            );
            root.setBackground(new Background(backgroundImage));
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

}
