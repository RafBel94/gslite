package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controllers.ControllerLogin;

public class GUILogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelLogo;
	private JPanel panelData;
	private JPanel panelRegister;
	private JLabel lblgsl;
	private JLabel lblRegister;
	private JButton btnRegister;
	private JPanel panelLoginExit;
	private JButton btnLogin;
	private JButton btnExit;
	private JPanel panelUserPass;
	private JPanel panelWelcome;
	private JLabel lblWelcome;
	private JPanel panelUsername;
	private JPanel panelPassword;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JPasswordField pwPassword;
	private JTextField txtUsername;
	private JPanel panelError;
	private JLabel lblError;
	private Font fontUserPassErr = new Font("Yu Gothic UI Semibold", Font.PLAIN, 13);
	private JSeparator separator;

	/**
	 * Create the frame.
	 */
	public GUILogin(JFrame frame) {
		setTitle("GSLite");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 420, 360);
		setIconImage(new ImageIcon("files/icons/gsliteLogo32.png").getImage());
		setLocationRelativeTo(frame);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panelLogo = new JPanel();
		contentPane.add(panelLogo, BorderLayout.NORTH);
		
		lblgsl = new JLabel();
		lblgsl.setIcon(new ImageIcon("files/icons/gsliteLogo100.png"));
		panelLogo.add(lblgsl);
		
		panelData = new JPanel();
		contentPane.add(panelData, BorderLayout.CENTER);
		panelData.setLayout(new BorderLayout(0, 0));
		
		panelLoginExit = new JPanel();
		panelData.add(panelLoginExit, BorderLayout.SOUTH);
		
		btnLogin = new JButton("Login");
		panelLoginExit.add(btnLogin);
		
		btnExit = new JButton("Exit");
		panelLoginExit.add(btnExit);
		
		panelUserPass = new JPanel();
		panelData.add(panelUserPass, BorderLayout.CENTER);
		panelUserPass.setLayout(new BoxLayout(panelUserPass, BoxLayout.Y_AXIS));
		
		separator = new JSeparator();
		panelUserPass.add(separator);
		
		panelUsername = new JPanel();
		panelUserPass.add(panelUsername);
		
		lblUsername = new JLabel("Username:");
		panelUsername.add(lblUsername);
		lblUsername.setFont(fontUserPassErr);
		
		txtUsername = new JTextField();
		panelUsername.add(txtUsername);
		txtUsername.setColumns(15);
		
		panelPassword = new JPanel();
		panelUserPass.add(panelPassword);
		
		lblPassword = new JLabel("Password:");
		panelPassword.add(lblPassword);
		lblPassword.setFont(fontUserPassErr);
		
		pwPassword = new JPasswordField();
		pwPassword.setColumns(15);
		panelPassword.add(pwPassword);
		
		panelError = new JPanel();
		panelUserPass.add(panelError);
		
		lblError = new JLabel("");
		lblError.setForeground(new Color(255, 0, 0));
		panelError.add(lblError);
		lblError.setFont(fontUserPassErr);
		
		panelWelcome = new JPanel();
		panelData.add(panelWelcome, BorderLayout.NORTH);
		
		lblWelcome = new JLabel("Welcome!");
		lblWelcome.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		panelWelcome.add(lblWelcome);
		
		panelRegister = new JPanel();
		contentPane.add(panelRegister, BorderLayout.SOUTH);
		
		lblRegister = new JLabel("Haven't registered yet?");
		lblRegister.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 11));
		panelRegister.add(lblRegister);
		
		btnRegister = new JButton("Register");
		panelRegister.add(btnRegister);
		//Adding the corresponding controller class
		new ControllerLogin(this);
		setVisible(true);
	}
	//Add action listeners
	public void addActListener(ActionListener listener) {
		btnLogin.addActionListener(listener);
		btnRegister.addActionListener(listener);
		btnExit.addActionListener(listener);
	}
	public JLabel getLblGsl() {
		return lblgsl;
	}
	public JLabel getLblRegister() {
		return lblRegister;
	}
	public JButton getBtnRegister() {
		return btnRegister;
	}
	public JButton getBtnLogin() {
		return btnLogin;
	}
	public JButton getBtnExit() {
		return btnExit;
	}
	public JPasswordField getPwPassword() {
		return pwPassword;
	}
	public JLabel getLblError() {
		return lblError;
	}
	public JTextField getTxtUsername() {
		return txtUsername;
	}
	
}
