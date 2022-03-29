package Controller;

import DAO.UserDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;

/**
 * The login controller
 */
public class loginController {
    @FXML
    public TextField usernameField;
    @FXML
    public TextField passwordField;
    @FXML
    public Label usernameLabel;
    @FXML
    public Label passwordLabel;
    @FXML
    public Button loginButton;
    @FXML
    public Label locationTitleLabel;
    @FXML
    public Label locationLabel;
    @FXML
    public Label loginError;

    private ResourceBundle rb = ResourceBundle.getBundle("Languages/i18n", Locale.getDefault());

    /**
     * Initializes login and sets the language based on the ZoneId
     */
    public void initialize() {

        locationLabel.setText(String.valueOf(ZoneId.of(TimeZone.getDefault().getID())));
        loginButton.setText(rb.getString("login"));
        usernameLabel.setText(rb.getString("username"));
        passwordLabel.setText(rb.getString("password"));

    }

    /**
     * Validates username and password when button is clicked
     *
     * @param actionEvent button click
     * @throws SQLException
     * @throws IOException
     */
    public void loginButtonClick(ActionEvent actionEvent) throws SQLException, IOException {

        if (!inputCheck()) return;
        boolean validLogin = UserDaoImpl.checkLogin(usernameField.getText(), passwordField.getText());
        if (validLogin) {
            UserDaoImpl.checkLogin(usernameField.getText(), passwordField.getText());
            logLogin(usernameField.getText() + " has logged in at ");

            Parent root = FXMLLoader.load(getClass().getResource("/View/Home.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1000, 700);
            stage.setTitle("Scheduling Application");
            stage.setScene(scene);
            stage.show();
        } else {
            loginError.setText(rb.getString("invalid"));
            logLogin(usernameField.getText() + " has unsuccessfully attempted to log in at ");
        }
    }

    /**
     * Checks if username and password fields are empty
     *
     * @return
     */
    public Boolean inputCheck() {
        if (usernameField.getText().isEmpty()) {
            loginError.setText(rb.getString("username_required"));
            return false;
        } else if (passwordField.getText().isEmpty()) {
            loginError.setText(rb.getString("password_required"));
            return false;
        }
        return true;
    }

    /**
     * Creates logs of login attempts
     *
     * @param loginText
     */
    private void logLogin(String loginText) {
        try (FileWriter fileWriter = new FileWriter("login_activity.txt", true)) {
            Date date = new Date(System.currentTimeMillis());
            SimpleDateFormat timeFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm");
            fileWriter.write(loginText + timeFormat.format(date) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}