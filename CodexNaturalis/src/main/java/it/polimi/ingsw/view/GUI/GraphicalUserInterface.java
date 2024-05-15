package it.polimi.ingsw.view.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class GraphicalUserInterface extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        AnchorPane root = FXMLLoader.load(getClass().getResource("/1Connection.fxml"));


        Scene scene = new Scene(root);



        Image icon = new Image(getClass().getResourceAsStream("/img/utils/icon.png"));
        Image background = new Image(getClass().getResourceAsStream("/img/utils/background.png"));

        BackgroundImage backgroundImage = new BackgroundImage(
                background, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT
        );


        root.setBackground(new Background(backgroundImage));
        stage.getIcons().add(icon);
        stage.setTitle("Codex Naturalis");
        stage.setScene(scene);
        stage.show();


    }
}
