package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ListModel;
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
		
		productList = getProductList();
		updateProductList(productList);
		guiCatalog.getList_catalog().setSelectedIndex(selectedIndex);
		
		guiCatalog.addActListener(new ActListener());
		guiCatalog.addItemSelectListener(new ListSelectListener());
		guiCatalog.addDocuListener(new DocuListener());
	}
	
	// Filters products to get only those who meet the requirement
	private void filterProducts() {
		String searchText = guiCatalog.getTxt_searchBar().getText().toLowerCase();

		List<Product> filteredProducts = productList.stream()
				.filter(product -> product.getName().toLowerCase().contains(searchText)).collect(Collectors.toList());

		updateProductList(filteredProducts);
	}
	
	// Update the DefaultListModel of the JList in GUICatalog to show the filtered product list
	private void updateProductList(List<Product> products) {
		DefaultListModel<String> listModel = guiCatalog.getListModel();
		listModel.clear();
		
		for (Product p : products)
			listModel.addElement(p.getName());
	}

	// Temporary method to fill the JList until database is ready
	private List<Product> getProductList() {
		List<Product> list = new ArrayList<>();
		
		list.add(new Product(0, "Razer Blackwidow V3", "Description example blackwidow", "Keyboard", null, 89.99f, 200));
		list.add(new Product(1, "Razer Deathadder", "Description example deathadder", "Mouse", null, 45.99f, 150));
		list.add(new Product(2, "Corsair K70", "Description example K70", "Keyboard", null, 85.99f, 180));
		list.add(new Product(3, "Logitech G502", "Description example G502", "Mouse", null, 55.99f, 250));
		
		return list;
	}
	
	
	
	
	// PRIVATE CLASSES

	private class ActListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			
			//TODO: new GUIProduct(Frame guiCatalog, Product product)
		}
		
	}
	
	private class ListSelectListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			JList list = (JList) e.getSource();
			selectedIndex = list.getSelectedIndex();
			
			Product product = productList.get(selectedIndex);
			//TODO: Load product image to lbl_image
			guiCatalog.getLbl_productName().setText(product.getName());
			guiCatalog.getArea_description().setText(product.getDescription());
			
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
