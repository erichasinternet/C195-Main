package Controller;

import DAO.CountryDaoImpl;
import DAO.CustomerDaoImpl;
import DAO.DivisionDaoImpl;
import Model.Country;
import Model.Customer;
import Model.Division;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Objects;

/**
 * The Customer controller
 */
public class customerController {
    ObservableList<String> countryList = FXCollections.observableArrayList();
    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField postalField;
    @FXML
    private TextField phoneField;
    @FXML
    private ComboBox<String> countryCombo;
    @FXML
    private ComboBox<String> divisionidCombo;

    /**
     * Removes non-integer values from Division ComboBox
     */
    public String cleanDiv() {
        String s = divisionidCombo.getValue();
        return s.replaceAll("\\D+", "");
    }

    /**
     * Checks if any fields are empty
     *
     * @return boolean
     */
    public boolean emptyField() {
        if (nameField.getText().isEmpty()) {
            return true;
        } else if (addressField.getText().isEmpty()) {
            return true;
        } else if (postalField.getText().isEmpty()) {
            return true;
        } else if (phoneField.getText().isEmpty()) {
            return true;
        } else if (countryCombo.getValue().isEmpty()) {
            return true;
        } else if (cleanDiv().isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * Saves customer updates and new customers in the database
     *
     * @param actionEvent button click
     * @throws Exception
     */
    public void saveButtonClick(ActionEvent actionEvent) throws Exception {
        if (emptyField()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setContentText("Please fill out all fields");
            alert.showAndWait();
            return;
        } else if (idField.getText().isEmpty()) {
            CustomerDaoImpl.createCustomer(
                    nameField.getText(),
                    addressField.getText(),
                    postalField.getText(),
                    phoneField.getText(),
                    cleanDiv());
        } else try {
            CustomerDaoImpl.updateCustomer(
                    Integer.parseInt(idField.getText()),
                    nameField.getText(),
                    addressField.getText(),
                    postalField.getText(),
                    phoneField.getText(),
                    cleanDiv());
        } catch (Exception e) {
            e.printStackTrace();
        }
        cancelButtonClick(actionEvent);
    }

    /**
     * Switches user to Home.fxml
     *
     * @param actionEvent
     * @throws Exception
     */
    public void cancelButtonClick(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/Home.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 700);
        stage.setTitle("Scheduling Application");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Populates ComboBox from database
     *
     * @param actionEvent Country ComboBox select
     * @throws SQLException
     */
    public void countryComboSelect(ActionEvent actionEvent) throws SQLException {
        ObservableList<String> divs = FXCollections.observableArrayList();

        try {

            ObservableList<Division> divisions = new DivisionDaoImpl().getCountry(countryCombo.getSelectionModel().getSelectedItem());

            if (divisions != null) {
                for (Division division : divisions) {
                    divs.add(division.getDivisionID() + " - " + division.getDivision());
                }
            }

            divisionidCombo.setItems(divs);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Parses data into relevant fields when customerController is launched
     */
    public void sendData(Customer customer) throws SQLException {

        try {
            ObservableList<Country> countries = CountryDaoImpl.getCountries();
            if (countries != null) {
                for (Country country : countries) {
                    countryList.add(country.getCountry());

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        countryCombo.setItems(countryList);

        if (customer != null) try {

            idField.setText(Integer.toString(customer.getCustomerID()));
            addressField.setText((customer.getCustomerAddress()));
            nameField.setText(customer.getCustomerName());
            postalField.setText((customer.getCustomerPostalCode()));
            phoneField.setText((customer.getCustomerPhone()));
            countryCombo.setValue(customer.getCountryID() + " - " + customer.getCountry());
            divisionidCombo.setValue(customer.getCustomerDivisionID() + " - " + customer.getDivision());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}