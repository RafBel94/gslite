package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import utils.DatabaseUtils;
import utils.Validator;
import views.GUILogin;
import views.GUIRegister;

public class ControllerRegister {
	private GUIRegister register;

	public ControllerRegister(GUIRegister register) {
		super();
		this.register = register;
		register.addActListeners(new ActListener());
	}
	/**
	 * Checks if all the validation works out fine.
	 * @return true if it's a successful, valid registration, false otherwise.
	 */
	public boolean checkValidation() {
		return Validator.validateUsername(register.getTxtUsername().getText())
				&& Validator.validatePassword(String.valueOf(register.getPwField().getPassword()))
				&& !DatabaseUtils.stringFieldFound("users", "username", register.getTxtUsername().getText())
				&& Validator.validateRepeatPassword(String.valueOf(register.getPasswordField().getPassword()),
						String.valueOf(register.getPwField().getPassword()))
				&& Validator.validateEmail(register.getTxtEmail().getText());

	}
	/**
	 * Sets the error label accordingly depending on the number of errors that exist.
	 */
	public void setErrorLabel() {
		// First, clear previous errors:
		register.getLblError().setText("");
		int numErrors = 4;
		// Check if username is valid.
		if (!Validator.validateUsername(register.getTxtUsername().getText())) {
			numErrors--;
			register.getLblError()
					.setText(register.getLblError().getText() + "<html>- Username must be 6-18 characters long.<br>");
		}
		// Check if username is taken.
		if (DatabaseUtils.stringFieldFound("users", "username", register.getTxtUsername().getText())) {
			numErrors--;
			register.getLblError().setText(register.getLblError().getText() + "<html>- Username is already taken!<br>");
		}
		// Check if password is valid.
		if (!Validator.validatePassword(String.valueOf(register.getPwField().getPassword()))) {
			numErrors--;
			register.getLblError()
					.setText(register.getLblError().getText()
							+ "<html>- Password should be between 8-18 characters long.<br>");
		}
		// Check repeated passwords.
		if (!Validator.validateRepeatPassword(String.valueOf(register.getPasswordField().getPassword()),
				String.valueOf(register.getPwField().getPassword()))) {
			numErrors--;
			register.getLblError().setText(register.getLblError().getText() + "<html>- Passwords don't match!<br>");
		}
		// Check if email is valid.
		if (!Validator.validateEmail(register.getTxtEmail().getText())) {
			numErrors--;
			register.getLblError()
					.setText(register.getLblError().getText() + "<html>- Email must follow: example@place.domain<br>");
		}
		//Set correct spacing according to number of errors (to maintain consistency in the design.)
		register.getLblError().setText(register.getLblError().getText() + "<br>".repeat(numErrors));
	}
	/**
	 * Handles the registration process. Only calls other methods.
	 */
	public void handleRegistration() {
		if (!checkValidation())
			setErrorLabel();
		else 
			register();
	}
	/**
	 * After a successful registration check, it'll grab the data sent in by the user and add it in the Database.
	 * For more information on the registerUser method:
	 * @see DatabaseUtils 
	 */
	public void register() {
		if (DatabaseUtils.registerUser(register.getTxtUsername().getText(),
				String.valueOf(register.getPwField().getPassword()), register.getTxtEmail().getText())) {
			JOptionPane.showMessageDialog(register, "Successfully registered! Proceed to login.", "Success",
					JOptionPane.INFORMATION_MESSAGE);
			new GUILogin(register);
			register.dispose();
		} else
			JOptionPane.showMessageDialog(register, "Oops, something happened!");
	}

	private class ActListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			if (btn == register.getBtnCancel()) {
				new GUILogin(register);
				register.dispose();
			} else
				handleRegistration();
		}

	}

}
