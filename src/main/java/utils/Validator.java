package utils;

import org.apache.commons.codec.digest.DigestUtils;


public class Validator {

	// -- IMPORTANT-- //
	// You MUST apply .trim() and .replaceAll("\\s+", " ") to values for the methods
	// to properly work
	// since it does not make sense to use them if the final value is different from
	// the evaluated here

	// CASE OF USE:
	// String name =
	// exampleView.getTextFieldName().getText().trim().replaceAll("\\s+", " ")
	// bool validName = Validator.validateUsername(name)

	public static boolean validateUsername(String username) {
		return username.matches("[A-Za-z0-9]{6,18}");
	}

	public static boolean validatePassword(String password) {
		return password.matches("[A-Za-z0-9\\.-_@]{8,18}");
	}

	public static boolean validateRepeatPassword(String password, String repPassword) {
		return password.equals(repPassword);
	}

	public static boolean validateEmail(String email) {
		return email.matches("[A-Za-z0-9._-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}");
	}

	public static boolean validateProductName(String name) {
		return name.matches("[A-Za-z0-9\\s\\.-]{1,200}");
	}
	
	public static boolean validateProductDescription(String description) {
		return description.matches(".{0,500}");
	}

	public static boolean validateProductPrice(String price) {
		return price.matches("[0-9\\.]{1,9}");
	}

	public static boolean validateProductAmount(String amount) {
		return amount.matches("[0-9]+");
	}

	/**
	 * Calculates the SHA-256 hash of a password given by the user and compares it
	 * to another
	 * 
	 * @param givenPassword     - Password given by the user, normally obtained from
	 *                          a JTextField
	 * @param encryptedPassword - Hashed password in SHA-256, normally obtained from
	 *                          a SQL query
	 * @return true if the passwords match, false otherwise
	 */
	public static boolean compareEncryptedPassword(String givenPassword, String encryptedPassword) {
		String hashedPassword = DigestUtils.sha256Hex(givenPassword);

		return hashedPassword.equals(encryptedPassword);
	}
	
	public static String formatBlankspaces(String field) {
		return field.trim().replaceAll("\\s+", " ");
	}
}
