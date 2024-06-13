package it.polimi.ingsw.view.gui.controllers;

import it.polimi.ingsw.controller.client.ClientController;
import it.polimi.ingsw.view.gui.GUIApplication;
import it.polimi.ingsw.view.gui.enums.PageType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;

public class ForLogin {
    String nickname;
    String pwd;

    @FXML
    private Label title;

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


    @FXML
    void initialize(){
        Font algerian48 = Font.loadFont(getClass().getResourceAsStream("/font/Alger.ttf"),48);
        title.setFont(algerian48);
    }


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
        ((GUIApplication)ClientController.getInstance().getView()).switchPage(PageType.REGISTRATION);
    }


    /**
     * try login in game
     * @param event
     * @throws IOException
     */
    @FXML
    void tryLogin(ActionEvent event) throws IOException {
        nickname = nicknameField.getText();
        pwd = pwdField.getText();
        if(checkNickname() && checkPwd()){
            ((GUIApplication)(ClientController.getInstance().getView())).setTryNickname(nickname);
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
