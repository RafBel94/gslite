package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controllers.ControllerProductDetails;
import models.Product;
import utils.ImageUtils;

public class GUIProductDetails extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel_textfields;
	private JLabel lbl_image;
	private JButton btn_return;
	private JScrollPane scroll_description;
	private JTextArea area_description;
	private JLabel lbl_productName;
	private JTextField txt_productName;
	private JPanel panel_price;
	private JLabel lbl_price;
	private JLabel lbl_productPrice;
	private JLabel lbl_description;
	private JLabel lbl_type;
	private JTextField txt_type;
	private JLabel lbl_amount;
	private JTextField txt_amount;

	public GUIProductDetails(JFrame frame, Product product) {
		setTitle("GSLite");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 638, 488);
		setIconImage(new ImageIcon("files/icons/gsliteLogo32.png").getImage());
		setLocationRelativeTo(frame);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel_textfields = new JPanel();
		panel_textfields.setBounds(10, 11, 305, 184);
		contentPane.add(panel_textfields);
		panel_textfields.setLayout(null);
		
		lbl_productName = new JLabel("Product name:");
		lbl_productName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_productName.setBounds(10, 11, 143, 21);
		panel_textfields.add(lbl_productName);
		
		txt_productName = new JTextField();
		txt_productName.setEditable(false);
		txt_productName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txt_productName.setBounds(10, 32, 266, 20);
		txt_productName.setText(product.getName());
		panel_textfields.add(txt_productName);
		txt_productName.setColumns(10);
		
		lbl_type = new JLabel("Type:");
		lbl_type.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_type.setBounds(10, 64, 116, 21);
		panel_textfields.add(lbl_type);
		
		txt_type = new JTextField();
		txt_type.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txt_type.setEditable(false);
		txt_type.setColumns(10);
		txt_type.setBounds(10, 85, 133, 20);
		txt_type.setText(product.getType());
		panel_textfields.add(txt_type);
		
		lbl_amount = new JLabel("Stock available:");
		lbl_amount.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_amount.setBounds(10, 116, 116, 21);
		panel_textfields.add(lbl_amount);
		
		txt_amount = new JTextField();
		txt_amount.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txt_amount.setEditable(false);
		txt_amount.setColumns(10);
		txt_amount.setBounds(10, 137, 93, 20);
		txt_amount.setText(String.valueOf(product.getAmount()));
		panel_textfields.add(txt_amount);
		
		lbl_image = new JLabel();
		lbl_image.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lbl_image.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_image.setBounds(353, 11, 230, 230);
		lbl_image.setIcon(new ImageIcon(ImageUtils.fromBinaryToBufferedImage(product.getImage())));
		contentPane.add(lbl_image);
		
		btn_return = new JButton("");
		btn_return.setContentAreaFilled(false);
		btn_return.setBounds(545, 378, 60, 60);
		btn_return.setIcon(new ImageIcon("files/icons/return-icon.png"));
		btn_return.setPressedIcon(new ImageIcon("files/icons/return-icon-pressed.png"));
		contentPane.add(btn_return);
		
		scroll_description = new JScrollPane();
		scroll_description.setBorder(new LineBorder(Color.LIGHT_GRAY));
		scroll_description.setBounds(10, 286, 525, 157);
		contentPane.add(scroll_description);
		
		area_description = new JTextArea();
		area_description.setLineWrap(true);
		area_description.setWrapStyleWord(true);
		area_description.setText(product.getDescription());
		scroll_description.setViewportView(area_description);
		
		panel_price = new JPanel();
		panel_price.setBounds(353, 240, 230, 46);
		contentPane.add(panel_price);
		panel_price.setLayout(null);
		
		lbl_price = new JLabel("Price:");
		lbl_price.setBounds(36, 11, 60, 21);
		lbl_price.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel_price.add(lbl_price);
		
		lbl_productPrice = new JLabel("---");
		lbl_productPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_productPrice.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl_productPrice.setBounds(92, 11, 100, 21);
		lbl_productPrice.setText(String.valueOf(product.getPrice()));
		panel_price.add(lbl_productPrice);
		
		lbl_description = new JLabel("Description:");
		lbl_description.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_description.setBounds(10, 265, 115, 21);
		contentPane.add(lbl_description);
		
		new ControllerProductDetails(this);
		
		setVisible(true);
	}
	
	public void addActListener(ActionListener actListener) {
		btn_return.addActionListener(actListener);
	}

	public JPanel getPanel_textfields() {
		return panel_textfields;
	}

	public void setPanel_textfields(JPanel panel_textfields) {
		this.panel_textfields = panel_textfields;
	}

	public JLabel getLbl_image() {
		return lbl_image;
	}

	public void setLbl_image(JLabel lbl_image) {
		this.lbl_image = lbl_image;
	}

	public JButton getBtn_return() {
		return btn_return;
	}

	public void setBtn_return(JButton btn_return) {
		this.btn_return = btn_return;
	}

	public JScrollPane getScroll_description() {
		return scroll_description;
	}

	public void setScroll_description(JScrollPane scroll_description) {
		this.scroll_description = scroll_description;
	}

	public JTextArea getArea_description() {
		return area_description;
	}

	public void setArea_description(JTextArea area_description) {
		this.area_description = area_description;
	}

	public JLabel getLbl_productName() {
		return lbl_productName;
	}

	public void setLbl_productName(JLabel lbl_productName) {
		this.lbl_productName = lbl_productName;
	}

	public JTextField getTxt_productName() {
		return txt_productName;
	}

	public void setTxt_productName(JTextField txt_productName) {
		this.txt_productName = txt_productName;
	}

	public JPanel getPanel_price() {
		return panel_price;
	}

	public void setPanel_price(JPanel panel_price) {
		this.panel_price = panel_price;
	}

	public JLabel getLbl_price() {
		return lbl_price;
	}

	public void setLbl_price(JLabel lbl_price) {
		this.lbl_price = lbl_price;
	}

	public JLabel getLbl_productPrice() {
		return lbl_productPrice;
	}

	public void setLbl_productPrice(JLabel lbl_productPrice) {
		this.lbl_productPrice = lbl_productPrice;
	}

	public JLabel getLbl_description() {
		return lbl_description;
	}

	public void setLbl_description(JLabel lbl_description) {
		this.lbl_description = lbl_description;
	}

	public JLabel getLbl_type() {
		return lbl_type;
	}

	public void setLbl_type(JLabel lbl_type) {
		this.lbl_type = lbl_type;
	}

	public JTextField getTxt_type() {
		return txt_type;
	}

	public void setTxt_type(JTextField txt_type) {
		this.txt_type = txt_type;
	}

	public JLabel getLbl_amount() {
		return lbl_amount;
	}

	public void setLbl_amount(JLabel lbl_amount) {
		this.lbl_amount = lbl_amount;
	}

	public JTextField getTxt_amount() {
		return txt_amount;
	}

	public void setTxt_amount(JTextField txt_amount) {
		this.txt_amount = txt_amount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
