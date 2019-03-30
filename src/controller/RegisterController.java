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

public class RegisterController {
    @FXML
    private TextField firstName;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPassword;

    public void ActionRegister(ActionEvent actionEvent) {
        if (emailField.getText() != null && passwordField.getText() != null && firstName.getText() != null && confirmPassword.getText() != null) {
            Connection.getInstance().post("sighUp " + firstName.getText().trim() + " " + emailField.getText().trim() + " " + passwordField.getText().trim());
        } else DialogManager.showErrorDialog("WARNING", "Заполните все поля!");
        User user = (User) ServerMessage.get();
        if(user == null){
            emailField.clear();
            passwordField.clear();
            DialogManager.showErrorDialog("WARNING", "Пользователь с таким логином уже есть!");
        } else{
            emailField.getScene().getWindow().hide();
            FXMLTransition.getInstance().transit("/scenes/client.fxml");
        }
    }

    public void ActionBack(ActionEvent actionEvent) {
        emailField.getScene().getWindow().hide();
        FXMLTransition.getInstance().transit("/scenes/main.fxml");
    }

    public void ActionLogin(ActionEvent actionEvent) {
        emailField.getScene().getWindow().hide();
        FXMLTransition.getInstance().transit("/scenes/login.fxml");
    }
}
