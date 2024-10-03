package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import models.User;

public class ConnectionDB {

    private static Connection conn = null;
    private static User currentUser;

    /**
     * Connects to the database.
     * 
     * @return the current connection.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    // Hard coded cause we don't careeee (for now)
    public static Connection connect() throws SQLException, ClassNotFoundException {
        if (conn != null)
            return conn;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://158.179.218.47:3306/gslite?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "program", "programpw");
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } catch (ClassNotFoundException ex) {
            throw new ClassCastException(ex.getMessage());
        }
        return conn;
    }
    
    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        ConnectionDB.currentUser = currentUser;
    }

    /**
     * Gets the user connected to the system.
     * 
     * @return the User that's connected. Null if the user's not connected to the
     *         system yet.
     */

    public static void close() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }
}