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

    /** Method to return Appointment object's appointmentID field
     @return appointmentID Integer storing appointment_ID field.
     */
    public int getAppointmentID() {
        return appointmentID;
    }

    /** Method to return Appointment object's title field
     @return title String storing title field.
     */
    public String getTitle() {
        return title;
    }

    /** Method to set Appointment object's title field
     @param title String parameter assigned to title field.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /** Method to return Appointment object's description field
     @return description String storing description field.
     */
    public String getDescription() {
        return description;
    }

    /** Method to return Appointment object's location field
     @return location String storing location field.
     */
    public String getLocation() {
        return location;
    }

    /** Method to set Appointment object's location field
     @param location String parameter assigned to location field.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /** Method to return Appointment object's type field
     @return type String storing type field.
     */
    public String getType() {
        return type;
    }

    /** Method to set Appointment object's type field
     @param type String parameter assigned to type field.
     */
    public void setType(String type) {
        this.type = type;
    }

    /** Method to return Appointment object's start field
     @return start Instant storing start field.
     */
    public Instant getStart() {
        return start;
    }

    /** Method to set Appointment object's start field
     @param start Instant parameter assigned to start field.
     */
    public void setStart(Instant start) {
        this.start = start;
    }

    /** Method to return Appointment object's startLocal field
     @return startLocal Timestamp storing startLocal field.
     */
    public Timestamp getStartLocal() {
        return startLocal;
    }

    /** Method to return Appointment object's end field
     @return end Instant storing end field.
     */
    public Instant getEnd() {
        return end;
    }

    /** Method to return Appointment object's endLocal field
     @return endLocal Timestamp storing endLocal field.
     */
    public Timestamp getEndLocal() {
        return endLocal;
    }

    /** Method to return Appointment object's customerID field
     @return customerID Integer storing customerID field.
     */
    public int getCustomerID() {
        return customerID;
    }

    /** Method to return Appointment object's userID field
     @return userID Integer storing userID field.
     */
    public int getUserID() {
        return userID;
    }

    /** Method to return Appointment object's contactID field
     @return contactID Integer storing contactID field.
     */
    public int getContactID() {
        return contactID;
    }

}
