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
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
	
	private SQLInterface database;

	private JFrame fLogin;
	private JTextField tUsername;
	private JTextField tZipcode;
	private JPasswordField passwordField;
	
	private String username;
	private String password;
	private String zipcode;
	
	private String userInfo;
	
	private int user = 2; // Make default 0 later

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
		
		database = new SQLInterface();
		
		fLogin = new JFrame();
		fLogin.getContentPane().setBackground(Color.BLACK);
		fLogin.setTitle("http://www.store.com/login.aspx?src=common");
		fLogin.setBounds(100, 100, 350, 214);
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
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				username = tUsername.getText();
				zipcode = tZipcode.getText();
				char temp[] = passwordField.getPassword();
				password = "";
				for(int i=0; i < temp.length; i++) {
					password += temp[i];
				}
				
				if (checkCredentials()) {
					login();	
				}
				else {
					tUsername.setText("");
					tZipcode.setText("");
					passwordField.setText("");
					_message("Invalid Login!", "Please enter a valid username, password and zipcode!", 1);
				}
					
			}
		});
		btnLogin.setBounds(189, 126, 95, 29);
		fLogin.getContentPane().add(btnLogin);
		
		JTextField name = new JTextField();
		JTextField pass = new JTextField();
		JTextField fname = new JTextField();
		JTextField lname = new JTextField();
		
		Object [] fields = {
				"First Name:", fname,
				"Last Name:", lname,
				"Username:", name, 
				"Password:", pass
		};
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.setBounds(58, 126, 117, 29);
		btnSignUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showConfirmDialog(null, fields, "Create a New Account", JOptionPane.CANCEL_OPTION);
				createAccount(fname.getText(), lname.getText(), name.getText(), pass.getText());
			}
		});
		fLogin.getContentPane().add(btnSignUp);		
		
		fLogin.setVisible(true);
		
	}
	
	private void _message(String title, String message, int type) {
		Object[] options = {"OK"};
		if (type == 1)
			JOptionPane.showOptionDialog(null, message, title, JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
		else if (type == 2)
			JOptionPane.showOptionDialog(null, message, title, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		else
			JOptionPane.showOptionDialog(null, message, title, JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
	}
	
	
	// Functions to Implement
	private boolean checkCredentials() {
		// check if username and password are in the database
		
		ResultSet rs = database.sqlCode("select * from login where username = ? and password = ?;", username, password);
		
		// if yes, set user = 1 for customer, user = 2 for staff and store all the information in the String userInfo
		// if no, set user = 0
		
		if (user == 1 || user == 2)
			return true;
		else
			return false;
	}
	
	private void login() {
		if (user == 1) {
			fLogin.setVisible(false);
			new Grocery_store(userInfo);
		}
		else if (user == 2) {
			fLogin.setVisible(false);
			new Grocery_store_2();
		}
		else {
			_message("Invalid Login!", "Please enter a valid username, password and zipcode!", 1);
		}
				
	}
	
	private void createAccount(String fname, String lname, String name, String pass) {
		int id = 0;
		// sql code to create new customer in the table with new id
		ResultSet rs = database.sqlCode("select max(id) from customer;");
		try {
			id = rs.getInt(1);
		} catch (SQLException e) {
		}
		database.sqlCode("insert into login values('?', '?', '?');",(id+1)+"", name, pass);
		database.sqlCode("insert into customer values('?', ?, ?);",(id+1)+"", fname, lname);
		
		_message("Success!", "Your account was created! Please login with the username and password you entered.", 0);
	}
	
}
