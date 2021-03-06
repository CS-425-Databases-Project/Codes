package cs425_databases;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Grocery_store_2 {

	private SQLInterface database;
	private JFrame fStore2;
	
	private JCheckBox cMeat;
	private JCheckBox cFruits;
	private JCheckBox cBeverages;
	private JCheckBox cSnacks;
	private JCheckBox cFrozen;
	
	private JRadioButton rbr;
	private JRadioButton rbplh;
	private JRadioButton rbphl;
	private JRadioButton rbla;
	
	private JTextField input;
	private boolean departments[] = {false, false, false, false, false};
	private int filter = 0;

	/**
	 * Create the application.
	 */
	public Grocery_store_2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		database = new SQLInterface();
		
		fStore2 = new JFrame();
		fStore2.getContentPane().setBackground(Color.BLACK);
		fStore2.setForeground(Color.BLACK);
		fStore2.setBackground(Color.BLACK);
		fStore2.setTitle("https://www.store.com/home?user=staff");
		fStore2.setBounds(100, 100, 814, 617);
		fStore2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fStore2.getContentPane().setLayout(null);
		
		Image hero_image = new ImageIcon(this.getClass().getResource("/hero.png")).getImage();
		ImageIcon hero = new ImageIcon(hero_image.getScaledInstance(815, 200, Image.SCALE_SMOOTH));
		
		Image icon_image = new ImageIcon(this.getClass().getResource("/store.png")).getImage();
		ImageIcon icon = new ImageIcon(icon_image.getScaledInstance(120, 140, Image.SCALE_SMOOTH));
		
		Image recent_image = new ImageIcon(this.getClass().getResource("/recent.png")).getImage();
		ImageIcon recent = new ImageIcon(recent_image.getScaledInstance(150, 100, Image.SCALE_SMOOTH));
		
		JLabel str_icn = new JLabel("");
		str_icn.setBounds(21, 0, 128, 144);
		str_icn.setIcon(icon);
		fStore2.getContentPane().add(str_icn);
		
		input = new JTextField();
		input.setColumns(10);
		input.setBounds(157, 97, 565, 26);
		fStore2.getContentPane().add(input);
		
		JButton bSearch = new JButton("Search");
		bSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				search(input.getText());
			}
		});
		bSearch.setBounds(734, 97, 78, 29);
		fStore2.getContentPane().add(bSearch);
		
		JButton bLogout = new JButton("Log Out");
		bLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				logout();
			}
		});
		bLogout.setBounds(710, 70, 102, 29);
		fStore2.getContentPane().add(bLogout);
		
		JButton bInfo = new JButton("Account Info");
		bInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				displayAccountInfo();
			}
		});
		bInfo.setBounds(572, 70, 139, 29);
		fStore2.getContentPane().add(bInfo);
		
		JButton bHome = new JButton("Staff Home");
		bHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				goHome();
			}
		});
		bHome.setBounds(154, 70, 117, 29);
		fStore2.getContentPane().add(bHome);
		
		JButton bCustomers = new JButton("Check Customers");
		bCustomers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				checkCustomers();
			}
		});
		bCustomers.setBounds(269, 70, 155, 29);
		fStore2.getContentPane().add(bCustomers);
		
		JButton bOrders = new JButton("Manage Orders");
		bOrders.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				manageOrders();
			}
		});
		bOrders.setBounds(424, 70, 147, 29);
		fStore2.getContentPane().add(bOrders);
		
		JScrollPane pMain = new JScrollPane();
		pMain.setBounds(161, 135, 643, 450);
		fStore2.getContentPane().add(pMain);
		
		JLabel lblFilterBy = new JLabel("Filter By");
		lblFilterBy.setForeground(Color.WHITE);
		lblFilterBy.setBounds(15, 350, 61, 16);
		fStore2.getContentPane().add(lblFilterBy);
		
		rbr = new JRadioButton("Relevance");
		rbr.setForeground(Color.WHITE);
		rbr.setBounds(7, 380, 94, 23);
		fStore2.getContentPane().add(rbr);
		
		rbplh = new JRadioButton("Price: Low to High");
		rbplh.setForeground(Color.WHITE);
		rbplh.setBounds(7, 410, 147, 23);
		fStore2.getContentPane().add(rbplh);
		
		rbphl = new JRadioButton("Price: High to Low");
		rbphl.setForeground(Color.WHITE);
		rbphl.setBounds(7, 440, 149, 23);
		fStore2.getContentPane().add(rbphl);
		
		rbla = new JRadioButton("Latest Arrivals");
		rbla.setForeground(Color.WHITE);
		rbla.setBounds(7, 470, 133, 23);
		fStore2.getContentPane().add(rbla);
		
		ButtonGroup filters = new ButtonGroup();
		filters.add(rbr);
		filters.add(rbphl);
		filters.add(rbplh);
		filters.add(rbla);
		
		JLabel lblShopByDepartment = new JLabel("Search By Department");
		lblShopByDepartment.setForeground(Color.WHITE);
		lblShopByDepartment.setBounds(15, 153, 139, 34);
		fStore2.getContentPane().add(lblShopByDepartment);
		
		cMeat = new JCheckBox("Meat & Seafood");
		cMeat.setForeground(Color.WHITE);
		cMeat.setBounds(7, 190, 133, 23);
		fStore2.getContentPane().add(cMeat);
		
		cFruits = new JCheckBox("Fruits & Vegetables");
		cFruits.setForeground(Color.WHITE);
		cFruits.setBounds(7, 215, 155, 23);
		fStore2.getContentPane().add(cFruits);
		
		cBeverages = new JCheckBox("Beverages");
		cBeverages.setForeground(Color.WHITE);
		cBeverages.setBounds(7, 240, 128, 23);
		fStore2.getContentPane().add(cBeverages);
		
		cSnacks = new JCheckBox("Snacks & Candy");
		cSnacks.setForeground(Color.WHITE);
		cSnacks.setBounds(7, 265, 142, 23);
		fStore2.getContentPane().add(cSnacks);
		
		cFrozen = new JCheckBox("Frozen Foods");
		cFrozen.setForeground(Color.WHITE);
		cFrozen.setBounds(7, 290, 128, 23);
		fStore2.getContentPane().add(cFrozen);
		
		JLabel lhero = new JLabel("");
		lhero.setBounds(0, -48, 829, 172);
		lhero.setIcon(hero);
		fStore2.getContentPane().add(lhero);
		
		goHome();
		showRecentChanges();
		
		fStore2.setVisible(true);
	}
	
	public boolean _isNumeric(String str)  
	{  
	  try  
	  {  
	    int d = Integer.parseInt(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
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
	
	private void _checkDepartments() {
		departments[0] = cMeat.isSelected();
		departments[1] = cFruits.isSelected();
		departments[2] = cBeverages.isSelected();
		departments[3] = cSnacks.isSelected();
		departments[4] = cFrozen.isSelected();
	} 
	
	private void _checkFilters() {
		if (rbr.isSelected())
			filter = 1;
		else if (rbplh.isSelected())
			filter = 2;
		else if (rbphl.isSelected())
			filter = 3;
		else if (rbla.isSelected())
			filter = 4;
		else
			filter = 0;
	}
	
	private void logout() {
		fStore2.setVisible(false);
		new Login();
	}
	
	private void displayAccountInfo() {
		
		// Show userInfo by default
		JTextField fname = new JTextField();
		JTextField lname = new JTextField();
		JTextField username = new JTextField();
		JTextField password = new JTextField();
		// Add other details later
		
		Object [] fields = {
				"First Name:", fname,
				"Last Name:", lname,
				"Username", username,
				"Password", password
				
		};
		JOptionPane.showConfirmDialog(null, fields, "Edit Customer Details", JOptionPane.CANCEL_OPTION);
		
		updateStaffInfo();
		
	}
	
	private void updateStaffInfo() {
		// sql code to update staff details in the database
		
		// update the value of the variable userInfo
		
		_message("Success", "Your account details have been updated successfully", 0);
	}
	
	private void goHome() {
	}
	
	private void checkCustomers() {
	}
	
	private void manageOrders() {
	}
	
	private void showRecentChanges() {
	}
	
	private void search(String searchText) {
		_checkDepartments();
		_checkFilters();
		if (searchText.equals(""))
			goHome();
		else {}
	}
}
