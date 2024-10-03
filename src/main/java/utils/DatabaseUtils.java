package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.codec.digest.DigestUtils;

import models.User;

public class DatabaseUtils {

	public static String getStringFromField(String table, String identifierField, String identifier,
			String fieldResult) {
		try {
			Connection conn = ConnectionDB.connect();
			String query = "SELECT " + fieldResult + " FROM " + table + " WHERE " + identifierField + " = '" + identifier
					+ "';";
			ResultSet rs = conn.createStatement().executeQuery(query);
			rs.next();
			return rs.getString(fieldResult);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static User getCurrentUser(String currUsername){
		try{
			Connection conn = ConnectionDB.connect();
			String query = "SELECT * FROM users WHERE username = '" + currUsername + "';";
			ResultSet rs = conn.createStatement().executeQuery(query);
			rs.next();
			return new User(rs.getInt(1), currUsername, rs.getString(3), rs.getString(4), rs.getString(5));
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean userFieldFound(String field, String value) {
		try {
			Connection conn = ConnectionDB.connect();
			String query = "SELECT " + field + " FROM gslite.users WHERE " + field + " = '" + value + "';";
			ResultSet rs = conn.createStatement().executeQuery(query);
			if (rs.next())
				return true;
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return false;
	}
	
	public static boolean registerUser(String username, String password, String email) {
		try {
			Connection conn = ConnectionDB.connect();
			PreparedStatement pst = conn.prepareStatement("INSERT INTO gslite.users (username, password, email) VALUES(?,?,?);");
			pst.setString(1, username);
			pst.setString(2, DigestUtils.sha256Hex(password));
			pst.setString(3, email);
			pst.execute();
			return true;
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}
}
