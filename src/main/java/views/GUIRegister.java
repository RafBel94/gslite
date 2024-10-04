package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controllers.ControllerRegister;

public class GUIRegister extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelTitle;
	private JPanel panelBottom;
	private JPanel panelTitleCard;
	private JSeparator separator;
	private JLabel lblRegister;
	private JPanel panelData;
	private JLabel lblUsername;
	private JTextField txtUsername;
	private JLabel lblEmail;
	private JTextField txtEmail;
	private JLabel lblPassword;
	private JPasswordField pwField;
	private JLabel lblRepeatPw;
	private JPasswordField passwordField;
	private JPanel panelButtons;
	private JPanel panelError;
	private JLabel lblError;
	private JButton btnRegister;
	private JButton btnCancel;

	/**
	 * Create the frame.
	 */
	public GUIRegister(JFrame frame) {
		setTitle("Register");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 389, 340);
		setLocationRelativeTo(frame);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panelTitle = new JPanel();
		contentPane.add(panelTitle, BorderLayout.NORTH);
		panelTitle.setLayout(new BorderLayout(0, 0));
		
		panelTitleCard = new JPanel();
		panelTitle.add(panelTitleCard, BorderLayout.NORTH);
		
		lblRegister = new JLabel("Register");
		lblRegister.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
		panelTitleCard.add(lblRegister);
		
		separator = new JSeparator();
		panelTitle.add(separator, BorderLayout.SOUTH);
		
		panelBottom = new JPanel();
		contentPane.add(panelBottom, BorderLayout.SOUTH);
		panelBottom.setLayout(new BorderLayout(0, 0));
		
		panelButtons = new JPanel();
		panelBottom.add(panelButtons, BorderLayout.CENTER);
		
		btnRegister = new JButton("OK");
		panelButtons.add(btnRegister);
		
		btnCancel = new JButton("Cancel");
		panelButtons.add(btnCancel);
		
		panelError = new JPanel();
		panelBottom.add(panelError, BorderLayout.NORTH);
		
		lblError = new JLabel("<html><br>By registering, you <strong>agree</strong> to our <a href=''>Terms of Service.</a><br><br><br>");
		lblError.setForeground(new Color(255, 0, 0));
		panelError.add(lblError);
		
		panelData = new JPanel();
		contentPane.add(panelData, BorderLayout.CENTER);
		panelData.setLayout(new GridLayout(4, 2, 0, 0));
		
		lblUsername = new JLabel("Username:");
		panelData.add(lblUsername);
		
		txtUsername = new JTextField();
		panelData.add(txtUsername);
		txtUsername.setColumns(10);
		
		lblPassword = new JLabel("Password:");
		panelData.add(lblPassword);
		
		pwField = new JPasswordField();
		panelData.add(pwField);
		
		lblRepeatPw = new JLabel("Repeat Password:");
		panelData.add(lblRepeatPw);
		
		passwordField = new JPasswordField();
		panelData.add(passwordField);
		
		lblEmail = new JLabel("Email:");
		panelData.add(lblEmail);
		
		txtEmail = new JTextField();
		panelData.add(txtEmail);
		txtEmail.setColumns(10);
		new ControllerRegister(this);
		setVisible(true);
	}
	
	public void addActListeners(ActionListener listener) {
		btnCancel.addActionListener(listener);
		btnRegister.addActionListener(listener);
	}

	public JPanel getPanelTitle() {
		return panelTitle;
	}

	public void setPanelTitle(JPanel panelTitle) {
		this.panelTitle = panelTitle;
	}

	public JPanel getPanelBottom() {
		return panelBottom;
	}

	public void setPanelBottom(JPanel panelBottom) {
		this.panelBottom = panelBottom;
	}

	public JPanel getPanelTitleCard() {
		return panelTitleCard;
	}

	public void setPanelTitleCard(JPanel panelTitleCard) {
		this.panelTitleCard = panelTitleCard;
	}

	public JSeparator getSeparator() {
		return separator;
	}

	public void setSeparator(JSeparator separator) {
		this.separator = separator;
	}

	public JLabel getLblRegister() {
		return lblRegister;
	}

	public void setLblRegister(JLabel lblRegister) {
		this.lblRegister = lblRegister;
	}

	public JPanel getPanelData() {
		return panelData;
	}

	public void setPanelData(JPanel panelData) {
		this.panelData = panelData;
	}

	public JLabel getLblUsername() {
		return lblUsername;
	}

	public void setLblUsername(JLabel lblUsername) {
		this.lblUsername = lblUsername;
	}

	public JTextField getTxtUsername() {
		return txtUsername;
	}

	public void setTxtUsername(JTextField txtUsername) {
		this.txtUsername = txtUsername;
	}

	public JLabel getLblEmail() {
		return lblEmail;
	}

	public void setLblEmail(JLabel lblEmail) {
		this.lblEmail = lblEmail;
	}

	public JTextField getTxtEmail() {
		return txtEmail;
	}

	public void setTxtEmail(JTextField txtEmail) {
		this.txtEmail = txtEmail;
	}

	public JLabel getLblPassword() {
		return lblPassword;
	}

	public void setLblPassword(JLabel lblPassword) {
		this.lblPassword = lblPassword;
	}

	public JPasswordField getPwField() {
		return pwField;
	}

	public void setPwField(JPasswordField pwField) {
		this.pwField = pwField;
	}

	public JLabel getLblRepeatPw() {
		return lblRepeatPw;
	}

	public void setLblRepeatPw(JLabel lblRepeatPw) {
		this.lblRepeatPw = lblRepeatPw;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}

	public JPanel getPanelButtons() {
		return panelButtons;
	}

	public void setPanelButtons(JPanel panelButtons) {
		this.panelButtons = panelButtons;
	}

	public JPanel getPanelError() {
		return panelError;
	}

	public void setPanelError(JPanel panelError) {
		this.panelError = panelError;
	}

	public JLabel getLblError() {
		return lblError;
	}

	public void setLblError(JLabel lblError) {
		this.lblError = lblError;
	}

	public JButton getBtnRegister() {
		return btnRegister;
	}

	public void setBtnRegister(JButton btnRegister) {
		this.btnRegister = btnRegister;
	}

	public JButton getBtnCancel() {
		return btnCancel;
	}

	public void setBtnCancel(JButton btnCancel) {
		this.btnCancel = btnCancel;
	}

}
