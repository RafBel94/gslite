package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import utils.ConnectionDB;
import utils.DatabaseUtils;
import views.GUIAdminMenu;
import views.GUICatalog;
import views.GUILogin;
import views.GUIRegister;

public class ControllerAdminMenu {
	private GUIAdminMenu adminMenu;

	public ControllerAdminMenu(GUIAdminMenu adminMenu) {
		this.adminMenu = adminMenu;
		adminMenu.addActListeners(new ActListener());
	}
	
	private class ActListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton)e.getSource();
			if(button == adminMenu.getBtnAdd());
				//TODO: Open GuiAddProduct
			else if (button == adminMenu.getBtnCatalog())
				new GUICatalog();
			else if (button == adminMenu.getBtnEdit());
				//TODO: Open GUIEditProduct
			else if (button == adminMenu.getBtnLogout()) {
				ConnectionDB.setCurrentUser(null);
				new GUILogin(adminMenu);
			}
			adminMenu.dispose();
		}
		
	}
	
	
}
