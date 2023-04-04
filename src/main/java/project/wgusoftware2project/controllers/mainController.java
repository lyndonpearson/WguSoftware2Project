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

    @FXML
    private ComboBox<String> languageComboBox;

    @FXML
    private Label languageLabel;



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
                String filename = "src/logins.txt";
                FileWriter fwriter = new FileWriter(filename, true);
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                fwriter.write("Unsuccessful login: " + timestamp + "\n");
                fwriter.close();
                throw new Exception();
            }

            String filename = "src/logins.txt";
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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.getDialogPane().setPrefSize(400, 200);
            alert.setContentText("Invalid login credentials");
            alert.showAndWait();
        }
    }

    /** This method is called if the Language ComboBox
     is clicked. If French is selected, all labels and text are translated
     to French. Otherwise the text is set to English.
     @param event The event of the Language ComboBox being clicked
     */
    @FXML
    void onLanguageComboBoxClick(ActionEvent event) {
        String selectedLanguage = languageComboBox.getSelectionModel().getSelectedItem();

        if (selectedLanguage.equals("French")) {
            Locale.setDefault(new Locale("fr", "FR"));
            ResourceBundle rb = ResourceBundle.getBundle("Nat");
            System.out.println(rb.getString("UserName") + rb.getString("Password") +
                    rb.getString("Location") + rb.getString("Login"));
            loginBtn.setText(rb.getString("Login"));
            userNameLabel.setText(rb.getString("UserName"));
            passwordLabel.setText(rb.getString("Password"));
            locationLabel.setText(rb.getString("Location"));
            languageLabel.setText(rb.getString("Language"));
            languageComboBox.setPromptText(rb.getString("Selection"));
        }

    }

    /** This method is called when the main.fxml file is loaded.
     The location TextField is updated to the system's default.
     The Language ComboBox is populated with English and French options.
     @param location The location of the relative path of the root object.
     @param resources Resource used to localize the root object; can be null if absolute path.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String locationZone = String.valueOf(ZoneId.systemDefault());
        locationText.setText(locationZone);

        ObservableList<String> language = FXCollections.observableArrayList();
        language.add("English");
        language.add("French");
        languageComboBox.setItems(language);
    }
}

