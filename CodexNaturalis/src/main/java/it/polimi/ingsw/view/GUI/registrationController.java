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

public class registrationController {

    String rNickname;
    String rPwd;
    String rCPwd;
    private Stage stage;
    private Scene scene;
    private AnchorPane root;

    @FXML
    private Button backToL;

    @FXML
    private Text errorRText;

    @FXML
    private PasswordField pFRCPwd;

    @FXML
    private PasswordField pFRPwd;

    @FXML
    private Button register;

    @FXML
    private TextField tFRNickname;

    @FXML
    void pfRConfirmPassword(ActionEvent event) {
        rCPwd = pFRCPwd.getText();
        if(rCPwd.equals(rPwd)){
            errorRText.setText("");
            register.requestFocus();
        } else {
            errorRText.setText("Incompatible password!");
            pFRCPwd.setText("");
        }
    }

    @FXML
    void pfRPassword(ActionEvent event) {
        rPwd = pFRPwd.getText();
        if(rPwd.matches(".{6,}") && rPwd.matches("[a-zA-Z0-9]+")){
            errorRText.setText("");
            pFRCPwd.requestFocus();
        }
        else{
            errorRText.setText("Sorry, your password must be at least 6 characters long. " +
                    "Special characters and sweeps are not allowed!");
            pFRPwd.setText("");
        }
    }

    @FXML
    void switchToLFromR(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/2Login.fxml"));
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
    void switchToStartFromRegister(ActionEvent event) throws IOException {
        rNickname = tFRNickname.getText();
        rPwd = pFRPwd.getText();
        rCPwd = pFRCPwd.getText();
        if(rNickname.matches(".*\\D.*") && rNickname.matches("[a-zA-Z0-9]+")
            && rPwd.matches(".{6,}") && rPwd.matches("[a-zA-Z0-9]+")
                && !rCPwd.isEmpty() && rCPwd.equals(rPwd)){
            //send credentials(password) to server
            //the server registers credentials
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
            } else {
                errorRText.setText("Registration failure!");
            }
        }
        else{
            errorRText.setText("Invalid nickname or password!");
            tFRNickname.requestFocus();
        }
    }

    @FXML
    void tfRNickname(ActionEvent event) {
        rNickname = tFRNickname.getText();
        if(rNickname.matches(".*\\D.*") && rNickname.matches("[a-zA-Z0-9]+")){
            //send username to server
            //the server checks if it already exists
            if(true){
                errorRText.setText("");
                pFRPwd.requestFocus();
            }
            else{
                errorRText.setText("Nickname already exists! Please choose another nickname.");
                tFRNickname.setText("");
            }
        }
        else{
            errorRText.setText("Sorry, your nickname must contain at least one character. " +
                    "Special characters and sweeps are not allowed!");
            tFRNickname.setText("");
        }
    }

}

