package FrontendCore;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import backendCore.ListElement;
import backendOperations.ListOperations;

public class MainMenu extends JFrame implements ActionListener {
	//Declare frame
	JFrame master;
	
	//Declare panels
	private JPanel mainP, titleP, addP, addFormP, addButtonP, productListP, tableP, searchP, editP, invP, sortP;
	
	//Declare labels
	private JLabel lblText, titleL, sortL, idL, vendorL, typeL, locationL, priceL, quantityL, nameL; 
	
	//Declare text field
	private JTextField searchTF, idTF, vendorTF, typeTF, locationTF, priceTF, quantityTF, nameTF;
	
	//Declare buttons
	private JButton allB, searchB, vendorB, typeB, locationB, 
		priceB, quantityB, discountB, addB, removeB, editB; 
	
	//Declare strings
	private String productInfo;
	private ArrayList<String> listToDisplay = new ArrayList<String>();  
	
	//Declare tables
	private DefaultTableModel tableModel;
	private JTable listT;
	
	// Constructor to setup GUI components and event handlers
	public MainMenu () {
		master = new JFrame();   // "super" Frame, which is a Container
		
		mainP = new JPanel();   //Panel that holds all other Panels
		master.add(mainP);      //Adding the main Panel into the super Frame
		
		//Panel for title
		titleP = new JPanel();
		titleP.setBackground(Color.green);
		titleL = new JLabel("Flower Shop IMS");
		titleL.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		titleL.setFont(titleL.getFont().deriveFont(50.0f));
		titleP.add(titleL);
		master.add(titleP, BorderLayout.PAGE_START);
		
		//Panel to add a product
		addP = new JPanel();
		addFormP = new JPanel();
		addButtonP = new JPanel();
		addP.setLayout(new BoxLayout(addP,BoxLayout.Y_AXIS));
		addP.setBorder(BorderFactory.createLoweredBevelBorder());
		addFormP.setLayout(new GridLayout(7,2));
		addFormP.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		addButtonP.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		idL = new JLabel("Product ID");
		nameL = new JLabel("Name");
		typeL = new JLabel("Type");
		priceL = new JLabel("Price");
		quantityL = new JLabel("Quantity on Hand");
		locationL = new JLabel("Location");
		vendorL = new JLabel("Vendor");
		
		idTF = new JTextField(15);
		nameTF = new JTextField(15);
		typeTF = new JTextField(15);
		priceTF = new JTextField(15);
		quantityTF = new JTextField(15);
		locationTF = new JTextField(15);
		vendorTF = new JTextField(15);
		
		addB = new JButton("Add Product");
		addB.addActionListener(this);
		
		addFormP.add(idL);
		addFormP.add(idTF);
		addFormP.add(nameL);
		addFormP.add(nameTF);
		addFormP.add(typeL);
		addFormP.add(typeTF);
		addFormP.add(priceL);
		addFormP.add(priceTF);
		addFormP.add(quantityL);
		addFormP.add(quantityTF);
		addFormP.add(locationL);
		addFormP.add(locationTF);
		addFormP.add(vendorL);
		addFormP.add(vendorTF);
		addButtonP.add(addB);
		addP.add(addFormP);
		addP.add(addButtonP);
		
		mainP.add(addP, BorderLayout.LINE_START);
		
		//ProductList panel
		productListP = new JPanel();
		searchP = new JPanel();
		tableP = new JPanel();
		sortP = new JPanel();
		
		productListP.setLayout(new BoxLayout(productListP, BoxLayout.Y_AXIS));
		tableP.setPreferredSize(new Dimension(50,50));
		
		//Search Panel
		allB = new JButton("View All");
		allB.addActionListener(this);
		
		searchTF = new JTextField(20);
		searchB = new JButton("Search");
		
		searchP.add(allB);
		searchP.add(searchTF);
		searchP.add(searchB);
		
		//Table Panel
		listT = new JTable();
		
		String[] colNames = {"Product ID","Product Name","Vendor","ProductType","Location","Price","Quantity","Discount"};
		//Object[][] data = {001, "Lily",""}
		tableP.add(listT);
		
		//Sort Panel
		sortL = new JLabel("Sort By: ");
		
		typeB = new JButton("Type");
		priceB = new JButton("Price");
		discountB = new JButton("Discount");
		quantityB = new JButton("Quantity");
		locationB = new JButton("Location");
		vendorB = new JButton("Vendor");
		
		typeB.addActionListener(this);
		priceB.addActionListener(this);
		discountB.addActionListener(this);
		quantityB.addActionListener(this);
		locationB.addActionListener(this);
		vendorB.addActionListener(this);
		
		sortP.add(sortL);
		sortP.add(typeB);
		sortP.add(priceB);
		sortP.add(discountB);
		sortP.add(quantityB);
		sortP.add(locationB);
		sortP.add(vendorB);
		
		productListP.add(searchP);
		productListP.add(tableP);
		productListP.add(sortP);
		
		mainP.add(productListP, BorderLayout.LINE_END);
		
		 /* //Inventory panel that displays all product invP = new JPanel(); lblText =
		 * new JLabel(); // construct the Label component lblText.setPreferredSize(new
		 * Dimension(800,100)); invP.add(lblText); // "super" Frame container adds Label
		 * component titleP.add(invP); */
	   
	    master.setTitle("Flower shop IMS Main Menu");  // "super" Frame sets its title
	    master.setSize(1000, 500);        // "super" Frame sets its initial window size
	 
	    master.setVisible(true);         // "super" Frame shows
	    master.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	    
	    }
	   
	   public void displayListInFrame(ArrayList<ListElement> flowerShopImsList) {
		   System.out.println("The IMS list size: "+flowerShopImsList.size());
		   //tableModel = new DefaultTableModel(colNames,flowerShopImsList.size());
		   //listTable = new JTable(tableModel);
		   for (Object obj : flowerShopImsList) {
			   //tableModel.addRow(obj);
	            ListElement node = (ListElement) obj;
	            productInfo = node.getProductID() + node.getProductName()+ node.getSalePrice()
	            + node.getVendorID()+ node.getQuantityOnHand();
	            System.out.println(" list :- " + productInfo);
	            listToDisplay.add(productInfo);
		   	}
	   }
	 
	   // ActionEvent handler - Called back upon button-click.
	   public void actionPerformed(ActionEvent e) {
		   if(e.getSource() == allB) {
		   displayListInFrame(ListOperations.flowerShopImsList);
		  
	       // Display the counter value on the TextField tfCount
		   lblText.setText(""+listToDisplay);
		   }
		   
		   if(e.getSource() == vendorB) {
			   
		   }
		   
		   if(e.getSource() == typeB) {
			   
		   }
	   }

	
}
