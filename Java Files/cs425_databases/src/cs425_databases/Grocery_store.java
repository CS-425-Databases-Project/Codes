package cs425_databases;

import java.awt.Image;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Grocery_store {

	private JFrame frmHttpwwwstorecom;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Grocery_store window = new Grocery_store();
					window.frmHttpwwwstorecom.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Grocery_store() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHttpwwwstorecom = new JFrame();
		frmHttpwwwstorecom.getContentPane().setBackground(Color.BLACK);
		frmHttpwwwstorecom.setResizable(false);
		frmHttpwwwstorecom.setTitle("http://www.store.com/home");
		frmHttpwwwstorecom.setBounds(100, 100, 814, 617);
		frmHttpwwwstorecom.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHttpwwwstorecom.getContentPane().setLayout(null);
		
		Image icon_image = new ImageIcon(this.getClass().getResource("/store.png")).getImage();
		ImageIcon icon = new ImageIcon(icon_image.getScaledInstance(120, 140, Image.SCALE_SMOOTH));
		
		Image hero_image = new ImageIcon(this.getClass().getResource("/hero.png")).getImage();
		ImageIcon hero = new ImageIcon(hero_image.getScaledInstance(815, 200, Image.SCALE_SMOOTH));
		
		Image offers_image = new ImageIcon(this.getClass().getResource("/offers.png")).getImage();
		ImageIcon offers = new ImageIcon(offers_image.getScaledInstance(150, 60, Image.SCALE_SMOOTH));
		
		JButton btnAccountInfo = new JButton("Account Info");
		btnAccountInfo.setBounds(614, 70, 117, 29);
		frmHttpwwwstorecom.getContentPane().add(btnAccountInfo);
		
		JButton btnCart = new JButton("View Cart");
		btnCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCart.setBounds(515, 70, 105, 29);
		frmHttpwwwstorecom.getContentPane().add(btnCart);
		
		textField = new JTextField();
		textField.setBounds(157, 97, 429, 26);
		frmHttpwwwstorecom.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(590, 97, 78, 29);
		frmHttpwwwstorecom.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(161, 135, 500, 450);
		frmHttpwwwstorecom.getContentPane().add(scrollPane);
		
		JLabel lblShopByDepartment = new JLabel("Shop By Department");
		lblShopByDepartment.setForeground(Color.WHITE);
		lblShopByDepartment.setBounds(15, 153, 133, 34);
		frmHttpwwwstorecom.getContentPane().add(lblShopByDepartment);
		
		JCheckBox chckbxMeatSeafood = new JCheckBox("Meat & Seafood");
		chckbxMeatSeafood.setForeground(Color.WHITE);
		chckbxMeatSeafood.setBounds(7, 190, 133, 23);
		frmHttpwwwstorecom.getContentPane().add(chckbxMeatSeafood);
		
		JCheckBox chckbxFruitsVegetables = new JCheckBox("Fruits & Vegetables");
		chckbxFruitsVegetables.setForeground(Color.WHITE);
		chckbxFruitsVegetables.setBounds(7, 215, 155, 23);
		frmHttpwwwstorecom.getContentPane().add(chckbxFruitsVegetables);
		
		JCheckBox chckbxBeverages = new JCheckBox("Beverages");
		chckbxBeverages.setForeground(Color.WHITE);
		chckbxBeverages.setBounds(7, 240, 128, 23);
		frmHttpwwwstorecom.getContentPane().add(chckbxBeverages);
		
		JCheckBox chckbxSnacksCandy = new JCheckBox("Snacks & Candy");
		chckbxSnacksCandy.setForeground(Color.WHITE);
		chckbxSnacksCandy.setBounds(7, 265, 142, 23);
		frmHttpwwwstorecom.getContentPane().add(chckbxSnacksCandy);
		
		JCheckBox chckbxFrozenFoods = new JCheckBox("Frozen Foods");
		chckbxFrozenFoods.setForeground(Color.WHITE);
		chckbxFrozenFoods.setBounds(7, 290, 128, 23);
		frmHttpwwwstorecom.getContentPane().add(chckbxFrozenFoods);
		
		JLabel lblFilterBy = new JLabel("Filter By");
		lblFilterBy.setForeground(Color.WHITE);
		lblFilterBy.setBounds(15, 350, 61, 16);
		frmHttpwwwstorecom.getContentPane().add(lblFilterBy);
		
		JRadioButton rbr = new JRadioButton("Relevance");
		rbr.setForeground(Color.WHITE);
		rbr.setBounds(7, 380, 94, 23);
		frmHttpwwwstorecom.getContentPane().add(rbr);
		
		JRadioButton rbplh = new JRadioButton("Price: Low to High");
		rbplh.setForeground(Color.WHITE);
		rbplh.setBounds(7, 410, 147, 23);
		frmHttpwwwstorecom.getContentPane().add(rbplh);
		
		JRadioButton rbphl = new JRadioButton("Price: High to Low");
		rbphl.setForeground(Color.WHITE);
		rbphl.setBounds(7, 440, 149, 23);
		frmHttpwwwstorecom.getContentPane().add(rbphl);
		
		JRadioButton rbla = new JRadioButton("Latest Arrivals");
		rbla.setForeground(Color.WHITE);
		rbla.setBounds(7, 470, 133, 23);
		frmHttpwwwstorecom.getContentPane().add(rbla);
		
		ButtonGroup filters = new ButtonGroup();
		filters.add(rbr);
		filters.add(rbphl);
		filters.add(rbplh);
		filters.add(rbla);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setBounds(725, 70, 90, 29);
		frmHttpwwwstorecom.getContentPane().add(btnLogOut);
		
		JButton btnHome = new JButton("Home");
		btnHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmHttpwwwstorecom.setVisible(false);
				new Grocery_store();
			}
		});
		btnHome.setBounds(154, 70, 117, 29);
		frmHttpwwwstorecom.getContentPane().add(btnHome);
		
		JButton btnAbout = new JButton("Contact Us");
		btnAbout.setBounds(265, 70, 99, 29);
		frmHttpwwwstorecom.getContentPane().add(btnAbout);
		
		JButton btnShopPastPurchases = new JButton("Shop Past Purchases");
		btnShopPastPurchases.setBounds(358, 70, 163, 29);
		frmHttpwwwstorecom.getContentPane().add(btnShopPastPurchases);
		
		JLabel offs = new JLabel("");
		offs.setForeground(Color.WHITE);
		offs.setBounds(665, 105, 164, 66);
		offs.setIcon(offers);
		frmHttpwwwstorecom.getContentPane().add(offs);
		
		JLabel str_icn = new JLabel("");
		str_icn.setBounds(21, 0, 128, 144);
		str_icn.setIcon(icon);
		frmHttpwwwstorecom.getContentPane().add(str_icn);
		
		JLabel lhero = new JLabel("");
		lhero.setBounds(0, -48, 829, 172);
		lhero.setIcon(hero);
		frmHttpwwwstorecom.getContentPane().add(lhero);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(671, 136, 133, 449);
		frmHttpwwwstorecom.getContentPane().add(scrollPane_1);
		
		frmHttpwwwstorecom.setVisible(true);
	}
}
