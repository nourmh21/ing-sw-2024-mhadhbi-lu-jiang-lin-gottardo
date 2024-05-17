package it.polimi.ingsw.view.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.awt.*;
import java.io.IOException;

public class GameApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        AnchorPane fxmlLoader = FXMLLoader.load(getClass().getResource("/GameScene.fxml"));
        Scene scene = new Scene(fxmlLoader);


        Image icon = new Image(getClass().getResourceAsStream("/img/utils/icon.png"));
        Image background = new Image(getClass().getResourceAsStream("/img/utils/background.png"));
        BackgroundImage backgroundImage = new BackgroundImage(
                background, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

        fxmlLoader.setBackground(new Background(backgroundImage));
        stage.setTitle("CODEX NATURALIS");
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }



    public static void main(String[] args) {
        launch();
    }
}