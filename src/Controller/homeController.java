package Controller;

import DAO.AppointmentDaoImpl;
import DAO.CustomerDaoImpl;
import Model.Appointment;
import Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import static javafx.fxml.FXMLLoader.load;

/**
 * The home controller (CONTAINS LAMBDA)
 */
public class homeController {

    @FXML
    public TableColumn<Customer, Integer> idColumn;
    @FXML
    public TableColumn<Customer, String> nameColumn;
    @FXML
    public TableColumn<Customer, String> addressColumn;
    @FXML
    public TableColumn<Customer, Integer> postalcodeColumn;
    @FXML
    public TableColumn<Customer, String> phoneColumn;
    @FXML
    public TableColumn<Customer, String> countryColumn;
    @FXML
    public TableColumn<Customer, String> divisionColumn;
    @FXML
    public TableColumn<Appointment, Integer> apptidColumn;
    @FXML
    public TableColumn<Appointment, String> titleColumn;
    @FXML
    public TableColumn<Appointment, String> descriptionColumn;
    @FXML
    public TableColumn<Appointment, String> locationColumn;
    @FXML
    public TableColumn<Appointment, String> contactColumn;
    @FXML
    public TableColumn<Appointment, String> typeColumn;
    @FXML
    public TableColumn<Appointment, Integer> startColumn;
    @FXML
    public TableColumn<Appointment, Integer> endColumn;
    @FXML
    public TableColumn<Appointment, Integer> customeridColumn;
    @FXML
    public TableColumn<Appointment, Integer> useridColumn;
    public ObservableList<Appointment> toggleQuery;
    @FXML
    private TableView<Customer> customerTable;
    @FXML
    private TableView<Appointment> appointmentTable;
    @FXML
    private RadioButton allRadioButton;
    @FXML
    private RadioButton weekRadioButton;
    @FXML
    private RadioButton monthRadioButton;
    @FXML
    private Label appointmentAlertLabel;

    /**
     * Lambda Justification: A lambda expression is used to alert the user if there is an appointment within 15 minutes
     *
     * Initializes the tableviews
     */
    @FXML
    public void initialize() {

        customerTable.setItems(CustomerDaoImpl.buildCustomers());
        idColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        postalcodeColumn.setCellValueFactory(new PropertyValueFactory<>("customerPostalCode"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        divisionColumn.setCellValueFactory(new PropertyValueFactory<>("division"));

        appointmentTable.setItems(AppointmentDaoImpl.buildAppointments());
        apptidColumn.setCellValueFactory(new PropertyValueFactory<>("aptID"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactColumn.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        startColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        customeridColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        useridColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));

        ObservableList<Appointment> upcomingAppointments = FXCollections.observableArrayList();
        ObservableList<Appointment> appointmentAlert = AppointmentDaoImpl.appointmentAlerts();

        if (appointmentAlert != null) {
            //Lambda expression
            appointmentAlert.forEach(appointment -> {
                upcomingAppointments.add(appointment);
                String alert = "There are appointments starting within the next 15 minutes:\n" + "Appointment ID: " + appointment.getAptID() + "\n" +
                        "Date: " + appointment.getStartDate() + "\n" + "Time: " + appointment.getStartTime().toLocalTime();
                appointmentAlertLabel.setText(alert);
            });
        }
    }

    /**
     * Switches to Appointment.fxml
     *
     * @param actionEvent button click
     * @throws IOException
     */
    public void addAppointmentClick(ActionEvent actionEvent) throws IOException {
        Appointment appointment = null;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/Appointment.fxml"));
        Parent scene = loader.load();
        appointmentController controller = loader.getController();
        controller.sendData(appointment);
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Switches to Customer.fxml
     *
     * @param actionEvent button click
     * @throws IOException
     * @throws SQLException
     */
    public void addCustomerClick(ActionEvent actionEvent) throws IOException, SQLException {
        Customer customer = null;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/Customer.fxml"));
        Parent scene = loader.load();
        customerController controller = loader.getController();
        controller.sendData(customer);
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Passes information to Customer.fxml if a selection is made
     *
     * @param actionEvent button click
     * @throws IOException
     * @throws SQLException
     */
    public void updateCustomerClick(ActionEvent actionEvent) throws IOException, SQLException {
        Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View/Customer.fxml"));
            Parent scene = loader.load();
            customerController controller = loader.getController();
            controller.sendData(selectedCustomer);
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(scene));
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setContentText("Please make a selection.");
            alert.showAndWait();
        }
    }

    /**
     * Allows user to delete a customer from the database. Also deletes any associate appointments.
     *
     * @param actionEvent button click
     * @throws SQLException
     */
    public void deleteCustomerClick(ActionEvent actionEvent) throws SQLException {
        Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();

        if (selectedCustomer != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Are you sure?");
            alert.setHeaderText("Delete " + selectedCustomer.getCustomerName() + " and any associated appointments.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                CustomerDaoImpl.deleteCustomer(selectedCustomer.getCustomerID());
                AppointmentDaoImpl.deleteAppointment(selectedCustomer.getCustomerID());
                customerTable.setItems(CustomerDaoImpl.buildCustomers());
                appointmentTable.setItems(AppointmentDaoImpl.buildAppointments());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setContentText("Please make a selection.");
            alert.showAndWait();
        }

    }

    /**
     * Passes information to Appointment.fxml if a selection is made
     *
     * @param actionEvent button click
     * @throws IOException
     */
    public void updateAppointmentClick(ActionEvent actionEvent) throws IOException {
        Appointment selectedAppointment = appointmentTable.getSelectionModel().getSelectedItem();
        if (selectedAppointment != null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View/Appointment.fxml"));
            Parent scene = loader.load();
            appointmentController controller = loader.getController();
            controller.sendData(selectedAppointment);
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(scene));
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setContentText("Please make a selection.");
            alert.showAndWait();
        }
    }

    /**
     * Allows user to delete an appointment from the database
     *
     * @param actionEvent button click
     * @throws SQLException
     */
    public void deleteAppointmentClick(ActionEvent actionEvent) throws SQLException {
        Appointment selectedAppointment = appointmentTable.getSelectionModel().getSelectedItem();
        if (selectedAppointment != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Are you sure?");
            alert.setHeaderText("Delete " + selectedAppointment.getTitle());
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                AppointmentDaoImpl.deleteAppointment(selectedAppointment.getAptID());
                appointmentTable.setItems(AppointmentDaoImpl.buildAppointments());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setContentText("Please make a selection.");
            alert.showAndWait();
        }
    }

    /**
     * Switches to Report.fxml
     *
     * @param actionEvent
     * @throws IOException
     */
    public void reportsButtonClick(ActionEvent actionEvent) throws IOException {
        Parent root = load(getClass().getResource("/View/Report.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 960, 590);
        stage.setTitle("Reports");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Allows user to sort the appointments tableview by all, current week, or current month
     */
    public void allWeekMonthToggle() {
        if (allRadioButton.isSelected()) {
            toggleQuery = AppointmentDaoImpl.buildAppointments();
        } else if (weekRadioButton.isSelected()) {
            toggleQuery = AppointmentDaoImpl.buildWeek();
        } else if (monthRadioButton.isSelected()) {
            toggleQuery = AppointmentDaoImpl.buildMonth();
        }
        appointmentTable.setItems(toggleQuery);
    }
}