package project.wgusoftware2project.helpers;


import javafx.collections.ObservableList;
import project.wgusoftware2project.model.Appointments;
import project.wgusoftware2project.model.Customers;
import project.wgusoftware2project.model.Inventory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public abstract class FruitsQuery {
    public static int insert(String fruitName, int colorID) throws SQLException {
        String sql = "INSERT INTO FRUITS (Fruit_Name, Color_ID) VALUES(?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, fruitName);
        ps.setInt(2, colorID);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    public static int update(int fruitID, String fruitName) throws SQLException {
        String sql = "UPDATE FRUITS SET Fruit_Name = ? WHERE Fruit_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, fruitName);
        ps.setInt(2, fruitID);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    public static int delete(int fruitID) throws SQLException {
        String sql = "DELETE FROM FRUITS WHERE Fruit_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, fruitID);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    public static void select() throws SQLException {
        String sql = "SELECT * FROM FRUITS";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int fruitID = rs.getInt("Fruit_ID");
            String fruitName = rs.getString("Fruit_Name");
            int colorID = rs.getInt("Color_ID");
            System.out.print(fruitID + " | " + fruitName + " | " + colorID + "\n");
        }
    }

    public static void select(int colorID) throws SQLException {
        String sql = "SELECT * FROM FRUITS WHERE Color_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, colorID);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int fruitID = rs.getInt("Fruit_ID");
            String fruitName = rs.getString("Fruit_Name");
            int colorIDMatch = rs.getInt("Color_ID");
            System.out.print(fruitID + " | " + fruitName + " | " + colorIDMatch + "\n");
        }
    }

    public static void selectUser(String userName, String password) throws SQLException {
        String sql = "SELECT * FROM USERS WHERE Password = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        //ps.setString(1, userName);
        ps.setString(1, password);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int sqlID = rs.getInt("User_ID");
            String sqlUser = rs.getString("User_Name");
            String sqlPassword = rs.getString("Password");
            LocalDateTime sqlDate = rs.getTimestamp("Create_Date").toLocalDateTime();
            String sqlCreatedBy = rs.getString("Created_By");
            LocalDateTime sqlLastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();

            System.out.print(sqlID + " | " + sqlUser + " | " + sqlPassword + " | " +
                    sqlDate + " | " + sqlCreatedBy + " | " + sqlLastUpdate + "\n");
        }
    }

//    public static ObservableList<Appointments> populateAppts(ObservableList<Appointments> inputArrayList) throws SQLException {
//
//        String sql = "SELECT * FROM APPOINTMENTS";
//        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
//        ResultSet rs = ps.executeQuery();
//        while(rs.next()){
//            int apptID = rs.getInt("Appointment_ID");
//            String title = rs.getString("Title");
//            String description = rs.getString("Description");
//            String location = rs.getString("Location");
//            String type = rs.getString("Type");
//            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
//            LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
//            int customerID = rs.getInt("Customer_ID");
//            int userID = rs.getInt("User_ID");
//            int contactID = rs.getInt("Contact_ID");
//            inputArrayList.add(new Appointments(apptID, title, description, location, type, start,
//                    end, customerID, userID, contactID));
//        }
//        return inputArrayList;
//    }

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
            String start = rs.getString("Start");
            String end = rs.getString("End");
            //LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
            //LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
            int customerID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            int contactID = rs.getInt("Contact_ID");
            Inventory.addAppt(new Appointments(apptID, title, description, location, type, start,
                    end, customerID, userID, contactID));
        }
    }

    public static ObservableList<Customers> populateCusts(ObservableList<Customers> inputArrayList) throws SQLException {

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
            inputArrayList.add(new Customers(custID, custName, custAddress, custPostal, custPhone, custState));
        }
        return inputArrayList;
    }

    public static int insertAppt(String fruitName, int colorID) throws SQLException {
        String sql = "INSERT INTO FRUITS (Fruit_Name, Color_ID) VALUES(?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, fruitName);
        ps.setInt(2, colorID);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }
}
