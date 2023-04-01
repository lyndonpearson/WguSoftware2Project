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

/** reportController class created with initialization capabilities.
 The controller interfaces with the comboBoxes, labels, text fields,
 and buttons shown in main.fxml
 */
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

    /** This method is called if the Cancel
     button is clicked. The window is switched to the apptCustomer.fxml file.
     @param event The event of the Cancel Button being clicked
     */
    @FXML
    void onBackBtnClick(ActionEvent event) throws IOException {
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();

        scene = FXMLLoader.load(getClass().getResource("/project/wgusoftware2project/apptCustomer.fxml"));

        stage.setScene(new Scene(scene));

        stage.show();
    }

    /** This method is called after if the ContactComboBox is
     clicked. The ComboBox value is set to the user selection.
     This selection is used to display all Appointments that
     match the selected Contact in the Contact TableView.
     @param event The event of the ComboBox being clicked
     */
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

    /** This method is called if the Logout button is
     clicked. The main.fxml view is loaded
     @param event The event of the Logout button being clicked
     */
    @FXML
    void onLogoutBtnClick(ActionEvent event) throws IOException {
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();

        scene = FXMLLoader.load(getClass().getResource("/project/wgusoftware2project/main.fxml"));

        stage.setScene(new Scene(scene));

        stage.show();
    }

    /** This method is called when the report.fxml file is loaded.
     The Contact ComboBox is populated with Inventory data.
     The ReportAppointment ComboBox is populated with Inventory data.
     The ReportDivision ComboBox is populated with Inventory data.
     @param location The location of the relative path of the root object.
     @param resources Resource used to localize the root object; can be null if absolute path.
     */
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

