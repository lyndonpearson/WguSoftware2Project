package project.wgusoftware2project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import project.wgusoftware2project.helpers.MySqlQuery;
import project.wgusoftware2project.helpers.JDBC;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;


public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException {

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        MySqlQuery.populateAppts();
        MySqlQuery.getStates();
        MySqlQuery.populateCusts();
        MySqlQuery.populateContacts();
        MySqlQuery.populateUsers();
        MySqlQuery.getCountries();

    }

    public static void main(String[] args) {
        JDBC.openConnection();

        launch();


        JDBC.closeConnection();
    }
}

//2023-03-30 10:30:00.0