package connection;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class FXMLTransition {
    private static FXMLTransition instance;
    private static ReentrantLock lock = new ReentrantLock();
    private static AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    private FXMLTransition() {
    }

    public static FXMLTransition getInstance() {
        lock.lock();
        if (!atomicBoolean.get()) {
            instance = new FXMLTransition();
            atomicBoolean.set(true);
        }
        lock.unlock();
        return instance;
    }

    public void transit(String url) {
        FXMLLoader load = new FXMLLoader();
        load.setLocation(getClass().getResource(url));

        try {
            load.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = load.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
