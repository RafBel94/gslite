package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import models.Product;
import utils.DatabaseUtils;
import utils.ImageUtils;
import views.GUIAdminMenu;
import views.GUIEditProduct;

public class ControllerEditProduct {
	private GUIEditProduct guiEditProduct;
	private int index;
	private boolean editMode;
	private List<Product> products;

	public ControllerEditProduct(GUIEditProduct guiEditProduct) {
		this.guiEditProduct = guiEditProduct;
		products = DatabaseUtils.getAllProductsAsList();
		editMode = true;
		index = 0;
		initializeCBType();
		initializeComponents();
		guiEditProduct.addActListeners(new ActListener());
		guiEditProduct.getTxtSearch().getDocument().addDocumentListener(new DocuListener());
	}
	/**
	 * Initialises the ListSelectionListener. Had to add it to a different method as I call the controller before
	 * the actual table is formed. Can be fixed/rearranged if needed.d
	 */
	public void initTableSelectionListener() {
		guiEditProduct.addLSListeners(new LSListener());
	}
	/**
	 * Toggle for editMode. Grabs all different buttons in the GUI and activates them or not depending on the status
	 * of the parameter editMode.
	 */
	public void editMode() {
		editMode = !editMode;
		// Buttons
		guiEditProduct.getBtnApply().setEnabled(editMode);
		guiEditProduct.getBtnSelectImg().setEnabled(editMode);
		if (editMode) {
			guiEditProduct.getBtnEdit().setIcon(new ImageIcon("files/icons/cancelEdit32.png"));
			guiEditProduct.getBtnEdit().setToolTipText("Cancel Changes...");
		} else {
			guiEditProduct.getBtnEdit().setIcon(new ImageIcon("files/icons/edit32.png"));
			guiEditProduct.getBtnEdit().setToolTipText("Edit selected product...");
			initializeComponents();
		}
		// Text & Comboboxes
		guiEditProduct.getTxtName().setEnabled(editMode);
		guiEditProduct.getsPPrice().setEnabled(editMode);
		guiEditProduct.gettADescription().setEnabled(editMode);
		guiEditProduct.getsPAmount().setEnabled(editMode);
		guiEditProduct.getcBType().setEnabled(editMode);
	}
	/**
	 * Handles applying changes. Only visual confirmations.
	 */
	public void handleApply() {
		int selection = JOptionPane.showConfirmDialog(guiEditProduct, "Are you sure you want to apply the changes?",
				"Confirm", JOptionPane.INFORMATION_MESSAGE);
		if (selection == JOptionPane.YES_OPTION)
			applyChanges();
		JOptionPane.showMessageDialog(guiEditProduct, "Applied Changes!", "Success", JOptionPane.INFORMATION_MESSAGE);
	}
	/**
	 * Applies the changes to the database. Calls a few methods from DatabaseUtils.
	 * After making the changes, it'll refresh the list and refresh all components.
	 * @see DatabaseUtils
	 */
	public void applyChanges() {
		DatabaseUtils.deleteProduct(products.get(index).getId());
		DatabaseUtils.insertProduct(products.get(index).getId(), guiEditProduct.getTxtName().getText(),
				guiEditProduct.gettADescription().getText(), (Double) guiEditProduct.getsPPrice().getValue(),
				(Integer) guiEditProduct.getsPAmount().getValue(),
				(String) guiEditProduct.getcBType().getSelectedItem(), ImageUtils.bufferedImageToByteArray(
						ImageUtils.iconToBufferedImage(guiEditProduct.getLblImage().getIcon()), "PNG"));
		// After making changes to products list
		products = DatabaseUtils.getAllProductsAsList();
		guiEditProduct.getTxtSearch().setText("");
		refreshTable();

	}
	/**
	 * Initialises the combo box for the type. Grabs all existing types and adds them into it.
	 */
	public void initializeCBType() {
		Set<String> typeSet = new HashSet<>();
		for (Product product : products)
			typeSet.add(product.getType());
		for (String string : typeSet)
			guiEditProduct.getcBType().addItem(string);
	}
	/**
	 * Initialises components. Probably the most important method of the class at it allows visualisation of
	 * the whole product, as well as it being responsible of, if there were no index, set all components to be
	 * either empty or disabled.
	 */
	public void initializeComponents() {
		if (index == -1) {
			guiEditProduct.getTxtName().setText("No Products found that meet search criteria!");
			guiEditProduct.getsPPrice().setValue(0);
			guiEditProduct.gettADescription().setText("");
			guiEditProduct.getsPAmount().setValue(0);
			guiEditProduct.getcBType().setSelectedItem("");
			guiEditProduct.getLblImage().setIcon(new ImageIcon());
			//Editing buttons
			if(editMode)
				editMode();
			guiEditProduct.getBtnEdit().setEnabled(false);
			guiEditProduct.getBtnDelete().setEnabled(false);
			return;
		}
		if (editMode)
			editMode();
		guiEditProduct.getBtnEdit().setEnabled(true);
		guiEditProduct.getBtnDelete().setEnabled(true);
		guiEditProduct.getTxtName().setText(products.get(index).getName());
		guiEditProduct.getsPPrice().setValue(products.get(index).getPrice());
		guiEditProduct.gettADescription().setText(products.get(index).getDescription());
		guiEditProduct.getsPAmount().setValue(products.get(index).getAmount());
		guiEditProduct.getcBType().setSelectedItem(products.get(index).getType());
		guiEditProduct.getLblImage()
				.setIcon(new ImageIcon(ImageUtils.fromBinaryToBufferedImage(products.get(index).getImage())));
	}
	/**
	 * Initialises the model for the JTable in the GUI. This method is the reason I had to divide the ListSelection
	 * Listener from the rest of the listeners.
	 * @return DefaultTableModel to apply to the JTable. 
	 */
	public DefaultTableModel initializeJTable() {
		String[] columnStrings = { "ID", "Name", "Description", "Price", "Amount", "Type", "Image" };
		DefaultTableModel defaultTableModel = new DefaultTableModel(columnStrings, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		for (Product product : products) {
			Object[] data = { product.getId(), product.getName(), product.getDescription(), product.getPrice(),
					product.getAmount(), product.getType(), product.getImage() };
			defaultTableModel.addRow(data);
		}
		return defaultTableModel;
	}
	/**
	 * Refreshes the table. Call in case anything is modified.
	 * @see handleDeletion() method
	 * @see handleApply() method
	 */
	public void refreshTable() {
		index = 0;
		guiEditProduct.getTable().setModel(initializeJTable());
	}
	/**
	 * Handles the deletion process.
	 */
	public void handleDeletion() {
		int selection = JOptionPane.showConfirmDialog(guiEditProduct,
				"Are you sure you want delete the selected product?", "Confirm", JOptionPane.INFORMATION_MESSAGE);
		if (selection == JOptionPane.YES_OPTION) {
			DatabaseUtils.deleteProduct(products.get(index).getId());
			JOptionPane.showMessageDialog(guiEditProduct, "Deleted product!", "Success",
					JOptionPane.INFORMATION_MESSAGE);
			// After making changes to products list
			guiEditProduct.getTxtSearch().setText("");
			products = DatabaseUtils.getAllProductsAsList();
			refreshTable();
		}
	}

	private class LSListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			index = guiEditProduct.getTable().getSelectedRow();
			initializeComponents();
		}

	}

	private class ActListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			if (btn == guiEditProduct.getBtnExit()) {
				new GUIAdminMenu(guiEditProduct);
				guiEditProduct.dispose();
			} else if (btn == guiEditProduct.getBtnApply()) {
				editMode();
				handleApply();
			} else if (btn == guiEditProduct.getBtnEdit()) {
				editMode();
			} else if (btn == guiEditProduct.getBtnSelectImg())
				ImageUtils.selectNewImg(guiEditProduct.getLblImage());
			else if (btn == guiEditProduct.getBtnDelete())
				handleDeletion();
		}

	}

	public void updateProductList(List<Product> products) {
		this.products = products;
		refreshTable();
	}

	private void filterProducts() {
		products = DatabaseUtils.getAllProductsAsList();
		String searchText = guiEditProduct.getTxtSearch().getText().toLowerCase();

		List<Product> filteredProducts = products.stream()
				.filter(product -> product.getName().toLowerCase().contains(searchText)).collect(Collectors.toList());

		updateProductList(filteredProducts);
		index = filteredProducts.isEmpty() ? -1 : 0;
		initializeComponents();
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
