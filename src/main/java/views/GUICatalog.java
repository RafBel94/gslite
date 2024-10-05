package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;

import controllers.ControllerCatalog;

public class GUICatalog extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel_searchBar;
	private JLabel lbl_searchBar;
	private JTextField txt_searchBar;
	private JSeparator separator_searchbar;
	private JScrollPane scroll_catalog;
	private JList<String> list_catalog;
	private JLabel lbl_image;
	private JSeparator separator_list;
	private JLabel lbl_product;
	private JLabel lbl_productName;
	private JLabel lbl_description;
	private JTextArea area_description;
	private JButton btn_details;
	private DefaultListModel<String> listModel;
	private JScrollPane scroll_description;
	private JButton btn_logout;

	public GUICatalog(JFrame frame) {
		setTitle("Catalog");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 570);
		setLocationRelativeTo(frame);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		btn_logout = new JButton("Log out");
		btn_logout.setBounds(549, 6, 89, 23);
		contentPane.add(btn_logout);

		panel_searchBar = new JPanel();
		panel_searchBar.setBounds(10, 11, 639, 51);
		contentPane.add(panel_searchBar);
		panel_searchBar.setLayout(null);

		lbl_searchBar = new JLabel("Search:");
		lbl_searchBar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_searchBar.setBounds(9, 7, 107, 14);
		panel_searchBar.add(lbl_searchBar);

		txt_searchBar = new JTextField();
		txt_searchBar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txt_searchBar.setBounds(9, 25, 619, 20);
		panel_searchBar.add(txt_searchBar);
		txt_searchBar.setColumns(10);

		separator_searchbar = new JSeparator();
		separator_searchbar.setBounds(20, 73, 617, 10);
		contentPane.add(separator_searchbar);

		scroll_catalog = new JScrollPane();
		scroll_catalog.setBorder(new LineBorder(Color.LIGHT_GRAY));
		scroll_catalog.setBounds(20, 94, 268, 426);
		contentPane.add(scroll_catalog);

		listModel = new DefaultListModel<String>();
		list_catalog = new JList<>(listModel);
		scroll_catalog.setViewportView(list_catalog);

		lbl_image = new JLabel("");
		lbl_image.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_image.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lbl_image.setBounds(372, 94, 230, 230);
		contentPane.add(lbl_image);

		separator_list = new JSeparator();
		separator_list.setOrientation(SwingConstants.VERTICAL);
		separator_list.setBounds(308, 94, 13, 426);
		contentPane.add(separator_list);

		lbl_product = new JLabel("Product:");
		lbl_product.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_product.setBounds(323, 356, 107, 19);
		contentPane.add(lbl_product);

		lbl_productName = new JLabel("No product selected");
		lbl_productName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl_productName.setBounds(323, 373, 311, 23);
		contentPane.add(lbl_productName);

		lbl_description = new JLabel("Description:");
		lbl_description.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_description.setBounds(323, 399, 116, 19);
		contentPane.add(lbl_description);

		btn_details = new JButton("View details");
		btn_details.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_details.setBounds(429, 329, 116, 25);
		contentPane.add(btn_details);

		scroll_description = new JScrollPane();
		scroll_description.setBounds(323, 429, 313, 91);
		contentPane.add(scroll_description);

		area_description = new JTextArea();
		scroll_description.setViewportView(area_description);
		area_description.setWrapStyleWord(true);
		area_description.setLineWrap(true);
		area_description.setEditable(false);
		area_description.setBorder(new LineBorder(Color.LIGHT_GRAY));

		new ControllerCatalog(this);

		setVisible(true);
	}

	public void addActListener(ActionListener actListener) {
		btn_details.addActionListener(actListener);
		btn_logout.addActionListener(actListener);
	}

	public void addDocuListener(DocumentListener docuListener) {
		txt_searchBar.getDocument().addDocumentListener(docuListener);
	}

	public void addItemSelectListener(ListSelectionListener selectListener) {
		list_catalog.addListSelectionListener(selectListener);
	}

	public JButton getBtn_logout() {
		return btn_logout;
	}

	public void setBtn_logout(JButton btn_logout) {
		this.btn_logout = btn_logout;
	}

	public DefaultListModel<String> getListModel() {
		return listModel;
	}

	public void setListModel(DefaultListModel<String> listModel) {
		this.listModel = listModel;
	}

	public JPanel getPanel_searchBar() {
		return panel_searchBar;
	}

	public void setPanel_searchBar(JPanel panel_searchBar) {
		this.panel_searchBar = panel_searchBar;
	}

	public JLabel getLbl_searchBar() {
		return lbl_searchBar;
	}

	public void setLbl_searchBar(JLabel lbl_searchBar) {
		this.lbl_searchBar = lbl_searchBar;
	}

	public JScrollPane getScroll_description() {
		return scroll_description;
	}

	public void setScroll_description(JScrollPane scroll_description) {
		this.scroll_description = scroll_description;
	}

	public JTextField getTxt_searchBar() {
		return txt_searchBar;
	}

	public void setTxt_searchBar(JTextField txt_searchBar) {
		this.txt_searchBar = txt_searchBar;
	}

	public JScrollPane getScroll_catalog() {
		return scroll_catalog;
	}

	public void setScroll_catalog(JScrollPane scroll_catalog) {
		this.scroll_catalog = scroll_catalog;
	}

	public JList<String> getList_catalog() {
		return list_catalog;
	}

	public void setList_catalog(JList<String> list_catalog) {
		this.list_catalog = list_catalog;
	}

	public JLabel getLbl_image() {
		return lbl_image;
	}

	public void setLbl_image(JLabel lbl_image) {
		this.lbl_image = lbl_image;
	}

	public JLabel getLbl_product() {
		return lbl_product;
	}

	public void setLbl_product(JLabel lbl_product) {
		this.lbl_product = lbl_product;
	}

	public JLabel getLbl_productName() {
		return lbl_productName;
	}

	public void setLbl_productName(JLabel lbl_productName) {
		this.lbl_productName = lbl_productName;
	}

	public JLabel getLbl_description() {
		return lbl_description;
	}

	public void setLbl_description(JLabel lbl_description) {
		this.lbl_description = lbl_description;
	}

	public JTextArea getArea_description() {
		return area_description;
	}

	public void setArea_description(JTextArea area_description) {
		this.area_description = area_description;
	}

	public JButton getBtn_details() {
		return btn_details;
	}

	public void setBtn_details(JButton btn_details) {
		this.btn_details = btn_details;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
