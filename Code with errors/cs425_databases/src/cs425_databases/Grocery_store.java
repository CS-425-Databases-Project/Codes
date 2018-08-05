package cs425_databases;

import java.awt.Image;

import javax.swing.AbstractCellEditor;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextArea;

public class Grocery_store {
	
	private SQLInterface database;

	private JFrame fStore1;
	private JTable table;
	private JTable table2;
	private JScrollPane pMain;
	private JScrollPane offersPane;
	
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
	private String departments[] = {"meats", "fruits", "beverages", "snacks", "frozen"};
	private String filter;
	private ArrayList<String> categories = new ArrayList<String>();
	private ArrayList<String> tags = new ArrayList<String>();
	
	private int addNum = -1;
	private int num_items = 0;
	
	private String userInfo;
	private Object cart;
	
	//private Arraylist<String> cart = new ArrayList<String>();

	/**
	 * Create the application.
	 */
	public Grocery_store(String userInfo) {
		this.userInfo = userInfo;
		initialize();
	}
	
	public Grocery_store(String userInfo, Object previousCart) {
		this.userInfo = userInfo;
		cart = previousCart;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		database = new SQLInterface();
		
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
				goToCart();
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
		
		pMain = new JScrollPane();
		pMain.setBounds(161, 135, 500, 450);
		fStore1.getContentPane().add(pMain);
		
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
		
		offersPane = new JScrollPane();
		offersPane.setBounds(674, 180, 128, 405);
		fStore1.getContentPane().add(offersPane);
		
		createProductView();
		createOffersView();
		goHome();
		getOffers();
		
		fStore1.setVisible(true);
		
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
	
	private void createProductView() {
		String[] columnNames = {"Picture", "Name", "Size", "Price", "More Info.", "Qty.", "Buy"};
        Object[][] data = {};

        DefaultTableModel model = new DefaultTableModel(data, columnNames)
        {
        	boolean[] canEdit = new boolean[]{
                    false, false, false, false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
            
            public Class getColumnClass(int column)
            {
                return getValueAt(0, column).getClass();
            }
        };
        table = new JTable(model);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        
        table.setRowHeight(50);
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(180);
        table.getColumnModel().getColumn(2).setPreferredWidth(50);
        table.getColumnModel().getColumn(3).setPreferredWidth(40);
        table.getColumnModel().getColumn(4).setPreferredWidth(60);
        table.getColumnModel().getColumn(5).setPreferredWidth(20);
        table.getColumnModel().getColumn(5).setPreferredWidth(20);
        table.getColumnModel().getColumn(6).setPreferredWidth(10);
        
        ShowMoreInfo moreInfo = new ShowMoreInfo(table, 4);
        AddToCartButton addToCart = new AddToCartButton(table, 6);
        pMain.setViewportView(table);
	}
	
	private void createOffersView() {
		
		String[] columnNames = {"", "", ""};
        Object[][] data = {};

        DefaultTableModel model = new DefaultTableModel(data, columnNames)
        {
        	boolean[] canEdit = new boolean[]{
                    false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
            
            public Class getColumnClass(int column)
            {
                return getValueAt(0, column).getClass();
            }
        };
        
        table2 = new JTable(model);
        table2.setPreferredScrollableViewportSize(table.getPreferredSize());
        table2.setTableHeader(null);
        
        table2.setRowHeight(50);
        table2.getColumnModel().getColumn(0).setPreferredWidth(50);
        table2.getColumnModel().getColumn(1).setPreferredWidth(30);
        table2.getColumnModel().getColumn(2).setPreferredWidth(10);
        
        AddOfferButton addToCart = new AddOfferButton(table2, 2);
        
        offersPane.setViewportView(table2);
        
        displayOffers();
	}

	private void displayProducts() { // Add "ResultSet rs" as parameter later
		
		DefaultTableModel dm = (DefaultTableModel) table.getModel();
		
		int rowCount = dm.getRowCount();
		//Remove rows one by one from the end of the table
		for (int i = rowCount - 1; i >= 0; i--) {
		    dm.removeRow(i);
		}
		
		Object [][] data = getProductData(); // Add "ResultSet rs" as parameter later
		
		for(int i = 0 ; i < num_items; i++) {
			dm.addRow(data[i]);
		}
	}
	
	private void displayOffers() { // Add "ResultSet rs" as parameter later
		
		DefaultTableModel dm = (DefaultTableModel) table2.getModel();
		
		int rowCount = dm.getRowCount();
		//Remove rows one by one from the end of the table
		for (int i = rowCount - 1; i >= 0; i--) {
		    dm.removeRow(i);
		}
		
		Object [][] data = getOfferData(); // Add "ResultSet rs" as parameter later
		
		for(int i = 0 ; i < 8; i++) {
			dm.addRow(data[i]);
		}
	}
	
	private Icon _getImage(String filename) {
		Image icon_image = new ImageIcon(this.getClass().getResource("/" + filename)).getImage();
		return new ImageIcon(icon_image.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
	}
	
	// To be done by Suleyma
	public Object [][] getProductData(ResultSet rs) { 
			  
		num_items = 1;

		Object[][] data = {};

		int count = 0;

		try {

			while(rs.next()) {

				Object[] itemDetails=new Object[7];;

				itemDetails[0] = _getImage("oreo.jpeg");

				itemDetails[1] = rs.getString("name");

				itemDetails[2] = rs.getString("size")+ "cu. ft.";

				itemDetails[3] = "$" + Double.toString(rs.getDouble("price"));

				itemDetails[4] = "See More";

				itemDetails[5] = "0";

				itemDetails[6] = "+";
				data[count] = itemDetails;
				count++;
				num_items++;

			}

		} 
		catch (SQLException e) {
			}
			    
		return data;   
	}
	
	// To be done by Suleyma
	public Object [][] getOfferData(ResultSet rs) { 
				  
		num_items = 8;

		Object[][] data ;

		int count = 0;

		try {

			while(rs.next()) {

				Object[] itemDetails= new Object[4];

				itemDetails[0] = _getImage("oreo.jpeg");

				itemDetails[1] = rs.getString("name");

				itemDetails[2] = "$" + Double.toString(rs.getDouble("price"));

				itemDetails[3] = "+";
				data[count] = itemDetails;
				count++;

			}

		} 
		catch (SQLException e) {
			}
			    
		return data;      
	}
	
	public void displayProductInfo() {
		String info = "Product Details.";
		_message("Product Details", info, 2);
	}
		
	private void _checkDepartments() {
		categories.clear();
		if (cMeat.isSelected())
			categories.add(departments[0]);
		if (cFruits.isSelected())
			categories.add(departments[1]);
		if (cBeverages.isSelected())
			categories.add(departments[2]);
		if (cSnacks.isSelected())
			categories.add(departments[3]);
		if (cFrozen.isSelected())
			categories.add(departments[4]);
	} 
	
	private void _checkFilters() {
		if (rbr.isSelected())
			filter = "id";
		else if (rbplh.isSelected())
			filter = "price asc";
		else if (rbphl.isSelected())
			filter = "price desc";
		else if (rbla.isSelected())
			filter = "rank";
		else
			filter = "id";
	}
	
	private void _getTags(String text) {
		tags.clear();
		for(String x : (text.toLowerCase()).split(" ")){
			tags.add("'%" + x + "%'");
		}
	}
	
	private void search(String searchText) {
		_checkDepartments();
		_checkFilters();
		_getTags(searchText);
		
		if (searchText.equals(""))
			goHome();
		else {
			// sql code to search products using categories & tags, and order results by filter
			// Return results in ResultSet rs
			
			displayProducts(); // Add "ResultSet rs" as parameter later
		}
	}
	
	private void goHome() {
		// sql code to return all products and order results by id
		// Return results in ResultSet rs
		
		displayProducts(); // Add "ResultSet rs" as parameter later
	}
	
	private void getOffers() {
		// sql code to return 8 lowest price products and order results by price
		// Return results in ResultSet rs
				
		displayOffers(); // Add "ResultSet rs" as parameter later
	}
	
	
	private void displayContactInfo() {
		_message("About Us", "Display Contact Information", 2);
	}
	
	// Need to figure out later
	private void showPastPurchases() {
		//tMain.setText("Display Past Purchases");
		// list past ordered items
	}
	
	private void displayAccountInfo() {
		
		// Show userInfo by default
		JTextField fname = new JTextField();
		JTextField lname = new JTextField();
		JTextField username = new JTextField();
		JTextField password = new JTextField();
		// Add credit cards and Addresses later
		
		Object [] fields = {
				"First Name:", fname,
				"Last Name:", lname,
				"Username", username,
				"Password", password
				
		};
		JOptionPane.showConfirmDialog(null, fields, "Edit Customer Details", JOptionPane.CANCEL_OPTION);
		
		updateCustomerInfo();
		
	}
	
	private void updateCustomerInfo() {
		// sql code to update customer details in the database
		
		// update the value of the variable userInfo
		
		_message("Success", "Your account details have been updated successfully", 0);
	}
	
	public void addToCart(String pname) {
		// sql code to use pname and add product to cart in database
	}
	
	public void addProduct() {
		
		String qty = table.getModel().getValueAt(addNum, 5).toString(); 
		if (_isNumeric(qty) && Integer.parseInt(qty) > 0) {
			addToCart(""); // Find pname or pid and add it to the argument
			_message("Success!", qty + " item(s) have been added to your cart!", 0);
		}
	}
	
	private void addOffer() {
	
		JTextField qty = new JTextField();
		Object [] fields = {
				"Enter Quantity:", qty
		};
		JOptionPane.showConfirmDialog(null, fields, "Add Item to Cart.", JOptionPane.CANCEL_OPTION);
		
		if (_isNumeric(qty.getText()) && Integer.parseInt(qty.getText()) > 0) {
			String name = table2.getModel().getValueAt(addNum, ).toString(); 
			addToCart(name);
			_message("Success!", qty.getText() + " items(s) have been added to your cart!", 0);
		}
	}
	
	private void logout() {
		fStore1.setVisible(false);
		new Login();
	}
	

	private void goToCart() {
		fStore1.setVisible(false);
		new CartMenu(userInfo, cart);
	}
	
	class ShowMoreInfo extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ActionListener {
		JTable table;
		JButton renderButton;
		JButton editButton;
		String text;

		public ShowMoreInfo(JTable table, int column) {
			super();
			this.table = table;
			renderButton = new JButton();

			editButton = new JButton();
			editButton.setFocusPainted(false);
			editButton.addActionListener(this);

			TableColumnModel columnModel = table.getColumnModel();
			columnModel.getColumn(column).setCellRenderer(this);
			columnModel.getColumn(column).setCellEditor(this);
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		{
			renderButton.setText( (value == null) ? "+" : value.toString() );
			return renderButton;
		}

		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
			text = (value == null) ? "+" : value.toString();
			editButton.setText(text);
			return editButton;
		}

		public Object getCellEditorValue()
		{
			return text;
		}

		public void actionPerformed(ActionEvent e)
		{
			fireEditingStopped();
			addNum = table.getSelectedRow();
			displayProductInfo();
			
		}
	}
	
	class AddToCartButton extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ActionListener {
		JTable table;
		JButton renderButton;
		JButton editButton;
		String text;

		public AddToCartButton(JTable table, int column) {
			super();
			this.table = table;
			renderButton = new JButton();

			editButton = new JButton();
			editButton.setFocusPainted(false);
			editButton.addActionListener(this);

			TableColumnModel columnModel = table.getColumnModel();
			columnModel.getColumn(column).setCellRenderer(this);
			columnModel.getColumn(column).setCellEditor(this);
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		{
			renderButton.setText( (value == null) ? "+" : value.toString() );
			return renderButton;
		}

		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
			text = (value == null) ? "+" : value.toString();
			editButton.setText(text);
			return editButton;
		}

		public Object getCellEditorValue()
		{
			return text;
		}

		public void actionPerformed(ActionEvent e)
		{
			fireEditingStopped();
			addNum = table.getSelectedRow();
			addProduct();
		}
		
	}
		
		class AddOfferButton extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ActionListener {
			JTable table;
			JButton renderButton;
			JButton editButton;
			String text;

			public AddOfferButton(JTable table, int column) {
				super();
				this.table = table;
				renderButton = new JButton();

				editButton = new JButton();
				editButton.setFocusPainted(false);
				editButton.addActionListener(this);

				TableColumnModel columnModel = table.getColumnModel();
				columnModel.getColumn(column).setCellRenderer(this);
				columnModel.getColumn(column).setCellEditor(this);
			}

			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
			{
				renderButton.setText( (value == null) ? "+" : value.toString() );
				return renderButton;
			}

			public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
				text = (value == null) ? "+" : value.toString();
				editButton.setText(text);
				return editButton;
			}

			public Object getCellEditorValue()
			{
				return text;
			}

			public void actionPerformed(ActionEvent e)
			{
				fireEditingStopped();
				addNum = table.getSelectedRow();
				addOffer();
			}
			
	}
}
