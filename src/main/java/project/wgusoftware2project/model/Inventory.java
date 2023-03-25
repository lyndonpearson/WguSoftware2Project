package project.wgusoftware2project.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import project.wgusoftware2project.App;

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

    public static void updateAppt(int apptID, Appointments selectedAppt){
        int loopIndex = -1;
        ObservableList<Appointments> tempOL;
        tempOL = Inventory.getAllAppts();
        for (Appointments appt : tempOL) {
            loopIndex++;
            if (appt.getAppointmentID() == apptID)
                Inventory.getAllAppts().set(loopIndex, selectedAppt);
        }
    }

    public static boolean deleteAppt(int apptID){
        int loopIndex = -1;
        for (Appointments appt : allAppts) {
            loopIndex++;
            if (appt.getAppointmentID() == apptID) {
                allAppts.remove(loopIndex);
                return true;
            }
        }
        return false;
    }
}
