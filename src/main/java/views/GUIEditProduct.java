package views;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class GUIEditProduct extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSearch;
	private JScrollPane sPTable;
	private JSeparator separator;
	private JTextField txtName;
	private JLabel lblImage;
	private JTable table;
	private JTextArea tADescription;
	private JComboBox<String> cBType;
	private JSpinner sPAmount;
	private JButton btnEdit;
	private JTextField txtPrice;
	private JButton btnApply;
	private JButton btnExit;
	/**
	 * Create the frame.
	 */
	public GUIEditProduct(JFrame frame) {
		setResizable(false);
		setTitle("Edit Products");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 780, 654);
		setLocationRelativeTo(frame);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(10, 11, 744, 20);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		
		sPTable = new JScrollPane();
		sPTable.setBounds(10, 42, 744, 283);
		contentPane.add(sPTable);
		
		table = new JTable();
		table.setBounds(10, 42, 744, 284);
		sPTable.setViewportView(table);
		
		separator = new JSeparator();
		separator.setBounds(10, 336, 744, 2);
		contentPane.add(separator);
		
		txtName = new JTextField();
		txtName.setBounds(10, 349, 369, 33);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		lblImage = new JLabel("X");
		lblImage.setBounds(399, 372, 212, 211);
		contentPane.add(lblImage);
		
		tADescription = new JTextArea();
		tADescription.setBounds(10, 393, 369, 167);
		contentPane.add(tADescription);
		
		cBType = new JComboBox<>();
		cBType.setBounds(10, 571, 212, 33);
		contentPane.add(cBType);
		
		sPAmount = new JSpinner();
		sPAmount.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		sPAmount.setBounds(232, 571, 60, 33);
		contentPane.add(sPAmount);
		
		btnEdit = new JButton("Edit");
		btnEdit.setBounds(639, 349, 89, 70);
		contentPane.add(btnEdit);
		
		txtPrice = new JTextField();
		txtPrice.setBounds(302, 571, 77, 33);
		contentPane.add(txtPrice);
		txtPrice.setColumns(10);
		
		btnApply = new JButton("Apply");
		btnApply.setBounds(639, 442, 89, 70);
		contentPane.add(btnApply);
		
		btnExit = new JButton("Exit");
		btnExit.setBounds(639, 534, 89, 70);
		contentPane.add(btnExit);
		setVisible(true);
	}
	public void addActListeners(ActionListener actListener) {
		btnApply.addActionListener(actListener);
		btnEdit.addActionListener(actListener);
		btnExit.addActionListener(actListener);
	}
	public JTextField getTxtSearch() {
		return txtSearch;
	}
	public void setTxtSearch(JTextField txtSearch) {
		this.txtSearch = txtSearch;
	}
	public JScrollPane getsPTable() {
		return sPTable;
	}
	public void setsPTable(JScrollPane sPTable) {
		this.sPTable = sPTable;
	}
	public JSeparator getSeparator() {
		return separator;
	}
	public void setSeparator(JSeparator separator) {
		this.separator = separator;
	}
	public JTextField getTxtName() {
		return txtName;
	}
	public void setTxtName(JTextField txtName) {
		this.txtName = txtName;
	}
	public JLabel getLblImage() {
		return lblImage;
	}
	public void setLblImage(JLabel lblImage) {
		this.lblImage = lblImage;
	}
	public JTable getTable() {
		return table;
	}
	public void setTable(JTable table) {
		this.table = table;
	}
	public JTextArea gettADescription() {
		return tADescription;
	}
	public void settADescription(JTextArea tADescription) {
		this.tADescription = tADescription;
	}
	public JComboBox<String> getcBType() {
		return cBType;
	}
	public void setcBType(JComboBox<String> cBType) {
		this.cBType = cBType;
	}
	public JSpinner getsPAmount() {
		return sPAmount;
	}
	public void setsPAmount(JSpinner sPAmount) {
		this.sPAmount = sPAmount;
	}
	public JButton getBtnEdit() {
		return btnEdit;
	}
	public void setBtnEdit(JButton btnEdit) {
		this.btnEdit = btnEdit;
	}
	public JTextField getTxtPrice() {
		return txtPrice;
	}
	public void setTxtPrice(JTextField txtPrice) {
		this.txtPrice = txtPrice;
	}
	public JButton getBtnApply() {
		return btnApply;
	}
	public void setBtnApply(JButton btnApply) {
		this.btnApply = btnApply;
	}
	public JButton getBtnExit() {
		return btnExit;
	}
	public void setBtnExit(JButton btnExit) {
		this.btnExit = btnExit;
	}
	
}
