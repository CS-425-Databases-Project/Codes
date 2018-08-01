package cs425_databases;

import java.awt.Image;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import javax.swing.JTextArea;

public class Grocery_store {

	private JFrame fStore1;
	
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
	
	
	private JTextArea tMain;
	private JTextArea tOffers;
	
	private Object cart;

	/**
	 * Create the application.
	 */
	public Grocery_store() {
		initialize();
	}
	
	public Grocery_store(Object previousCart) {
		cart = previousCart;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		fStore1 = new JFrame();
		fStore1.getContentPane().setBackground(Color.BLACK);
		fStore1.setResizable(false);
		fStore1.setTitle("https://www.store.com/home?user=customer");
		fStore1.setBounds(100, 100, 814, 617);
		fStore1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fStore1.getContentPane().setLayout(null);
		
		Image icon_image = new ImageIcon(this.getClass().getResource("/store.png")).getImage();
		ImageIcon icon = new ImageIcon(icon_image.getScaledInstance(120, 140, Image.SCALE_SMOOTH));
		
		Image hero_image = new ImageIcon(this.getClass().getResource("/hero.png")).getImage();
		ImageIcon hero = new ImageIcon(hero_image.getScaledInstance(815, 200, Image.SCALE_SMOOTH));
		
		Image offers_image = new ImageIcon(this.getClass().getResource("/offers.png")).getImage();
		ImageIcon offers = new ImageIcon(offers_image.getScaledInstance(150, 60, Image.SCALE_SMOOTH));
		
		JButton btnAccountInfo = new JButton("Account Info");
		btnAccountInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				displayAccountInfo();
			}
		});
		btnAccountInfo.setBounds(614, 70, 117, 29);
		fStore1.getContentPane().add(btnAccountInfo);
		
		JButton btnCart = new JButton("View Cart");
		btnCart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				createCart();
				fStore1.setVisible(false);
				new CartMenu(cart);
			}
		});
		btnCart.setBounds(515, 70, 105, 29);
		fStore1.getContentPane().add(btnCart);
		
		input = new JTextField();
		input.setBounds(157, 97, 429, 26);
		input.setText("");
		fStore1.getContentPane().add(input);
		input.setColumns(10);
		
		JButton bSearch = new JButton("Search");
		bSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				search(input.getText());
			}
		});
		bSearch.setBounds(590, 97, 78, 29);
		fStore1.getContentPane().add(bSearch);
		
		JScrollPane pMain = new JScrollPane();
		pMain.setBounds(161, 135, 500, 450);
		fStore1.getContentPane().add(pMain);
		
		tMain = new JTextArea();
		pMain.setViewportView(tMain);
		
		JLabel lblShopByDepartment = new JLabel("Shop By Department");
		lblShopByDepartment.setForeground(Color.WHITE);
		lblShopByDepartment.setBounds(15, 153, 133, 34);
		fStore1.getContentPane().add(lblShopByDepartment);
		
		cMeat = new JCheckBox("Meat & Seafood");
		cMeat.setForeground(Color.WHITE);
		cMeat.setBounds(7, 190, 133, 23);
		fStore1.getContentPane().add(cMeat);
		
		cFruits = new JCheckBox("Fruits & Vegetables");
		cFruits.setForeground(Color.WHITE);
		cFruits.setBounds(7, 215, 155, 23);
		fStore1.getContentPane().add(cFruits);
		
		cBeverages = new JCheckBox("Beverages");
		cBeverages.setForeground(Color.WHITE);
		cBeverages.setBounds(7, 240, 128, 23);
		fStore1.getContentPane().add(cBeverages);
		
		cSnacks = new JCheckBox("Snacks & Candy");
		cSnacks.setForeground(Color.WHITE);
		cSnacks.setBounds(7, 265, 142, 23);
		fStore1.getContentPane().add(cSnacks);
		
		cFrozen = new JCheckBox("Frozen Foods");
		cFrozen.setForeground(Color.WHITE);
		cFrozen.setBounds(7, 290, 128, 23);
		fStore1.getContentPane().add(cFrozen);
		
		JLabel lblFilterBy = new JLabel("Filter By");
		lblFilterBy.setForeground(Color.WHITE);
		lblFilterBy.setBounds(15, 350, 61, 16);
		fStore1.getContentPane().add(lblFilterBy);
		
		rbr = new JRadioButton("Relevance");
		rbr.setForeground(Color.WHITE);
		rbr.setBounds(7, 380, 94, 23);
		fStore1.getContentPane().add(rbr);
		
		rbplh = new JRadioButton("Price: Low to High");
		rbplh.setForeground(Color.WHITE);
		rbplh.setBounds(7, 410, 147, 23);
		fStore1.getContentPane().add(rbplh);
		
		rbphl = new JRadioButton("Price: High to Low");
		rbphl.setForeground(Color.WHITE);
		rbphl.setBounds(7, 440, 149, 23);
		fStore1.getContentPane().add(rbphl);
		
		rbla = new JRadioButton("Latest Arrivals");
		rbla.setForeground(Color.WHITE);
		rbla.setBounds(7, 470, 133, 23);
		fStore1.getContentPane().add(rbla);
		
		ButtonGroup filters = new ButtonGroup();
		filters.add(rbr);
		filters.add(rbphl);
		filters.add(rbplh);
		filters.add(rbla);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				logout();
			}
		});
		btnLogOut.setBounds(725, 70, 90, 29);
		fStore1.getContentPane().add(btnLogOut);
		
		JButton btnHome = new JButton("Home");
		btnHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				goHome();
			}
		});
		btnHome.setBounds(154, 70, 117, 29);
		fStore1.getContentPane().add(btnHome);
		
		JButton btnAbout = new JButton("Contact Us");
		btnAbout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				displayContactInfo();
			}
		});
		btnAbout.setBounds(265, 70, 99, 29);
		fStore1.getContentPane().add(btnAbout);
		
		JButton btnShopPastPurchases = new JButton("Shop Past Purchases");
		btnShopPastPurchases.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showPastPurchases();
			}
		});
		btnShopPastPurchases.setBounds(358, 70, 163, 29);
		fStore1.getContentPane().add(btnShopPastPurchases);
		
		JLabel offs = new JLabel("");
		offs.setForeground(Color.WHITE);
		offs.setBounds(665, 105, 164, 66);
		offs.setIcon(offers);
		fStore1.getContentPane().add(offs);
		
		JLabel str_icn = new JLabel("");
		str_icn.setBounds(21, 0, 128, 144);
		str_icn.setIcon(icon);
		fStore1.getContentPane().add(str_icn);
		
		JLabel lhero = new JLabel("");
		lhero.setBounds(0, -48, 829, 172);
		lhero.setIcon(hero);
		fStore1.getContentPane().add(lhero);
		
		JScrollPane pOffers = new JScrollPane();
		pOffers.setBounds(671, 136, 133, 449);
		fStore1.getContentPane().add(pOffers);
		
		tOffers = new JTextArea();
		pOffers.setViewportView(tOffers);
		
		goHome();
		displayOffers();
		
		fStore1.setVisible(true);
		
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
		fStore1.setVisible(false);
		new Login();
	}
	
	private void displayContactInfo() {
		tMain.setText("Display Contact Information");
	}
	
	private void goHome() {
		 Connection conn = null;
	        try
	        {
	    		String url = "jdbc:postgresql://localhost/cs425_project";
	    	   String user = "postgres";
	    	   String password = "88378821";
	            conn = DriverManager.getConnection(url, user, password);
	            System.out.println("Connection to database established.");
	        }
	        catch (SQLException e)
	        {
	            System.err.println("SQL exception: " + e.getMessage());
	            System.err.println("SQL state: " + e.getSQLState());
	            System.err.println("Error code: " + e.getErrorCode());
	        }

	        if (conn != null)
	        {
	            try
	            {
		
		
		 PreparedStatement stmt;                
         stmt = conn.prepareStatement( " SELECT pname,size,category" + 
 				"FROM product");      
		ResultSet rs=stmt.executeQuery();
		
		while (rs.next())
		{
		
			
			String pname = rs.getString("pname");
			double size = rs.getDouble("size");
			String category=rs.getString("category");
					
			System.out.printf("%9d %-16s %-6s%n",
			  pname, size,category);
		}
		
			
		
	    System.out.println("Database connection closed.");
	            }
	            catch (SQLException e)
	            {
	                System.err.println("SQL exception: " + e.getMessage());
	                System.err.println("SQL state: " + e.getSQLState());
	                System.err.println("Error code: " + e.getErrorCode());
	            }
		
	}
	}
	
	private void showPastPurchases() {
		tMain.setText("Display Past Purchases");
	}
	
	private void displayOffers() {
		tOffers.setText("\n\n\n     Display Offers");
	}
	
	private void displayAccountInfo() {
		tMain.setText("Display Account Information");
	}
	
	private void search(String searchText) {
		_checkDepartments();
		_checkFilters();
		if (searchText.equals(""))
			goHome();
		else
			tMain.setText("Display Search Results (Using Search Text, Departments and Filters)");
	}
	
	private void createCart() {
		
	}
	

	
}