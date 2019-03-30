package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AdminController {
    @FXML
    private Button btnAdd;
    @FXML
    private TableView tableDoctorView;
    @FXML
    private TableColumn columnName;
    @FXML
    private TableColumn columnSpecialty;
    @FXML
    private TableColumn columnEmail;
    @FXML
    private TableColumn columnPassword;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnDelete;

    public void ActionButtonPressed(ActionEvent actionEvent) {
    }

    public void ActionLogout(ActionEvent actionEvent) {
    }
}
