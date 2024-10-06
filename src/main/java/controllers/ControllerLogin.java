package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import utils.ConnectionDB;
import utils.DatabaseUtils;
import utils.Validator;
import views.GUIAdminMenu;
import views.GUICatalog;
import views.GUILogin;
import views.GUIRegister;

/**
 * This class is responsible for handling the login functionality of the
 * application. It contains methods to check if a user exists and if the
 * provided password matches the stored one.
 */
public class ControllerLogin {
	private GUILogin login;

	public ControllerLogin(GUILogin login) {
		this.login = login;

		login.addActListener(new ActListener());
	}

	/**
	 * This method checks if the provided password matches the stored password for
	 * the given username. It determines which table to check based on the user
	 * access level set in the class.
	 * 
	 * @param username The username to check the password for.
	 * @param password The password to check against the stored password.
	 * @return true if the passwords match, false otherwise.
	 */
	public boolean passwordMatches(String username, String password) {
		return Validator.compareEncryptedPassword(password,
				DatabaseUtils.getStringFromField("users", "username", username, "password"));
	}

	

	private class ActListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton buttonPressed = (JButton) e.getSource();

			if (buttonPressed == login.getBtnExit())
				System.exit(0);
			else if (buttonPressed == login.getBtnRegister()) {
				new GUIRegister(login);
				login.dispose();
			} else if (buttonPressed == login.getBtnLogin()) {
				String username = login.getTxtUsername().getText();
				if (!DatabaseUtils.stringFieldFound("users", "username", username))
					login.getLblError().setText("Username not found.");
				else if (!passwordMatches(username, String.valueOf(login.getPwPassword().getPassword())))
					login.getLblError().setText("Incorrect password.");
				else {
					ConnectionDB.setCurrentUser(DatabaseUtils.getCurrentUser(username));
					switch (ConnectionDB.getCurrentUser().getRole()) {
					case "user" -> new GUICatalog(login);
					case "admin" -> new GUIAdminMenu(login);
					}
					login.dispose();
				}
			}

		}

	}
}
