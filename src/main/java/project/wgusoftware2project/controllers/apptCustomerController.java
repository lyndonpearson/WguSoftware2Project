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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import project.wgusoftware2project.helpers.FruitsQuery;
import project.wgusoftware2project.model.Appointments;
import project.wgusoftware2project.model.Customers;
import project.wgusoftware2project.model.Inventory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

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
    private Button updateApptBtn;

    @FXML
    private Button updateCustomerBtn;

    @FXML
    private TableColumn<?, ?> userIdCol;

    @FXML
    void onAddBtnClick(ActionEvent event) throws IOException {
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();

        scene = FXMLLoader.load(getClass().getResource("/project/wgusoftware2project/addAppt.fxml"));

        stage.setScene(new Scene(scene));

        stage.show();
    }

    @FXML
    void onDeleteBtnClick(ActionEvent event) {

    }

    @FXML
    void onUpdateBtnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/project/wgusoftware2project/changeAppt.fxml"));
        loader.load();
        changeApptController CAController = loader.getController();

        CAController.receiveInAppt((Appointments) apptTable.getSelectionModel().getSelectedItem());

        stage = (Stage)((Button) event.getSource()).getScene().getWindow();

        Parent scene = loader.getRoot();

        stage.setScene(new Scene(scene));

        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //ObservableList<Appointments> apptArrayList = FXCollections.observableArrayList();
        ObservableList<Customers> custArrayList = FXCollections.observableArrayList();

        try {
            //apptTable.setItems(FruitsQuery.populateAppts());
            //FruitsQuery.populateAppts();
            apptTable.setItems(Inventory.getAllAppts());
            custTable.setItems(FruitsQuery.populateCusts(custArrayList));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        idCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        endTable.setCellValueFactory(new PropertyValueFactory<>("end"));
        apptCustIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
        contactIdCol.setCellValueFactory(new PropertyValueFactory<>("contactID"));

        custIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        custName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        custAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        custPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        custState.setCellValueFactory(new PropertyValueFactory<>("state"));
        //CURRENTLY PRINTING DIVISION ID - USE THAT KEY TO RETRIEVE STATE
        custPostal.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
    }

}
