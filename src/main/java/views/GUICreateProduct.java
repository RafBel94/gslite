package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controllers.ControllerCreateProduct;

public class GUICreateProduct extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel_textfields;
	private JLabel lbl_image;
	private JButton btn_return;
	private JScrollPane scroll_description;
	private JTextArea area_description;
	private JLabel lbl_productName;
	private JTextField txt_productName;
	private JLabel lbl_price;
	private JLabel lbl_description;
	private JLabel lbl_type;
	private JLabel lbl_amount;
	private JTextField txt_amount;
	private JTextField txt_price;
	private JButton btn_image;
	private JButton btn_accept;
	private JComboBox<String> combo_type;

	// This constructor receives " JFrame frame " but its empty because it's WIP
	//Edit: Done
	public GUICreateProduct(JFrame frame) {
		setTitle("Insert product");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 638, 488);
		setLocationRelativeTo(frame);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel_textfields = new JPanel();
		panel_textfields.setBounds(10, 11, 305, 243);
		contentPane.add(panel_textfields);
		panel_textfields.setLayout(null);

		lbl_productName = new JLabel("Product name:");
		lbl_productName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_productName.setBounds(10, 8, 143, 21);
		panel_textfields.add(lbl_productName);

		txt_productName = new JTextField();
		txt_productName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txt_productName.setBounds(10, 28, 266, 20);
		panel_textfields.add(txt_productName);
		txt_productName.setColumns(10);

		lbl_type = new JLabel("Type:");
		lbl_type.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_type.setBounds(10, 65, 116, 21);
		panel_textfields.add(lbl_type);

		lbl_amount = new JLabel("Stock available:");
		lbl_amount.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_amount.setBounds(10, 122, 116, 21);
		panel_textfields.add(lbl_amount);

		txt_amount = new JTextField();
		txt_amount.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txt_amount.setColumns(10);
		txt_amount.setBounds(10, 142, 93, 20);
		panel_textfields.add(txt_amount);

		lbl_price = new JLabel("Price:");
		lbl_price.setBounds(10, 179, 60, 21);
		panel_textfields.add(lbl_price);
		lbl_price.setFont(new Font("Tahoma", Font.PLAIN, 13));

		txt_price = new JTextField();
		txt_price.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txt_price.setBounds(10, 198, 93, 20);
		panel_textfields.add(txt_price);
		txt_price.setColumns(10);

		String[] types = { "Cooler", "Keyboard", "Mouse", "Tower Case", "Power Supply", "Memory", "Motherboard" };
		combo_type = new JComboBox<String>(types);
		combo_type.setFont(new Font("Tahoma", Font.PLAIN, 13));
		combo_type.setBounds(10, 85, 143, 22);
		panel_textfields.add(combo_type);

		lbl_image = new JLabel();
		lbl_image.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_image.setBounds(353, 11, 230, 230);
		lbl_image.setIcon(new ImageIcon("files/images/no-image.png"));
		contentPane.add(lbl_image);

		btn_return = new JButton("");
		btn_return.setContentAreaFilled(false);
		btn_return.setBounds(545, 374, 60, 60);
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
		scroll_description.setViewportView(area_description);

		lbl_description = new JLabel("Description:");
		lbl_description.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_description.setBounds(10, 265, 115, 21);
		contentPane.add(lbl_description);

		btn_image = new JButton("Select Image");
		btn_image.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_image.setIcon(new ImageIcon("files/icons/img30.png"));
		btn_image.setBounds(390, 245, 145, 23);
		contentPane.add(btn_image);

		btn_accept = new JButton("");
		btn_accept.setIcon(new ImageIcon("files/icons/accept-icon.png"));
		btn_accept.setPressedIcon(new ImageIcon("files/icons/accept-icon-pressed.png"));
		btn_accept.setContentAreaFilled(false);
		btn_accept.setBounds(545, 296, 60, 60);
		contentPane.add(btn_accept);

		new ControllerCreateProduct(this);

		setVisible(true);
	}

	public List<JTextField> getAllJTextFields() {
		List<JTextField> list = new ArrayList<>();

		list.add(txt_productName);
		list.add(txt_amount);
		list.add(txt_price);

		return list;
	}

	public void addActListener(ActionListener actListener) {
		btn_return.addActionListener(actListener);
		btn_accept.addActionListener(actListener);
		btn_image.addActionListener(actListener);
	}

	public JComboBox<String> getCombo_type() {
		return combo_type;
	}

	public void setCombo_type(JComboBox<String> combo_type) {
		this.combo_type = combo_type;
	}

	public JPanel getPanel_textfields() {
		return panel_textfields;
	}

	public void setPanel_textfields(JPanel panel_textfields) {
		this.panel_textfields = panel_textfields;
	}

	public JButton getBtn_accept() {
		return btn_accept;
	}

	public void setBtn_accept(JButton btn_accept) {
		this.btn_accept = btn_accept;
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

	public JLabel getLbl_price() {
		return lbl_price;
	}

	public void setLbl_price(JLabel lbl_price) {
		this.lbl_price = lbl_price;
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

	public JTextField getTxt_price() {
		return txt_price;
	}

	public void setTxt_price(JTextField txt_price) {
		this.txt_price = txt_price;
	}

	public JButton getBtn_image() {
		return btn_image;
	}

	public void setBtn_image(JButton btn_image) {
		this.btn_image = btn_image;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
