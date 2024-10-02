package views;

import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controllers.ControllerConnect;

public class GUIConnect extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelLogo;
	private JPanel panelConnecting;
	private JLabel lblLogo;
	private JLabel lblConnecting;
	/**
	 * Create the frame.
	 */
	public GUIConnect() {
		setTitle("Connection");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 388);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		panelLogo = new JPanel();
		contentPane.add(panelLogo);
		
		lblLogo = new JLabel("");
		panelLogo.add(lblLogo);
		lblLogo.setIcon(new ImageIcon("files/images/InmoManager291.png"));
		panelConnecting = new JPanel();
		contentPane.add(panelConnecting);
		
		lblConnecting = new JLabel("Connecting...");
		lblConnecting.setFont(new Font("Yu Gothic UI", Font.PLAIN, 36));
		panelConnecting.add(lblConnecting);
		
		setVisible(true);
		new ControllerConnect(this);
	}

}
