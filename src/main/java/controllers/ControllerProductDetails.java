package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import views.GUICatalog;
import views.GUIProductDetails;

public class ControllerProductDetails {
	private GUIProductDetails guiProductDetails;

	public ControllerProductDetails(GUIProductDetails guiProductDetails) {
		super();
		this.guiProductDetails = guiProductDetails;
		
		guiProductDetails.addActListener(new ActListener());
	}
	
	private class ActListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			
			new GUICatalog(guiProductDetails);
			guiProductDetails.dispose();
		}
		
	}
}
