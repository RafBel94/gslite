package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    private static Connection conn = null;
    private static String currentUsername;
    private static String userAccessLvl;
    /**
     * Connects to the database.
     * @return the current connection.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    // Hard coded cause we don't careeee (for now)
    public static Connection connect() throws SQLException, ClassNotFoundException {
        if (conn == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(
                        "jdbc:mysql://158.179.219.240:3306/gslite?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                        "pablorv28", "Pablorv123");
            } catch (SQLException ex) {
                throw new SQLException(ex);
            } catch (ClassNotFoundException ex) {
                throw new ClassCastException(ex.getMessage());
            }
        }
        return conn;
    }

    public static String getUserAccessLvl() {
        return userAccessLvl;
    }

    public static void setUserAccessLvl(String userAccessLvl) {
        ConnectionDB.userAccessLvl = userAccessLvl;
    }

    public static String getLoginUsername() {
        return currentUsername;
    }

    public static void setLoginUsername(String loginUsername) {
        ConnectionDB.currentUsername = loginUsername;
    }
    /**
     * Gets the user connected to the system.
     * @return the User that's connected. Null if the user's not connected to the system yet.
     */

    public static void close() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }
}