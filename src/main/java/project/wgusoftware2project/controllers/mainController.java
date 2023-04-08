package project.wgusoftware2project.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import project.wgusoftware2project.model.Inventory;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

/** mainController class created with initialization capabilities.
 The controller interfaces with the comboBoxes, labels, text fields,
 and buttons shown in main.fxml
 */
public class mainController implements Initializable {
    Parent scene;
    @FXML
    private Button loginBtn;

    @FXML
    private TextField locationText;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField userNameTextField;

    @FXML
    private Label userNameLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private Label locationLabel;

    /** This method is called if the Login
     button is clicked. The input userId and password are validated.
     If incorrect, a log file is updated with an unsuccessful entry and timestamp
     along with an alert displayed
     If correct, the log file is updated with a successful entry and timestamp
     The view is then changed to apptCustomer.fxml
     @param event The event of the Login Button being clicked
     */
    @FXML
    void onLoginBtnClick(ActionEvent event) throws SQLException, IOException {
        System.out.println("UserName: " + userNameTextField.getText());
        System.out.println("Password: " + passwordTextField.getText());

        try {
            if (!Inventory.checkLogin(userNameTextField.getText(), passwordTextField.getText())) {
                String filename = "login_activity.txt";
                FileWriter fwriter = new FileWriter(filename, true);
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                fwriter.write("Unsuccessful login: " + timestamp + "\n");
                fwriter.close();
                throw new Exception();
            }

            String filename = "login_activity.txt";
            FileWriter fwriter = new FileWriter(filename, true);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            fwriter.write("Successful login: " + timestamp + "\n");
            fwriter.close();

            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            scene = FXMLLoader.load(getClass().getResource("/project/wgusoftware2project/apptCustomer.fxml"));

            stage.setScene(new Scene(scene));

            stage.setWidth(900);
            stage.setHeight(600);

            stage.show();

            Inventory.checkAppointmentTimes();
        } catch(Exception msg){
            Locale systemLanguage = Locale.getDefault();
            String systemLanguageString = systemLanguage.toString();
            ResourceBundle rb = ResourceBundle.getBundle("Nat");
            if (systemLanguageString.equals("fr_FR")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.getDialogPane().setPrefSize(400, 200);
                alert.setContentText(rb.getString("Invalid") +  " " + rb.getString("Login") + " " + rb.getString("Entry"));
                ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText(rb.getString("OK"));
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.getDialogPane().setPrefSize(400, 200);
                alert.setContentText("Invalid login credentials");
                alert.showAndWait();
            }
        }
    }

    /** This method is called when the main.fxml file is loaded.
     The location TextField is updated to the system's default.
     @param location The location of the relative path of the root object.
     @param resources Resource used to localize the root object; can be null if absolute path.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String locationZone = String.valueOf(ZoneId.systemDefault());
        locationText.setText(locationZone);

        Locale systemLanguage = Locale.getDefault();
        String systemLanguageString = systemLanguage.toString();

        if (systemLanguageString.equals("fr_FR")) {
            Locale.setDefault(new Locale("fr", "FR"));
            ResourceBundle rb = ResourceBundle.getBundle("Nat");
            System.out.println(rb.getString("UserName") + rb.getString("Password") +
                    rb.getString("Location") + rb.getString("Login"));
            loginBtn.setText(rb.getString("Login"));
            userNameLabel.setText(rb.getString("UserName"));
            passwordLabel.setText(rb.getString("Password"));
            locationLabel.setText(rb.getString("Location"));
        }
    }
}

