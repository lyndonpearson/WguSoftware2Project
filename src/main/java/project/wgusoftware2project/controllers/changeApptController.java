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

    @FXML
    void onCancelBtnClick(ActionEvent event) throws IOException {
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();

        scene = FXMLLoader.load(getClass().getResource("/project/wgusoftware2project/apptCustomer.fxml"));

        stage.setScene(new Scene(scene));

        stage.show();
    }

    @FXML
    void onSaveBtnClick(ActionEvent event) throws IOException, SQLException {
        int appointmentID;
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
        boolean noOverlap = false;

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

            String dayEST = String.valueOf(estZdt.getDayOfWeek());
            int hourEST = estZdt.getHour();
            int minuteEST = estZdt.getMinute();

            if (dayEST.equals("SATURDAY") || dayEST.equals("SUNDAY")) {
                throw new Exception();
            } else if (hourEST < 8 || hourEST > 21) {
                throw new Exception();
            }

            customerID = Integer.parseInt(customerIdText.getText());
            userID = Integer.parseInt(userIdText.getText());
            contactID = Integer.parseInt(contactIdText.getText());

            noOverlap = Inventory.checkAppointmentOverlap(customerID, hourEST, minuteEST);
            if(!noOverlap){
                throw new Exception();
            }

            Appointments newAppt = new Appointments(appointmentID, title, description, location, type, start,
                    startLocal, end, endLocal, customerID, userID, contactID);
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
            if (!noOverlap) {
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idText.setDisable(true);
    }
}


