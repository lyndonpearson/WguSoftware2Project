package project.wgusoftware2project.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import project.wgusoftware2project.helpers.FruitsQuery;
import project.wgusoftware2project.model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
        ZoneId zone = ZoneId.of("UTC");
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").withZone(zone);

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
        ZonedDateTime startLocal = start.atZone(ZoneId.systemDefault());
        ZonedDateTime endLocal = end.atZone(ZoneId.systemDefault());


        customerId = custIdCombo.getValue().getCustomerID();
        userId = userIdCombo.getValue().getUserId();
        contactId = contactIdCombo.getValue().getContactId();

        Appointments newAppt = new Appointments(id, title, description, location, type, start,
                startLocal, end, endLocal, customerId, userId, contactId);
        Inventory.addAppt(newAppt);
        if (FruitsQuery.insertAppt(newAppt) > 0){
            System.out.println("Successfully inserted into DB");
        } else {
            System.out.println("Failed to insert in DB");
        }

        stage = (Stage)((Button) event.getSource()).getScene().getWindow();

        scene = FXMLLoader.load(getClass().getResource("/project/wgusoftware2project/apptCustomer.fxml"));

        stage.setScene(new Scene(scene));

        stage.show();

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
    }
}
