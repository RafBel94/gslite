package views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import controllers.ControllerAdminMenu;
import utils.ConnectionDB;

public class GUIAdminMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelTitle;
	private JPanel panelTitleCard;
	private JLabel lblTitle;
	private JSeparator separator;
	private JPanel panelButtons;
	private JButton btnAdd;
	private JButton btnCatalog;
	private JButton btnEdit;
	private JButton btnLogout;

	/**
	 * Create the frame.
	 */
	public GUIAdminMenu(JFrame frame) {
		setTitle("GSLite");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		setIconImage(new ImageIcon("files/icons/gsliteLogo32.png").getImage());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panelTitle = new JPanel();
		contentPane.add(panelTitle, BorderLayout.NORTH);
		panelTitle.setLayout(new BorderLayout(0, 0));
		
		panelTitleCard = new JPanel();
		panelTitle.add(panelTitleCard, BorderLayout.NORTH);
		
		lblTitle = new JLabel("Welcome back, " + ConnectionDB.getCurrentUser().getName());
		lblTitle.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		panelTitleCard.add(lblTitle);
		
		separator = new JSeparator();
		panelTitle.add(separator, BorderLayout.SOUTH);
		
		panelButtons = new JPanel();
		contentPane.add(panelButtons, BorderLayout.CENTER);
		panelButtons.setLayout(new GridLayout(0, 3, 0, 0));
		
		btnAdd = new JButton("");
		btnAdd.setToolTipText("Add a new product...");
		btnAdd.setIcon(new ImageIcon("files/icons/addIcon64.png"));
		panelButtons.add(btnAdd);
		
		btnCatalog = new JButton("");
		btnCatalog.setToolTipText("Browse the catalog...");
		btnCatalog.setIcon(new ImageIcon("files/icons/searchIcon64.png"));
		panelButtons.add(btnCatalog);
		
		btnEdit = new JButton("");
		btnEdit.setToolTipText("Edit the current products...");
		btnEdit.setIcon(new ImageIcon("files/icons/editIcon64.png"));
		panelButtons.add(btnEdit);
		
		btnLogout = new JButton("Log Out");
		contentPane.add(btnLogout, BorderLayout.SOUTH);
		new ControllerAdminMenu(this);
		setVisible(true);
	}
	
	public void addActListeners(ActionListener listener) {
		btnAdd.addActionListener(listener);
		btnCatalog.addActionListener(listener);
		btnEdit.addActionListener(listener);
		btnLogout.addActionListener(listener);
	}

	public JPanel getPanelTitle() {
		return panelTitle;
	}

	public void setPanelTitle(JPanel panelTitle) {
		this.panelTitle = panelTitle;
	}

	public JPanel getPanelTitleCard() {
		return panelTitleCard;
	}

	public void setPanelTitleCard(JPanel panelTitleCard) {
		this.panelTitleCard = panelTitleCard;
	}

	public JLabel getLblTitle() {
		return lblTitle;
	}

	public void setLblTitle(JLabel lblTitle) {
		this.lblTitle = lblTitle;
	}

	public JSeparator getSeparator() {
		return separator;
	}

	public void setSeparator(JSeparator separator) {
		this.separator = separator;
	}

	public JPanel getPanelButtons() {
		return panelButtons;
	}

	public void setPanelButtons(JPanel panelButtons) {
		this.panelButtons = panelButtons;
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}

	public void setBtnAdd(JButton btnAdd) {
		this.btnAdd = btnAdd;
	}

	public JButton getBtnCatalog() {
		return btnCatalog;
	}

	public void setBtnCatalog(JButton btnCatalog) {
		this.btnCatalog = btnCatalog;
	}

	public JButton getBtnEdit() {
		return btnEdit;
	}

	public void setBtnEdit(JButton btnEdit) {
		this.btnEdit = btnEdit;
	}

	public JButton getBtnLogout() {
		return btnLogout;
	}

	public void setBtnLogout(JButton btnLogout) {
		this.btnLogout = btnLogout;
	}

}
