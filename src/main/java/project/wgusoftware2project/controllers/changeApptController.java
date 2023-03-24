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
        String start;
        String end;
        int customerID;
        int userID;
        int contactID;


        appointmentID = Integer.parseInt(idText.getText());
        title = titleText.getText();
        description = descText.getText();
        location = locText.getText();
        type = typeText.getText();
        start = startText.getText();
        end = endText.getText();
        customerID = Integer.parseInt(customerIdText.getText());
        userID = Integer.parseInt(userIdText.getText());
        contactID = Integer.parseInt(contactIdText.getText());

        Appointments newAppt = new Appointments(appointmentID, title, description, location, type, start,
                end, customerID, userID, contactID);

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
        startText.setText(String.valueOf(inAppt.getStart()));
        endText.setText(inAppt.getEnd());
        customerIdText.setText(String.valueOf(inAppt.getCustomerID()));
        userIdText.setText(String.valueOf(inAppt.getUserID()));
        contactIdText.setText(String.valueOf(inAppt.getContactID()));



    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idText.setDisable(true);
    }
}


