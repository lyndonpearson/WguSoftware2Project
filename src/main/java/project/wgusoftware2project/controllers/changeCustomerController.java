package project.wgusoftware2project.controllers;

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
import project.wgusoftware2project.helpers.MySqlQuery;
import project.wgusoftware2project.model.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class changeCustomerController implements Initializable {
    Stage stage;
    Parent scene;
    @FXML
    private Button cancelBtn;

    @FXML
    private ComboBox<Countries> countryCombo;
    @FXML
    private TextField addressText;
    @FXML
    private TextField idText;

    @FXML
    private TextField nameText;

    @FXML
    private TextField phoneText;

    @FXML
    private TextField postalText;
    @FXML
    private Button saveBtn;

    @FXML
    private ComboBox<States> stateCombo;

    @FXML
    void onCancelBtnClick(ActionEvent event) throws IOException {
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();

        scene = FXMLLoader.load(getClass().getResource("/project/wgusoftware2project/apptCustomer.fxml"));

        stage.setScene(new Scene(scene));

        stage.show();
    }

    @FXML
    void onCountryComboClick(ActionEvent event) {

    }

    @FXML
    void onSaveBtnClick(ActionEvent event) throws SQLException, IOException {
        int id = 0;
        String name;
        String address;
        String phone;
        String postal;
        String divisionId;
        String state;


        id = Integer.parseInt(idText.getText());
        name = nameText.getText();
        address = addressText.getText();
        phone = phoneText.getText();
        postal = postalText.getText();
        divisionId = Inventory.getDivisonIdFromState(String.valueOf(stateCombo.getValue()));
        state = String.valueOf(stateCombo.getValue());

        Customers newCust = new Customers(id, name, address, phone, postal, divisionId, state);
        Inventory.updateCustomer(id, newCust);
        MySqlQuery.updateCustomer(newCust);


        stage = (Stage)((Button) event.getSource()).getScene().getWindow();

        scene = FXMLLoader.load(getClass().getResource("/project/wgusoftware2project/apptCustomer.fxml"));

        stage.setScene(new Scene(scene));

        stage.show();
    }


    @FXML
    void onStateComboClick(ActionEvent event) {

    }

    public void receiveInCustomer(Customers inCustomer){

        idText.setText(String.valueOf(inCustomer.getCustomerID()));
        nameText.setText(inCustomer.getCustomerName());
        addressText.setText(inCustomer.getAddress());
        phoneText.setText(inCustomer.getPhone());
        postalText.setText(inCustomer.getPostalCode());
        stateCombo.setValue(Inventory.lookupState(Integer.parseInt(inCustomer.getDivisionId())));
        stateCombo.setItems(Inventory.getAllStates());
        int countryID = Inventory.countryIdByDivId(Integer.parseInt(inCustomer.getDivisionId()));
        countryCombo.setValue(Inventory.lookupCountry(countryID));
        countryCombo.setItems(Inventory.getAllCountries());

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idText.setDisable(true);
    }

}

