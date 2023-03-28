package project.wgusoftware2project.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import project.wgusoftware2project.helpers.FruitsQuery;
import project.wgusoftware2project.model.Appointments;
import project.wgusoftware2project.model.Inventory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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
        int customerID;
        int userID;
        int contactID;
        ZoneId zone = ZoneId.of(String.valueOf(ZoneId.systemDefault()));
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").withZone(zone);

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

        ZonedDateTime startLocal = start.atZone(ZoneId.systemDefault());
        ZonedDateTime endLocal = end.atZone(ZoneId.systemDefault());

        customerID = Integer.parseInt(customerIdText.getText());
        userID = Integer.parseInt(userIdText.getText());
        contactID = Integer.parseInt(contactIdText.getText());

        Appointments newAppt = new Appointments(appointmentID, title, description, location, type, start,
                startLocal, end, endLocal, customerID, userID, contactID);

        Inventory.updateAppt(appointmentID, newAppt);
        FruitsQuery.updateAppt(newAppt);

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

        scene = FXMLLoader.load(getClass().getResource("/project/wgusoftware2project/apptCustomer.fxml"));

        stage.setScene(new Scene(scene));

        stage.show();
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


