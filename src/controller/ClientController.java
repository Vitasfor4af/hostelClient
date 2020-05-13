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
import starter.Start;
import utils.DialogManager;

import java.sql.ResultSet;
import java.sql.SQLException;
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
            ResultSet resultOrder = Start.connection.query("SELECT * FROM ORDERS");

            try {
                while (resultOrder.next()) {
                    if(!dateStart.getValue().equals(resultOrder.getDate("date_start")) && !dateEnd.getValue().equals(resultOrder.getDate("date_end"))){
                        DialogManager.showErrorDialog("ОШИБКА", "Заказа на заданную дату несуществует!");
                        break;
                    }
                }
            } catch (SQLException e){
                e.printStackTrace();
            }

//            Connection.getInstance().post("getHostelNumber " + dateStart.getValue() + " " + dateEnd.getValue());
//            List<Room> roomList = (List<Room>) ServerMessage.get();
        }
    }
}
