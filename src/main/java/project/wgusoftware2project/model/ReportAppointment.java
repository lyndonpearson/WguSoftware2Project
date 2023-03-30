package project.wgusoftware2project.model;

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

    public String getAllApptTypes() {
        return allApptTypes;
    }

    public int getAllTotalAppts() {
        return allTotalAppts;
    }
}
