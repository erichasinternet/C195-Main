package Controller;

import Database.JDBC;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 * The report controller (CONTAINS LAMBDA)
 */
public class reportController {

    public ObservableList data;
    public String variableQuery;
    @FXML
    private TableView reportsTable;
    @FXML
    private ToggleGroup reportsRadioToggle;
    @FXML
    private RadioButton totalRadio;
    @FXML
    private RadioButton allRadio;
    @FXML
    private RadioButton schedulesRadio;

    /**
     * Determines which sort should be used
     * based on which radio button is selected
     */
    public void initialize() {
        reportsRadioToggle();
    }

    /**
     * Lambda Justification: A lambda expression is used to dynamically generate the tableview
     */
    public void buildReport() {
        Connection c;
        data = FXCollections.observableArrayList();
        reportsTable.getColumns().clear();
        try {
            c = JDBC.getConnection();
            String SQL = variableQuery;
            ResultSet rs = c.createStatement().executeQuery(SQL);

            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                // Lambda expression
                col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param ->
                        new SimpleStringProperty(param.getValue().get(j).toString()));

                reportsTable.getColumns().addAll(col);
            }
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
                data.add(row);
            }

            reportsTable.setItems(data);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the SQL query based on which radio button is selected
     */
    public void reportsRadioToggle() {
        if (totalRadio.isSelected()) {
            variableQuery = ("SELECT Type, MONTHNAME(Start) as Month, count(*) AS Count FROM appointments GROUP BY Type, MONTHNAME(Start) ORDER BY Type;");
        } else if (allRadio.isSelected()) {
            variableQuery = ("SELECT contacts.Contact_Name, appointments.Appointment_ID, appointments.Title, appointments.Type, appointments.Description, CONVERT_TZ(start, '+00:00', 'system') AS start, CONVERT_TZ(end, '+00:00', 'system') AS end, appointments.Customer_ID FROM appointments, contacts WHERE appointments.Contact_ID = contacts.Contact_ID order by Contact_Name;");
        } else if (schedulesRadio.isSelected()) {
            variableQuery = ("SELECT users.User_Name, contacts.Contact_Name, appointments.Appointment_ID, appointments.Title, appointments.Type, appointments.Description, CONVERT_TZ(start, '+00:00', 'system') AS start, CONVERT_TZ(end, '+00:00', 'system') AS end, appointments.Customer_ID FROM appointments, contacts, users WHERE appointments.Contact_ID = contacts.Contact_ID order by User_Name;");
        }
        buildReport();
    }

    /**
     * Returns the user to Home.fxml
     *
     * @param actionEvent button click
     * @throws Exception
     */
    public void backButtonClick(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Home.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 700);
        stage.setTitle("Scheduling Application");
        stage.setScene(scene);
        stage.show();
    }

}