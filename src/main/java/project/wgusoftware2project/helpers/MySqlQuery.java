package project.wgusoftware2project.helpers;


import project.wgusoftware2project.model.*;
import java.sql.*;
import java.time.*;

/** Abstract class created to interface (create, read, update, delete)
 * with MySQL database.
 */
public abstract class MySqlQuery {

    /** This method retrieves all Appointments from the
     * Appointments table in MySQL database. Appointment
     * objects are created from each row in the table
     * and they are added to the Inventory's Appointment
     * ObservableList
     */
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

    /** This method retrieves all Customers from the
     * Customers table in MySQL database. Customer
     * objects are created from each row in the table
     * and they are added to the Inventory's Customer
     * ObservableList
     */
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

    /** This method retrieves all Users from the
     * Users table in MySQL database. User
     * objects are created from each row in the table
     * and they are added to the Inventory's User
     * ObservableList
     */
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

    /** This method retrieves all Contacts from the
     * Contacts table in MySQL database. Contact
     * objects are created from each row in the table
     * and they are added to the Inventory's Contact
     * ObservableList
     */
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

    /** This method accepts an Appointment object as input
     * to be added into the Appointments Table of the MySQL database.
     * Fields are extracted from the Appointment object and used
     * to populate the columns of a row corresponding to the object.
     @param addAppt Appointment object to be added to MySQL database
     @return rowsAffected Integer of Table row updated
     */
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

    /** This method accepts a Customer object as input
     * to be added into the Customer Table of the MySQL database.
     * Fields are extracted from the Customer object and used
     * to populate the columns of a row corresponding to the object.
     @param addCust Customer object to be added to MySQL database
     @return rowsAffected Integer of Table row updated
     */
    public static int insertCust(Customers addCust) throws SQLException {
        String sql = "INSERT INTO CUSTOMERS (CUSTOMER_ID, CUSTOMER_NAME, ADDRESS, POSTAL_CODE, " +
                "PHONE, DIVISION_ID) VALUES(?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = JDBC.connection.prepareStatement(sql);

        ps.setInt(1, addCust.getCustomerID());
        ps.setString(2, addCust.getCustomerName());
        ps.setString(3, addCust.getAddress());
        ps.setString(4, addCust.getPostalCode());
        ps.setString(5, addCust.getPhone());
        ps.setInt(6, Inventory.divisionIdByDivision(addCust.getState()));

        int rowsAffected = ps.executeUpdate();
        return rowsAffected;

    }

    /** This method accepts an Appointment object as input
     * for updating a row of the Appointments Table of the MySQL database.
     * The row to update is identified by the WHERE statement referencing
     * the APPOINTMENT_ID.
     * Fields are extracted from the Appointment object and used
     * to update the columns of a row corresponding to the object.
     @param addAppt Appointment object used to update Table row in MySQL database
     @return rowsAffected Integer of Table row updated
     */
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

    /** This method accepts a Customer object as input
     * for updating a row of the Customer Table of the MySQL database.
     * The row to update is identified by the WHERE statement referencing
     * the CUSTOMER_ID.
     * Fields are extracted from the Customer object and used
     * to update the columns of a row corresponding to the object.
     @param addCustomer Appointment object used to update Table row in MySQL database
     @return rowsAffected Integer of Table row updated
     */
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

    /** This method accepts an Integer as input
     * for deleting the matching row of the Appointment Table of the MySQL database.
     * The row to delete is identified by the WHERE statement referencing
     * the APPOINTMENT_ID.
     @param apptId Integer ID used to delete matching Table row in MySQL database
     @return rowsAffected Integer of Table row updated
     */
    public static int deleteAppt(int apptId) throws SQLException {
        String sql = "DELETE FROM APPOINTMENTS WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, apptId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    /** This method accepts an Integer as input
     * for deleting the matching row of the Customer Table of the MySQL database.
     * The row to delete is identified by the WHERE statement referencing
     * the CUSTOMER_ID.
     @param custId Integer ID used to delete matching Table row in MySQL database
     @return rowsAffected Integer of Table row updated
     */
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

    /** This method retrieves all countries from the
     * Country Table of the MySQL database.
     * Each row is used to create a Country object
     * that is stored in the Inventory's Country ObservableList
     */
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

    /** This method retrieves all states from the
     * State Table of the MySQL database.
     * Each row is used to create a State object
     * that is stored in the Inventory's State ObservableList
     */
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
