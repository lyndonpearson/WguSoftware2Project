package project.wgusoftware2project.model;
import java.sql.Timestamp;
import java.time.Instant;

/** Appointments class created to store rows extracted from
 * Appointment Table data from MySQL database.
 */
public class Appointments {
    int appointmentID;
    String title;
    String description;
    String location;
    String type;
    Instant start;
    Timestamp startLocal;
    Instant end;
    Timestamp endLocal;
    int customerID;
    int userID;
    int contactID;

    /** Appointments constructor to store rows extracted from
     * Appointment Table data from MySQL database.
     @param appointmentID Integer storing appointment_ID column of MySQL table row.
     @param title String storing title column of MySQL table row.
     @param description String storing description column of MySQL table row.
     @param location String storing location column of MySQL table row.
     @param type String storing type column of MySQL table row.
     @param start Instant storing start column of MySQL table row.
     @param startLocal Timestamp storing start column of MySQL table row.
     @param end Instant storing end column of MySQL table row.
     @param endLocal Timestamp storing end column of MySQL table row.
     @param customerID Integer storing customer_ID column of MySQL table row.
     @param userID Integer storing user_ID column of MySQL table row.
     @param contactID Integer storing contact_ID column of MySQL table row.
     */
    public Appointments(int appointmentID, String title, String description, String location, String type, Instant start, Timestamp startLocal, Instant end, Timestamp endLocal, int customerID, int userID, int contactID) {
        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.startLocal = startLocal;
        this.end = end;
        this.endLocal = endLocal;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Instant getStart() {
        return start;
    }

    public void setStart(Instant start) {
        this.start = start;
    }

    public Timestamp getStartLocal() {
        return startLocal;
    }

    public Instant getEnd() {
        return end;
    }

    public Timestamp getEndLocal() {
        return endLocal;
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getUserID() {
        return userID;
    }

    public int getContactID() {
        return contactID;
    }

}
