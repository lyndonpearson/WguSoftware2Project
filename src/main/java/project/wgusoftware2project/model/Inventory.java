package project.wgusoftware2project.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import java.time.Instant;
import java.time.ZoneId;
import java.time.*;

/** Static class for containing and interfacing with all program models. */
public class Inventory {

    public static ObservableList<Appointments> allAppts = FXCollections.observableArrayList();
    public static ObservableList<Customers> allCusts = FXCollections.observableArrayList();
    public static ObservableList<Countries> allCountries = FXCollections.observableArrayList();
    public static ObservableList<States> allStates = FXCollections.observableArrayList();
    public static ObservableList<Users> allUsers = FXCollections.observableArrayList();
    public static ObservableList<Contacts> allContacts = FXCollections.observableArrayList();
    public static ObservableList<ReportAppointment> allReportAppointments = FXCollections.observableArrayList();

    public static ObservableList<ReportDivision> allReportDivisions = FXCollections.observableArrayList();

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

    /** Searches State ObservableList by stateId property and returns
     * state object if found - otherwise returns null.
     @param stateId Integer parameter and object property used to search ObservableList.
     @return state Object containing matching stateId property.
     */
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

    /** Searches User ObservableList by userId and password properties and returns
     * true if valid login - otherwise returns false.
     @param user String parameter for login
     @param password String parameter for password
     @return true if there is a user matching login and password parameter
     @return false if there is no matching user
     */
    public static boolean checkLogin(String user, String password){
        ObservableList<Users> userList = getAllUsers();
        for(Users userSearch: userList){
            if (userSearch.getUserName().equals(user) && userSearch.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    /** Searches Appointments ObservableList and
     * organizes them by month and number of occurrences.
     @return allReportAppointments ObservableList of ReportAppointment
     objects
     */
    public static ObservableList appointmentsByMonth() {
        allReportAppointments.clear();
        ObservableList<Appointments> tempOL;
        tempOL = Inventory.getAllAppts();

        for (Appointments appt : tempOL) {
            int count = 0;
            int month = appt.getStartLocal().getMonth() + 1;
            String type = appt.getType();
            for (Appointments apptSearch : tempOL) {
                int searchMonth = apptSearch.getStartLocal().getMonth() + 1;
                if (searchMonth == month && apptSearch.getType().equals(type)) {
                    count++;
                }
            }
            ReportAppointment addReport = new ReportAppointment(month, type, count);
            if (allReportAppointments.size() == 0) {
                allReportAppointments.add(addReport);
            } else {
                int duplicateflag = 0;
                int arraySize = allReportAppointments.size();
                for (int Index = 0; Index < arraySize; Index++) {
                    if (allReportAppointments.get(Index).getAllApptMonths() == addReport.getAllApptMonths()) {
                        if(allReportAppointments.get(Index).getAllApptTypes().equals(addReport.getAllApptTypes()))
                            duplicateflag++;
                    }
                }
                if (duplicateflag < 1) {
                    allReportAppointments.add(addReport);
                }

            }
        }
        return allReportAppointments;
    }

    /** Searches Customers ObservableList and
     * organizes them by state and number of occurrences.
     @return allReportDivisions ObservableList of ReportDivision
     objects
     */
    public static ObservableList customersByDivisionName(){
        allReportDivisions.clear();
        ObservableList<Customers> tempOL;
        tempOL = Inventory.getAllCusts();
        GeneralInterface stateCompare = (String state1, String state2) -> {return state1.equals(state2);};
        for (Customers customer : tempOL){
            int total = 0;
            for (Customers customerSearch : tempOL){
                if(customer.getState().equals(customerSearch.getState())){
                    total++;
                }
            }
            ReportDivision reportDivision = new ReportDivision(customer.getState(), total);
            if(allReportDivisions.size() == 0){
                allReportDivisions.add(reportDivision);
            }
            else{
                int duplicateflag = 0;
                int arraySize = allReportDivisions.size();
                for (int Index = 0; Index < arraySize; Index++){
                    /** GeneralInterface compareStates method implemented to check if
                     * two state strings are equal. Used here as a clear, concise method to check
                     * if a state already exists in a list of states and their associated customers.
                     * */
                    if(stateCompare.compareStates(allReportDivisions.get(Index).getState(), reportDivision.getState())){
                        duplicateflag++;
                    }
                }
                if(duplicateflag < 1){
                    allReportDivisions.add(reportDivision);
                }
            }
        }
        return allReportDivisions;
    }

    /** Searches Appointments ObservableList and
     * compares their starting time with the current system local time.
     * A warning is displayed if an appointment is starting within 15 minutes.
     */
    public static void checkAppointmentTimes(){

        ObservableList<Appointments> tempOL;
        tempOL = Inventory.getAllAppts();
        String locationZone = String.valueOf(ZoneId.systemDefault());
        for (Appointments appt: tempOL){
            LocalTime timeAppt = appt.getStart().atZone(ZoneId.of(locationZone)).toLocalTime();
            Instant nowInstant = Instant.now();

            int apptHour = appt.getStart().atZone(ZoneId.of(locationZone)).getHour();
            int apptMinute = appt.getStart().atZone(ZoneId.of(locationZone)).getMinute();

            int hour = nowInstant.atZone(ZoneId.of(locationZone)).getHour();
            int minute = nowInstant.atZone(ZoneId.of(locationZone)).getMinute();

            /** Interface TimeInterface method implemented to calculate
             * the current time in units of hours and compare to appointment time
             * in units of hours. Used here as a clear, concise method to check
             * if an upcoming appointment exists.
             * */
            TimeInterface timeTest = (int apptHourInput, int apptMinuteInput, int hourInput, int minuteInput) -> {
                double apptHours = apptHourInput + (apptMinuteInput / 60.0);
                double currentHours = hourInput + (minuteInput / 60.0);
                double result = apptHours - currentHours;
                return (0 <= result && result <= 0.25);
            };

            if (timeTest.timeCalculation(apptHour, apptMinute, hour, minute)){
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

    /** Searches Appointments ObservableList by customerID,
     * and starting time. If there is an appointment already in
     * the timeslot return true - otherwise returns false.
     @param customerId String parameter for customer Id
     @param hourStart Int parameter for starting hour
     @param minuteStart Int parameter for starting minute
     @return true if there is already an appointment in timeslot
     @return false if the timeslot is available
     */
    public static boolean checkAppointmentOverlap(int customerId, int hourStart, int minuteStart){
            for (Appointments appt : allAppts) {
                ZonedDateTime estEndDateTime = appt.getEnd().atZone(ZoneId.of("America/New_York"));
                int hourEndEST = estEndDateTime.getHour();
                int minuteEndEST = estEndDateTime.getMinute();
                if (appt.getCustomerID() == customerId && hourEndEST == hourStart && minuteStart < minuteEndEST) {
                    return false;
                }
            }
            return true;
            }


    /** Searches States ObservableList by String stateName,
     * If there is a match, return the matches divisionId
     * if not return null.
     @param stateName String parameter for stateName
     @return divisionId String of matching State
     @return null if no match
     */
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

    /** Searches States ObservableList by Int countryId,
     * Add matches to ObservableList of matching states.
     @param countryId Int parameter for countryId
     @return filteredList States Observable list of matches
     */
    public static ObservableList getStatesByCountry(int countryId){
        ObservableList<States> tempOL = allStates;
        ObservableList<States> filteredList = FXCollections.observableArrayList();
        for (States stateSearch : tempOL){
            if (stateSearch.getCountryId() == countryId){
                filteredList.add(stateSearch);
            }
        }
        return filteredList;
    }

    /** Searches Appointments ObservableList by Int contactId,
     * Add matches to ObservableList of matching Appointments.
     @param contactId Int parameter for contactId
     @return filteredList Appointments Observable list of matches
     */
    public static ObservableList getAppointmentsByCustId(int contactId){
        ObservableList<Appointments> tempOL;
        tempOL = Inventory.getAllAppts();
        ObservableList<Appointments> contactApptList = FXCollections.observableArrayList();
        for (Appointments appt : tempOL) {
            if (appt.getContactID() == contactId) {
                contactApptList.add(appt);
            }
        }
        return contactApptList;
    }

    /** Searches States ObservableList by Int divId,
     * If there is a matching state/prov return its countryId
     @param divId Int parameter for divisionId
     @return countryId Int of matching state/prov
     */
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

    /** Searches States ObservableList by String division,
     * If there is a match, return the matches divisionId
     @param division String parameter for state/prov
     @return divisionId Int of matching state/prov ID
     */
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

    /** Searches Countries ObservableList by Int countryId,
     * If there is a match, return the matching Countries Object
     * if not return null.
     @param countryId Int parameter for countryId
     @return country Countries object of matching country
     @return null if no match
     */
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

    /** Searches Appointments ObservableList by Int apptId,
     * and input Appointments object.
     * If there is a match, update the existing Appointment
     * with the new Appointment object.
     @param apptID int parameter for Appointment to be updated
     @param selectedAppt Appointments object used for updating
     */
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

    /** Searches Customers ObservableList by Int custId,
     * and input Customers object.
     * If there is a match, update the existing Customer
     * with the new Customers object.
     @param custId int parameter for Customers to be updated
     @param selectedCustomer Customers object used for updating
     */
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

    /** Searches Appointments ObservableList by Int apptId,
     * If there is a match, delete the matching Appointment
     @param apptID int parameter for Appointment to be removed
     @return true If successful in deletion of Appointment
     @return false if deletion operation was unsuccessful
     */
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

    /** Searches Appointments ObservableList by Int custId,
     * If there is a match, adds the matching Appointment
     * to deletion ObservableList. Deletes all matching
     * Appointments at conclusion
     @param custId int parameter for Customer of search
     */
    public static void deleteApptByCustId(int custId){
        int loopIndex = -1;
        ObservableList<Appointments> deleteAppts = FXCollections.observableArrayList();
        for (Appointments appt : allAppts) {
            loopIndex++;
            System.out.println(appt.getCustomerID());
            if (appt.getCustomerID() == custId) {
                deleteAppts.add(appt);
            }
        }
        allAppts.removeAll(deleteAppts);
    }

    /** Searches Customers ObservableList by Int custId,
     * If there is a match, delete the matching Customer
     @param custId int parameter for Customer to be removed
     @return true If successful in deletion of Customer
     @return false if deletion operation was unsuccessful
     */
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
