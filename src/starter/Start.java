package starter;


import connection.FXMLTransition;
import javafx.application.Application;
import javafx.stage.Stage;

public class Start extends Application {

    @Override
    public void start(Stage primaryStage){
        FXMLTransition.getInstance().transit("/scenes/main.fxml");
    }

    public static void main(String[] args) {
        launch(args);
    }

}
