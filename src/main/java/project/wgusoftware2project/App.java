package project.wgusoftware2project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import project.wgusoftware2project.helpers.FruitsQuery;
import project.wgusoftware2project.helpers.JDBC;
import project.wgusoftware2project.model.GeneralInterface;
import project.wgusoftware2project.model.TimeInterface;


import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException {

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        FruitsQuery.populateAppts();
        FruitsQuery.getStates();
        FruitsQuery.populateCusts();
        FruitsQuery.populateContacts();
        FruitsQuery.populateUsers();
        FruitsQuery.getCountries();

    }

    public static void main(String[] args) {
        JDBC.openConnection();

        launch();

        JDBC.closeConnection();
    }
}