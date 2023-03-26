package project.wgusoftware2project.helpers;


import javafx.collections.ObservableList;
import project.wgusoftware2project.App;
import project.wgusoftware2project.model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public abstract class FruitsQuery {

    public static void populateAppts() throws SQLException {

        String sql = "SELECT * FROM APPOINTMENTS";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int apptID = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
//            String start = rs.getString("Start");
//            String end = rs.getString("End");
            Instant start = rs.getTimestamp("Start").toInstant();
            Instant end = rs.getTimestamp("End").toInstant();
            int customerID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            int contactID = rs.getInt("Contact_ID");
            Inventory.addAppt(new Appointments(apptID, title, description, location, type, start,
                    end, customerID, userID, contactID));
        }
    }


//    public static ObservableList<Customers> populateCusts(ObservableList<Customers> inputArrayList) throws SQLException {
//
//        String sql = "SELECT * FROM CUSTOMERS";
//        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
//        ResultSet rs = ps.executeQuery();
//        while(rs.next()){
//            int custID = rs.getInt("Customer_ID");
//            String custName = rs.getString("Customer_Name");
//            String custAddress = rs.getString("Address");
//            String custPostal = rs.getString("Postal_Code");
//            String custPhone = rs.getString("Phone");
//            String custState = rs.getString("Division_ID");
//            inputArrayList.add(new Customers(custID, custName, custAddress, custPostal, custPhone, custState));
//        }
//        return inputArrayList;
//    }

    public static void populateCusts() throws SQLException {

        String sql = "SELECT * FROM CUSTOMERS";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int custID = rs.getInt("Customer_ID");
            String custName = rs.getString("Customer_Name");
            String custAddress = rs.getString("Address");
            String custPostal = rs.getString("Postal_Code");
            String custPhone = rs.getString("Phone");
            String custState = rs.getString("Division_ID");
            Inventory.addCust(new Customers(custID, custName, custAddress, custPostal, custPhone, custState));
        }
    }

    public static int insertAppt(Appointments addAppt) throws SQLException {
        String sql = "INSERT INTO APPOINTMENTS (Appointment_ID, Title, Description, Location, " +
                "Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = JDBC.connection.prepareStatement(sql);

        //   WORKING WITH VALID FOREIGN KEYS (CUSTOMER/USER/CONTACT IDS) ONLY
        ps.setInt(1, addAppt.getAppointmentID());
        ps.setString(2, addAppt.getTitle());
        ps.setString(3, addAppt.getDescription());
        ps.setString(4, addAppt.getLocation());
        ps.setString(5, addAppt.getType());

        Timestamp timestampStart = Timestamp.from(addAppt.getStart());
        ps.setTimestamp(6, timestampStart);
        Timestamp timestampEnd = Timestamp.from(addAppt.getEnd());
        ps.setTimestamp(7, timestampEnd);

        // NEED TO INCORPORATE BELOW AS PRIMARY KEYS/FOREIGN KEYS
        ps.setInt(8, addAppt.getCustomerID());
        ps.setInt(9, addAppt.getUserID());
        ps.setInt(10, addAppt.getUserID());

        int rowsAffected = ps.executeUpdate();
        return rowsAffected;

    }

    public static int insertCust(Customers addCust) throws SQLException {
        String sql = "INSERT INTO CUSTOMERS (CUSTOMER_ID, CUSTOMER_NAME, ADDRESS, POSTAL_CODE, " +
                "PHONE, DIVISION_ID) VALUES(?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = JDBC.connection.prepareStatement(sql);

        ps.setInt(1, addCust.getCustomerID());
        ps.setString(2, addCust.getCustomerName());
        ps.setString(3, addCust.getAddress());
        ps.setString(4, addCust.getPostalCode());
        ps.setString(5, addCust.getPhone());
        ps.setInt(6, Inventory.divisionIdByDivision(addCust.getDivisionId()));

        int rowsAffected = ps.executeUpdate();
        return rowsAffected;

    }

    public static int updateAppt(Appointments addAppt) throws SQLException {
        String sql = "UPDATE APPOINTMENTS SET Title = ?, Description = ?, Location = ?, " +
                "Type = ?, Start = ?, End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE APPOINTMENT_ID = " +
                String.valueOf(addAppt.getAppointmentID());

        PreparedStatement ps = JDBC.connection.prepareStatement(sql);

        //   WORKING WITH VALID FOREIGN KEYS (CUSTOMER/USER/CONTACT IDS) ONLY
        ps.setString(1, addAppt.getTitle());
        ps.setString(2, addAppt.getDescription());
        ps.setString(3, addAppt.getLocation());
        ps.setString(4, addAppt.getType());

        Timestamp timestampStart = Timestamp.from(addAppt.getStart());
        ps.setTimestamp(5, timestampStart);
        Timestamp timestampEnd = Timestamp.from(addAppt.getEnd());
        ps.setTimestamp(6, timestampEnd);

        // NEED TO INCORPORATE BELOW AS PRIMARY KEYS/FOREIGN KEYS
        ps.setInt(7, addAppt.getCustomerID());
        ps.setInt(8, addAppt.getUserID());
        ps.setInt(9, addAppt.getUserID());

        int rowsAffected = ps.executeUpdate();
        return rowsAffected;

    }

    public static int deleteAppt(int apptId) throws SQLException {
        String sql = "DELETE FROM APPOINTMENTS WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, apptId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    public static int deleteCust(int custId) throws SQLException {
        String sql = "DELETE FROM CUSTOMERS WHERE CUSTOMER_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, custId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    public static void getCountries() throws SQLException {

        String sql = "SELECT * FROM COUNTRIES";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int countryId = rs.getInt("Country_ID");
            String country= rs.getString("Country");
            Inventory.addCountry(new Countries(countryId, country));
        }
    }


    public static void getStates() throws SQLException {

        String sql = "SELECT * FROM FIRST_LEVEL_DIVISIONS";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int divisionId = rs.getInt("Division_ID");
            String division = rs.getString("Division");
            int countryId = rs.getInt("COUNTRY_ID");
            Inventory.addState(new States(divisionId, division, countryId));
        }
    }


}
