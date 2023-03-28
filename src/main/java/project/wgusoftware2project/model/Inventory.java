package project.wgusoftware2project.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
