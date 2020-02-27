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
	private JPanel mainP, titleP, addP, addFormP, editButtonP, updateP, productListP, searchP, sortP;
	private JScrollPane tableP;
	
	//Declare labels
	private JLabel lblText, titleL, sortL, idL, vendorL, typeL, locationL, priceL, quantityL, nameL, discountL; 
	
	//Declare text field
	private JTextField searchTF, idTF, vendorTF, typeTF, locationTF, priceTF, quantityTF, nameTF, discountTF;
	
	//Declare buttons
	private JButton allB, searchB, vendorB, typeB, locationB, 
		priceB, quantityB, discountB, addB, removeB, updateB;
	
	//Declare strings
	private String productInfo;
	private ArrayList<String> listToDisplay = new ArrayList<String>();  
	
	//Declare table
	private DefaultTableModel tableModel;
	private JTable listT;
	
	//Create instance of the ListOperations class
	
	// Constructor to setup GUI components and event handlers
	public MainMenu () {
		master = new JFrame();   // "super" Frame, which is a Container
		
		mainP = new JPanel();   //Panel that holds all other Panels
		master.add(mainP);      //Adding the main Panel into the super Frame
		
		//Panel for title
		titleP = new JPanel();
		titleP.setBackground(Color.green);    //Sets title panel's background color
		titleL = new JLabel("Flower Shop IMS");   //Set title text
		titleL.setAlignmentX(JLabel.CENTER_ALIGNMENT);  //Aligns the title to the center
		titleL.setFont(titleL.getFont().deriveFont(50.0f));  //Change font size
		
		titleP.add(titleL);   //Adds title text to title panel
		master.add(titleP, BorderLayout.PAGE_START);  //Adds title panel to "super" Frame
		
		//Edit panel with add & update buttons
		addP = new JPanel();
		addFormP = new JPanel();
		editButtonP = new JPanel();
		
		addP.setLayout(new BoxLayout(addP,BoxLayout.Y_AXIS));   //Sets layout to edit panel for all containers in this panel to a vertical alignment
		addP.setPreferredSize(new Dimension(400,300));  //sets size of the edit panel
		addFormP.setLayout(new GridLayout(8,2));   //sets a grid layout to the add form
		
		addP.setBorder(BorderFactory.createLoweredBevelBorder());
		addFormP.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		editButtonP.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		//Initialize labels
		idL = new JLabel("Product ID");
		nameL = new JLabel("Name");
		typeL = new JLabel("Type");
		priceL = new JLabel("Price");
		quantityL = new JLabel("Quantity on Hand");
		locationL = new JLabel("Location");
		vendorL = new JLabel("VendorID");
		discountL = new JLabel("Discount");
		
		//Initialize text fields
		idTF = new JTextField(15);
		nameTF = new JTextField(15);
		typeTF = new JTextField(15);
		priceTF = new JTextField(15);
		quantityTF = new JTextField(15);
		locationTF = new JTextField(15);
		vendorTF = new JTextField(15);
		discountTF = new JTextField("Enter true or false",15);
		
		//initialize buttons
		addB = new JButton("Add Product");
		updateB = new JButton("Update Product");
		
		addB.addActionListener(this);
		updateB.addActionListener(this);
		
		//Add components to the edit panel
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
		addFormP.add(discountL);
		addFormP.add(discountTF);
		editButtonP.add(addB);
		editButtonP.add(updateB);
		addP.add(addFormP);
		addP.add(editButtonP);
		
		//Add edit panel to the main panel
		mainP.add(addP, BorderLayout.LINE_START);

		//ProductList panel that displays the product inventory
		productListP = new JPanel();
		searchP = new JPanel();
		sortP = new JPanel();
		
		//Set formats for panels
		productListP.setLayout(new BoxLayout(productListP, BoxLayout.Y_AXIS));
		productListP.setBorder(BorderFactory.createLoweredBevelBorder());
		sortP.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		//Search Panel
		allB = new JButton("View All");   //Initialize "View All" button
		allB.addActionListener(this);     //Add actionlistener to "View All" button
		
		searchTF = new JTextField("Input product ID or product name to search");  //Initialize search textfield
		searchB = new JButton("Search");  //Initialize search button
		
		searchP.add(allB);
		searchP.add(searchTF);
		searchP.add(searchB);
		
		//Table Panel
		listT = createTable(ListOperations.flowerShopImsList);
		listT.setFillsViewportHeight(true);
		
		tableP = new JScrollPane(listT);
		tableP.setPreferredSize(new Dimension(100,200));
		
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
		sortP.add(quantityB);
		sortP.add(discountB);
		sortP.add(locationB);
		sortP.add(vendorB);
		
		productListP.add(searchP);
		productListP.add(tableP);
		productListP.add(sortP);
		
		mainP.add(productListP, BorderLayout.LINE_END);
	   
		//Set super Frame properties
	    master.setTitle("Flower shop IMS Main Menu");  // "super" Frame sets its title
	    master.setSize(1100, 450);        // "super" Frame sets its initial window size
	    master.setResizable(false);
	    
	    master.setVisible(true);         // "super" Frame shows
	    master.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
	    
	    }
	
		public JTable createTable(ArrayList<ListElement> arrayList) {
			String[] colNames = {"Product ID","Product Name","ProductType","Price","Location","VendorID","Quantity","Discount"};
			tableModel = new DefaultTableModel(colNames,0);
			JTable table = new JTable(tableModel);
			
			for (ListElement obj: arrayList) {
				int id = obj.getProductID();
				String name = obj.getProductName();
				String type = obj.getProductType();
				double price = obj.getSalePrice();
				String location = obj.getLocation();
				int vendorID = obj.getVendorID();
				int quantity = obj.getQuantityOnHand();
				boolean discount = obj.getDiscount();
				
				Object[] product = {id, name, type, price, location, vendorID, quantity, discount};
				tableModel.addRow(product);
			}
			return table;
		}
		
		public void displayListInFrame(ArrayList<ListElement> flowerShopImsList) {
			for (Object obj : flowerShopImsList) {
	            ListElement node = (ListElement) obj;
	            productInfo = node.getProductID() + node.getProductName()+ node.getSalePrice()
	            + node.getVendorID()+ node.getQuantityOnHand();
	            System.out.println(" list :- " + productInfo);
	            listToDisplay.add(productInfo);
		   	}
	   }
	 
	   // ActionEvent handler - Called back upon button-click.
	   public void actionPerformed(ActionEvent e) {
		   ListOperations listOp = new ListOperations();
		   if(e.getSource() == allB) {
			   productListP.revalidate();
			   productListP.repaint();
		   }
		   
		   else if(e.getSource() == typeB) {
			   
		   }
		   
		   else if(e.getSource() == priceB) {
			   
		   }
		   
		   else if(e.getSource() == quantityB) {
			   
		   }
		   
		   else if(e.getSource() == discountB) {
			   
		   }
		   
		   else if(e.getSource() == locationB) {
			   
		   }
		   
		   else if(e.getSource() == vendorB) {
			   
		   }
		   
		   else if(e.getSource() == addB) {
			   String id = idTF.getText();
			   String name = nameTF.getText();
			   String type = typeTF.getText();
			   String price = priceTF.getText();
			   String location = locationTF.getText();
			   String vendor = vendorTF.getText();
			   String quantity = quantityTF.getText();
			   String discount = discountTF.getText();
			   
			   int idInt = Integer.parseInt(id);
			   int vendorInt = Integer.parseInt(vendor);
			   int quantityInt = Integer.parseInt(quantity);
			   double priceDouble = Double.parseDouble(price);
			   boolean discountBoolean = Boolean.parseBoolean(discount);
			   
			   listOp.push(idInt, name, type, priceDouble, location, vendorInt, quantityInt, discountBoolean);
			   
			   JTable newT = new JTable(tableModel);
			   newT = createTable(listOp.flowerShopImsList);
			   listT.setModel(newT.getModel());
			   
			   productListP.revalidate();
			   productListP.repaint();
		   }
		   
		   else if(e.getSource() == updateB) {
			   String id = idTF.getText();
			   String name = nameTF.getText();
			   String type = typeTF.getText();
			   String price = priceTF.getText();
			   String location = locationTF.getText();
			   String vendor = vendorTF.getText();
			   String quantity = quantityTF.getText();
			   String discount = discountTF.getText();
			   
			   int idInt = Integer.parseInt(id);
			   int vendorInt = Integer.parseInt(vendor);
			   int quantityInt = Integer.parseInt(quantity);
			   double priceDouble = Double.parseDouble(price);
			   boolean discountBoolean = Boolean.parseBoolean(discount);
			   
			   listOp.update(idInt, name, type, priceDouble, location, vendorInt, quantityInt, discountBoolean);
			   
			   JTable newT = new JTable(tableModel);
			   newT = createTable(listOp.flowerShopImsList);
			   listT.setModel(newT.getModel());
			   
			   productListP.revalidate();
			   productListP.repaint();
		   }
	   }
}
