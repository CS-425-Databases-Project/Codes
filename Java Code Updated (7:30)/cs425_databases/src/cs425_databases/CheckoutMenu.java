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
				createNewCard(cName.getText(), cNumber.getText(), cExp.getText());
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
	
	private boolean _checkSelectedCard() {
		return true;
	}
	
	private boolean _checkAddress() {
		return true;
	}
	
	private void _createAddress() {
		address = tName.getText() + ", " + tStreet.getText() + ", " + tCity.getText() + " " + tState.getText() + " " + tZipcode.getText() + ".";
	}
	
	private void _countItems(){
		count = 0;
	}
	
	private void createNewCard(String name, String number, String expiration) {
		
	}
	
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
		if (!_checkSelectedCard()) {
			errorC.setVisible(true);
			errorA.setVisible(false);
		}
		else if (!_checkAddress()) {
			errorA.setVisible(true);
			errorC.setVisible(false);
		}
		else {
			_createAddress();
			_countItems();
			Object [] fields = {
					"Thank you! Your order has been placed!",
					count + " items will be delivered to the following address:",
					address
			};
			JOptionPane.showConfirmDialog(null, fields, "Transaction Complete", JOptionPane.PLAIN_MESSAGE);
			cancelOrder();
		}
			
	}
}
