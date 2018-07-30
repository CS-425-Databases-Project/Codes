import java.awt.EventQueue;

import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.JDesktopPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import java.awt.Button;
import java.awt.Label;
import java.awt.Choice;
import java.awt.Panel;
import java.awt.image.*;
import java.awt.EventQueue;
import java.awt.ScrollPane;
import java.awt.TextArea;
import java.awt.List;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import java.awt.Canvas;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JEditorPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Scrollbar;
import javax.swing.JFormattedTextField;
import javax.swing.JMenu;
import javax.swing.JList;
import javax.swing.JTextPane;

public class Cart {

	private JFrame frame;
	private JTextField txtProducts;
	private JTextField txtQty;
	private JTextField txtPrice;
	private JTextField txtRemove;
	private JTextField textField_1;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_6;
	private JTextField textField;
	private JLabel label;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_7;
	private JTextField textField_2;
	private JLabel label_1;
	private JLabel lblNewLabel_8;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cart window = new Cart();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Cart() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(240, 255, 240));
		frame.getContentPane().setForeground(Color.WHITE);
		frame.setBounds(100, 100, 733, 559);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtProducts = new JTextField();
		txtProducts.setForeground(new Color(50, 205, 50));
		txtProducts.setBackground(Color.WHITE);
		txtProducts.setText("                           Products(s)");
		txtProducts.setBounds(16, 58, 309, 26);
		frame.getContentPane().add(txtProducts);
		txtProducts.setColumns(10);
		
		txtQty = new JTextField();
		txtQty.setForeground(new Color(50, 205, 50));
		txtQty.setBackground(Color.WHITE);
		txtQty.setText("  Qty.");
		txtQty.setBounds(375, 58, 52, 26);
		frame.getContentPane().add(txtQty);
		txtQty.setColumns(10);
		
		txtPrice = new JTextField();
		txtPrice.setForeground(new Color(50, 205, 50));
		txtPrice.setText("   Price");
		txtPrice.setBackground(Color.WHITE);
		txtPrice.setBounds(467, 58, 62, 26);
		frame.getContentPane().add(txtPrice);
		txtPrice.setColumns(10);
		
		txtRemove = new JTextField();
		txtRemove.setForeground(new Color(50, 205, 50));
		txtRemove.setText(" Remove");
		txtRemove.setBackground(Color.WHITE);
		txtRemove.setBounds(567, 58, 70, 26);
		frame.getContentPane().add(txtRemove);
		txtRemove.setColumns(10);
		
		JButton btnNewButton = new JButton("Update Cart");
		btnNewButton.setForeground(new Color(30, 144, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setBounds(47, 471, 117, 29);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Continue Shopping");
		btnNewButton_1.setForeground(new Color(30, 144, 255));
		btnNewButton_1.setBackground(new Color(30, 144, 255));
		btnNewButton_1.setBounds(179, 471, 156, 29);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Checkout");
		btnNewButton_2.setForeground(new Color(255, 0, 0));
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.setBounds(598, 471, 117, 29);
		frame.getContentPane().add(btnNewButton_2);
		
		textField_1 = new JTextField();
		textField_1.setText(" 2");
		textField_1.setBounds(385, 96, 26, 26);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		Image Images=new ImageIcon (this.getClass().getResource("/trashG-icon.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(Images));
		lblNewLabel.setBounds(592, 92, 26, 41);
		frame.getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("$12.99");
		lblNewLabel_1.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(477, 96, 61, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_4 = new JLabel("");
		Image Images1=new ImageIcon(this.getClass().getResource("/7up.png")).getImage();
		lblNewLabel_4.setIcon(new ImageIcon(Images1));
		lblNewLabel_4.setBounds(26, 92, 319, 85);
		frame.getContentPane().add(lblNewLabel_4);
		Image Images2=new ImageIcon(this.getClass().getResource("/juice.png")).getImage();
		
		lblNewLabel_6 = new JLabel("");
		Image Images3=new ImageIcon(this.getClass().getResource("/dasaniwater.png")).getImage();
		lblNewLabel_6.setIcon(new ImageIcon(Images3));
		lblNewLabel_6.setBackground(new Color(255, 255, 255));
		lblNewLabel_6.setBounds(15, 189, 355, 91);
		frame.getContentPane().add(lblNewLabel_6);
		
		textField = new JTextField();
		textField.setText(" 1");
		textField.setBounds(385, 212, 26, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		label = new JLabel(" $5.99");
		label.setBounds(477, 200, 61, 16);
		frame.getContentPane().add(label);
		
		lblNewLabel_5 = new JLabel("");
		Image Images4=new ImageIcon (this.getClass().getResource("/trashG-icon.png")).getImage();
		lblNewLabel_5.setIcon(new ImageIcon(Images));
		lblNewLabel_5.setBounds(592, 190, 34, 26);
		frame.getContentPane().add(lblNewLabel_5);
		
		lblNewLabel_7 = new JLabel("");
		Image Images5=new ImageIcon (this.getClass().getResource("/tea.png")).getImage();
		lblNewLabel_7.setIcon(new ImageIcon(Images5));
		lblNewLabel_7.setBounds(25, 292, 345, 73);
		frame.getContentPane().add(lblNewLabel_7);
		
		textField_2 = new JTextField();
		textField_2.setText(" 1");
		textField_2.setBounds(385, 306, 26, 26);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		label_1 = new JLabel(" $3.99");
		label_1.setBounds(477, 309, 52, 21);
		frame.getContentPane().add(label_1);
		
		lblNewLabel_8 = new JLabel("");
		Image Images6=new ImageIcon (this.getClass().getResource("/trashG-icon.png")).getImage();
		lblNewLabel_8.setIcon(new ImageIcon(Images6));
		lblNewLabel_8.setBounds(592, 302, 34, 30);
		frame.getContentPane().add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("    Your Cart");
		lblNewLabel_9.setFont(new Font("Apple Braille", Font.PLAIN, 13));
		Image Images7=new ImageIcon (this.getClass().getResource("/shopping-cart-icon.png")).getImage();
		lblNewLabel_9.setIcon(new ImageIcon(Images7));
		lblNewLabel_9.setBounds(255, 6, 172, 48);
		frame.getContentPane().add(lblNewLabel_9);
		
		JEditorPane dtrpnSubtotalDelivery = new JEditorPane();
		dtrpnSubtotalDelivery.setForeground(new Color(0, 0, 128));
		dtrpnSubtotalDelivery.setFont(new Font("Lucida Bright", Font.PLAIN, 13));
		dtrpnSubtotalDelivery.setBackground(new Color(192, 192, 192));
		dtrpnSubtotalDelivery.setText(" Subtotal                           $22.97\n\n Delivery Fee                    $5.99\n\n Tax                                   $ 3.97\n\n Total:   $32.93");
		dtrpnSubtotalDelivery.setBounds(488, 377, 227, 123);
		frame.getContentPane().add(dtrpnSubtotalDelivery);
		
		JButton btnNewButton_3 = new JButton("X");
		btnNewButton_3.setForeground(new Color(255, 0, 0));
		btnNewButton_3.setBounds(676, 14, 39, 31);
		frame.getContentPane().add(btnNewButton_3);
		
		Scrollbar scrollbar = new Scrollbar();
		scrollbar.setBounds(645, 96, 9, 220);
		frame.getContentPane().add(scrollbar);
		
		
		
	
	
		
		
	
		
		
	}
}
