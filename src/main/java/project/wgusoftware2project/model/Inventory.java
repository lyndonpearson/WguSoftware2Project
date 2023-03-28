package project.wgusoftware2project.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import project.wgusoftware2project.App;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.*;
import java.time.temporal.ChronoField;

import static java.lang.Math.abs;

public class Inventory {

    public static ObservableList<Appointments> allAppts = FXCollections.observableArrayList();
    public static ObservableList<Customers> allCusts = FXCollections.observableArrayList();
    public static ObservableList<Countries> allCountries = FXCollections.observableArrayList();
    public static ObservableList<States> allStates = FXCollections.observableArrayList();
    public static ObservableList<Users> allUsers = FXCollections.observableArrayList();
    public static ObservableList<Contacts> allContacts = FXCollections.observableArrayList();

    public static void addAppt(Appointments addAppt){
        allAppts.add(addAppt);
    }

    public static void addCust(Customers addCust){
        allCusts.add(addCust);
    }

    public static void addCountry(Countries addCountry){
        allCountries.add(addCountry);
    }

    public static void addState(States addState){
        allStates.add(addState);
    }

    public static void addUser(Users addUser){
        allUsers.add(addUser);
    }

    public static void addContact(Contacts addContact){
        allContacts.add(addContact);
    }

    public static ObservableList getAllAppts(){
        return allAppts;
    }
    public static ObservableList getAllCusts(){
        return allCusts;
    }

    public static ObservableList getAllCountries(){
        return allCountries;
    }
    public static ObservableList getAllStates(){
        return allStates;
    }
    public static ObservableList getAllUsers(){
        return allUsers;
    }

    public static ObservableList getAllContacts(){
        return allContacts;
    }

    public static void clearAllAppts() {
        allAppts.clear();
    }

    public static States lookupState(int stateId){
        ObservableList<States> tempOL;
        tempOL = Inventory.getAllStates();
        for (States state : tempOL) {
            if (state.getDivisionId() == stateId) {
                return state;
            }
        }
        return null;
    }

    public static void checkAppointmentTimes(){

        // CURRENTLY TIME STORED IN TABLEVIEW IS IN UTC AND CALCULATIONS
        // RETURN 15 MINUTE WARNING FOR UTC VALUES. SHOULD PROBABLY
        // BE EITHER SYSTEM LOCAL TIME OR EST

        ObservableList<Appointments> tempOL;
        tempOL = Inventory.getAllAppts();
        String locationZone = String.valueOf(ZoneId.systemDefault());
        for (Appointments appt: tempOL){
            LocalTime timeAppt = appt.getStart().atZone(ZoneId.of(locationZone)).toLocalTime();
            Instant nowInstant = Instant.now();
            LocalTime timeNow = nowInstant.atZone(ZoneId.of(locationZone)).toLocalTime();


            int apptHour = appt.getStart().atZone(ZoneId.of(locationZone)).getHour();
            // get minute
            int apptMinute = appt.getStart().atZone(ZoneId.of(locationZone)).getMinute();
            // get second
            int apptSecond = appt.getStart().atZone(ZoneId.of(locationZone)).getSecond();


            // get hour
            int hour = nowInstant.atZone(ZoneId.of(locationZone)).getHour();
            // get minute
            int minute = nowInstant.atZone(ZoneId.of(locationZone)).getMinute();
            // get second
            int second = nowInstant.atZone(ZoneId.of(locationZone)).getSecond();

            if ((apptHour - hour) == 0 && abs(apptMinute - minute) <= 15){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Appointment Warning");
                alert.setContentText("Appointment within 15 minutes! \n " +
                        "Appointment ID: " + appt.getAppointmentID() +
                        " Appointment time: " + timeAppt);
                alert.showAndWait();
                return;
            }

        }
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No Appointments Soon");
        alert.setContentText("No upcoming appointments");
        alert.showAndWait();
    }



    public static String getDivisonIdFromState(String stateName){
        ObservableList<States> tempOL;
        tempOL = Inventory.getAllStates();
        for (States state : tempOL) {
            if (state.getDivision() == stateName) {
                return String.valueOf(state.getDivisionId());
            }
        }
        return null;
    }

    public static int countryIdByDivId(int divId){
        ObservableList<States> tempOL;
        tempOL = Inventory.getAllStates();
        for (States state : tempOL) {
            if (state.getDivisionId() == divId) {
                return state.getCountryId();
            }
        }
        return 0;
    }

    public static int divisionIdByDivision(String division){
        ObservableList<States> tempOL;
        tempOL = Inventory.getAllStates();
        for (States state : tempOL) {
            if (state.getDivision() == division) {
                return state.divisionId;
            }
        }
        return 0;
    }

    public static Countries lookupCountry(int countryId){
        ObservableList<Countries> tempOL;
        tempOL = Inventory.getAllCountries();
        for (Countries country : tempOL) {
            if (country.getCountryId() == countryId) {
                return country;
            }
        }
        return null;
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

    public static void updateCustomer(int custId, Customers selectedCustomer){
        int loopIndex = -1;
        ObservableList<Customers> tempOL;
        tempOL = Inventory.getAllCusts();
        for (Customers customer : tempOL) {
            loopIndex++;
            if (customer.getCustomerID() == custId)
                Inventory.getAllCusts().set(loopIndex, selectedCustomer);
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

    public static void deleteApptByCustId(int custId){
        int loopIndex = -1;
        ObservableList<Appointments> deleteAppts = FXCollections.observableArrayList();
        for (Appointments appt : allAppts) {
            loopIndex++;
            System.out.println(appt.getCustomerID());
            if (appt.getCustomerID() == custId) {
                //allAppts.remove(loopIndex);
                deleteAppts.add(appt);
            }
        }
        System.out.println("Delete array size at end is: " + deleteAppts.size());
        allAppts.removeAll(deleteAppts);
        System.out.println("Array size at end is: " + allAppts.size());
    }

    public static boolean deleteCust(int custId){
        int loopIndex = -1;
        for (Customers cust : allCusts) {
            loopIndex++;
            if (cust.getCustomerID() == custId) {
                allCusts.remove(loopIndex);
                return true;
            }
        }
        return false;
    }
}
