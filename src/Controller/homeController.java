package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class homeController {
    public void addAppointmentClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Appointment.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 435, 557);
        stage.setTitle("Add Appointment");
        stage.setScene(scene);
        stage.show();
    }

    public void addCustomerClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Customer.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 435, 557);
        stage.setTitle("Add Customer");
        stage.setScene(scene);
        stage.show();
    }

    public void updateCustomerClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Customer.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 435, 557);
        stage.setTitle("Update Customer");
        stage.setScene(scene);
        stage.show();
    }

    public void deleteCustomerClick(ActionEvent actionEvent) {
    }

    public void updateAppointmentClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Appointment.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 435, 557);
        stage.setTitle("Update Appointment");
        stage.setScene(scene);
        stage.show();
    }

    public void deleteAppointmentClick(ActionEvent actionEvent) {
    }

    public void allRadioClick(ActionEvent actionEvent) {
    }

    public void weekRadioClick(ActionEvent actionEvent) {
    }

    public void monthRadioClick(ActionEvent actionEvent) {
    }

    public void reportsButtonClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Report.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1100, 900);
        stage.setTitle("Reports");
        stage.setScene(scene);
        stage.show();
    }
}
