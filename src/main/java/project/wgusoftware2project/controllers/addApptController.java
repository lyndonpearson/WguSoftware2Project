package project.wgusoftware2project.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import project.wgusoftware2project.helpers.MySqlQuery;
import project.wgusoftware2project.model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.ResourceBundle;

public class addApptController implements Initializable {
    Stage stage;
    Parent scene;


    @FXML
    public ComboBox<Contacts> contactIdCombo;

    @FXML
    private ComboBox<Customers> custIdCombo;

    @FXML
    private ComboBox<Users> userIdCombo;
    @FXML
    private Button cancelBtn;

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
    void onCancelBtnClick(ActionEvent event) throws IOException {
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();

        scene = FXMLLoader.load(getClass().getResource("/project/wgusoftware2project/apptCustomer.fxml"));

        stage.setScene(new Scene(scene));

        stage.show();


    }

    @FXML
    void onSaveBtnClick(ActionEvent event) throws IOException, SQLException {
        int id = 0;
        String title;
        String description;
        String location;
        String type;
        Instant start;
        Instant end;
        int customerId = 0;
        int userId = 0;
        int contactId = 0;
        ZoneId zone = ZoneId.of(String.valueOf(ZoneId.systemDefault()));
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").withZone(zone);
        boolean noOverlap = false;

        try {
            id = Integer.parseInt(idText.getText());
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

            customerId = custIdCombo.getValue().getCustomerID();
            userId = userIdCombo.getValue().getUserId();
            contactId = contactIdCombo.getValue().getContactId();

            noOverlap = Inventory.checkAppointmentOverlap(customerId, hourEST, minuteEST);
            if(!noOverlap){
                throw new Exception();
            }

            Appointments newAppt = new Appointments(id, title, description, location, type, start,
                    startLocal, end, endLocal, customerId, userId, contactId);
            Inventory.addAppt(newAppt);
            MySqlQuery.insertAppt(newAppt);

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            scene = FXMLLoader.load(getClass().getResource("/project/wgusoftware2project/apptCustomer.fxml"));

            stage.setScene(new Scene(scene));

            stage.show();
        } catch (Exception msg) {
            Alert alert;
            if (!noOverlap) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Appointment Warning");
                alert.setContentText("Appointment will overlap existing appointments " +
                                "for customer: " + customerId);
            }else {
                alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.getDialogPane().setPrefSize(400, 200);
                alert.setContentText("Appointments can only be scheduled on Weekdays from 8AM-10PM EST");
            }
            alert.showAndWait();
        }

    }

    @FXML
    void onContactIdComboClick(ActionEvent event) {
        int contactSelected = -1;
        for(Contacts contact: contactIdCombo.getItems()){
            if(contactSelected == contact.getContactId()){
                contactIdCombo.setValue(contact);
                break;
            }
        }
    }


    @FXML
    void onCustIdComboClick(ActionEvent event) {
        int contactSelected = -1;
        for(Customers customer: custIdCombo.getItems()){
            if(contactSelected == customer.getCustomerID()){
                custIdCombo.setValue(customer);
                break;
            }
        }
    }

    @FXML
    void onUserIdComboClick(ActionEvent event) {
        int contactSelected = -1;
        for(Users user: userIdCombo.getItems()){
            if(contactSelected == user.getUserId()){
                userIdCombo.setValue(user);
                break;
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Users> userList = FXCollections.observableArrayList();
        ObservableList<Customers> custList = FXCollections.observableArrayList();
        ObservableList<Contacts> contactList = FXCollections.observableArrayList();
        userList = Inventory.getAllUsers();
        custList = Inventory.getAllCusts();
        contactList = Inventory.getAllContacts();
        contactIdCombo.setItems(contactList);
        custIdCombo.setItems(custList);
        userIdCombo.setItems(userList);
        Random rand = new Random();
        int nextId = rand.nextInt(10000);
        idText.setText(String.valueOf(nextId));
        idText.setDisable(true);
        startText.setText("YYYY-MM-DD HH:MM");
        endText.setText("YYYY-MM-DD HH:MM");
    }
}
