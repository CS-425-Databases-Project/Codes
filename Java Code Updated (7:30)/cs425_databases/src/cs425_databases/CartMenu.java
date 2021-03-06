package cs425_databases;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CartMenu {

	private JFrame fCart;
	
	private double subtotal = 0.0;
	private double deliveryfee = 0.0;
	private double tax = 0.0;
	private double total = subtotal + deliveryfee + tax; 
	
	private Object cart;
	private Object checkoutInfo;
	
	private JTextArea tMain;
	
	/**
	 * Create the application.
	 */
	public CartMenu(Object input) {
		cart = input;
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
		fCart.setBounds(100, 100, 898, 402);
		fCart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fCart.getContentPane().setLayout(null);
		
		JLabel top = new JLabel("    Your Cart");
		top.setForeground(Color.WHITE);
		top.setFont(new Font("Apple Braille", Font.PLAIN, 13));
		Image Images7=new ImageIcon (this.getClass().getResource("/shopping-cart-icon.png")).getImage();
		top.setIcon(new ImageIcon(Images7));
		top.setBounds(688, 20, 139, 48);
		fCart.getContentPane().add(top, BorderLayout.WEST);
		
		JTextField txtProducts = new JTextField();
		txtProducts.setForeground(new Color(50, 205, 50));
		txtProducts.setBackground(Color.WHITE);
		txtProducts.setText("                           Product(s)");
		txtProducts.setBounds(16, 18, 309, 26);
		txtProducts.setEditable(false);
		fCart.getContentPane().add(txtProducts);
		txtProducts.setColumns(10);
		
		JTextField txtQty = new JTextField();
		txtQty.setForeground(new Color(50, 205, 50));
		txtQty.setBackground(Color.WHITE);
		txtQty.setText("  Qty.");
		txtQty.setBounds(375, 18, 52, 26);
		txtQty.setEditable(false);
		fCart.getContentPane().add(txtQty);
		txtQty.setColumns(10);
		
		JTextField txtPrice = new JTextField();
		txtPrice.setForeground(new Color(50, 205, 50));
		txtPrice.setText("   Price");
		txtPrice.setBackground(Color.WHITE);
		txtPrice.setBounds(467, 18, 62, 26);
		fCart.getContentPane().add(txtPrice);
		txtPrice.setColumns(10);
		txtPrice.setEditable(false);
		
		JTextField txtRemove = new JTextField();
		txtRemove.setForeground(new Color(50, 205, 50));
		txtRemove.setText(" Remove");
		txtRemove.setBackground(Color.WHITE);
		txtRemove.setBounds(567, 18, 70, 26);
		fCart.getContentPane().add(txtRemove);
		txtRemove.setColumns(10);
		txtRemove.setEditable(false);
		
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
		
		JEditorPane dtrpnSubtotalDelivery = new JEditorPane();
		dtrpnSubtotalDelivery.setForeground(new Color(0, 0, 128));
		dtrpnSubtotalDelivery.setFont(new Font("Lucida Bright", Font.PLAIN, 13));
		dtrpnSubtotalDelivery.setBackground(new Color(192, 192, 192));
		dtrpnSubtotalDelivery.setText("\n Subtotal                           $" + subtotal + "\n\n Delivery Fee                     $" + deliveryfee + "\n\n Tax                                   $" + tax + "\n\n Total                                $" + (double)((int)(total*100))/100);
		dtrpnSubtotalDelivery.setBounds(652, 179, 227, 181);
		fCart.getContentPane().add(dtrpnSubtotalDelivery);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(195, 186, -54, 37);
		fCart.getContentPane().add(scrollPane);
		
		JScrollPane pMain = new JScrollPane();
		pMain.setBounds(19, 56, 615, 304);
		fCart.getContentPane().add(pMain);
		
		tMain = new JTextArea();
		pMain.setViewportView(tMain);
		
		displayCart();
		
		fCart.setVisible(true);
		
	}
	
	
	// Functions to Implement
	
	private void _createCheckoutInfo() {
		
	}
	
	private void displayCart() {
		tMain.setText("Display Cart Items");
	}
	
	private void updateCart() {
		tMain.setText("Display Updated Cart Items");
		//displayCart();
	}
	
	private void continueShopping() {
		fCart.setVisible(false);
		new Grocery_store(cart);
	}
	
	private void checkout() {
		_createCheckoutInfo();
		fCart.setVisible(false);
		new CheckoutMenu(cart, checkoutInfo);
	}
}
