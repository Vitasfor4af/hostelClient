package controller;

import connection.Connection;
import connection.FXMLTransition;
import connection.ServerMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.User;
import utils.DialogManager;

public class LoginController {

    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;

    public void ActionRegister(ActionEvent actionEvent) {
        emailField.getScene().getWindow().hide();
        FXMLTransition.getInstance().transit("/scenes/register.fxml");
    }

    public void ActionLogin(ActionEvent actionEvent) {
        if (emailField.getText() != null && passwordField.getText() != null) {
            Connection.getInstance().post("logIn " + emailField.getText().trim() + " " + passwordField.getText().trim());
        } else DialogManager.showErrorDialog("WARNING", "Заполните все поля!");
        User user = (User) ServerMessage.get();
        if(user == null){
            emailField.clear();
            passwordField.clear();
            DialogManager.showErrorDialog("WARNING", "Такого пользователя нет!");
        } else{
            emailField.getScene().getWindow().hide();
            switch (user.getRole()){
                case 1: FXMLTransition.getInstance().transit("/scenes/client.fxml");
                break;
                case 2: FXMLTransition.getInstance().transit("/scenes/admin.fxml");
            }
        }
    }

    public void ActionBack(ActionEvent actionEvent) {
        emailField.getScene().getWindow().hide();
        FXMLTransition.getInstance().transit("/scenes/main.fxml");
    }
}
