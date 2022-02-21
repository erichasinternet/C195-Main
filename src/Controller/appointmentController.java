package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class appointmentController {
    public void contactComboSelect(ActionEvent actionEvent) {
    }

    public void typeComboSelect(ActionEvent actionEvent) {
    }

    public void customerComboSelect(ActionEvent actionEvent) {
    }

    public void userComboSelect(ActionEvent actionEvent) {
    }

    public void saveButtonClick(ActionEvent actionEvent) {
    }

    public void cancelButtonClick(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Home.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 700);
        stage.setTitle("Scheduling Application");
        stage.setScene(scene);
        stage.show();
    }

    public void startComboSelect(ActionEvent actionEvent) {
    }

    public void endComboSelect(ActionEvent actionEvent) {
    }
}
