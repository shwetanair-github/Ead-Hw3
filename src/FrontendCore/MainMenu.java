package FrontendCore;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import backendCore.DatabaseConnection;
import backendCore.ListElement;
import backendOperations.ListOperations;

public class MainMenu extends JFrame implements ActionListener {
	//Declare frame
	JFrame master;
	
	//Declare panels
	JPanel mainP, titleP, editP, editFormP, editButtonP, productListP, searchP, searchTextFieldP, sortP;
	
	private JScrollPane tableP;
	
	//Declare labels
	private JLabel titleL, sortL, searchHint;
	
	//Declare text field
	private JTextField searchTF;
	
	//Declare buttons
	private JButton allB, searchB, vendorB, typeB, locationB, priceB, quantityB, discountB, addB, removeB, updateB;
	
	//Declare strings
	String productInfo;
	private ArrayList<String> listToDisplay = new ArrayList<String>();  
	
	//Declare table
	DefaultTableModel tableModel;
	JTable listT;
	
	//Database object
	DatabaseConnection dbObject= new DatabaseConnection();
	
	// Constructor to setup GUI components and event handlers
	public MainMenu () {
		master = new JFrame();   // "super" Frame, which is a Container
		
		mainP = new JPanel();   //Panel that holds all other Panels
		master.add(mainP);      //Adding the main Panel into the super Frame
		
		//Panel for title
		titleP = new JPanel();
		titleP.setBackground(Color.pink);    //Sets title panel's background color
		titleL = new JLabel("Flower Shop IMS");   //Set title text
		titleL.setAlignmentX(JLabel.CENTER_ALIGNMENT);  //Aligns the title to the center
		titleL.setFont(titleL.getFont().deriveFont(50.0f));  //Change font size
		
		titleP.add(titleL);   //Adds title text to title panel
		master.add(titleP, BorderLayout.PAGE_START);  //Adds title panel to "super" Frame
		
		//ProductList panel that displays the product inventory
		productListP = new JPanel();
		searchP = new JPanel();  //holds the view all button, search text field, and search button
		sortP = new JPanel();  //holds all sorting buttons
		
		//Set formats for panels
		productListP.setPreferredSize(new Dimension(700,325));   //Sets the product list panel's size
		productListP.setLayout(new BoxLayout(productListP, BoxLayout.Y_AXIS));   //sets the product list's layout to be in a vertical alignment
		productListP.setBorder(BorderFactory.createLoweredBevelBorder());   //creates a bevel border for the product list panel
		sortP.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));   //creates spacing between the sort button panel and the table panel
		
		//Search Panel
		searchTextFieldP = new JPanel();
		searchTextFieldP.setLayout(new BoxLayout(searchTextFieldP, BoxLayout.Y_AXIS));   //sets the search text field panel to a vertical layout
		
		allB = new JButton("Refresh List");   //Initialize "Refresh List" button
		allB.addActionListener(this);     //Add actionlistener to "View All" button
		
		searchTF = new JTextField();  //Initialize search textfield
		searchHint = new JLabel("Enter product ID, name, type, location, vendor, or discount to search ");   //create a hint for the search text field
		searchB = new JButton("Search");  //Initialize search button
		searchB.addActionListener(this);
		
		//Add components to the appropriate search panels
		searchTextFieldP.add(searchHint);
		searchTextFieldP.add(searchTF);
		
		searchP.add(allB);
		searchP.add(searchTextFieldP);
		searchP.add(searchB);
		
		//Table Panel that displays the inventory
		listT = createTable(ListOperations.flowerShopImsList);  //creates the table
		listT.setFillsViewportHeight(true);   //set table height
		
		tableP = new JScrollPane(listT);
		tableP.setPreferredSize(new Dimension(110,200));  //sets size of table panel
		
		//Sort Panel
		//Initialize components
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
		
		//Add components to the sort panel
		sortP.add(sortL);
		sortP.add(typeB);
		sortP.add(priceB);
		sortP.add(quantityB);
		sortP.add(discountB);
		sortP.add(locationB);
		sortP.add(vendorB);
		
		//Add the sort panel to the product list panel
		productListP.add(searchP);
		productListP.add(tableP);
		productListP.add(sortP);
		
		//Add the product list panel to the main panel
		mainP.add(productListP, BorderLayout.LINE_START);
		
		//Edit panel that contains a form to add/update a product and a button to remove a product
		editButtonP = new JPanel();
		
		editButtonP.setLayout(new GridLayout(5,1));
		editButtonP.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10),
			(BorderFactory.createCompoundBorder(BorderFactory.createLoweredBevelBorder(), BorderFactory.createEmptyBorder(20,20,20,20)))));
		
		//initialize edit panel buttons
		addB = new JButton("Add Product");
		updateB = new JButton("Update Product");
		removeB = new JButton("Remove Product");
		
		addB.addActionListener(this);
		updateB.addActionListener(this);
		removeB.addActionListener(this);
		
		//Add buttons to main panel
		JLabel invisL = new JLabel(" "); //invisible components to create spacing between buttons
		JLabel invisL2 = new JLabel(" ");
		
		editButtonP.add(addB);
		editButtonP.add(invisL);
		editButtonP.add(updateB);
		editButtonP.add(invisL2);
		editButtonP.add(removeB);
		
		//Center align buttons
		addB.setAlignmentX(JButton.CENTER_ALIGNMENT);
		updateB.setAlignmentX(JButton.CENTER_ALIGNMENT);
		removeB.setAlignmentX(JButton.CENTER_ALIGNMENT);

		//Add button container to main container
		mainP.add(editButtonP, BorderLayout.LINE_END);

		//Set super Frame properties
	    master.setTitle("Flower shop IMS Main Menu");  // "super" Frame sets its title
	    master.setSize(1000, 450);        // "super" Frame sets its initial window size
	    //master.setResizable(false);
	    
	    master.setVisible(true);         // "super" Frame shows
	    master.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
	    
	    }
		
		//Function to create a table holding all products & their properties
		public JTable createTable(ArrayList<ListElement> arrayList) {
			String[] colNames = {"ProductID","ProductName","ProductType","Price","Location","Vendor","QuantityOnHand","Discount"};  //column names
			tableModel = new DefaultTableModel(colNames,0);  //initialize the table model
			JTable table = new JTable(tableModel);  //initialize the table
			
			//Goes through each element in the arrayList and gets their properties
			for (ListElement obj: arrayList) {
				int id = obj.getProductID();
				String name = obj.getProductName();
				String type = obj.getProductType();
				double price = obj.getSalePrice();
				String location = obj.getLocation();
				String vendor = obj.getVendorID();
				int quantity = obj.getQuantityOnHand();
				boolean discount = obj.getDiscount();
				
				Object[] product = {id, name, type, price, location, vendor, quantity, discount}; //Add product properties to an object array
				tableModel.addRow(product);   //Create table row containing product data
			}
			return table;
		}
		
		public void displayListInFrame(ArrayList<ListElement> flowerShopImsList) {
			for (Object obj : flowerShopImsList) {
	            ListElement node = (ListElement) obj;
	            productInfo = node.getProductID() + node.getProductName()+ node.getSalePrice()
	            + node.getVendorID()+ node.getQuantityOnHand();
	            System.out.println(" list in frame:- " + productInfo);
	            listToDisplay.add(productInfo);
		   	}
		}
		
	   // ActionEvent handler - Called back upon button-click.
	   public void actionPerformed(ActionEvent e) {
		   ListOperations listOp = new ListOperations();
		   ArrayList<ListElement> tempList = new ArrayList<ListElement>();
		   EditForm EF = new EditForm();
		   
		   //Refresh List Button - Refreshes the product table 
		   if(e.getSource() == allB) {
			   tempList=listOp.sortby("id");
			   JTable newT = new JTable(tableModel);
			   newT = createTable(ListOperations.flowerShopImsList);
			   listT.setModel(newT.getModel());  
			   productListP.revalidate();
			   productListP.repaint();
		   }
		   
		   //Search button searches for the items based on the input in the text field
		   else if(e.getSource() == searchB) {
		
			   tempList=listOp.searchby(searchTF.getText());
			   JTable newT = new JTable(tableModel);
			   newT = createTable(tempList);
			   listT.setModel(newT.getModel());  //replace table with updated table
			   
			   //Refreshes the table
			   productListP.revalidate();
			   productListP.repaint();
		   }
		   
		   //Product Type Button - calls the sort function to sort by type
		   else if(e.getSource() == typeB) {
			   tempList=listOp.sortby("type");
			   JTable newT = new JTable(tableModel);
			   newT = createTable(tempList);
			   listT.setModel(newT.getModel());  //replace table with updated table
			   
			   //Refreshes the table
			   productListP.revalidate();
			   productListP.repaint();
		   }
		   
		   //Price Button - calls the sort function to sort by price
		   else if(e.getSource() == priceB) {
			   tempList=listOp.sortby("price");
			   JTable newT = new JTable(tableModel);
			   newT = createTable(tempList);
			   listT.setModel(newT.getModel());  //replace table with updated table
			   
			   //Refreshes the table
			   productListP.revalidate();
			   productListP.repaint();
		   }
		   
		   //Quantity Button - calls the sort function to sort by quantity
		   else if(e.getSource() == quantityB) {
			   tempList=listOp.sortby("quantity");
			   tableModel.fireTableDataChanged();
			   JTable newT = new JTable(tableModel);
			   newT = createTable(tempList);
			   listT.setModel(newT.getModel());  //replace table with updated table
			   
			   //Refreshes the table
			   productListP.revalidate();
			   productListP.repaint();
		   }
		   
		   //Discount Button - calls the search function to show discounted products
		   else if(e.getSource() == discountB) {
			   tempList=listOp.searchby("true");
			   JTable newT = new JTable(tableModel);
			   newT = createTable(tempList);
			   listT.setModel(newT.getModel());  //replace table with updated table
			   
			   //Refreshes the table
			   productListP.revalidate();
			   productListP.repaint();
			   
		   }
		   
		   //Location Button - calls the sort function to sort by location
		   else if(e.getSource() == locationB) {
			   tempList=listOp.sortby("location");
			   JTable newT = new JTable(tableModel);
			   newT = createTable(tempList);
			   listT.setModel(newT.getModel());  //replace table with updated table
			   
			   //Refreshes the table
			   productListP.revalidate();
			   productListP.repaint();
		   }
		   
		   //Vendor Button - calls the sort function to sort by vendor
		   else if(e.getSource() == vendorB) {
			   tempList=listOp.sortby("vendor");
			   JTable newT = new JTable(tableModel);
			   newT = createTable(tempList);
			   listT.setModel(newT.getModel());  //replace table with updated table
			   
			   //Refreshes the table
			   productListP.revalidate();
			   productListP.repaint();
		   }
		   
		   //Add Product Button - adds a product listing to the table
		   else if(e.getSource() == addB) {
			  EF.editF.setTitle("Add Product Form");
			  EF.submitB.setText("Add Product");
			  EF.editF.setVisible(true);
		   }
		   
		   //Update Product Button - updates a product listing in the table
		   else if(e.getSource() == updateB) {
			   EF.ID = JOptionPane.showInputDialog(master, "Enter the Product ID of the product you would like to update", "Update Product ID", JOptionPane.OK_CANCEL_OPTION);   //Creates a dialog box to ask for the product ID
			   if(EF.ID == null) {
				     JOptionPane.showMessageDialog(master, "Update operation cancelled");
			   }
			   else {
				   EF.editF.setTitle("Update Product Form");
				   EF.submitB.setText("Update Product");
				   EF.editF.setVisible(true);
			   }
		   }
		   
		   //Remove Product Button - removes a product listing from the table
		   if(e.getSource() == removeB) {
			   int productID =0;
			   String removeText = JOptionPane.showInputDialog(master, "Enter Product ID");   //Creates a dialog box to ask for the product ID
			   if(removeText == null) {
				   JOptionPane.showMessageDialog(master, "Remove operation cancelled");
			   }
			   else {
				   try {
					   productID = Integer.parseInt(removeText);   //Convert input to an integer
			   
					   //Call the remove function to remove the product listing
					   int flag=dbObject.deleteTable(productID);
					   if(flag!=0) {
						   JOptionPane.showMessageDialog(master, "Product ID: "+productID+" removed!"); 
					   }
					   else {
						   JOptionPane.showMessageDialog(master, "Product ID not present!"); 
					   }
			     
					   //Copy the table and initialize it with the new inventory list
					   JTable newT = new JTable(tableModel);
					   newT = createTable(listOp.flowerShopImsList);
					   listT.setModel(newT.getModel());   //replace table with updated table
  
					   //Refreshes the table
					   productListP.revalidate();
					   productListP.repaint();
				   }
				   catch(NumberFormatException e1) {
					   JOptionPane.showMessageDialog(master, "No product removed.Please use correct data format for input.");  
				   }
			   }
		   }
	   }
}
