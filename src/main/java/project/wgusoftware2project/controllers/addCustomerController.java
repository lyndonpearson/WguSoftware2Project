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
import project.wgusoftware2project.model.Countries;
import project.wgusoftware2project.model.Inventory;
import project.wgusoftware2project.model.States;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class addCustomerController implements Initializable {
    Stage stage;
    Parent scene;
    @FXML
    private Button cancelBtn;

    @FXML
    private ComboBox<Countries> countryCombo;

    @FXML
    private TextField descText;

    @FXML
    private TextField idText;

    @FXML
    private TextField locText;

    @FXML
    private Button saveBtn;

    @FXML
    private TextField startText;

    @FXML
    private ComboBox<States> stateCombo;

    @FXML
    private TextField titleText;

    @FXML
    void onCancelBtnClick(ActionEvent event) throws IOException {

        stage = (Stage)((Button) event.getSource()).getScene().getWindow();

        scene = FXMLLoader.load(getClass().getResource("/project/wgusoftware2project/apptCustomer.fxml"));

        stage.setScene(new Scene(scene));

        stage.show();
    }

    @FXML
    void onCountryComboClick(ActionEvent event) {
        int contactSelected = -1;
        for(Countries country: countryCombo.getItems()){
            if(contactSelected == country.getCountryId()){
                countryCombo.setValue(country);
                break;
            }
        }
        // #################################################################
        // #################################################################
        //NEED TO CREATE INVENTORY METHOD THAT FILTERS STATE/PROV BY COUNTRY
        // #################################################################
        // #################################################################
        // #################################################################

        ObservableList<States> stateList = FXCollections.observableArrayList();
        stateList = Inventory.getAllStates();
        stateCombo.setItems(stateList);
    }

    @FXML
    void onSaveBtnClick(ActionEvent event) {

    }

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
