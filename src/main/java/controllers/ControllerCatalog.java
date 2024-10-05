package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import models.Product;
import utils.ConnectionDB;
import utils.DatabaseUtils;
import utils.ImageUtils;
import views.GUICatalog;
import views.GUILogin;
import views.GUIProductDetails;

public class ControllerCatalog {
	private GUICatalog guiCatalog;
	private List<Product> productList;
	private List<Product> filteredProducts;
	private int selectedIndex = 0;

	public ControllerCatalog(GUICatalog guiCatalog) {
		super();
		this.guiCatalog = guiCatalog;
		
		productList = DatabaseUtils.getAllProductsAsList();
		filteredProducts = new ArrayList<>(productList);
		updateProductList(productList);
		guiCatalog.getList_catalog().setSelectedIndex(0);
		
		updateInfoComponents();
		
		guiCatalog.addActListener(new ActListener());
		guiCatalog.addItemSelectListener(new ListSelectListener());
		guiCatalog.addDocuListener(new DocuListener());
	}
	
	private void updateInfoComponents() {
		if(!filteredProducts.isEmpty()) {
			if(selectedIndex == -1) {
				Product product = filteredProducts.get(0);
				guiCatalog.getLbl_image().setIcon(new ImageIcon(ImageUtils.fromBinaryToBufferedImage(product.getImage())));
				guiCatalog.getLbl_productName().setText(product.getName());
				guiCatalog.getArea_description().setText(product.getDescription());
			} else {
				Product product = filteredProducts.get(selectedIndex);
				guiCatalog.getLbl_image().setIcon(new ImageIcon(ImageUtils.fromBinaryToBufferedImage(product.getImage())));
				guiCatalog.getLbl_productName().setText(product.getName());
				guiCatalog.getArea_description().setText(product.getDescription());
			}
		} else {
			guiCatalog.getLbl_image().setText("NO IMAGE");
			guiCatalog.getLbl_productName().setText("No product found");
			guiCatalog.getArea_description().setText("No description");
		}
	}

	// Filters products to get only those who meet the requirement
	private void filterProducts() {
		String searchText = guiCatalog.getTxt_searchBar().getText().toLowerCase();
		
		filteredProducts = productList.stream()
				.filter(product -> product.getName().toLowerCase().contains(searchText)).collect(Collectors.toList());

		updateProductList(filteredProducts);
		
		if (!filteredProducts.isEmpty()) {
	        guiCatalog.getList_catalog().setSelectedIndex(0);
	        selectedIndex = 0;
	    } else
	        selectedIndex = -1;
	    
		updateInfoComponents();
	}
	
	// Update the DefaultListModel of the JList in GUICatalog to show the filtered product list
	private void updateProductList(List<Product> products) {
		DefaultListModel<String> listModel = guiCatalog.getListModel();
		listModel.clear();
		
		for (Product p : products)
			listModel.addElement(p.getName());
	}

	
	
	
	
	// PRIVATE CLASSES

	private class ActListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();

			if (btn == guiCatalog.getBtn_details()) {

				new GUIProductDetails(guiCatalog, filteredProducts.get(selectedIndex));
				guiCatalog.dispose();
			} else if (btn == guiCatalog.getBtn_logout()) {
				int selection = JOptionPane.showConfirmDialog(guiCatalog, "Are you sure you want to log out?", "Confirmation", JOptionPane.WARNING_MESSAGE);
				
				if(selection == JOptionPane.OK_OPTION) {
					new GUILogin(guiCatalog);
					guiCatalog.dispose();
					ConnectionDB.setCurrentUser(null);
				}
			}
		}

	}
	
	private class ListSelectListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			@SuppressWarnings("unchecked")
			JList<String> list = (JList<String>) e.getSource();
			selectedIndex = list.getSelectedIndex();
			
			updateInfoComponents();
		}
		
	}
	
	private class DocuListener implements DocumentListener {

		@Override
		public void insertUpdate(DocumentEvent e) {
			filterProducts();
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			filterProducts();
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			filterProducts();
		}
		
	}
}
