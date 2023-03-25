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
import project.wgusoftware2project.App;
import project.wgusoftware2project.helpers.FruitsQuery;
import project.wgusoftware2project.model.Appointments;
import project.wgusoftware2project.model.Inventory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class addApptController implements Initializable {
    Stage stage;
    Parent scene;

    @FXML
    public ComboBox<Appointments> contactIdCombo;
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
        int id = 0;
        String title;
        String description;
        String location;
        String type;
        String start;
        String end;
        int customerId = 0;
        int userId = 0;
        int contactId = 0;

        id = Integer.parseInt(idText.getText());
        title = titleText.getText();
        description = descText.getText();
        location = locText.getText();
        type = typeText.getText();
        start = startText.getText();
        end = startText.getText();
        customerId = Integer.parseInt(customerIdText.getText());
        userId = Integer.parseInt(userIdText.getText());
        contactId = contactIdCombo.getValue().getContactID();

        Appointments newAppt = new Appointments(id, title, description, location, type, start, end, customerId, userId, contactId);
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
        for(Appointments appt: contactIdCombo.getItems()){
            if(contactSelected == appt.getContactID()){
                contactIdCombo.setValue(appt);
                break;
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Appointments> apptList = FXCollections.observableArrayList();
        apptList = Inventory.getAllAppts();
        contactIdCombo.setItems(apptList);
    }
}
