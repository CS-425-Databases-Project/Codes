package cs425_databases;

import java.awt.Font;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cs425_databases.Grocery_store.AddToCartButton;
import cs425_databases.Grocery_store.ShowMoreInfo;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CartMenu {

	private JFrame fCart;
	private JScrollPane pMain;
	private JTable table;
	private JEditorPane moneyDetails;
	private String userInfo;
	private Object cart;
	private Object checkoutInfo;
	
	private int num_items = 0;
	
	private boolean cartIsOK = true;
	
	
	/**
	 * Create the application.
	 */
	public CartMenu(String userInfo, Object cart) {
		this.userInfo = userInfo;
		this.cart = cart;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		fCart = new JFrame();
		fCart.setTitle("https://www.store.com/cart?status=default");
		fCart.getContentPane().setBackground(Color.DARK_GRAY);
		fCart.setBackground(Color.BLACK);
		fCart.setBounds(100, 100, 898, 406);
		fCart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fCart.getContentPane().setLayout(null);
		
		JLabel top = new JLabel("    Your Cart");
		top.setForeground(Color.WHITE);
		top.setFont(new Font("Apple Braille", Font.PLAIN, 13));
		Image Images7=new ImageIcon (this.getClass().getResource("/shopping-cart-icon.png")).getImage();
		top.setIcon(new ImageIcon(Images7));
		top.setBounds(688, 20, 139, 48);
		fCart.getContentPane().add(top, BorderLayout.WEST);
		
		JButton bUpdate = new JButton("Update Cart");
		bUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				updateCart();
			}
		});
		bUpdate.setForeground(new Color(30, 144, 255));
		bUpdate.setBackground(new Color(255, 255, 255));
		bUpdate.setBounds(705, 90, 117, 29);
		fCart.getContentPane().add(bUpdate);
		
		JButton bContinue = new JButton("Continue Shopping");
		bContinue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				continueShopping();
			}
		});
		bContinue.setForeground(new Color(30, 144, 255));
		bContinue.setBackground(new Color(30, 144, 255));
		bContinue.setBounds(687, 130, 156, 29);
		fCart.getContentPane().add(bContinue);
		
		JButton bCheckout = new JButton("Checkout");
		bCheckout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				checkout();
			}
		});
		bCheckout.setForeground(new Color(255, 0, 0));
		bCheckout.setBackground(Color.WHITE);
		bCheckout.setBounds(705, 325, 117, 29);
		fCart.getContentPane().add(bCheckout);
		
		moneyDetails = new JEditorPane();
		moneyDetails.setForeground(new Color(0, 0, 128));
		moneyDetails.setFont(new Font("Lucida Bright", Font.PLAIN, 13));
		moneyDetails.setBackground(new Color(192, 192, 192));
		moneyDetails.setText("\n Subtotal                           $0.0\n\n Delivery Fee                     $0.0\n\n Tax                                   $0.0\n\n Total                                $0.0");
		moneyDetails.setBounds(652, 179, 227, 181);
		fCart.getContentPane().add(moneyDetails);
		
		pMain = new JScrollPane();
        pMain.setBounds(30, 20, 590, 340);
        fCart.getContentPane().add(pMain);
		
		createCartView();
		displayCartItems();
		
		fCart.setVisible(true);
		
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
	
	private void createCartView() {
		
		String[] columnNames = {"Picture", "Name", "Price", "Qty."};
        Object[][] data = {};

        DefaultTableModel model = new DefaultTableModel(data, columnNames)
        {
        	boolean[] canEdit = new boolean[]{
                    false, false, false, true
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
        
        pMain.setViewportView(table);
    
	}
	
	public void displayCurrentCart() {
		displayCartItems(); // Add "prevRS" as argument
	}
	
	
	private void displayCartItems() { // Add "ResultSet rs" as parameter later
		
		DefaultTableModel dm = (DefaultTableModel) table.getModel();
		
		int rowCount = dm.getRowCount();
		//Remove rows one by one from the end of the table
		for (int i = rowCount - 1; i >= 0; i--) {
		    dm.removeRow(i);
		}
		
		Object [][] data = getCartData(); // Add "ResultSet rs" as parameter later
		
		for(int i = 0 ; i < num_items; i++) {
			dm.addRow(data[i]);
		}
		
		createCheckoutInfo();
	}
	
	private Icon _getImage(String filename) {
		Image icon_image = new ImageIcon(this.getClass().getResource("/" + filename)).getImage();
		return new ImageIcon(icon_image.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
	}
	
	// To be done by Suleyma
	public Object [][] getCartData() { // Add "ResultSet rs" as parameter later
			  
		num_items = 1;
		  
		Object [][] data = { 
			{_getImage("oreo.jpeg"), "Oreo Milk Cookies", "$3.99", "3"}
		};
			    
		return data;    
	}
	
	private void updateCart() {

		cartIsOK = true;
		String qty = "0";
		
		for (int i = 0; i < num_items; i++) {
			qty = table.getModel().getValueAt(i, 3).toString(); 
			if (!_isNumeric(qty) || !(Integer.parseInt(qty) >= 0))
				cartIsOK = false;
		}
		
		if (cartIsOK) {
			// sql code to update the qty of all items in the cart in database
			// if qty = 0, then delete that item from the cart
			// Get updated cart in ResultSet rs
			// update the value of num_items
			
			createCheckoutInfo();
			displayCartItems(); // Add "rs" as argument
			_message("Success!", "The items in your cart have been successfully updated!", 0);
		}
		else {
			_message("Invalid Items!", "Some of the items in the cart have invalid quantities.", 1);
		}
		
	}
	
	private void continueShopping() {
		fCart.setVisible(false);
		new Grocery_store(userInfo, cart);
	}
	
	private void checkout() {
		createCheckoutInfo();
		fCart.setVisible(false);
		new CheckoutMenu(cart, checkoutInfo);
	}
	
	private void createCheckoutInfo() {
		
		double values[] = new double[3]; // return 3 values: subtotal, shipping, tax
		values[0] = 7.0;
		
		// find subtotal, shipping, tax... using sql + java codes
	
		displayCheckoutInfo(values);
	}
	
	private void displayCheckoutInfo(double values []) {
		moneyDetails.setText("\n Subtotal                           $" + values[0] + "\n\n Delivery Fee                     $" + values[1] + "\n\n Tax                                   $" + values[2] + "\n\n Total                                $" + (double)((int)((values[0] + values[1] + values[2])*100))/100);
	}
	
}
