package controllers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.imgscalr.Scalr;

import utils.ConnectionDB;
import utils.ImageUtils;
import utils.Validator;
import views.GUICreateProduct;

public class ControllerCreateProduct {
	private GUICreateProduct guiCreateProduct;

	public ControllerCreateProduct(GUICreateProduct guiCreateProduct) {
		super();
		this.guiCreateProduct = guiCreateProduct;
		
		guiCreateProduct.addActListener(new ActListener());
	}
	
	private class ActListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			
			if (btn == guiCreateProduct.getBtn_return()) {
				// new GUIManageProducts(guiCreateProduct);
				// guiCreateProduct.dispose();
			} else if (btn == guiCreateProduct.getBtn_image()) {
				handleImageSelection();
			} else if (btn == guiCreateProduct.getBtn_accept()) {
				if(checkTextFieldsValues())
					insertProduct();
			}
		}

		// Applies a format to all the required fields and inserts a new product into the database
		// In the case of the image, it firsts parses the Icon of the JLabel to BufferedImage and then parses it to byte[]
		private void insertProduct() {
			String productName = Validator.formatBlankspaces(guiCreateProduct.getTxt_productName().getText());
			String description = guiCreateProduct.getArea_description().getText().trim();
			String type = guiCreateProduct.getCombo_type().getSelectedItem().toString();
			byte[] image = ImageUtils.bufferedImageToByteArray(ImageUtils.iconToBufferedImage(guiCreateProduct.getLbl_image().getIcon()), "PNG");
			double price = Double.parseDouble(Validator.formatBlankspaces(guiCreateProduct.getTxt_price().getText()));
			int amount = Integer.parseInt(Validator.formatBlankspaces(guiCreateProduct.getTxt_amount().getText()));
			
			try {
				Connection connection = ConnectionDB.connect();

				String sql = "INSERT INTO products (name, description, price, amount, type, image) VALUES (?, ?, ?, ?, ?, ?)";

				PreparedStatement pstmt = connection.prepareStatement(sql);
				
				pstmt.setString(1, productName);
				pstmt.setString(2, description);
				pstmt.setDouble(3, price);
				pstmt.setInt(4, amount);
				pstmt.setString(5, type);
				pstmt.setBytes(6, image);

				int operationResult = pstmt.executeUpdate();
				
				if(operationResult == 1) {
					JOptionPane.showMessageDialog(guiCreateProduct, "The product has been added to the catalog", "Product added succesfully", JOptionPane.INFORMATION_MESSAGE);
					guiCreateProduct.dispose();
				}else
					JOptionPane.showMessageDialog(guiCreateProduct, "There was an unknown error, please contact an administrator", "Failed to add product", JOptionPane.ERROR_MESSAGE);

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	// Iterates over an ArrayList applying a format to it's text and validating each one
	// If it validates incorrectly, that JTextField border is painted red, and black otherwise
	// In the case of the "txt_productName" JTextField it checks if it already exists in the database too
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
				} else if(searchProductInDatabase(text)){
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
		
		if (allVerified)
			return true;
		else
			return false;
	}

	// Search for products with the same name in the database
	private boolean searchProductInDatabase(String text) {
		String sql = "SELECT name FROM products";
		
		try {
			Connection conn = ConnectionDB.connect();
			
			ResultSet resultSet = conn.createStatement().executeQuery(sql);
			
			while(resultSet.next())
				if(resultSet.getString("name").equalsIgnoreCase(text))
					return true;
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	// Opens the JFileChooser with a filter and sets the JLabel "lbl_image" with the choosed image
	// Applies a resize to the choosed image to fit correctly into the JLabel
	private void handleImageSelection() {
		BufferedImage bufferedImage = null;

		JFileChooser fileChooser = new JFileChooser();

		FileNameExtensionFilter filter = new FileNameExtensionFilter("Select only files with extension: .png", "png");

		fileChooser.setFileFilter(filter);

		int result = fileChooser.showOpenDialog(null);

		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();

			try {
				bufferedImage = Scalr.resize(ImageIO.read(selectedFile), 230, 230);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

			guiCreateProduct.getLbl_image().setIcon(new ImageIcon(bufferedImage));
		}
	}
}
