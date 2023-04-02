package project.wgusoftware2project.helpers;

import java.sql.Connection;
import java.sql.DriverManager;

/** JDBC class created to connect and disconnect from MySQL database.
 */
public abstract class JDBC {
    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";

    private static final String jdbcUrl = protocol + vendor + location + databaseName
            + "?connectionTimeZone = SERVER";

    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String userName = "sqlUser";
    private static final String password = "Passw0rd!";
    public static Connection connection;

    /** JDBC method to open a connection with MySQL database.
     */
    public static void openConnection(){
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(jdbcUrl, userName, password);
            System.out.println("Success!");
        } catch (Exception e) {
            System.out.println("Failure! " + e.getMessage());
        }
    }

    /** JDBC method to close a connection with MySQL database.
     */
    public static void closeConnection(){
        try{
            connection.close();
            System.out.println("Closed Successfully!");
        } catch (Exception e){
            System.out.println("Failure to close! " + e.getMessage());
        }
    }
}
