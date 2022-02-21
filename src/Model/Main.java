package Model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    /**
     * Initializes Home.fxml
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../View/Home.fxml"));
        primaryStage.setTitle("Scheduling Application");
        primaryStage.setScene(new Scene(root, 1000, 700));
        primaryStage.show();
    }
}

