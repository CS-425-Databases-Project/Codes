package cs425_databases;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Date;

public class CheckoutMenu {

	private JFrame fCheckout;
	private double subtotal = 0.0;
	private double deliveryfee = 0.0;
	private double tax = 0.0;
	private double total = subtotal + deliveryfee + tax; 
	private JTextField tName;
	private JTextField tStreet;
	private JTextField tCity;
	private JTextField tState;
	private JTextField tZipcode;
	private JLabel errorA;
	private JLabel errorC; 
	
	private Object cart;
	private Object checkoutInfo;
	
	private String address;
	private int count;
	private String url = "jdbc:postgresql://localhost/cs425_project";
	private String user = "postgres";
	private   String password = "88378821";
	private String cid;
	private double ceyear, cemonth, cvs,number;
	
	/**
	 * Create the application.
	 */
	public CheckoutMenu(Object inputCart, Object inputInfo) {
		cart = inputCart;
		checkoutInfo = inputInfo;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		fCheckout = new JFrame();
		fCheckout.setTitle("https://www.store.com/checkout?id=19201");
		fCheckout.getContentPane().setBackground(Color.DARK_GRAY);
		fCheckout.setBounds(100, 100, 898, 455);
		fCheckout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fCheckout.getContentPane().setLayout(null);
		
		JLabel label = new JLabel();
		label.setForeground(Color.WHITE);
		label.setText("Payment Information");
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label.setBounds(83, 27, 436, 37);
		fCheckout.getContentPane().add(label);
		
		JLabel label_1 = new JLabel();
		label_1.setForeground(Color.WHITE);
		label_1.setText("Shipping Address");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_1.setBounds(83, 137, 153, 53);
		fCheckout.getContentPane().add(label_1);
		
		JLabel lblName = new JLabel();
		lblName.setForeground(Color.WHITE);
		lblName.setText("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblName.setBounds(100, 190, 131, 22);
		fCheckout.getContentPane().add(lblName);
		
		tName = new JTextField();
		tName.setBounds(258, 190, 317, 26);
		fCheckout.getContentPane().add(tName);
		
		JLabel lblBuildingstreet = new JLabel();
		lblBuildingstreet.setForeground(Color.WHITE);
		lblBuildingstreet.setText("Building/Street");
		lblBuildingstreet.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBuildingstreet.setBounds(100, 224, 131, 22);
		fCheckout.getContentPane().add(lblBuildingstreet);
		
		tStreet = new JTextField();
		tStreet.setBounds(258, 224, 317, 26);
		fCheckout.getContentPane().add(tStreet);
		
		JLabel lblCity = new JLabel();
		lblCity.setForeground(Color.WHITE);
		lblCity.setText("City");
		lblCity.setFont(new Font("Tahoma", lblCity.getFont().getStyle(), 16));
		lblCity.setBounds(100, 258, 44, 22);
		fCheckout.getContentPane().add(lblCity);
		
		tCity = new JTextField();
		tCity.setBounds(258, 258, 317, 26);
		fCheckout.getContentPane().add(tCity);
		
		JLabel lblState = new JLabel();
		lblState.setForeground(Color.WHITE);
		lblState.setText("State");
		lblState.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblState.setBounds(100, 292, 67, 22);
		fCheckout.getContentPane().add(lblState);
		
		tState = new JTextField();
		tState.setBounds(258, 292, 317, 26);
		fCheckout.getContentPane().add(tState);
		
		JButton bOrder = new JButton("Place Order");
		bOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				placeOrder();
			}
		});
		bOrder.setForeground(new Color(255, 0, 0));
		bOrder.setBackground(Color.WHITE);
		bOrder.setBounds(705, 295, 117, 29);
		fCheckout.getContentPane().add(bOrder);
		
		JEditorPane dtrpnSubtotalDelivery = new JEditorPane();
		dtrpnSubtotalDelivery.setForeground(new Color(0, 0, 128));
		dtrpnSubtotalDelivery.setFont(new Font("Lucida Bright", Font.PLAIN, 13));
		dtrpnSubtotalDelivery.setBackground(new Color(192, 192, 192));
		dtrpnSubtotalDelivery.setText("\n Subtotal                           $" + subtotal + "\n\n Delivery Fee                     $" + deliveryfee + "\n\n Tax                                   $" + tax + "\n\n Total                                $" + (double)((int)(total*100))/100);
		dtrpnSubtotalDelivery.setBounds(652, 149, 227, 181);
		fCheckout.getContentPane().add(dtrpnSubtotalDelivery);
		
		JLabel label_6 = new JLabel();
		label_6.setForeground(Color.WHITE);
		label_6.setText("Order summary");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_6.setBounds(652, 100, 171, 37);
		fCheckout.getContentPane().add(label_6);
		
		JComboBox<String> existingCards = new JComboBox<String>();
		existingCards.setBounds(195, 73, 143, 27);
		fCheckout.getContentPane().add(existingCards);
		
		JTextField cName = new JTextField();
		JTextField cNumber = new JTextField();
		JTextField cExp = new JTextField();
		
		Object [] fields = {
				"Name on Card:", cName, 
				"Card Number:", cNumber,
				"Expiration date(mm/yy):", cExp
		};
		
		JButton bAddCard = new JButton("Add New Card");
		bAddCard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showConfirmDialog(null, fields, "Enter your card information", JOptionPane.CANCEL_OPTION);
				//createNewCard(cName.getText(), cNumber.getText(), cExp.getText());
				createNewCard(number, ceyear, cemonth, cvs, cid);
			}
		});
		bAddCard.setForeground(Color.BLACK);
		bAddCard.setBackground(Color.WHITE);
		bAddCard.setBounds(363, 74, 131, 25);
		fCheckout.getContentPane().add(bAddCard);
		
		JLabel lblZipCode = new JLabel();
		lblZipCode.setText("Zip Code");
		lblZipCode.setForeground(Color.WHITE);
		lblZipCode.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblZipCode.setBounds(100, 325, 131, 22);
		fCheckout.getContentPane().add(lblZipCode);
		
		tZipcode = new JTextField();
		tZipcode.setBounds(258, 325, 317, 26);
		fCheckout.getContentPane().add(tZipcode);
		
		JLabel lblUseExistingCard = new JLabel("Use Card");
		lblUseExistingCard.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUseExistingCard.setForeground(Color.WHITE);
		lblUseExistingCard.setBounds(100, 76, 72, 18);
		fCheckout.getContentPane().add(lblUseExistingCard);
		
		JButton bModify = new JButton("Modify Cart");
		bModify.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				modifyCart();
			}
		});
		bModify.setForeground(new Color(30, 144, 255));
		bModify.setBackground(Color.WHITE);
		bModify.setBounds(130, 378, 156, 29);
		fCheckout.getContentPane().add(bModify);
		
		JButton bContinue = new JButton("Continue Shopping");
		bContinue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				continueShopping();
			}
		});
		bContinue.setForeground(new Color(30, 144, 255));
		bContinue.setBackground(new Color(30, 144, 255));
		bContinue.setBounds(373, 378, 156, 29);
		fCheckout.getContentPane().add(bContinue);
		
		JButton bCancel = new JButton("Cancel Order");
		bCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cancelOrder();
			}
		});
		bCancel.setForeground(new Color(30, 144, 255));
		bCancel.setBackground(new Color(30, 144, 255));
		bCancel.setBounds(625, 378, 156, 29);
		fCheckout.getContentPane().add(bCancel);
		
		errorC = new JLabel("* Please select a valid card");
		errorC.setForeground(Color.RED);
		errorC.setBounds(100, 102, 186, 16);
		errorC.setVisible(false);
		fCheckout.getContentPane().add(errorC);
		
		errorA = new JLabel("* Please enter a complete address");
		errorA.setForeground(Color.RED);
		errorA.setBounds(100, 355, 255, 16);
		errorA.setVisible(false);
		fCheckout.getContentPane().add(errorA);
		
		
		fCheckout.setVisible(true);
	}
	
	
	
	// Functions to Implement
	
	private boolean _checkSelectedCard(String cid) {Connection conn = null;
    try
    {
	
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
 stmt = conn.prepareStatement( " SELECT cnumber FROM creditcard where customerid=?");      
 stmt.setString(1,cid);
 ResultSet rs=stmt.executeQuery();
String result="";

while (rs.next())
{

	double showcard=rs.getDouble("cnumber");
	
	
			result=result+showcard+ "\n" ;
			

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
		return true;
	}
	
	private boolean _checkAddress(String cid) {
		Connection conn = null;
	    try
	    {
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
	 stmt = conn.prepareStatement( " SELECT pcity,pstate,pstreet,pzipcode FROM creditcard where customerid=?");      
	 stmt.setString(1,cid);
	 ResultSet rs=stmt.executeQuery();
	String result="";

	while (rs.next())
	{

		String showcity=rs.getString("picty");

		String showstate=rs.getString("pstate");

		String showstreet=rs.getString("pstreet");

		double showzipcode=rs.getDouble("pzipcode");
		
		
				result= result+ showcity +","+showstate+"," +showstreet+","+showzipcode+ "\n" ;
				

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
		return true;
	}
	private void _getpaymentinfo(String cid) {Connection conn = null;
    try
    {
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
 stmt = conn.prepareStatement( " SELECT * FROM creditcard where customerid=?");      
 stmt.setString(1,cid);
 ResultSet rs=stmt.executeQuery();
String result="";

while (rs.next())
{

	String showcity=rs.getString(2);

	String showstate=rs.getString(3);

	String showstreet=rs.getString(4);

	double showzipcode=rs.getDouble(5);
	double showcard=rs.getDouble(6);
	double showyear=rs.getDouble(7);
	double showmonth=rs.getDouble(8);
	double showcvs=rs.getDouble(9);
	
			result= result+ showcity +","+showstate+"," +showstreet+","+showzipcode+ "\n" +showcard+"      "+showyear+"/"+showmonth+"   "+showcvs+ "\n" ;
			

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
	
	private void _createAddress(String cid) {
		 
	
	
		Connection conn = null;
	    try
	    {
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
	 stmt = conn.prepareStatement( " select customerid,pcity,pstate,pstreet,pzipcode,cnumber from creditcard where customerid=?");      
	 stmt.setString(1,cid);
	 ResultSet rs=stmt.executeQuery();

       double number=0;
	while (rs.next())
	{

		
		
				number= rs.getDouble("cnumber");
				

	}

	PreparedStatement p2;
	p2=conn.prepareStatement("update creditcard set pcity=?,pstate=?,pstreet=?,pzipcode=? where cnumber=?");
	
	p2.setDouble(5, number);
	p2.setString(2,   tState.getText());
	p2.setString(3, tStreet.getText());
	p2.setString(1,   tCity.getText());
	p2.setString(4,   tZipcode.getText());
	
	
	
	ResultSet r2=p2.executeQuery();
		

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
	
	private void _countItems(String cid){
		Connection conn = null;
	    try
	    {
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
	 stmt = conn.prepareStatement( " SELECT sum(num) FROM creditcard where customerid=?");      
	 stmt.setString(1,cid);
	 ResultSet rs=stmt.executeQuery();
	int result;

	while (rs.next())
	{

		int shownum=rs.getInt(1);

		
		result=shownum;
				
				

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
	
	private void createNewCard(Double number,  Double ceyear,Double cemonth,Double cvs,String cid) {
		Connection conn = null;
	    try
	    {
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
	 stmt = conn.prepareStatement( " insert into creditcard(customerid,cnumber,ceyear,cemonth,cvs) values(?,?,?,?,?)");      
	 stmt.setString(1,cid);
	 stmt.setDouble(2,number);
	 stmt.setDouble(3,ceyear);
	 stmt.setDouble(4,cemonth);
	 stmt.setDouble(5,cvs);
	 ResultSet rs=stmt.executeQuery();
	int result;



		

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
	
	private void deletecart(String cid)
	{   
	Connection conn = null;
    try
    {
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
 stmt = conn.prepareStatement( "delete from table where cid=?");
 stmt.setString(1, cid);
ResultSet rs=stmt.executeQuery();



	

System.out.println("Database connection closed.");
        }
        catch (SQLException e)
        {
            System.err.println("SQL exception: " + e.getMessage());
            System.err.println("SQL state: " + e.getSQLState());
            System.err.println("Error code: " + e.getErrorCode());
        }


    }	}
	private void modifyCart() {
		fCheckout.setVisible(false);
		new CartMenu(cart);
	}
	
	private void continueShopping() {
		fCheckout.setVisible(false);
		new Grocery_store(cart);
	}
	
	private void cancelOrder() {
		fCheckout.setVisible(false);
		new Grocery_store();
	}
	
	private void placeOrder() {
		if (!_checkSelectedCard(cid)) {
			errorC.setVisible(true);
			errorA.setVisible(false);
		}
		else if (!_checkAddress(cid)) {
			errorA.setVisible(true);
			errorC.setVisible(false);
		}
		else {
			_createAddress(cid);
			_countItems(cid);
			Object [] fields = {
					"Thank you! Your order has been placed!",
					count + " items will be delivered to the following address:",
					address
			};
			JOptionPane.showConfirmDialog(null, fields, "Transaction Complete", JOptionPane.PLAIN_MESSAGE);
			cancelOrder();
		}
			
	}
	private void createorder(String cid,double card)
	{
	Connection conn = null;
    try
    {
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
 stmt = conn.prepareStatement( "select pid,num from cart where customerid=?");      
 stmt.setString(1,cid);
String ordernum="";
 ResultSet rs=stmt.executeQuery();
 Date mydate=new Date();
 String minedate=""+mydate;
 while(rs.next())
 {        String pid=rs.getString("pid");
 		int num=rs.getInt("num");
 
 PreparedStatement p2;
 
    
 
 
  p2=conn.prepareStatement("insert into orders values(?,?,?,?,?,?,?)");
  
  p2.setString(1, ordernum);
  p2.setString(2,cid);
  p2.setString(3,pid);
  p2.setInt(4, num);
 p2.setDouble(5,card);

 
 p2.setString(6, "processing");
 
p2.setString(7, minedate);


 }
 
 
 



	

System.out.println("Database connection closed.");
        }
        catch (SQLException e)
        {
            System.err.println("SQL exception: " + e.getMessage());
            System.err.println("SQL state: " + e.getSQLState());
            System.err.println("Error code: " + e.getErrorCode());
        }


    }	}
	
	
	
	
	
	
	
}