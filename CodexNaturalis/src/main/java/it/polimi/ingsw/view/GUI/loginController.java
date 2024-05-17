package it.polimi.ingsw.view.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class loginController {
    String lNickname;
    String lPwd;
    private Stage stage;
    private Scene scene;
    private AnchorPane root;

    @FXML
    private Text errorLText;

    @FXML
    private Button login;

    @FXML
    private PasswordField pFLPwd;

    @FXML
    private TextField tFLNickname;

    @FXML
    void pfLPassword(ActionEvent event) {
        lPwd = pFLPwd.getText();
        if(lPwd.matches(".{6,}") && lPwd.matches("[a-zA-Z0-9]+")){
            errorLText.setText("");
            login.requestFocus();
        }
        else{
            errorLText.setText("Sorry, your password must be at least 6 characters long. " +
                    "Special characters and sweeps are not allowed!");
            pFLPwd.setText("");
        }
    }

    @FXML
    void switchToRegister(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/3Registration.fxml"));
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

    @FXML
    void switchToStartFromLogin(ActionEvent event) throws IOException {
        lNickname = tFLNickname.getText();
        lPwd = pFLPwd.getText();
        if(lNickname.matches(".*\\D.*") && lNickname.matches("[a-zA-Z0-9]+")
                && lPwd.matches(".{6,}") && lPwd.matches("[a-zA-Z0-9]+")){
            //send credentials to server
            //the server validates credentials
            if(true){
                root = FXMLLoader.load(getClass().getResource("/4Start.fxml"));
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
            else{
                errorLText.setText("Invalid nickname or password!");
                tFLNickname.setText("");
                pFLPwd.setText("");
            }
        }
        else{
            errorLText.setText("At least one character for nickname! " +
                    "At least 6 characters long for Password! " +
                    "Special characters and sweeps are not allowed!");
            tFLNickname.requestFocus();
        }
    }

    @FXML
    void tfLNickname(ActionEvent event) {
        lNickname = tFLNickname.getText();
        if(lNickname.matches(".*\\D.*") && lNickname.matches("[a-zA-Z0-9]+")){
            errorLText.setText("");
            pFLPwd.requestFocus();
        }
        else{
            errorLText.setText("Sorry, your nickname must contain at least one character. " +
                    "Special characters and sweeps are not allowed!");
            tFLNickname.setText("");
        }
    }

}
