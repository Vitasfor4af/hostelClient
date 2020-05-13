package controller;

import connection.FXMLTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import starter.Start;
import utils.DialogManager;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;

    public void ActionRegister(ActionEvent actionEvent) {
        loginField.getScene().getWindow().hide();
        FXMLTransition.getInstance().transit("/scenes/register.fxml");
    }

    public void ActionLogin(ActionEvent actionEvent) {
        boolean isLoginValid = false;
        boolean isPasswordValid = false;

        ResultSet resultUser = Start.connection.query("SELECT * FROM USERS");
        if (!loginField.getText().equals("") && !passwordField.getText().equals("")) {
            try {
                while (resultUser.next()) {
                    if (loginField.getText().equals(resultUser.getString("login"))) {
                        isLoginValid = true;
                        if(passwordField.getText().equals(resultUser.getString("password"))){
                            isPasswordValid = true;
                        }
                    }
                }
                if(isLoginValid){
                    if(!isPasswordValid){
                        DialogManager.showErrorDialog("ОШИБКА", "Неверный пароль!");
                        loginField.clear();
                        passwordField.clear();
                    }
                } else{
                    DialogManager.showErrorDialog("ОШИБКА", "Пользователя с таким логином не существует!");
                    loginField.clear();
                    passwordField.clear();
                }


            } catch(SQLException e){
                e.printStackTrace();
            }

        } else {DialogManager.showErrorDialog("WARNING", "Заполните все поля!");}
        if(isLoginValid && isPasswordValid){
            resultUser = Start.connection.query("SELECT roleID FROM USERS WHERE login='" + loginField.getText() + "'");



            try {
                int role = 1;
                while (resultUser.next()){
                    role = resultUser.getInt("roleID");
                }
                loginField.getScene().getWindow().hide();
                switch (role) {
                    case 1:
                        FXMLTransition.getInstance().transit("/scenes/admin.fxml");
                        break;
                    case 2:
                        FXMLTransition.getInstance().transit("/scenes/client.fxml");
                        break;
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

        }


    public void ActionBack(ActionEvent actionEvent) {
        loginField.getScene().getWindow().hide();
        FXMLTransition.getInstance().transit("/scenes/main.fxml");
    }
}
