package project.wgusoftware2project.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import project.wgusoftware2project.model.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class reportController implements Initializable {
    Stage stage;
    Parent scene;
    @FXML
    private TableColumn<?, ?> apptCustIdCol;

    @FXML
    private Button backBtn;

    @FXML
    private TableView<Appointments> contactApptTable;

    @FXML
    private ComboBox<Contacts> contactComboBox;

    @FXML
    private TableColumn<?, ?> contactIdCol;

    @FXML
    private TableColumn<?, ?> descCol;

    @FXML
    private TableView<ReportDivision> divisionTable;

    @FXML
    private TableColumn<?, ?> endTable;

    @FXML
    private TableColumn<?, ?> idCol;

    @FXML
    private TableColumn<?, ?> locCol;

    @FXML
    private Button logoutBtn;

    @FXML
    private TableView<ReportAppointment> monthApptTable;

    @FXML
    private TableColumn<?, ?> startCol;

    @FXML
    private TableColumn<?, ?> titleCol;

    @FXML
    private TableColumn<?, ?> typeCol;

    @FXML
    private TableColumn<?, ?> userIdCol;

    @FXML
    private TableColumn<?, ?> apptMonthCol;

    @FXML
    private TableColumn<?, ?> apptTypeCol;

    @FXML
    private TableColumn<?, ?> divisionNameCol;

    @FXML
    private TableColumn<?, ?> totalApptCol;

    @FXML
    private TableColumn<?, ?> totalCustomersCol;

    @FXML
    void onBackBtnClick(ActionEvent event) throws IOException {
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();

        scene = FXMLLoader.load(getClass().getResource("/project/wgusoftware2project/apptCustomer.fxml"));

        stage.setScene(new Scene(scene));

        stage.show();
    }

    @FXML
    void onContactComboBoxClick(ActionEvent event) {
        int contactSelected = -1;
        for(Contacts contact: contactComboBox.getItems()){
            if(contactSelected == contact.getContactId()){
                contactComboBox.setValue(contact);
                break;
            }
        }
        ObservableList<Appointments> contactAppointments = Inventory.getAppointmentsByCustId(contactComboBox.getValue().getContactId());
        contactApptTable.setItems(contactAppointments);

        idCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("startLocal"));
        endTable.setCellValueFactory(new PropertyValueFactory<>("endLocal"));
        apptCustIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
        contactIdCol.setCellValueFactory(new PropertyValueFactory<>("contactID"));
    }

    @FXML
    void onLogoutBtnClick(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Contacts> contactList = Inventory.getAllContacts();
        contactComboBox.setItems(contactList);
        ObservableList<ReportAppointment> apptReport =  Inventory.appointmentsByMonth();
        monthApptTable.setItems(apptReport);
        ObservableList<ReportDivision> divisionList = Inventory.customersByDivisionName();
        divisionTable.setItems(divisionList);

        apptMonthCol.setCellValueFactory(new PropertyValueFactory<>("allApptMonths"));
        apptTypeCol.setCellValueFactory(new PropertyValueFactory<>("allApptTypes"));
        totalApptCol.setCellValueFactory(new PropertyValueFactory<>("allTotalAppts"));

        divisionNameCol.setCellValueFactory(new PropertyValueFactory<>("state"));
        totalCustomersCol.setCellValueFactory(new PropertyValueFactory<>("total"));
    }
}

