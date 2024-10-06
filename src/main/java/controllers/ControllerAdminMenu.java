package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import utils.ConnectionDB;
import views.GUIAdminMenu;
import views.GUICatalog;
import views.GUICreateProduct;
import views.GUIEditProduct;
import views.GUILogin;

public class ControllerAdminMenu {
	private GUIAdminMenu adminMenu;

	public ControllerAdminMenu(GUIAdminMenu adminMenu) {
		this.adminMenu = adminMenu;
		adminMenu.addActListeners(new ActListener());
	}

	private class ActListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			if (button == adminMenu.getBtnAdd()) {
				new GUICreateProduct(adminMenu);
				adminMenu.dispose();
			} else if (button == adminMenu.getBtnCatalog()) {
				new GUICatalog(adminMenu);
				adminMenu.dispose();
			} else if (button == adminMenu.getBtnEdit()) {
				new GUIEditProduct(adminMenu);
				adminMenu.dispose();
			} else if (button == adminMenu.getBtnLogout()) {
				int selection = JOptionPane.showConfirmDialog(adminMenu, "Are you sure you want to log out?", "Warning",
						JOptionPane.WARNING_MESSAGE);
				if (selection == JOptionPane.YES_OPTION) {
					ConnectionDB.setCurrentUser(null);
					new GUILogin(adminMenu);
					adminMenu.dispose();
				}
			}

		}

	}

}
