package Model;

import Database.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Creates an application that allows the user to manage customers and appointments
 */
public class Main extends Application {

    /**
     * Opens and closes connection to MySQL server
     *
     * @param args
     */
    public static void main(String[] args) {
        JDBC.openConnection();
        launch(args);
        JDBC.closeConnection();
    }

    /**
     * Initializes Home.fxml
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../View/Login.fxml")));
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Languages/i18n", Locale.getDefault());
        primaryStage.setTitle(resourceBundle.getString("application"));
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }
}