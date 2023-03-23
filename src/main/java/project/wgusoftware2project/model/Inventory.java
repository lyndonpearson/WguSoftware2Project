package project.wgusoftware2project.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

public class Inventory {

    public static ObservableList<Appointments> allAppts = FXCollections.observableArrayList();

    public static void addAppt(Appointments addAppt){
        allAppts.add(addAppt);
    }

    public static ObservableList getAllAppts(){
        return allAppts;
    }

    public static void clearAllAppts() {
        allAppts.clear();
    }
}
