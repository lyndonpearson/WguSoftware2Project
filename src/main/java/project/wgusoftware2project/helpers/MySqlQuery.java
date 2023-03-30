package project.wgusoftware2project.helpers;


import project.wgusoftware2project.model.*;
import java.sql.*;
import java.time.*;

public abstract class MySqlQuery {

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

            Timestamp test = rs.getTimestamp("Start");
            OffsetDateTime test2 = OffsetDateTime.of(test.toLocalDateTime(), ZoneOffset.UTC);
            Instant start = Instant.from(test2);
            Timestamp startLocal = Timestamp.from(start);


            Timestamp endTest = rs.getTimestamp("End");
            OffsetDateTime endTest2 = OffsetDateTime.of(endTest.toLocalDateTime(), ZoneOffset.UTC);
            Instant end = Instant.from(endTest2);
            Timestamp endLocal = Timestamp.from(end);


            int customerID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            int contactID = rs.getInt("Contact_ID");
            Inventory.addAppt(new Appointments(apptID, title, description, location, type, start,
                    startLocal, end, endLocal, customerID, userID, contactID));
        }
    }


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
            String custDivisionId = rs.getString("Division_ID");
            String custState = Inventory.lookupState(Integer.parseInt(custDivisionId)).getDivision();
            Inventory.addCust(new Customers(custID, custName, custAddress, custPostal, custPhone, custDivisionId, custState));
        }
    }

    public static void populateUsers() throws SQLException {

        String sql = "SELECT * FROM USERS";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int userId = rs.getInt("User_ID");
            String userName = rs.getString("User_Name");
            String password = rs.getString("Password");
            Inventory.addUser(new Users(userId, userName, password));
        }
    }

    public static void populateContacts() throws SQLException {

        String sql = "SELECT * FROM CONTACTS";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int contactId = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            String email = rs.getString("Email");
            Inventory.addContact(new Contacts(contactId, contactName, email));
        }
    }

    public static int insertAppt(Appointments addAppt) throws SQLException {
        String sql = "INSERT INTO APPOINTMENTS (Appointment_ID, Title, Description, Location, " +
                "Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = JDBC.connection.prepareStatement(sql);

        ps.setInt(1, addAppt.getAppointmentID());
        ps.setString(2, addAppt.getTitle());
        ps.setString(3, addAppt.getDescription());
        ps.setString(4, addAppt.getLocation());
        ps.setString(5, addAppt.getType());

        Instant timestampStart = Timestamp.from(addAppt.getStart()).toInstant();
        ZonedDateTime utcTimeStart = timestampStart.atZone(ZoneId.of("UTC"));
        ps.setTimestamp(6, Timestamp.valueOf(utcTimeStart.toLocalDateTime()));

        Instant timestampEnd = Timestamp.from(addAppt.getEnd()).toInstant();
        ZonedDateTime utcTime = timestampEnd.atZone(ZoneId.of("UTC"));
        ps.setTimestamp(7, Timestamp.valueOf(utcTime.toLocalDateTime()));

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

        ps.setString(1, addAppt.getTitle());
        ps.setString(2, addAppt.getDescription());
        ps.setString(3, addAppt.getLocation());
        ps.setString(4, addAppt.getType());

        Instant timestampStart = Timestamp.from(addAppt.getStart()).toInstant();
        ZonedDateTime utcTimeStart = timestampStart.atZone(ZoneId.of("UTC"));
        ps.setTimestamp(5, Timestamp.valueOf(utcTimeStart.toLocalDateTime()));

        Instant timestampEnd = Timestamp.from(addAppt.getEnd()).toInstant();
        ZonedDateTime utcTime = timestampEnd.atZone(ZoneId.of("UTC"));
        ps.setTimestamp(6, Timestamp.valueOf(utcTime.toLocalDateTime()));

        ps.setInt(7, addAppt.getCustomerID());
        ps.setInt(8, addAppt.getUserID());
        ps.setInt(9, addAppt.getUserID());

        int rowsAffected = ps.executeUpdate();
        return rowsAffected;

    }

    public static int updateCustomer(Customers addCustomer) throws SQLException {
        String sql = "UPDATE CUSTOMERS SET Customer_Name = ?, Address = ?, Postal_Code = ?, " +
                "Phone = ? WHERE CUSTOMER_ID = " + String.valueOf(addCustomer.getCustomerID());
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);

        ps.setString(1, addCustomer.getCustomerName());
        ps.setString(2, addCustomer.getAddress());
        ps.setString(3, addCustomer.getPostalCode());
        ps.setString(4, addCustomer.getPhone());

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
        String apptSql = "DELETE FROM APPOINTMENTS WHERE CUSTOMER_ID = ?";
        PreparedStatement psAppt = JDBC.connection.prepareStatement(apptSql);
        psAppt.setInt(1, custId);
        psAppt.executeUpdate();
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
