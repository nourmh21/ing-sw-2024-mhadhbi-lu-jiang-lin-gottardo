package it.polimi.ingsw.view.GUI.controllers;

import it.polimi.ingsw.controller.client.ClientController;
import it.polimi.ingsw.view.GUI.GUI;
import it.polimi.ingsw.view.GUI.enums.SceneType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class ForLogin {
    String nickname;
    String pwd;

    private AnchorPane root;

    @FXML
    private Text nicknameError;

    @FXML
    private Text pwdError;

    @FXML
    private Button login;

    @FXML
    private PasswordField pwdField;

    @FXML
    private TextField nicknameField;

    private Stage stage;
    private Scene scene;


    @FXML
    void tryPassword(ActionEvent event) {
        pwd = pwdField.getText();
        if(checkPwd()){
            pwdError.setText("");
            login.requestFocus();
        }else{
            showPwdError();
        }
    }


    @FXML
    void tryNickname(ActionEvent event) {
        nickname = nicknameField.getText();

        if(checkNickname()){
            nicknameError.setText("");
            pwdField.requestFocus(); //框框重点跳跃
        } else{
            nicknameError.setText("At least one character and no spaces");
            nicknameField.setText("");
        }
    }


    @FXML
    void switchToRegister(ActionEvent event) throws IOException {
        ((GUI)ClientController.getInstance().getView()).getGuiApplication().switchScene(SceneType.REGISTRATION);
    }


    @FXML
    void tryLogin(ActionEvent event) throws IOException {
        nickname = nicknameField.getText();
        pwd = pwdField.getText();
        if(checkNickname() && checkPwd()){
            ClientController.getInstance().getClientAction().access(nickname, pwd, true);
        } else{
            if (!checkPwd()){
                showPwdError();
                pwdField.requestFocus();
            }
            if (!checkNickname()){
                showNicknameError();
                nicknameField.requestFocus();
            }
        }
    }



    private boolean checkPwd(){
        return pwd.length() >= 6;
    }

    private boolean checkNickname(){
        return (!nickname.matches(".*\\s+.*") && nickname.matches(".*[a-zA-Z].*"));
    }

    private void showNicknameError(){
        nicknameError.setText("At least one character alphabetic and no spaces");
        nicknameField.setText("");
    }

    private void showPwdError(){
        pwdError.setText("At least 6 characters long.");
        pwdField.setText("");
    }


}
