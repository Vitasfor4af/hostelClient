package controller;

import connection.FXMLTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainController {

    @FXML
    private Button login_button;

    public void ActionLogin(ActionEvent actionEvent) {
        login_button.getScene().getWindow().hide();
        FXMLTransition.getInstance().transit("/scenes/login.fxml");
    }

    public void ActionRegister(ActionEvent actionEvent) {
        login_button.getScene().getWindow().hide();
        FXMLTransition.getInstance().transit("/scenes/register.fxml");
    }
}
