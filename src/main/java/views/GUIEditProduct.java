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
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;

import controllers.ControllerEditProduct;
import javax.swing.SpinnerNumberModel;
import javax.swing.ImageIcon;

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
	private JButton btnApply;
	private JButton btnExit;
	private JButton btnSelectImg;
	private JSpinner sPPrice;
	private JButton btnDelete;

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

		
		separator = new JSeparator();
		separator.setBounds(10, 336, 744, 2);
		contentPane.add(separator);

		txtName = new JTextField();
		txtName.setFont(new Font("Yu Gothic UI", Font.PLAIN, 15));
		txtName.setEnabled(false);
		txtName.setBounds(10, 349, 369, 33);
		contentPane.add(txtName);
		txtName.setColumns(10);

		lblImage = new JLabel();
		lblImage.setBounds(399, 349, 212, 211);
		contentPane.add(lblImage);

		tADescription = new JTextArea();
		tADescription.setLineWrap(true);
		tADescription.setWrapStyleWord(true);
		tADescription.setEnabled(false);
		tADescription.setBounds(10, 393, 369, 167);
		contentPane.add(tADescription);

		cBType = new JComboBox<>();
		cBType.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		cBType.setEnabled(false);
		cBType.setBounds(10, 571, 147, 33);
		contentPane.add(cBType);

		sPAmount = new JSpinner();
		sPAmount.setEnabled(false);
		sPAmount.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		sPAmount.setBounds(167, 571, 96, 33);
		contentPane.add(sPAmount);

		sPPrice = new JSpinner();
		sPPrice.setEnabled(false);
		sPPrice.setModel(new SpinnerNumberModel(Double.valueOf(0), null, null, Double.valueOf(1)));
		sPPrice.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		sPPrice.setBounds(273, 571, 106, 33);
		contentPane.add(sPPrice);
		
		btnEdit = new JButton("");
		btnEdit.setIcon(new ImageIcon(GUIEditProduct.class.getResource("/icons/edit32.png")));
		btnEdit.setToolTipText("Edit selected product...");
		btnEdit.setBounds(639, 349, 89, 52);
		contentPane.add(btnEdit);

		btnApply = new JButton("");
		btnApply.setIcon(new ImageIcon(GUIEditProduct.class.getResource("/icons/tick32.png")));
		btnApply.setToolTipText("Apply Changes");
		btnApply.setBounds(639, 412, 89, 52);
		contentPane.add(btnApply);

		btnExit = new JButton("");
		btnExit.setIcon(new ImageIcon(GUIEditProduct.class.getResource("/icons/return-icon.png")));
		btnExit.setPressedIcon(new ImageIcon("files/icons/return-icon-pressed.png"));
		btnExit.setToolTipText("Return to Admin Menu");
		btnExit.setBounds(639, 534, 89, 70);
		contentPane.add(btnExit);
		
		btnDelete = new JButton("");
		btnDelete.setIcon(new ImageIcon(GUIEditProduct.class.getResource("/icons/delete32.png")));
		btnDelete.setToolTipText("Delete selected product...");
		btnDelete.setBounds(639, 471, 89, 52);
		contentPane.add(btnDelete);
		
		btnSelectImg = new JButton("Select Image...");
		btnSelectImg.setIcon(new ImageIcon(GUIEditProduct.class.getResource("/icons/img30.png")));
		btnSelectImg.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		btnSelectImg.setBounds(399, 571, 212, 33);
		contentPane.add(btnSelectImg);
		
		sPTable = new JScrollPane();
		sPTable.setBounds(10, 42, 744, 283);
		contentPane.add(sPTable);

		ControllerEditProduct cep = new ControllerEditProduct(this);
		table = new JTable(cep.initializeJTable());
		table.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		table.setRowHeight(20);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBounds(10, 42, 744, 284);
		sPTable.setViewportView(table);
		
		
		cep.initTableSelectionListener();
		setVisible(true);
	}
	

	public void addLSListeners (ListSelectionListener listener) {
		table.getSelectionModel().addListSelectionListener(listener);
	}

	public void addActListeners(ActionListener actListener) {
		btnApply.addActionListener(actListener);
		btnEdit.addActionListener(actListener);
		btnExit.addActionListener(actListener);
		btnDelete.addActionListener(actListener);
		btnSelectImg.addActionListener(actListener);
	}
	

	public JButton getBtnDelete() {
		return btnDelete;
	}


	public void setBtnDelete(JButton btnDelete) {
		this.btnDelete = btnDelete;
	}


	public JButton getBtnSelectImg() {
		return btnSelectImg;
	}

	public void setBtnSelectImg(JButton btnSelectImg) {
		this.btnSelectImg = btnSelectImg;
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
	
	public JSpinner getsPPrice() {
		return sPPrice;
	}

	public void setsPPrice(JSpinner sPPrice) {
		this.sPPrice = sPPrice;
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
