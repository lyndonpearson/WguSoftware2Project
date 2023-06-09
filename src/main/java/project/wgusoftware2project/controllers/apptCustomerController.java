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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import project.wgusoftware2project.helpers.MySqlQuery;
import project.wgusoftware2project.model.Appointments;
import project.wgusoftware2project.model.Customers;
import project.wgusoftware2project.model.Inventory;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.IsoFields;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

/** apptCustomerController class created with initialization capabilities.
 The controller interfaces with the comboBoxes, labels, text fields,
 and buttons show in apptCustomer.fxml
 */
public class apptCustomerController implements Initializable {
    Stage stage;
    Parent scene;
    @FXML
    private AnchorPane addApptBtn;

    @FXML
    private Button addCustomerBtn;

    @FXML
    private RadioButton allBtn;

    @FXML
    private TableColumn<?, ?> custAddress;

    @FXML
    private TableColumn<?, ?> custIdCol;

    @FXML
    private TableColumn<?, ?> custName;

    @FXML
    private TableColumn<?, ?> custPhone;

    @FXML
    private TableColumn<?, ?> custPostal;

    @FXML
    private TableColumn<?, ?> custState;

    @FXML
    private TableView<Customers> custTable;

    @FXML
    private TableView<Appointments> apptTable;

    @FXML
    private TableColumn<?, ?> contactIdCol;

    @FXML
    private RadioButton currentMonthBtn;

    @FXML
    private ToggleGroup apptViewGroup;

    @FXML
    private RadioButton currentWeekBtn;

    @FXML
    private Button deleteApptBtn;

    @FXML
    private Button deleteCustomerBtn;

    @FXML
    private TableColumn<?, ?> descCol;

    @FXML
    private TableColumn<?, ?> endTable;

    @FXML
    private TableColumn<?, ?> idCol;

    @FXML
    private TableColumn<?, ?> apptCustIdCol;

    @FXML
    private TableColumn<?, ?> locCol;

    @FXML
    private Button logoutBtn;

    @FXML
    private Button reportsBtn;

    @FXML
    private TableColumn<?, ?> startCol;

    @FXML
    private TableColumn<?, ?> titleCol;

    @FXML
    private TableColumn<?, ?> typeCol;

    @FXML
    private TableColumn<?, ?> divisionIdCol;

    @FXML
    private Button updateApptBtn;

    @FXML
    private Button updateCustomerBtn;

    @FXML
    private TableColumn<?, ?> userIdCol;

    /** This method is called after if the Add button is
     clicked. The addAppt.fxml view is loaded
     @param event The event of the Add button being clicked
     */
    @FXML
    void onAddBtnClick(ActionEvent event) throws IOException {
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();

        scene = FXMLLoader.load(getClass().getResource("/project/wgusoftware2project/addAppt.fxml"));

        stage.setScene(new Scene(scene));

        stage.show();
    }

    /** This method is called after if the Delete button is
     clicked. The selected Appointment from TableView is removed
     from Inventory and MySQL database. An alert is created
     either confirming the outcome of the operation.
     @param event The event of the Delete button being clicked
     */
    @FXML
    void onDeleteBtnClick(ActionEvent event) throws SQLException {
        Appointments selectedAppt = apptTable.getSelectionModel(). getSelectedItem();
        if(Inventory.deleteAppt(selectedAppt.getAppointmentID())) {
            apptTable.getItems().remove(selectedAppt);
            MySqlQuery.deleteAppt(selectedAppt.getAppointmentID());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Deleted Appointment");
            alert.setContentText("Appointment ID# " + selectedAppt.getAppointmentID() + " of type " +
                    selectedAppt.getType() + " has been canceled.");
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Deletion Error");
            alert.setContentText("ERROR! Appointment was not canceled");
            alert.showAndWait();
        }
    }

    /** This method is called after if the Update button is
     clicked. The selected Appointment from TableView is upated
     in Inventory and MySQL database. If no Appointment is selected,
     an alert is displayed.
     @param event The event of the Update button being clicked
     */
    @FXML
    void onUpdateBtnClick(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/project/wgusoftware2project/changeAppt.fxml"));
        loader.load();
        changeApptController CAController = loader.getController();
        try {
            if (apptTable.getSelectionModel().getSelectedItem() == null) {
                throw new Exception();
            }

            CAController.receiveInAppt((Appointments) apptTable.getSelectionModel().getSelectedItem());

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            Parent scene = loader.getRoot();

            stage.setScene(new Scene(scene));

            stage.show();
        } catch (Exception msg){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Please select an appointment to update");
            alert.showAndWait();
        }
    }

    /** This method is called if the Update Customer button is
     clicked. The selected Customer from TableView is upated
     in Inventory and MySQL database. If no Customer is selected,
     an alert is displayed.
     @param event The event of the Update button being clicked
     */
    @FXML
    void onUpdateCustClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/project/wgusoftware2project/changeCustomer.fxml"));
        loader.load();
        changeCustomerController CCController = loader.getController();
        try {
            CCController.receiveInCustomer((Customers) custTable.getSelectionModel().getSelectedItem());

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            Parent scene = loader.getRoot();

            stage.setScene(new Scene(scene));

            stage.show();
        } catch (Exception msg){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Please select a customer to update");
            alert.showAndWait();
        }
    }


    /** This method is called when the apptCustomer.fxml file is loaded.
     The Appointments TableView is populated with existing data from Inventory.
     The Customers TableView is populated with existing data from Inventory.
     @param url The location of the relative path of the root object.
     @param resourceBundle Resource used to localize the root object; can be null if absolute path.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allBtn.setSelected(true);

        apptTable.setItems(Inventory.getAllAppts());
        custTable.setItems(Inventory.getAllCusts());

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

        custIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        custName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        custAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        custPostal.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        custPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        divisionIdCol.setCellValueFactory(new PropertyValueFactory<>("divisionId"));
        custState.setCellValueFactory(new PropertyValueFactory<>("state"));


    }

    /** This method is called if the Delete Customer button is
     clicked. The selected Customer from TableView is removed
     from Inventory and MySQL database and a confirmation alert
     is displayed. The Appointments TableView is updated.
     If no Customer is selected, an alert is displayed.
     @param event The event of the Update button being clicked
     */
    @FXML
    void onDeleteCustClick(ActionEvent event) throws SQLException {
        Customers selectedCust = custTable.getSelectionModel(). getSelectedItem();
        if(Inventory.deleteCust(selectedCust.getCustomerID())) {
            custTable.getItems().remove(selectedCust);
            Inventory.deleteApptByCustId(selectedCust.getCustomerID());
            MySqlQuery.deleteCust(selectedCust.getCustomerID());
            apptTable.setItems(Inventory.getAllAppts());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Deleted Customer");
            alert.setContentText("Customer ID# " + selectedCust.getCustomerID() + " has been deleted " +
                    "along with all associated appointments.");
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Deletion Error");
            alert.setContentText("ERROR! Customer was not deleted");
            alert.showAndWait();
        }
    }

    /** This method is called if the Add button is
     clicked. The addCustomer.fxml view is loaded
     @param event The event of the Add button being clicked
     */
    @FXML
    void onAddCustBtnClick(ActionEvent event) throws IOException {
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();

        scene = FXMLLoader.load(getClass().getResource("/project/wgusoftware2project/addCustomer.fxml"));

        stage.setScene(new Scene(scene));

        stage.show();
    }

    /** This method is called if the All radio button is
     clicked. The Appointment TableView is populated with
     all Appointments in inventory
     @param event The event of the All radio button being clicked
     */
    @FXML
    void onAllClick(ActionEvent event) {
        apptTable.setItems(Inventory.getAllAppts());
    }

    /** This method is called if the Month radio button is
     clicked. The Appointment TableView is populated with
     Appointments occurring in the current month.
     @param event The event of the Month radio button being clicked
     */
    @FXML
    void onCurrentMonthClick(ActionEvent event) {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        ObservableList<Appointments> items = FXCollections.observableArrayList();
        ObservableList<Appointments> allAppts = Inventory.getAllAppts();
        for (Appointments appt: allAppts){
            int apptMonth = appt.getStart().atZone(ZoneId.systemDefault()).getMonth().getValue();
            int apptYear = appt.getStart().atZone(ZoneId.systemDefault()).getYear();
            if (apptMonth == month && apptYear == year){
                items.add(appt);
            }
        }
        apptTable.setItems(items);
    }

    /** This method is called if the Week radio button is
     clicked. The Appointment TableView is populated with
     Appointments occurring in the current week.
     @param event The event of the Week radio button being clicked
     */
    @FXML
    void onCurrentWeekClick(ActionEvent event) {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week = cal.get(Calendar.WEEK_OF_YEAR);
        int year = cal.get(Calendar.YEAR);
        ObservableList<Appointments> items = FXCollections.observableArrayList();
        ObservableList<Appointments> allAppts = Inventory.getAllAppts();
        for (Appointments appt: allAppts){
            ZonedDateTime apptWeek = ZonedDateTime.ofInstant(appt.getStart(), ZoneId.systemDefault());
            int apptWeekInt = apptWeek.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
            int apptYear = appt.getStart().atZone(ZoneId.systemDefault()).getYear();
            if (apptWeekInt == week && apptYear == year){
                items.add(appt);
            }
        }
        apptTable.setItems(items);
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

    /** This method is called if the Reports button is
     clicked. The report.fxml view is loaded
     @param event The event of the Report button being clicked
     */
    @FXML
    void onReportsBtnClick(ActionEvent event) throws IOException {
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();

        scene = FXMLLoader.load(getClass().getResource("/project/wgusoftware2project/report.fxml"));

        stage.setScene(new Scene(scene));

        stage.show();
    }

}
