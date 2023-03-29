package project.wgusoftware2project.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ReportAppointment {
    int allApptMonths;

    String allApptTypes;

    int allTotalAppts;

    public ReportAppointment(int allApptMonths, String allApptTypes, int allTotalAppts) {
        this.allApptMonths = allApptMonths;
        this.allApptTypes = allApptTypes;
        this.allTotalAppts = allTotalAppts;
    }

    public int getAllApptMonths() {
        return allApptMonths;
    }

    public void setAllApptMonths(int allApptMonths) {
        this.allApptMonths = allApptMonths;
    }

    public String getAllApptTypes() {
        return allApptTypes;
    }

    public void setAllApptTypes(String allApptTypes) {
        this.allApptTypes = allApptTypes;
    }

    public int getAllTotalAppts() {
        return allTotalAppts;
    }

    public void setAllTotalAppts(int allTotalAppts) {
        this.allTotalAppts = allTotalAppts;
    }
}
