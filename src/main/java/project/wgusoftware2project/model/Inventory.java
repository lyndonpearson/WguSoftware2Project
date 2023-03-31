package project.wgusoftware2project.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.time.Instant;
import java.time.ZoneId;
import java.time.*;

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

    public static boolean checkLogin(String user, String password){
        ObservableList<Users> userList = getAllUsers();
        for(Users userSearch: userList){
            if (userSearch.getUserName().equals(user) && userSearch.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

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
                deleteAppts.add(appt);
            }
        }
        allAppts.removeAll(deleteAppts);
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
