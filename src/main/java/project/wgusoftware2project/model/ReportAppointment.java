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

    /** Method to return month in integer format of Appointment
     @return allApptMonths Integer storing allApptMonths field.
     */
    public int getAllApptMonths() {
        return allApptMonths;
    }

    /** Method to return type of Appointment in report
     @return allApptTypes Integer storing allApptTypes field.
     */
    public String getAllApptTypes() {
        return allApptTypes;
    }

    /** Method to return count of Appointments in Month
     @return allTotalAppts Integer storing allTotalAppts field.
     */
    public int getAllTotalAppts() {
        return allTotalAppts;
    }
}
