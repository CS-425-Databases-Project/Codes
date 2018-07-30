package cs425_databases;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login {

	private JFrame fLogin;
	private JTextField tUsername;
	private JTextField tZipcode;
	private JPasswordField passwordField;
	
	private String username;
	private String password;
	private String nUsername;
	private String nPassword;
	private String zipcode;
	
	private int user = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.fLogin.setVisible(true);
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
		fLogin = new JFrame();
		fLogin.getContentPane().setBackground(Color.BLACK);
		fLogin.setTitle("http://www.store.com/login.aspx?src=common");
		fLogin.setBounds(100, 100, 350, 250);
		fLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fLogin.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setBounds(65, 37, 74, 16);
		fLogin.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setBounds(65, 65, 74, 16);
		fLogin.getContentPane().add(lblPassword);
		
		JLabel lblZipcode = new JLabel("Zipcode:");
		lblZipcode.setForeground(Color.WHITE);
		lblZipcode.setBounds(65, 93, 74, 16);
		fLogin.getContentPane().add(lblZipcode);
		
		tUsername = new JTextField();
		tUsername.setBounds(150, 32, 130, 26);
		fLogin.getContentPane().add(tUsername);
		tUsername.setColumns(10);
		
		tZipcode = new JTextField();
		tZipcode.setBounds(150, 88, 130, 26);
		fLogin.getContentPane().add(tZipcode);
		tZipcode.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(150, 60, 130, 26);
		fLogin.getContentPane().add(passwordField);
		
		JLabel error = new JLabel("* invalid credentials or zip code");
		error.setForeground(Color.RED);
		error.setBounds(70, 167, 212, 16);
		fLogin.getContentPane().add(error);
		error.setVisible(false);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (checkCredentials()) {
					username = tUsername.getText();
					zipcode = tZipcode.getText();
					char temp[] = passwordField.getPassword();
					password = "";
					for(int i=0; i < temp.length; i++) {
						password += temp[i];
					}
					if (user == 0)
						customerLogin();
					else
						staffLogin();
				}
				else {
					tUsername.setText("");
					tZipcode.setText("");
					passwordField.setText("");
					error.setVisible(true);
				}
					
			}
		});
		btnLogin.setBounds(189, 126, 95, 29);
		fLogin.getContentPane().add(btnLogin);
		
		JTextField name = new JTextField();
		JTextField pass = new JTextField();
		
		Object [] fields = {
				"Username:", name, 
				"Password:", pass
		};
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.setBounds(58, 126, 117, 29);
		btnSignUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showConfirmDialog(null, fields, "Create a New Account", JOptionPane.CANCEL_OPTION);
				createAccount(name.getText(), pass.getText());
			}
		});
		fLogin.getContentPane().add(btnSignUp);		
		
		fLogin.setVisible(true);
		
	}
	
	
	// Functions to Implement
	
	private boolean checkCredentials() {
		return true;
	}
	
	private void customerLogin() {
		fLogin.setVisible(false);
		new Grocery_store();
	}
	
	private void staffLogin() {
		fLogin.setVisible(false);
		new Grocery_store_2();
	}
	
	private void createAccount(String name, String pass) {
		
	}
}
