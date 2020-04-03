package FrontendCore;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import backendCore.DatabaseConnection;
import backendOperations.ListOperations;

public class EditForm extends JFrame implements ActionListener{
	JFrame editF;
	JPanel editP, editFormP;
	JLabel nameL, typeL, priceL, quantityL, locationL, vendorL, discountL;
	JTextField nameTF, typeTF, priceTF, quantityTF, locationTF, vendorTF, discountTF;
	JButton submitB;
	String ID;
	
	public EditForm() {
		editF = new JFrame();
		editP = new JPanel();
		editFormP = new JPanel();
		
		editP.setLayout(new BoxLayout(editP, BoxLayout.Y_AXIS));
		editFormP.setLayout(new GridLayout(7,2));   //sets a grid layout to the add form
		
		editP.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10),
				(BorderFactory.createCompoundBorder(BorderFactory.createLoweredBevelBorder(), BorderFactory.createEmptyBorder(10, 10, 10, 10)))));
		editFormP.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));   //Creates an empty border for better spacing
	
		
		//Initialize edit panel labels
		nameL = new JLabel("Name");
		typeL = new JLabel("Type");
		priceL = new JLabel("Price");
		quantityL = new JLabel("Quantity on Hand");
		locationL = new JLabel("Location");
		vendorL = new JLabel("Vendor");
		discountL = new JLabel("Discount (True/False)");
				
		//Initialize edit panel text fields
		nameTF = new JTextField(15);
		typeTF = new JTextField(15);
		priceTF = new JTextField(15);
		quantityTF = new JTextField(15);
		locationTF = new JTextField(15);
		vendorTF = new JTextField(15);
		discountTF = new JTextField(15);
		
		//Initialize submit button
		submitB = new JButton("");
		submitB.setAlignmentX(JButton.CENTER_ALIGNMENT);
		
		submitB.addActionListener(this);
		
		//Add components to the edit panel
		editFormP.add(nameL);
		editFormP.add(nameTF);
		editFormP.add(typeL);
		editFormP.add(typeTF);
		editFormP.add(priceL);
		editFormP.add(priceTF);
		editFormP.add(locationL);
		editFormP.add(locationTF);
		editFormP.add(vendorL);
		editFormP.add(vendorTF);
		editFormP.add(quantityL);
		editFormP.add(quantityTF);
		editFormP.add(discountL);
		editFormP.add(discountTF);
		
		editP.add(editFormP);
		editP.add(submitB);
		editF.add(editP);
		
		editF.setSize(new Dimension(400,300));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MainMenu main = new MainMenu();
		ListOperations listOp = new ListOperations();
		DatabaseConnection dbObject= new DatabaseConnection();
		
		if(e.getSource() == submitB) {
			if(submitB.getText() == "Add Product") {
				 //Get the edit form's inputs 
				  String name = nameTF.getText(); 
				  String type = typeTF.getText(); 
				  String price = priceTF.getText(); 
				  String location = locationTF.getText(); 
				  String vendor = vendorTF.getText(); 
				  String quantity = quantityTF.getText(); 
				  String discount = discountTF.getText();
				  
				  try {
					  //Convert properties that need to be a different data type
					  int quantityInt = Integer.parseInt(quantity); 
					  double priceDouble = Double.parseDouble(price); 
					  boolean discountBoolean = Boolean.parseBoolean(discount);
					  
					  if(quantityInt < 0 | priceDouble < 0) {
						  JOptionPane.showMessageDialog(main.master, "Please enter values greater than 0");
					  }
					  else {
						  //Call the push function to add product to the table
						  dbObject.insertIntoTable(name, type, priceDouble, location, vendor, quantityInt, discountBoolean);
						  JOptionPane.showMessageDialog(main.master, "Product: "+name+" added!"); 
					  }
				  }
				  //Shows an error when user enters incorrect data type
				  catch(Exception e1) {
					  JOptionPane.showMessageDialog(main.master,"No product added. Please use correct data format for input."); 
				  }
				 
				  //Copy the table and initialize it with the new inventory list 
				  DefaultTableModel listTModel = (DefaultTableModel)main.listT.getModel();
				  listTModel.fireTableDataChanged();
				  
				  //Refreshes the table 
				  main.productListP.revalidate(); 
				  main.productListP.repaint();
			}
			else if(submitB.getText() == "Update Product"){
				//Get the edit form's inputs 
				  String name = nameTF.getText(); 
				  String type = typeTF.getText(); 
				  String price = priceTF.getText(); 
				  String location = locationTF.getText(); 
				  String vendor = vendorTF.getText(); 
				  String quantity = quantityTF.getText(); 
				  String discount = discountTF.getText();
				  
				  try {
					  //Convert properties that need to be a different data type
					  int IDint = Integer.parseInt(ID);
					  int quantityInt = Integer.parseInt(quantity); 
					  double priceDouble = Double.parseDouble(price); 
					  boolean discountBoolean = Boolean.parseBoolean(discount);
					  
					  if(quantityInt < 0 | priceDouble < 0) {
						  JOptionPane.showMessageDialog(main.master, "Please enter values greater than 0");
					  }
					  else {
						  //Calls the update function to update the product's properties
						  dbObject.updateTable(IDint, name, type, priceDouble, location, vendor, quantityInt, discountBoolean);
					  }
				  }
				  catch(NumberFormatException e1) {
					  JOptionPane.showMessageDialog(main.master,"No product updated. Please use correct data format for input."); 
				  }
				  
				  //Display updated message. 
				  JOptionPane.showMessageDialog(main.master,"Product ID: "+ID+" updated!");
				  
				  //Clears the text fields
				  nameTF.setText("");
				  typeTF.setText("");
				  priceTF.setText(""); 
				  locationTF.setText("");
				  vendorTF.setText(""); 
				  quantityTF.setText(""); 
				  discountTF.setText("");
				  
				  //Copy the table and initialize it with the new inventory list 
				  JTable newT = new JTable(main.tableModel); 
				  newT = main.createTable(listOp.flowerShopImsList);
				  main.listT.setModel(newT.getModel()); //replace table with updated table
			}
			
			
			
		}
		
	}

}
