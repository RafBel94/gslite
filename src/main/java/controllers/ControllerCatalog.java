package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import models.Product;
import views.GUICatalog;

public class ControllerCatalog {
	private GUICatalog guiCatalog;
	private List<Product> productList;
	private int selectedIndex = 0;

	public ControllerCatalog(GUICatalog guiCatalog) {
		super();
		this.guiCatalog = guiCatalog;
		
		guiCatalog.addActListener(new ActListener());
		guiCatalog.addItemSelectListener(new ListSelectListener());
		guiCatalog.addDocuListener(new DocuListener());
	}
	
	private class ActListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			
			//TODO: new GUIProduct(guiCatalog)
		}
		
	}
	
	private class ListSelectListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			JList list = (JList) e.getSource();
			selectedIndex = list.getSelectedIndex();
			
			//TODO: Load Product info into lbl_productName and area_description
		}
		
	}
	
	private class DocuListener implements DocumentListener {

		@Override
		public void insertUpdate(DocumentEvent e) {
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
		}
		
	}
}
