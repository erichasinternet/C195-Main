package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class JDBC {

    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost:3306/";
    private static final String database = "client_schedule";
    private static final String jdbURL = protocol + vendor + location + database + "?connectionTimeZone=SERVER";
    private static final String username = "sqlUser";
    private static final String password = "Passw0rd!";
    private static Connection connection;

    /**
     * Opens connection to the database
     */
    public static void openConnection() {
        try {
            connection = DriverManager.getConnection(jdbURL, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes connection to the database
     */
    public static void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Getter for the database connection
     *
     * @return Connection
     */
    public static Connection getConnection() {
        return connection;
    }
}
