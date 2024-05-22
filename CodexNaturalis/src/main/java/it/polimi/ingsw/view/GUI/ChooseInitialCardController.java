package it.polimi.ingsw.view.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChooseInitialCardController {

    private Stage stage;
    private Scene scene;
    Integer id;

    @FXML
    private ImageView initialBack1;

    @FXML
    private ImageView initialBack2;

    @FXML
    private ImageView initialFront1;

    @FXML
    private ImageView initialFront2;

    @FXML
    private Button select;

    @FXML
    private Button back1;

    @FXML
    private Button back2;

    @FXML
    private Button front1;

    @FXML
    private Button front2;

    @FXML
    private Label errorInitialCardLabel;

    private boolean back1Selected = false;
    private boolean back2Selected = false;
    private boolean front1Selected = false;
    private boolean front2Selected = false;

    public void showInitialCards(List<Integer> initialCards){
        Image im1 = new Image(getClass().getResourceAsStream("/img/cards/front/" + initialCards.get(0) + ".png"));
        Image im2 = new Image(getClass().getResourceAsStream("/img/cards/back/" + initialCards.get(1) + ".png" ));
        Image im3 = new Image(getClass().getResourceAsStream("/img/cards/front/" + initialCards.get(2) + ".png"));
        Image im4 = new Image(getClass().getResourceAsStream("/img/cards/back/" + initialCards.get(3) + ".png" ));

        initialFront1.setImage(im1);
        initialBack1.setImage(im2);
        initialFront2.setImage(im3);
        initialBack2.setImage(im4);
    }

    @FXML
    public void initialize() {

        back1.setOnAction(event -> {
            back1Selected = true;
            back2Selected = false;
            front1Selected = false;
            front2Selected = false;

        });

        back2.setOnAction(event -> {
            back1Selected = false;
            back2Selected = true;
            front1Selected = false;
            front2Selected = false;

        });

        front1.setOnAction(event -> {
            back1Selected = false;
            back2Selected = false;
            front1Selected = true;
            front2Selected = false;
        });

        front2.setOnAction(event -> {
            back1Selected = false;
            back2Selected = false;
            front1Selected = false;
            front2Selected = true;
        });
    }

    public Integer getId(List<Integer> initialCards){
        if(front1Selected){
            id = initialCards.get(0);
        }
        else if(back1Selected){
            id = initialCards.get(1);
        }
        else if(front2Selected){
            id = initialCards.get(2);
        }
        else if(back2Selected){
            id = initialCards.get(3);
        }
        return id;
    }

    @FXML
    void switchToNextChoice(ActionEvent event) throws IOException {
        if (back1Selected || back2Selected || front1Selected || front2Selected) {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/7.1ChoosePersonalGoal.fxml"));
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

        } else {
            errorInitialCardLabel.setText("Choose your initial card!");
        }
    }

}
