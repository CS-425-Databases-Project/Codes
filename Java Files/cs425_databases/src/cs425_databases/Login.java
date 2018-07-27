package cs425_databases;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JPasswordField;

public class Login {

	private JFrame frmHttpwwwstorecom;
	private JTextField textField;
	private JTextField textField_2;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmHttpwwwstorecom.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHttpwwwstorecom = new JFrame();
		frmHttpwwwstorecom.getContentPane().setBackground(Color.BLACK);
		frmHttpwwwstorecom.setTitle("http://www.store.com/login.aspx?src=common");
		frmHttpwwwstorecom.setBounds(100, 100, 350, 250);
		frmHttpwwwstorecom.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHttpwwwstorecom.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setBounds(65, 37, 74, 16);
		frmHttpwwwstorecom.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setBounds(65, 65, 74, 16);
		frmHttpwwwstorecom.getContentPane().add(lblPassword);
		
		JLabel lblZipcode = new JLabel("Zipcode:");
		lblZipcode.setForeground(Color.WHITE);
		lblZipcode.setBounds(65, 93, 74, 16);
		frmHttpwwwstorecom.getContentPane().add(lblZipcode);
		
		textField = new JTextField();
		textField.setBounds(150, 32, 130, 26);
		frmHttpwwwstorecom.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(150, 88, 130, 26);
		frmHttpwwwstorecom.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(189, 126, 95, 29);
		frmHttpwwwstorecom.getContentPane().add(btnLogin);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.setBounds(58, 126, 117, 29);
		frmHttpwwwstorecom.getContentPane().add(btnSignUp);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(150, 60, 130, 26);
		frmHttpwwwstorecom.getContentPane().add(passwordField);
		 
		JLabel error = new JLabel("* invalid username or password");
		error.setForeground(Color.RED);
		error.setBounds(70, 167, 212, 16);
		frmHttpwwwstorecom.getContentPane().add(error);
		error.setVisible(false);
	}
}
