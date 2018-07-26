package cs425_databases;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Login {

	private JFrame frmHttpwwwstorecom;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

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
		frmHttpwwwstorecom.setTitle("http://www.store.com/login.aspx");
		frmHttpwwwstorecom.setBounds(100, 100, 450, 300);
		frmHttpwwwstorecom.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHttpwwwstorecom.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(98, 77, 74, 16);
		frmHttpwwwstorecom.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(98, 105, 74, 16);
		frmHttpwwwstorecom.getContentPane().add(lblPassword);
		
		JLabel lblZipcode = new JLabel("Zipcode:");
		lblZipcode.setBounds(98, 133, 74, 16);
		frmHttpwwwstorecom.getContentPane().add(lblZipcode);
		
		textField = new JTextField();
		textField.setBounds(182, 72, 130, 26);
		frmHttpwwwstorecom.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(182, 100, 130, 26);
		frmHttpwwwstorecom.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(182, 128, 130, 26);
		frmHttpwwwstorecom.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(217, 166, 95, 29);
		frmHttpwwwstorecom.getContentPane().add(btnLogin);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.setBounds(88, 166, 117, 29);
		frmHttpwwwstorecom.getContentPane().add(btnSignUp);
	}

}
