package project.wgusoftware2project.model;

/** ReportAppointment class created to store data grouped by
 * Appointment month and type.
 */
public class ReportAppointment {
    int allApptMonths;

    String allApptTypes;

    int allTotalAppts;

    /** ReportAppointment constructor.
     @param allApptMonths Integer storing Month of Appointment.
     @param allApptTypes String storing Type of Appointment.
     @param allTotalAppts Integer storing Total of Appointments occurring with
     given Month and Type.
     */
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
