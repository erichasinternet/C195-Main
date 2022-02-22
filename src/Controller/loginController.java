package Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class loginController {
    public TextField usernameField;
    public TextField passwordField;
    public Label usernameLabel;
    public Label passwordLabel;
    public Button loginButton;
    public Label locationTitleLabel;
    public Label locationLabel;
    public Label loginError;
    private ResourceBundle resourceBundle;

    public void initialize() {
        resourceBundle = ResourceBundle.getBundle("i18n/i18n", Locale.getDefault());
        locationLabel.setText(String.valueOf(ZoneId.of(TimeZone.getDefault().getID())));
    }
    
    public void loginButtonClick(ActionEvent actionEvent) {
    }
}
