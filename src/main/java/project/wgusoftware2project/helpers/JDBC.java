package project.wgusoftware2project.helpers;

import java.sql.Connection;
import java.sql.DriverManager;

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

    public static void openConnection(){
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(jdbcUrl, userName, password);
            System.out.println("Success!");
        } catch (Exception e) {
            System.out.println("Failure! " + e.getMessage());
        }
    }

    public static void closeConnection(){
        try{
            connection.close();
            System.out.println("Closed Successfully!");
        } catch (Exception e){
            System.out.println("Failure to close! " + e.getMessage());
        }
    }
}
