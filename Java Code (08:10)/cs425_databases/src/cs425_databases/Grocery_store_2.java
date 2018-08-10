package cs425_databases;

import java.awt.Image;

import javax.swing.AbstractCellEditor;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Grocery_store_2 {

	private JTable table;
	private JTable cTable;
	private JTable oTable;
	private JScrollPane pMain;
	private JFrame fStore2;
	
	private JCheckBox cMeat;
	private JCheckBox cFruits;
	private JCheckBox cBeverages;
	private JCheckBox cSnacks;
	private JCheckBox cFrozen;
	
	private JRadioButton rbr;
	private JRadioButton rbplh;
	private JRadioButton rbphl;
	
	private JTextField input;
	private String departments[] = {"meats", "fruits", "beverages", "snacks", "frozen"};
	private String filter;
	private ArrayList<String> categories = new ArrayList<String>();
	private ArrayList<String> tags = new ArrayList<String>();
	
	private int num_items;
	
	private String userInfo [] = {"","","",""};
	
	private ArrayList<ArrayList<Object>> products;
	private ArrayList<ArrayList<Object>> customers;
	private ArrayList<ArrayList<Object>> orders;

	/**
	 * Create the application.
	 */
	public Grocery_store_2(String userInfo []) {
		this.userInfo = userInfo;
		num_items = 0;
		initialize();
	}
		
	public void initialize() {
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
		
		JButton bHome = new JButton("Home");
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
		
		pMain = new JScrollPane();
		pMain.setBounds(161, 135, 643, 409);
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
		
		ButtonGroup filters = new ButtonGroup();
		filters.add(rbr);
		filters.add(rbphl);
		filters.add(rbplh);
		
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
		
		JButton btnCommit = new JButton("Commit Changes");
		btnCommit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				editProducts();
			}
		});
		btnCommit.setBounds(154, 556, 658, 29);
		fStore2.getContentPane().add(btnCommit);
		
		createProductView();
		createCustomerView();
		createOrdersView();
		goHome();
		
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
	
	public boolean _isDouble(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
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
	
	private void _message(String title, JTable t) {
		JOptionPane.showConfirmDialog(null, new JScrollPane(t), title, JOptionPane.OK_CANCEL_OPTION);
	}
	
	private Icon _getImage(String filename) {
		Image icon_image = new ImageIcon(this.getClass().getResource("/" + filename)).getImage();
		return new ImageIcon(icon_image.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
	}
	
	private void createProductView() {
		String[] columnNames = {"Picture", "Name", "Size", "Price", "More Info.", "State"};
        Object[][] data = {};

        DefaultTableModel model = new DefaultTableModel(data, columnNames)
        {
        	boolean[] canEdit = new boolean[]{
                    false, true, true, true, true, false
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
        
        ShowMoreInfo moreInfo = new ShowMoreInfo(table, 4);
        pMain.setViewportView(table);
	}
	
	private void createCustomerView() {
		String[] columnNames = {"Customer ID", "First Name", "Last Name", "Balance"};
        Object[][] data = {};

        DefaultTableModel model = new DefaultTableModel(data, columnNames)
        {
        	boolean[] canEdit = new boolean[]{
                    false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
            
            public Class getColumnClass(int column)
            {
                return getValueAt(0, column).getClass();
            }
        };
        cTable = new JTable(model);
        cTable.setPreferredScrollableViewportSize(cTable.getPreferredSize());
        
        cTable.setRowHeight(50);
        cTable.getColumnModel().getColumn(0).setPreferredWidth(80);
        cTable.getColumnModel().getColumn(1).setPreferredWidth(80);
        cTable.getColumnModel().getColumn(2).setPreferredWidth(80);
        cTable.getColumnModel().getColumn(3).setPreferredWidth(80);
        
	}
	
	private void createOrdersView() {
		
		String[] columnNames = {"Order No.", "CustomerID", "Product", "Qty", "Street", "City", "State", "Zipcode", "Status", "Time Stamp"};
        Object[][] data = {};

        DefaultTableModel model = new DefaultTableModel(data, columnNames)
        {
        	boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
            
            public Class getColumnClass(int column)
            {
                return getValueAt(0, column).getClass();
            }
        };
        oTable = new JTable(model);
        oTable.setPreferredScrollableViewportSize(oTable.getPreferredSize());
        
        oTable.setRowHeight(50);
        oTable.getColumnModel().getColumn(0).setPreferredWidth(80);
        oTable.getColumnModel().getColumn(1).setPreferredWidth(80);
        oTable.getColumnModel().getColumn(2).setPreferredWidth(80);
        oTable.getColumnModel().getColumn(3).setPreferredWidth(30);
        oTable.getColumnModel().getColumn(4).setPreferredWidth(80);
        oTable.getColumnModel().getColumn(5).setPreferredWidth(80);
        oTable.getColumnModel().getColumn(6).setPreferredWidth(80);
        oTable.getColumnModel().getColumn(7).setPreferredWidth(80);
        oTable.getColumnModel().getColumn(8).setPreferredWidth(80);
        oTable.getColumnModel().getColumn(9).setPreferredWidth(80);
        setUpStatus(oTable, oTable.getColumnModel().getColumn(8));
        
	}
	
	public void setUpStatus(JTable table,
            TableColumn column) {
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("issued");
		comboBox.addItem("sent");
		comboBox.addItem("received");
		column.setCellEditor(new DefaultCellEditor(comboBox));

		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Click for combo box");
		column.setCellRenderer(renderer);
}
	
	private void displayProducts() {
		
		DefaultTableModel dm = (DefaultTableModel) table.getModel();
		
		int rowCount = dm.getRowCount();
		
		for (int i = rowCount - 1; i >= 0; i--) {
		    dm.removeRow(i);
		}
		
		ArrayList<ArrayList<Object>> temp = new ArrayList<ArrayList<Object>>();
		ArrayList<Object> rowData = new ArrayList<Object>();
		
		num_items = products.size() - 1;
		
		for(int i = 1; i < products.size(); i++) {
			rowData = new ArrayList<Object>();
			rowData.add(_getImage(products.get(i).get(5).toString()));
			rowData.add(products.get(i).get(1));
			rowData.add(products.get(i).get(2));
			rowData.add(products.get(i).get(7));
			rowData.add("See More");
			rowData.add(products.get(i).get(6));
			temp.add(rowData);
		}
	
		for(int i = 0 ; i < num_items; i++) {
			dm.addRow(temp.get(i).toArray());
		}
	
	}
	
	private boolean checkValues() {
		boolean temp = true;
		for(int i = 0; i < table.getModel().getRowCount(); i++) {
			if(!_isDouble(table.getModel().getValueAt(i, 2).toString()) || !_isDouble(table.getModel().getValueAt(i, 3).toString()))
				temp = false;
		}
		return temp;
	}
	
	private void editProducts() {
		if(checkValues()) {
			
			int update = 0;
			
			for(int i = 1; i < products.size(); i++) {
			update = SQLInterface.sqlUpdate("update product set pname = '"+table.getModel().getValueAt(i-1, 1).toString()+"', size = '"+table.getModel().getValueAt(i-1, 2).toString()+"' where pid = '"+products.get(i).get(0).toString()+"';");
			update = SQLInterface.sqlUpdate("update pprice set price = '"+table.getModel().getValueAt(i-1, 3).toString()+"' where pid = '"+products.get(i).get(0).toString()+"';");

			}
			if(update == 0)
				_message("Failed!", "Unable to update products. Please try again later.", 1);
			else {
				_message("Success!", "Items were successfully updated!", 0);
			}
		}
		else
			_message("Invalid Details!!", "Please enter valid information in all fields!", 0);
	
	}
	
	private void displayCustomers() {
		
		customers = SQLInterface.sqlExecute("select *  from customer;");
		
		DefaultTableModel dm = (DefaultTableModel) cTable.getModel();
		
		int rowCount = dm.getRowCount();
		
		for (int i = rowCount - 1; i >= 0; i--) {
		    dm.removeRow(i);
		}
		
		ArrayList<ArrayList<Object>> temp = new ArrayList<ArrayList<Object>>();
		ArrayList<Object> rowData = new ArrayList<Object>();
		
		int cnum = customers.size() - 1;
		
		for(int i = 1; i < customers.size(); i++) {
			rowData = new ArrayList<Object>();
			rowData.add(customers.get(i).get(0));
			rowData.add(customers.get(i).get(1));
			rowData.add(customers.get(i).get(2));
			rowData.add("$" + customers.get(i).get(3));
			temp.add(rowData);
		}
	
		for(int i = 0 ; i < cnum; i++) {
			dm.addRow(temp.get(i).toArray());
		}
	
	}
	
	private void saveStatus() {
		int update = 0;
		for(int i = 0; i < oTable.getRowCount(); i++) {
			update = SQLInterface.sqlUpdate("update orders set status = '"+oTable.getModel().getValueAt(i, 8) +"' where oid = '"+orders.get(i+1).get(0).toString()+"' and cid = '"+orders.get(i+1).get(1).toString()+"' and pid = '"+orders.get(i+1).get(2).toString()+"';");
		}
		
		if(update == 0)
			_message("Failed!", "Unable to modify order statuses. Please try again later.", 1);
		else
			_message("Success!", "All orders statuses were modified successfully!", 0);
		
	}
	
	private void displayOrders() { 
		
		orders = SQLInterface.sqlExecute("select * from sorder;");
		
		DefaultTableModel dm = (DefaultTableModel) oTable.getModel();
		
		int rowCount = dm.getRowCount();
		
		for (int i = rowCount - 1; i >= 0; i--) {
		    dm.removeRow(i);
		}
		
		ArrayList<ArrayList<Object>> temp = new ArrayList<ArrayList<Object>>();
		ArrayList<Object> rowData = new ArrayList<Object>();
		
		int num = orders.size() - 1;
		
		for(int i = 1; i < orders.size(); i++) {
			rowData = new ArrayList<Object>();
			rowData.add(orders.get(i).get(0));
			rowData.add(orders.get(i).get(1));
			rowData.add(orders.get(i).get(2));
			rowData.add(orders.get(i).get(3));
			rowData.add(orders.get(i).get(4));
			rowData.add(orders.get(i).get(5));
			rowData.add(orders.get(i).get(6));
			rowData.add(orders.get(i).get(7));
			rowData.add(orders.get(i).get(8));
			rowData.add(orders.get(i).get(9));
			temp.add(rowData);
		}
	
		for(int i = 0 ; i < num; i++) {
			dm.addRow(temp.get(i).toArray());
		}
	}
	
	public void displayProductInfo(int pnum) {
		
		JTextField pinfo = new JTextField();
		pinfo.setText(products.get(pnum+1).get(4).toString());
		
		Object [] fields = {
				"Nutrition Content:    ", pinfo
				
		};
		JOptionPane.showConfirmDialog(null, fields, "Edit Additional Information", JOptionPane.CANCEL_OPTION);
		System.out.println(pinfo.getText());
		saveProductInfo(pnum, pinfo.getText());
	}
	
	public void saveProductInfo(int pnum, String info) {
		if(SQLInterface.sqlUpdate("update product set info = '"+info+"' where pid = '"+products.get(pnum+1).get(0).toString()+"';") == 0)
			_message("Failed!", "Unable to update product info. Please try again later.", 1);
		else
			_message("Success!", "Product info was modified successfully!", 0);
		products = SQLInterface.sqlExecute("select * from cproduct;");
		displayProducts();
	}
		
	private void _checkDepartments() {
		categories.clear();
		if (cMeat.isSelected())
			categories.add(" category = '" + departments[0] + "' ");
		if (cFruits.isSelected())
			categories.add(" category = '" + departments[1] + "' ");
		if (cBeverages.isSelected())
			categories.add(" category = '" + departments[2] + "' ");
		if (cSnacks.isSelected())
			categories.add(" category = '" + departments[3] + "' ");
		if (cFrozen.isSelected())
			categories.add(" category = '" + departments[4] + "' ");
	} 
	
	private void _checkFilters() {
		if (rbr.isSelected())
			filter = "pid";
		else if (rbplh.isSelected())
			filter = "price asc";
		else if (rbphl.isSelected())
			filter = "price desc";
		else
			filter = "pid";
	}
	
	private void _getTags(String text) {
		tags.clear();
		for(String x : (text.toLowerCase()).split(" ")){
			tags.add(" pname like '%" + x + "%' ");
		}
	}
	
	private void search(String searchText) {
		
		_checkDepartments();
		_checkFilters();
		_getTags(searchText);
		
		if (searchText.equals("")) {
			goHome();
		}
		else {
			String code = "select * from cproduct";
			if (tags.size()!=0 || categories.size()!=0) {
				code +=  " where (";
				if (tags.size()==0 && categories.size()!=0) {
					for(int i = 0; i < categories.size(); i++) {
						if (i > 0)
							code += "or";
						code += categories.get(i);
					}
					code += ") order by " + filter + ";";
				}
				else if (tags.size()!=0 && categories.size()==0) {
					for(int i = 0; i < tags.size(); i++) {
						if (i > 0)
							code += "or";
						code += tags.get(i);
					}
					code += ") order by " + filter + ";";
				}
				else {
					for(int i = 0; i < categories.size(); i++) {
						if (i > 0)
							code += "or";
						code += categories.get(i);
					}
					code += ") and (";
					for(int i = 0; i < tags.size(); i++) {
						if (i > 0)
							code += "or";
						code += tags.get(i);
					}
					code += ") order by " + filter + ";";
				}
				
			}
			else {
				code += " order by " + filter + ";";
			}
			products = SQLInterface.sqlExecute(code);
			displayProducts();
		}
	}
	
	private void goHome() {
		products = SQLInterface.sqlExecute("select * from cproduct;");
		displayProducts();
	}
	
	private void logout() {
		fStore2.setVisible(false);
		new Login();
	}
	
	private void checkCustomers() {
		displayCustomers();
		_message("Check Customers", cTable);
	}
	
	private void manageOrders() {
			displayOrders();
			_message("Manage Orders", oTable);
			saveStatus();
	}
	
	private ArrayList<ArrayList<Object>> getStaffDetails() {
		return SQLInterface.sqlExecute("select * from login, staff where uid = sid and sid = '" + userInfo[0]+"';");
	}
	
	private void displayAccountInfo() {
		
		ArrayList<ArrayList<Object>> data = getStaffDetails();
		
		JTextField fname = new JTextField();
		fname.setText(data.get(1).get(4).toString());
		JTextField lname = new JTextField();
		lname.setText(data.get(1).get(5).toString());
		JTextField password = new JTextField();
		password.setText(data.get(1).get(2).toString());
		
		Object [] fields = {
				"First Name:", fname,
				"Last Name:", lname,
				"Username: " + data.get(1).get(1).toString(),
				"Password:", password,
				"Salary: $" + data.get(1).get(10).toString(),
				"Job Title: " + data.get(1).get(11).toString().toUpperCase()		
		};
		JOptionPane.showConfirmDialog(null, fields, "Edit Customer Details", JOptionPane.CANCEL_OPTION);
		
		updateStaffInfo(fname.getText(), lname.getText(),password.getText());
		
	}
	
	private void updateStaffInfo(String fname, String lname, String password) {
		if(SQLInterface.sqlUpdate("update login set upass = '" + password + "' where uid = '" + userInfo[0] + "';") == 0 ||
				SQLInterface.sqlUpdate("update staff set fname = '" + fname + "', lname = '"+ lname +"' where sid = '" + userInfo[0] + "';") == 0)
			_message("Failed!", "Account update was unsuccessful. Please try again later.", 1);
		else
			_message("Success", "Your account details have been saved.", 0);
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
			displayProductInfo(table.getSelectedRow());
			
		}
	}
	
}
