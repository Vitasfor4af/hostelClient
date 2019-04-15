package controller;

import collections.CollectionUsers;
import connection.Connection;
import connection.FXMLTransition;
import connection.ServerMessage;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.User;
import utils.DialogManager;

public class AdminController {
    public ChoiceBox<String> role;
    public TextField name;
    public TextField email;
    public TextField password;
    @FXML
    private Button btnAdd;
    @FXML
    private TableView<User> tableUserView;
    @FXML
    private TableColumn<User, String> columnName;
    @FXML
    private TableColumn<User, String> columnRole;
    @FXML
    private TableColumn<User, String> columnEmail;
    @FXML
    private TableColumn<User, String> columnPassword;
    @FXML
    private Button btnDelete;

    private CollectionUsers collectionUsers = new CollectionUsers();
    private User selectedUser = new User();

    @FXML
    public void initialize(){

        tableUserView.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                    selectedUser = tableUserView.getSelectionModel().getSelectedItem();

                    name.setText(selectedUser.getName());
                    email.setText(selectedUser.getLogin());
                    password.setText(selectedUser.getPassword());
                    role.setValue(selectedUser.getRole());
                }
            }
        });

        role.setItems(FXCollections.observableArrayList("admin", "user", "worker"));

        columnName.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        columnRole.setCellValueFactory(new PropertyValueFactory<User, String>("role"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<User, String>("login"));
        columnPassword.setCellValueFactory(new PropertyValueFactory<User, String>("password"));

        collectionUsers.fillData();

        tableUserView.setItems(collectionUsers.getUsersList());
        tableUserView.refresh();
    }

    public void ActionButtonPressed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        if(!(source instanceof Button)){
            return;
        }
        Button clickedButton = (Button) source;

        switch (clickedButton.getId()){
            case "btnAdd":
                if(name.getText() != null && role.getItems() != null && email.getText() != null && password.getText() != null){

                    selectedUser.setName(name.getText());
                    selectedUser.setRole(role.getSelectionModel().getSelectedItem());
                    selectedUser.setLogin(email.getText());
                    selectedUser.setPassword(password.getText());
                }else {
                    DialogManager.showErrorDialog("Error", "Заполните поля!");
                    break;
                }


                Connection.getInstance().post("addUser " + selectedUser.getId() + " " + selectedUser.getName() + " " + selectedUser.getRole() + " " + selectedUser.getLogin() + " " + selectedUser.getPassword());

                if((Boolean) ServerMessage.get()){
                    DialogManager.showInfoDialog("Info", "Добавление произошло успешно!");
                    name.clear();
                    email.clear();
                    password.clear();
                    initialize();
                }
                break;
            case "btnDelete":
                Connection.getInstance().post("deleteUser " + selectedUser.getId());
                if((Boolean) ServerMessage.get()){
                    initialize();
                    DialogManager.showInfoDialog("Info", "Удалено!");
                }
                break;
        }
    }

    public void ActionLogout(ActionEvent actionEvent) {
        btnAdd.getScene().getWindow().hide();
        FXMLTransition.getInstance().transit("/scenes/main.fxml");
    }
}
