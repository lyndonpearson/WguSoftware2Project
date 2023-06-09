package project.wgusoftware2project.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import project.wgusoftware2project.helpers.MySqlQuery;
import project.wgusoftware2project.model.Appointments;
import project.wgusoftware2project.model.Inventory;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;

/** changeApptController class created with initialization capabilities.
 The controller interfaces with the text fields,
 and buttons show in changeAppt.fxml
 */
public class changeApptController implements Initializable {
    Stage stage;
    Parent scene;
    @FXML
    private Button cancelBtn;

    @FXML
    private TextField contactIdText;

    @FXML
    private TextField customerIdText;

    @FXML
    private TextField descText;

    @FXML
    private TextField endText;

    @FXML
    private TextField idText;

    @FXML
    private TextField locText;

    @FXML
    private Button saveBtn;

    @FXML
    private TextField startText;

    @FXML
    private TextField titleText;

    @FXML
    private TextField typeText;

    @FXML
    private TextField userIdText;

    /** This method is called if the Cancel
     button is clicked. The window is switched to the apptCustomer.fxml file.
     @param event The event of the Cancel Button being clicked
     */
    @FXML
    void onCancelBtnClick(ActionEvent event) throws IOException {
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();

        scene = FXMLLoader.load(getClass().getResource("/project/wgusoftware2project/apptCustomer.fxml"));

        stage.setScene(new Scene(scene));

        stage.show();
    }

    /** This method is called if the Save
     button is selected. The TextFields are parsed, a new Appointment object
     is created and validation is done on time format and weekday business
     hours in EST. Then the object is added to the Inventory ObservableList as well
     as the mySQL database.
     @param event The event of the Save button being clicked
     */
    @FXML
    void onSaveBtnClick(ActionEvent event) throws IOException, SQLException {
        int appointmentID = 0;
        String title;
        String description;
        String location;
        String type;
        Instant start;
        Instant end;
        int customerID = 0;
        int userID;
        int contactID;
        ZoneId zone = ZoneId.of(String.valueOf(ZoneId.systemDefault()));
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").withZone(zone);
        boolean Overlap = false;

        try {
            appointmentID = Integer.parseInt(idText.getText());
            title = titleText.getText();
            description = descText.getText();
            location = locText.getText();
            type = typeText.getText();

            String startTimeText = startText.getText();
            ZonedDateTime zdt = ZonedDateTime.parse(startTimeText, fmt);
            start = zdt.toInstant();

            String endTimeText = endText.getText();
            ZonedDateTime zdtEnd = ZonedDateTime.parse(endTimeText, fmt);
            end = zdtEnd.toInstant();

            Timestamp startLocal = Timestamp.from(start);
            Timestamp endLocal = Timestamp.from(end);

            ZonedDateTime estZdt = start.atZone(ZoneId.of("America/New_York"));
            ZonedDateTime estZdtEnd = end.atZone(ZoneId.of("America/New_York"));

            String dayEST = String.valueOf(estZdt.getDayOfWeek());
            int hourEST = estZdt.getHour();
            int minuteEST = estZdt.getMinute();
            int dayNumEST = estZdt.getDayOfYear();
            int hourESTEnd = estZdtEnd.getHour();
            int minuteESTEnd = estZdtEnd.getMinute();

            if (dayEST.equals("SATURDAY") || dayEST.equals("SUNDAY")) {
                throw new Exception();
            } else if (hourEST < 8 || hourEST > 21) {
                throw new Exception();
            }

            customerID = Integer.parseInt(customerIdText.getText());
            userID = Integer.parseInt(userIdText.getText());
            contactID = Integer.parseInt(contactIdText.getText());

            Appointments newAppt = new Appointments(appointmentID, title, description, location, type, start,
                    startLocal, end, endLocal, customerID, userID, contactID);

            Overlap = Inventory.checkOverlapChange(newAppt, customerID, hourEST, minuteEST, dayNumEST, hourESTEnd, minuteESTEnd);
            if(Overlap){
                throw new Exception();
            }

            Inventory.updateAppt(appointmentID, newAppt);
            MySqlQuery.updateAppt(newAppt);

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            scene = FXMLLoader.load(getClass().getResource("/project/wgusoftware2project/apptCustomer.fxml"));

            stage.setScene(new Scene(scene));

            stage.show();
        } catch(DateTimeParseException inputError) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("Please enter time in YYYY-MM-DD HH:MM format in Start and End fields");
            alert.showAndWait();
        } catch (Exception msg) {
            Alert alert;
            if (Overlap) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Appointment Warning");
                alert.setContentText("Appointment will overlap existing appointments " +
                        "for customer: " + customerID);
            }else {
                alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.getDialogPane().setPrefSize(400, 200);
                alert.setContentText("Appointments can only be scheduled on Weekdays from 8AM-10PM EST");
            }
            alert.showAndWait();
        }
    }

    /** This method receives an Appointment object to modify from the apptCustomerController
     The field labels (Id, title, etc.) are all used to set the corresponding text fields.
     @param inAppt this parameter handles the input Appointment
     */
    public void receiveInAppt(Appointments inAppt){

        idText.setText(String.valueOf(inAppt.getAppointmentID()));
        titleText.setText(inAppt.getTitle());
        descText.setText(String.valueOf(inAppt.getDescription()));
        locText.setText(String.valueOf(inAppt.getLocation()));
        typeText.setText(String.valueOf(inAppt.getType()));

        startText.setText(inAppt.getStartLocal().toString());
        endText.setText(inAppt.getEndLocal().toString());

        customerIdText.setText(String.valueOf(inAppt.getCustomerID()));
        userIdText.setText(String.valueOf(inAppt.getUserID()));
        contactIdText.setText(String.valueOf(inAppt.getContactID()));



    }

    /** This method is called when the changeAppointment.fxml file is loaded.
     The ID text field is disabled.
     @param url The location of the relative path of the root object.
     @param resourceBundle Resource used to localize the root object; can be null if absolute path.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idText.setDisable(true);
    }
}


