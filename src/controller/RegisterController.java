package controller;

import connection.FXMLTransition;
import connection.ServerMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import model.User;
import starter.Start;
import utils.DialogManager;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterController {
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private TextField roleIdField;

    public void ActionRegister(ActionEvent actionEvent) {

        if (!loginField.getText().equals("") && !passwordField.getText().equals("") && !firstNameField.getText().equals("") && !confirmPasswordField.getText().equals("")) {
            ResultSet resultUser = Start.connection.query("SELECT * FROM USERS");
            try {
                while(resultUser.next()){
                    if(loginField.getText().equals(resultUser.getString("login"))){
                        DialogManager.showErrorDialog("ОШИБКА", "Пользователь с таким логином уже есть!");
                        firstNameField.clear();
                        loginField.clear();
                        passwordField.clear();
                        confirmPasswordField.clear();
                        roleIdField.clear();
                        break;
                    }
                }
                if(passwordField.getText().equals(confirmPasswordField.getText())){
                    Start.connection.updateQuery("INSERT INTO USERS(name, login, password, roleID) VALUES('"+ firstNameField.getText() + "', '" + loginField.getText() + "', '" + passwordField.getText() + "', "+ roleIdField.getText() + ")");
                } else {
                    DialogManager.showErrorDialog("ОШИБКА", "Пароль не был корректно подтвержден. Пароль и подтверждение должны совпадать!");
                    firstNameField.clear();
                    loginField.clear();
                    passwordField.clear();
                    confirmPasswordField.clear();
                    roleIdField.clear();
                }
            } catch(SQLException e){
                e.printStackTrace();
            }

        } else DialogManager.showErrorDialog("ОШИБКА", "Заполните все поля!");
    }

    public void ActionBack(ActionEvent actionEvent) {
        loginField.getScene().getWindow().hide();
        FXMLTransition.getInstance().transit("/scenes/main.fxml");
    }

    public void ActionLogin(ActionEvent actionEvent) {
        loginField.getScene().getWindow().hide();
        FXMLTransition.getInstance().transit("/scenes/login.fxml");
    }
}
