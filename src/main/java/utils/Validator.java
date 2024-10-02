package utils;

public class Validator {
	
	// -- IMPORTANT-- //
	// You MUST apply .trim() and .replaceAll("\\s+", " ") to values for the methods to properly work
	// since it does not make sense to use them if the final value is different from the evaluated here
	
	// CASE OF USE:
	// String name = exampleView.getTextFieldName().getText().trim().replaceAll("\\s+", " ")
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
		return name.matches("[A-Za-z0-9\\.]+");
	}

	public static boolean validateProductPrice(String price) {
		return price.matches("[0-9\\.]{1,9}");
	}

	public static boolean validateProductAmount(String amount) {
		return amount.matches("[0-9]+");
	}
}
