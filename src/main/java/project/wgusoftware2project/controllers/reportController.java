package project.wgusoftware2project.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class reportController {
    Stage stage;
    Parent scene;
    @FXML
    private TableColumn<?, ?> apptCustIdCol;

    @FXML
    private Button backBtn;

    @FXML
    private TableView<?> contactApptTable;

    @FXML
    private ComboBox<?> contactComboBox;

    @FXML
    private TableColumn<?, ?> contactIdCol;

    @FXML
    private TableColumn<?, ?> descCol;

    @FXML
    private TableView<?> divisionTable;

    @FXML
    private TableColumn<?, ?> endTable;

    @FXML
    private TableColumn<?, ?> idCol;

    @FXML
    private TableColumn<?, ?> locCol;

    @FXML
    private Button logoutBtn;

    @FXML
    private TableView<?> monthApptTable;

    @FXML
    private TableColumn<?, ?> startCol;

    @FXML
    private TableColumn<?, ?> titleCol;

    @FXML
    private TableColumn<?, ?> typeCol;

    @FXML
    private TableColumn<?, ?> userIdCol;

    @FXML
    void onBackBtnClick(ActionEvent event) throws IOException {
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();

        scene = FXMLLoader.load(getClass().getResource("/project/wgusoftware2project/apptCustomer.fxml"));

        stage.setScene(new Scene(scene));

        stage.show();
    }

    @FXML
    void onContactComboBoxClick(ActionEvent event) {

    }

    @FXML
    void onLogoutBtnClick(ActionEvent event) {

    }

}

