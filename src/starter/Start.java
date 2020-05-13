package starter;


import connection.FXMLTransition;
import dao.sql.DbConnection;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Properties;

public class Start extends Application {

    public static DbConnection connection = new DbConnection("localhost:3306", "root","22383465887","hostel");

    @Override
    public void start(Stage primaryStage){
        FXMLTransition.getInstance().transit("/scenes/main.fxml");
    }

    public static void main(String[] args) {

        connection.initProperties();
        connection.init();

        launch(args);
    }

}
