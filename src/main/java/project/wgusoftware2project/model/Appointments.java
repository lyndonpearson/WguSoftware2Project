package project.wgusoftware2project.model;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class Appointments {
    int appointmentID;
    String title;
    String description;
    String location;
    String type;
    Instant start;
    ZonedDateTime startLocal;
    Instant end;
    ZonedDateTime endLocal;
    int customerID;
    int userID;
    int contactID;

    public Appointments(int appointmentID, String title, String description, String location, String type, Instant start, ZonedDateTime startLocal, Instant end, ZonedDateTime endLocal, int customerID, int userID, int contactID) {
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

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
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

    public void setDescription(String description) {
        this.description = description;
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

    public ZonedDateTime getStartLocal() {
        return startLocal;
    }

    public void setStartLocal(ZonedDateTime startLocal) {
        this.startLocal = startLocal;
    }

    public Instant getEnd() {
        return end;
    }

    public void setEnd(Instant end) {
        this.end = end;
    }

    public ZonedDateTime getEndLocal() {
        return endLocal;
    }

    public void setEndLocal(ZonedDateTime endLocal) {
        this.endLocal = endLocal;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getContactID() {
        return contactID;
    }

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }
}
