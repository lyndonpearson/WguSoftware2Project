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
import project.wgusoftware2project.helpers.MySqlQuery;
import project.wgusoftware2project.model.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;

/** addCustomerController class created with initialization capabilities.
 The controller interfaces with the comboBoxes, labels, text fields,
 and buttons show in addCustomer.fxml
 */
public class addCustomerController implements Initializable {
    Stage stage;
    Parent scene;
    @FXML
    private Button cancelBtn;

    @FXML
    private ComboBox<Countries> countryCombo;

    @FXML
    private TextField nameText;

    @FXML
    private TextField idText;

    @FXML
    private TextField addressText;

    @FXML
    private Button saveBtn;

    @FXML
    private TextField phoneText;

    @FXML
    private ComboBox<States> stateCombo;

    @FXML
    private TextField postalText;

    /** This method is called after if the Cancel
     button is clicked. The window is switched to the apptCustomer.fxml file.
     @param event The event of the Cancel Button being clicked
     */
    @FXML
    void onCancelBtnClick(ActionEvent event) throws IOException {

        stage = (Stage)((Button) event.getSource()).getScene().getWindow();

        scene = FXMLLoader.load(getClass().getResource("/project/wgusoftware2project/apptCustomer.fxml"));

        stage.setScene(new Scene(scene));

        stage.show();
    }

    /** This method is called after if the CountryComboBox is
     clicked. The ComboBox value is set to the user selection.
     @param event The event of the ComboBox being clicked
     */
    @FXML
    void onCountryComboClick(ActionEvent event) {
        int contactSelected = -1;
        for(Countries country: countryCombo.getItems()){
            if(contactSelected == country.getCountryId()){
                countryCombo.setValue(country);
                break;
            }
        }

        ObservableList<States> stateList = FXCollections.observableArrayList();
        stateList = Inventory.getStatesByCountry(countryCombo.getValue().getCountryId());
        stateCombo.setItems(stateList);
    }

    /** This method is called if the Save
     button is selected. The TextFields are parsed, a new Customer object
     is created and added to the Inventory ObservableList as well
     as the mySQL database. The window is then returned to the apptCustomer.fxml file.
     @param event The event of the Save button being clicked
     */
    @FXML
    void onSaveBtnClick(ActionEvent event) throws IOException, SQLException {
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
        divisionId = String.valueOf(stateCombo.getValue());
        state = String.valueOf(stateCombo.getValue());

        Customers newCust = new Customers(id, name, address, phone, postal, divisionId, state);
        Inventory.addCust(newCust);
        MySqlQuery.insertCust(newCust);

        stage = (Stage)((Button) event.getSource()).getScene().getWindow();

        scene = FXMLLoader.load(getClass().getResource("/project/wgusoftware2project/apptCustomer.fxml"));

        stage.setScene(new Scene(scene));

        stage.show();
    }

    /** This method is called after if the StateComboBox is
     clicked. The ComboBox value is set to the user selection.
     @param event The event of the ComboBox being clicked
     */
    @FXML
    void onStateComboClick(ActionEvent event) {

        int contactSelected = -1;
        for(States state: stateCombo.getItems()){
            if(contactSelected == state.getCountryId()){
                stateCombo.setValue(state);
                break;
            }
        }
    }

    /** This method is called when the addCustomer.fxml file is loaded.
     The ID text field is disabled and populated with a random value.
     The Country ComboBox is populated with existing data from Inventory.
     @param location The location of the relative path of the root object.
     @param resources Resource used to localize the root object; can be null if absolute path.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Countries> countryList = FXCollections.observableArrayList();
        countryList = Inventory.getAllCountries();
        countryCombo.setItems(countryList);

        Random rand = new Random();
        int nextId = rand.nextInt(10000);
        idText.setText(String.valueOf(nextId));
        idText.setDisable(true);
    }

}
