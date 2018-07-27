package cs425_databases;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class Grocery_store_2 {

	private JFrame frmHttpswwwstorecomhomeuserstaff;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Grocery_store_2 window = new Grocery_store_2();
					window.frmHttpswwwstorecomhomeuserstaff.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
		
		frmHttpswwwstorecomhomeuserstaff = new JFrame();
		frmHttpswwwstorecomhomeuserstaff.getContentPane().setBackground(Color.BLACK);
		frmHttpswwwstorecomhomeuserstaff.setForeground(Color.BLACK);
		frmHttpswwwstorecomhomeuserstaff.setBackground(Color.BLACK);
		frmHttpswwwstorecomhomeuserstaff.setTitle("https://www.store.com/home?user=staff");
		frmHttpswwwstorecomhomeuserstaff.setBounds(100, 100, 814, 617);
		frmHttpswwwstorecomhomeuserstaff.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHttpswwwstorecomhomeuserstaff.getContentPane().setLayout(null);
		
		Image hero_image = new ImageIcon(this.getClass().getResource("/hero.png")).getImage();
		ImageIcon hero = new ImageIcon(hero_image.getScaledInstance(815, 200, Image.SCALE_SMOOTH));
		
		Image icon_image = new ImageIcon(this.getClass().getResource("/store.png")).getImage();
		ImageIcon icon = new ImageIcon(icon_image.getScaledInstance(120, 140, Image.SCALE_SMOOTH));
		
		Image recent_image = new ImageIcon(this.getClass().getResource("/recent.png")).getImage();
		ImageIcon recent = new ImageIcon(recent_image.getScaledInstance(150, 100, Image.SCALE_SMOOTH));
		
		JLabel rec = new JLabel("");
		rec.setBounds(662, 120, 155, 92);
		rec.setIcon(recent);
		frmHttpswwwstorecomhomeuserstaff.getContentPane().add(rec);
		
		JLabel str_icn = new JLabel("");
		str_icn.setBounds(21, 0, 128, 144);
		str_icn.setIcon(icon);
		frmHttpswwwstorecomhomeuserstaff.getContentPane().add(str_icn);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(157, 97, 429, 26);
		frmHttpswwwstorecomhomeuserstaff.getContentPane().add(textField);
		
		JButton button = new JButton("Search");
		button.setBounds(590, 97, 78, 29);
		frmHttpswwwstorecomhomeuserstaff.getContentPane().add(button);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setBounds(710, 70, 102, 29);
		frmHttpswwwstorecomhomeuserstaff.getContentPane().add(btnLogOut);
		
		JButton btnAccountInfo = new JButton("Account Info");
		btnAccountInfo.setBounds(572, 70, 139, 29);
		frmHttpswwwstorecomhomeuserstaff.getContentPane().add(btnAccountInfo);
		
		JButton btnStaffView = new JButton("Staff Home");
		btnStaffView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnStaffView.setBounds(154, 70, 117, 29);
		frmHttpswwwstorecomhomeuserstaff.getContentPane().add(btnStaffView);
		
		JButton btnCheckCustomers = new JButton("Check Customers");
		btnCheckCustomers.setBounds(269, 70, 155, 29);
		frmHttpswwwstorecomhomeuserstaff.getContentPane().add(btnCheckCustomers);
		
		JButton btnManageOrders = new JButton("Manage Orders");
		btnManageOrders.setBounds(424, 70, 147, 29);
		frmHttpswwwstorecomhomeuserstaff.getContentPane().add(btnManageOrders);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(161, 135, 500, 450);
		frmHttpswwwstorecomhomeuserstaff.getContentPane().add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(671, 136, 133, 449);
		frmHttpswwwstorecomhomeuserstaff.getContentPane().add(scrollPane_1);
		
		JLabel lblFilterBy = new JLabel("Filter By");
		lblFilterBy.setForeground(Color.WHITE);
		lblFilterBy.setBounds(15, 350, 61, 16);
		frmHttpswwwstorecomhomeuserstaff.getContentPane().add(lblFilterBy);
		
		JRadioButton rbr = new JRadioButton("Relevance");
		rbr.setForeground(Color.WHITE);
		rbr.setBounds(7, 380, 94, 23);
		frmHttpswwwstorecomhomeuserstaff.getContentPane().add(rbr);
		
		JRadioButton rbplh = new JRadioButton("Price: Low to High");
		rbplh.setForeground(Color.WHITE);
		rbplh.setBounds(7, 410, 147, 23);
		frmHttpswwwstorecomhomeuserstaff.getContentPane().add(rbplh);
		
		JRadioButton rbphl = new JRadioButton("Price: High to Low");
		rbphl.setForeground(Color.WHITE);
		rbphl.setBounds(7, 440, 149, 23);
		frmHttpswwwstorecomhomeuserstaff.getContentPane().add(rbphl);
		
		JRadioButton rbla = new JRadioButton("Latest Arrivals");
		rbla.setForeground(Color.WHITE);
		rbla.setBounds(7, 470, 133, 23);
		frmHttpswwwstorecomhomeuserstaff.getContentPane().add(rbla);
		
		ButtonGroup filters = new ButtonGroup();
		filters.add(rbr);
		filters.add(rbphl);
		filters.add(rbplh);
		filters.add(rbla);
		
		JLabel lblShopByDepartment = new JLabel("Search By Department");
		lblShopByDepartment.setForeground(Color.WHITE);
		lblShopByDepartment.setBounds(15, 153, 139, 34);
		frmHttpswwwstorecomhomeuserstaff.getContentPane().add(lblShopByDepartment);
		
		JCheckBox chckbxMeatSeafood = new JCheckBox("Meat & Seafood");
		chckbxMeatSeafood.setForeground(Color.WHITE);
		chckbxMeatSeafood.setBounds(7, 190, 133, 23);
		frmHttpswwwstorecomhomeuserstaff.getContentPane().add(chckbxMeatSeafood);
		
		JCheckBox chckbxFruitsVegetables = new JCheckBox("Fruits & Vegetables");
		chckbxFruitsVegetables.setForeground(Color.WHITE);
		chckbxFruitsVegetables.setBounds(7, 215, 155, 23);
		frmHttpswwwstorecomhomeuserstaff.getContentPane().add(chckbxFruitsVegetables);
		
		JCheckBox chckbxBeverages = new JCheckBox("Beverages");
		chckbxBeverages.setForeground(Color.WHITE);
		chckbxBeverages.setBounds(7, 240, 128, 23);
		frmHttpswwwstorecomhomeuserstaff.getContentPane().add(chckbxBeverages);
		
		JCheckBox chckbxSnacksCandy = new JCheckBox("Snacks & Candy");
		chckbxSnacksCandy.setForeground(Color.WHITE);
		chckbxSnacksCandy.setBounds(7, 265, 142, 23);
		frmHttpswwwstorecomhomeuserstaff.getContentPane().add(chckbxSnacksCandy);
		
		JCheckBox chckbxFrozenFoods = new JCheckBox("Frozen Foods");
		chckbxFrozenFoods.setForeground(Color.WHITE);
		chckbxFrozenFoods.setBounds(7, 290, 128, 23);
		frmHttpswwwstorecomhomeuserstaff.getContentPane().add(chckbxFrozenFoods);
		
		JLabel lhero = new JLabel("");
		lhero.setBounds(0, -48, 829, 172);
		lhero.setIcon(hero);
		frmHttpswwwstorecomhomeuserstaff.getContentPane().add(lhero);
	}
}
