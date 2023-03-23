package project.wgusoftware2project.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import project.wgusoftware2project.helpers.FruitsQuery;

import java.io.IOException;
import java.sql.SQLException;


public class mainController {
    Stage stage;
    Parent scene;
    @FXML
    private Button loginBtn;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField userNameTextField;

    @FXML
    void onLoginBtnClick(ActionEvent event) throws SQLException, IOException {
        System.out.println("UserName: " + userNameTextField.getText());
        System.out.println("Password: " + passwordTextField.getText());

        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

        scene = FXMLLoader.load(getClass().getResource("/project/wgusoftware2project/apptCustomer.fxml"));

        stage.setScene(new Scene(scene));

        stage.show();
    }

}

