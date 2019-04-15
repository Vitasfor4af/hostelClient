package controller;

import connection.Connection;
import connection.FXMLTransition;
import connection.ServerMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Room;
import utils.DialogManager;

import java.util.List;

public class ClientController {

    public DatePicker dateStart;
    public DatePicker dateEnd;
    public TableView tableRooms;
    public TableColumn numberRoom;
    public TableColumn isEmpty;
    public TableColumn price;
    public Button search;

    @FXML
    public void initialize(){
    }

    public void ActionLogout(ActionEvent actionEvent) {
        search.getScene().getWindow().hide();
        FXMLTransition.getInstance().transit("/scenes/main.fxml");
    }

    public void ActionOrder(ActionEvent actionEvent) {
    }

    public void ActionSearch(ActionEvent actionEvent) {
        if(dateStart.getValue() == null && dateEnd.getValue() == null){
            DialogManager.showErrorDialog("Error", "Выберите дни");
        }else {
            Connection.getInstance().post("getHostelNumber " + dateStart.getValue() + " " + dateEnd.getValue());
            List<Room> roomList = (List<Room>) ServerMessage.get();
        }
    }
}
