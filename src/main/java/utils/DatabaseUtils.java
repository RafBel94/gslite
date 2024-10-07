package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import models.Product;
import models.User;
/**
 * This class must include ALL connections and queries to the database, which the rest of the codebase makes
 * reference to.
 * @author Pablorv28
 * @author FalBel94
 * @version 1
 */
public class DatabaseUtils {
	/**
	 * Grabs a string from a desired field and table. If there are multiple, it grabs the first one it finds.
	 * @param table you'd like to search.
	 * @param identifierField The identifier's field. (I.g "Robert34"'s field is "username")
	 * @param identifier The Identifier to find the desired result. (I.g "Robert34")
	 * @param fieldResult The field you'd like to find. (I.g "email")
	 * @return The String it's found (I.g "robert@gmail.com"). Null if otherwise.
	 */
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
	/**
	 * Grabs the current user that's logged in.
	 * @param currUsername the username of the user.
	 * @return the whole User object.
	 */
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
	/**
	 * Checks if it finds a string given a table & value.
	 * @param table the table you'd like to search.
	 * @param field the field of the value.
	 * @param value the value of the field you'd like to search.
	 * @return True if it finds the string. Otherwise false.
	 */
	public static boolean stringFieldFound(String table, String field, String value) {
		try {
			Connection conn = ConnectionDB.connect();
			String query = "SELECT " + field + " FROM " + table + " WHERE " + field + " = '" + value + "';";
			ResultSet rs = conn.createStatement().executeQuery(query);
			return rs.next();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return false;
	}
	/**
	 * Registers a new user.
	 * @param username The username
	 * @param password The password (Not SHA256'd!)
	 * @param email The email
	 * @return True if it ends up registering the user. False otherwise.
	 */
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
	
	/**
	 * Deletes a product given an ID.
	 * @param id id of the product you'd like to delete.
	 * @return 1 if product is deleted. 0 if it couldn't find the product. -1 if an error occurred.
	 */
	public static int deleteProduct(int id) {
		try {
			Connection connection = ConnectionDB.connect();
			PreparedStatement pStatement = connection.prepareStatement("DELETE FROM products WHERE id = ?;");
			pStatement.setInt(1, id);
			return pStatement.executeUpdate();
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return -1;
	}
	/**
	 * Inserts a product.
	 * !Only use if re-inserting a product!
	 * @param id The ID of the product (Only difference with other method)
	 * @param productName The name of the product
	 * @param description The product's description
	 * @param price It's price
	 * @param amount The amount in stock
	 * @param type Type of product
	 * @param image The byte array of the image.
	 * @return 1 if product is inserted correctly. 0 If it wasn't. -1 If an error occurred.
	 */
	public static int insertProduct(int id, String productName, String description, double price, int amount, String type, byte[] image) {
		try {
			Connection connection = ConnectionDB.connect();

			String sql = "INSERT INTO products (id, name, description, price, amount, type, image) VALUES (?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, productName);
			pstmt.setString(3, description);
			pstmt.setDouble(4, price);
			pstmt.setInt(5, amount);
			pstmt.setString(6, type);
			pstmt.setBytes(7, image);

			return pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	/**
	 * Inserts a product. Use this one if you just want to insert a product, nothing else.
	 * @param productName The name of the product
	 * @param description The product's description
	 * @param price It's price
	 * @param amount The amount in stock
	 * @param type Type of product
	 * @param image The byte array of the image.
	 * @return 1 if product is inserted correctly. 0 If it wasn't. -1 If an error occurred.
	 */
	public static int insertProduct(String productName, String description, double price, int amount, String type, byte[] image) {
		try {
			Connection connection = ConnectionDB.connect();

			String sql = "INSERT INTO products (name, description, price, amount, type, image) VALUES (?, ?, ?, ?, ?, ?)";

			PreparedStatement pstmt = connection.prepareStatement(sql);
			
			pstmt.setString(1, productName);
			pstmt.setString(2, description);
			pstmt.setDouble(3, price);
			pstmt.setInt(4, amount);
			pstmt.setString(5, type);
			pstmt.setBytes(6, image);

			return pstmt.executeUpdate();
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	/**
	 * Gets all the products from the database and returns it as a List<Product>.
	 * @return List<Product> full of our products (if we have any).
	 */
	public static List<Product> getAllProductsAsList() {
		List<Product> list = new ArrayList<>();
		
		try {
			Connection conn = ConnectionDB.connect();
			
			String sql = "SELECT * FROM products ORDER BY id;";
			
			Statement stmnt = conn.createStatement();
			ResultSet rs = stmnt.executeQuery(sql);
			
			while(rs.next()) {
				list.add(new Product(
					rs.getInt("id"),
					rs.getString("name"),
					rs.getString("description"),
					rs.getString("type"),
					rs.getBytes("image"),
					rs.getDouble("price"),
					rs.getInt("amount")
				));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
