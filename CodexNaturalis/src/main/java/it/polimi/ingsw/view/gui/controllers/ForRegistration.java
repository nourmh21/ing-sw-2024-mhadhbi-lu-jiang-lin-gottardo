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

public class ForRegistration {

    @FXML
    private Label title;

    @FXML
    private TextField nicknameField;

    @FXML
    private Text nicknameError;

    @FXML
    private PasswordField pwd1Field;

    @FXML
    private PasswordField pwd2Field;

    @FXML
    private Text pwd1Error;

    @FXML
    private Text pwd2Error;

    @FXML
    private Button submit;

    @FXML
    private Button backToLogin;

    String nickname;
    String pwd1;
    String pwd2;


    @FXML
    void initialize(){
        Font algerian48 = Font.loadFont(getClass().getResourceAsStream("/font/Alger.ttf"),48);
        title.setFont(algerian48);
    }

    @FXML
    void tryConfirmPassword(ActionEvent event) {
        pwd2 = pwd1Field.getText();
        if(checkPwdCoincidence()){
            pwd2Error.setText("");
            submit.requestFocus();
        } else {
            showPwdCoincidenceError();
        }
    }

    @FXML
    void tryPassword(ActionEvent event) {
        pwd1 = pwd2Field.getText();
        if(checkPwd(pwd1)){
            pwd2Error.setText("");
            pwd1Field.requestFocus();
        } else{
            showPwdLengthError();
        }
    }

    @FXML
    void switchToLogin(ActionEvent event) throws IOException {
        ((GUIApplication) ClientController.getInstance().getView()).switchPage(PageType.LOGIN);
    }


    @FXML
    void tryRegistration(ActionEvent event) throws IOException {
        nickname = nicknameField.getText();
        pwd1 = pwd2Field.getText();
        pwd2 = pwd1Field.getText();

        if(checkNickname() && checkPwd(pwd1) && checkPwd(pwd2)
                && checkPwdCoincidence()){
            ClientController.getInstance().getClientAction().access(nickname, pwd1, false);
            ((GUIApplication)(ClientController.getInstance().getView())).setTryNickname(nickname);
        } else{
            if (!checkPwdCoincidence()) {
                showPwdCoincidenceError();
                pwd2Field.requestFocus();
            }
            if (!checkPwd(pwd1)) {
                showPwdLengthError();
                pwd1Field.requestFocus();
            }
            if (!checkNickname()){
                showNicknameError();
                nicknameField.requestFocus();
            }

        }
    }

    @FXML
    void tryNickname(ActionEvent event) {
        nickname = nicknameField.getText();
        if(checkNickname()){
            nicknameError.setText("");
            pwd1Field.requestFocus();
        } else{
            showNicknameError();
        }
    }


    private boolean checkPwd(String pwd){
        return pwd.length() >= 6;
    }

    private boolean checkNickname(){
        return (!nickname.matches(".*\\s+.*") && nickname.matches(".*[a-zA-Z].*"));
    }

    private boolean checkPwdCoincidence(){
        return pwd2.equals(pwd1);
    }

    private void showNicknameError(){
        nicknameError.setText("At least one character and no spaces");
        nicknameField.setText("");
    }

    private void showPwdLengthError(){
        pwd1Error.setText("At least 6 characters long.");
        pwd1Field.setText("");
    }

    private void showPwdCoincidenceError(){
        pwd2Error.setText("The two password are different");
        pwd2Field.setText("");
    }



}

