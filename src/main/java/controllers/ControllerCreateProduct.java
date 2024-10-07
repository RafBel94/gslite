package controllers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import utils.DatabaseUtils;
import utils.ImageUtils;
import utils.Validator;
import views.GUIAdminMenu;
import views.GUICreateProduct;

public class ControllerCreateProduct {
	private GUICreateProduct guiCreateProduct;
	/**
	 * Creates the Controller and initialises values.
	 * @param guiCreateProduct the GUICreateProduct that has this instance of the controller.
	 */
	public ControllerCreateProduct(GUICreateProduct guiCreateProduct) {
		super();
		this.guiCreateProduct = guiCreateProduct;
		
		guiCreateProduct.addActListener(new ActListener());
	}
	/**
	 * Private class to handle all button actions.
	 */
	private class ActListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			
			if (btn == guiCreateProduct.getBtn_return()) {
				new GUIAdminMenu(guiCreateProduct);
				guiCreateProduct.dispose();
			} else if (btn == guiCreateProduct.getBtn_image()) {
				ImageUtils.selectNewImg(guiCreateProduct.getLbl_image());;
			} else if (btn == guiCreateProduct.getBtn_accept()) {
				if(checkTextFieldsValues())
					insertProduct();
			}
		}
	}

		/**
		 * Applies a format to all the required fields and inserts a new product into the database.
		 * In the case of the image, it firsts parses the Icon of the JLabel to BufferedImage 
		 * and then parses it to a byte[].
		 */
		private void insertProduct() {
			String productName = Validator.formatBlankspaces(guiCreateProduct.getTxt_productName().getText());
			String description = guiCreateProduct.getArea_description().getText().trim();
			String type = guiCreateProduct.getCombo_type().getSelectedItem().toString();
			byte[] image = ImageUtils.bufferedImageToByteArray(ImageUtils.iconToBufferedImage(guiCreateProduct.getLbl_image().getIcon()), "PNG");
			double price = Double.parseDouble(Validator.formatBlankspaces(guiCreateProduct.getTxt_price().getText()));
			int amount = Integer.parseInt(Validator.formatBlankspaces(guiCreateProduct.getTxt_amount().getText()));
			
			if(DatabaseUtils.insertProduct(productName, description, price, amount, type, image) == 1) {
				JOptionPane.showMessageDialog(guiCreateProduct, "The product has been added to the catalog", "Product added succesfully", JOptionPane.INFORMATION_MESSAGE);
				guiCreateProduct.dispose();
				new GUIAdminMenu(guiCreateProduct);
			}else
				JOptionPane.showMessageDialog(guiCreateProduct, "There was an unknown error, please contact an administrator", "Failed to add product", JOptionPane.ERROR_MESSAGE);

		
	}
	
	/**
	 *  Iterates over an ArrayList applying a format to it's text and validating each one.
	 *  If it validates incorrectly, that JTextField border is painted red, and black otherwise.
	 *  In the case of the "txt_productName" JTextField it checks if it already exists in the database too.
	 * @return true if it verifies that all values are correct. False otherwise.
	 */
	public boolean checkTextFieldsValues() {
		List<JTextField> fields = guiCreateProduct.getAllJTextFields();
		
		boolean allVerified = true;
		
		for(JTextField textField : fields) {
			String text = Validator.formatBlankspaces(textField.getText());
			
			if(textField == guiCreateProduct.getTxt_productName()) {
				if(!Validator.validateProductName(text)) {
					textField.setBorder(new LineBorder(Color.RED));
					JOptionPane.showMessageDialog(guiCreateProduct, "The name field cannot be empty or contain special characters except for \".\" and \"-\" ", "Error in product name field", JOptionPane.WARNING_MESSAGE);
					allVerified = false;
				} else if(DatabaseUtils.stringFieldFound("products", "name", text)){
					textField.setBorder(new LineBorder(Color.RED));
					JOptionPane.showMessageDialog(guiCreateProduct, "There already exists a product with that name in the catalog", "Product already exists", JOptionPane.WARNING_MESSAGE);
					allVerified = false;
				}else {
					textField.setBorder(new LineBorder(Color.BLACK));
				}
			} else if(textField == guiCreateProduct.getTxt_price()) {
				if(!Validator.validateProductPrice(text)) {
					textField.setBorder(new LineBorder(Color.RED));
					JOptionPane.showMessageDialog(guiCreateProduct, "The price field can only contain numbers except for \".\" and have a total length of 9", "Error in price field", JOptionPane.WARNING_MESSAGE);
					allVerified = false;
				} else {
					textField.setBorder(new LineBorder(Color.BLACK));
				}
			} else if(textField == guiCreateProduct.getTxt_amount()) {
				if(!Validator.validateProductAmount(text)) {
					textField.setBorder(new LineBorder(Color.RED));
					JOptionPane.showMessageDialog(guiCreateProduct, "The amount field can only contain numbers", "Error in amount field", JOptionPane.WARNING_MESSAGE);
					allVerified = false;
				} else {
					textField.setBorder(new LineBorder(Color.BLACK));
				}
			}
		}
		
		return allVerified;
	}
}
