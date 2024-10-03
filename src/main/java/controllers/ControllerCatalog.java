package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import models.Product;
import utils.ConnectionDB;
import utils.ImageUtils;
import views.GUICatalog;

public class ControllerCatalog {
	private GUICatalog guiCatalog;
	private List<Product> productList;
	private List<Product> filteredProducts;
	private int selectedIndex = 0;

	public ControllerCatalog(GUICatalog guiCatalog) {
		super();
		this.guiCatalog = guiCatalog;
		
		productList = getProductList();
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

	// Temporary method to fill the JList until database is ready
	private List<Product> getProductList() {
		List<Product> list = new ArrayList<>();
		
		try {
			Connection conn = ConnectionDB.connect();
			
			String sql = "SELECT * FROM products";
			
			Statement stmnt = conn.createStatement();
			ResultSet rs = stmnt.executeQuery(sql);
			
			while(rs.next()) {
				list.add(new Product(
					rs.getInt("id"),
					rs.getString("name"),
					rs.getString("description"),
					rs.getString("type"),
					rs.getBytes("image"),
					rs.getDouble("price"),
					rs.getInt("amount")
				));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
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
