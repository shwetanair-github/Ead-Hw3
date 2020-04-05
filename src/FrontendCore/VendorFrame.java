package FrontendCore;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DateFormatter;

import org.jdatepicker.JDateComponent;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import backendCore.DatabaseConnection;
import backendOperations.ListOperations;

public class VendorFrame extends JFrame implements ActionListener{

	JFrame vendorFrame;
	JPanel vendorP, poListP,editPOButtonP, addPoP, vendorUpdateP, vendorSortP, vendorTablenSortP;
	JLabel prodIdL, nameL, typeL, priceL, quantityL, locationL, vendorL, dateL, invisL, sortL;
	JTextField productIdTF, nameTF, typeTF, priceTF, quantityTF, locationTF, vendorTF, dateTF;
	JButton  addPoB, updatePoB, removePoB, monthlyB, quarterlyB, yearlyB;
	DefaultTableModel tableModel;
	DatabaseConnection dbObject= new DatabaseConnection();
	Date date;
	JTable vendorTable,listT;
	JDatePickerImpl datePicker;
	JScrollPane tableP;

	public VendorFrame(){
		vendorFrame = new JFrame();
		vendorP = new JPanel();
		vendorUpdateP = new JPanel();
		vendorSortP = new JPanel();
		vendorTablenSortP = new JPanel();
		//Purchase order and product panel that displays the PO history
		poListP = new JPanel();

		//Set formats for panels
		poListP.setPreferredSize(new Dimension(800,325));   //Sets the product list panel's size
		poListP.setLayout(new BoxLayout(poListP, BoxLayout.Y_AXIS));   //sets the product list's layout to be in a vertical alignment
		poListP.setBorder(BorderFactory.createLoweredBevelBorder());   //creates a bevel border for the product list panel


		//Table Panel that displays the inventory
		listT = createPOTable();  //creates the table
		listT.setFillsViewportHeight(true);   //set table height

		tableP = new JScrollPane(listT);
		tableP.setPreferredSize(new Dimension(225,250));  //sets size of table panel

		// Add table to the panel
		poListP.add(tableP);

		// Set the layout for the table and sort 
		vendorTablenSortP.setPreferredSize(new Dimension(800,350));
		vendorTablenSortP.setLayout(new BoxLayout(vendorTablenSortP, BoxLayout.Y_AXIS)); 
		// Adding layout to the table panel
		vendorTablenSortP.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10),
				(BorderFactory.createCompoundBorder(BorderFactory.createLoweredBevelBorder(), BorderFactory.createEmptyBorder(10, 10, 10, 10)))));


		// Add the table to the table and sort panel
		vendorTablenSortP.add(poListP);

		// Sort PO panel
		sortL = new JLabel("View POs sorted By: ");

		//Add the button names
		monthlyB = new JButton("Monthly");
		quarterlyB = new JButton("Quarterly");
		yearlyB = new JButton("Yearly");

		//Add action listener
		monthlyB.addActionListener(this);
		quarterlyB.addActionListener(this);
		yearlyB.addActionListener(this);

		//Add components to the sort panel
		vendorSortP.add(sortL);
		vendorSortP.add(monthlyB);
		vendorSortP.add(quarterlyB);
		vendorSortP.add(yearlyB);

		//Add the product list panel to the main panel
		//vendorTablenSortP.add(vendorSortP);

		//Add table and sort panel
		vendorP.add(vendorTablenSortP);


		//PO create and edit Panel
		editPOButtonP = new JPanel();

		// Button panel
		editPOButtonP.setLayout(new GridLayout(7,1));
		editPOButtonP.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10),
				(BorderFactory.createCompoundBorder(BorderFactory.createLoweredBevelBorder(), BorderFactory.createEmptyBorder(50,20,20,20)))));
	
		//initialize PO edit panel buttons and label
		invisL = new JLabel(" Make Purchase Order changes ");
		addPoB = new JButton("Create Purchase Order");
		updatePoB = new JButton("Update Purchase Order");
		removePoB = new JButton("Remove Purchase Order");

		//Add action listener
		addPoB.addActionListener(this);
		updatePoB.addActionListener(this);
		removePoB.addActionListener(this);

		editPOButtonP.add(invisL);
		editPOButtonP.add(addPoB);
		editPOButtonP.add(updatePoB);
		editPOButtonP.add(removePoB);

		//Add button container to main container
		vendorP.add(editPOButtonP, BorderLayout.LINE_END);

		// Add the entire vendor panel to frame
		vendorFrame.add(vendorP);
		vendorFrame.setSize(new Dimension(1100,400));

		//Initialize the additional panel for creating PO
		addPoP =new JPanel();
		invisL.setAlignmentX(JButton.CENTER_ALIGNMENT); // Align the label to center
		addPoP.setLayout(new BoxLayout(addPoP, BoxLayout.Y_AXIS));
		addPoP.setLayout(new GridLayout(7,2));   //sets a grid layout to the add form

		addPoP.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10),
				(BorderFactory.createCompoundBorder(BorderFactory.createLoweredBevelBorder(), BorderFactory.createEmptyBorder(10, 10, 10, 10)))));

		//Initialize the input form items

		//Initialize panel labels
		prodIdL = new JLabel("Product ID");
		nameL = new JLabel("Name");
		typeL = new JLabel("Type");
		quantityL = new JLabel("Quantity to be requested");
		locationL = new JLabel("Location");
		vendorL = new JLabel("Vendor");
		dateL = new JLabel("Select Date of PO");

		//Initialize edit panel text fields
		productIdTF = new JTextField(15);
		nameTF = new JTextField(15);
		typeTF = new JTextField(15);
		priceTF = new JTextField(15);
		quantityTF = new JTextField(15);
		locationTF = new JTextField(15);
		vendorTF = new JTextField(15);

		// Date picker item
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");

		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePicker = new JDatePickerImpl(datePanel,  new DateLabelFormatter());

		//Add components to the edit panel
		addPoP.add(prodIdL);
		addPoP.add(productIdTF);
		addPoP.add(nameL);
		addPoP.add(nameTF);
		addPoP.add(typeL);
		addPoP.add(typeTF);
		addPoP.add(locationL);
		addPoP.add(locationTF);
		addPoP.add(vendorL);
		addPoP.add(vendorTF);
		addPoP.add(quantityL);
		addPoP.add(quantityTF);
		addPoP.add(dateL);
		addPoP.add(datePicker); 


		vendorUpdateP.add(addPoP);
		//Creates an empty border for better spacing
		vendorUpdateP.setPreferredSize(new Dimension(500,425));

	}

	//Function to create a table of the PO
	public JTable createPOTable() {
		String[] colNames = {"PO ID","Product ID","Product Name","Product Type","Location","Vendor","Quantity in PO","Date of PO"};  //column names
		tableModel = new DefaultTableModel(colNames,0);  //initialize the table model
		vendorTable = new JTable(tableModel);  //initialize the table

		//Goes through each element in the table and gets their properties
		try{  

			Statement statement=dbObject.connection.createStatement();  
			ResultSet resultset=statement.executeQuery("select * from PO_table");  
			while(resultset.next())  {
				//System.out.println(resultset.getInt(1)+"  "+resultset.getInt(2)+""+resultset.getString(2)+"  "+resultset.getString(3));
				int POid = resultset.getInt(1);
				int Productid = resultset.getInt(2);
				String name = resultset.getString(3);
				String type =resultset.getString(4);
				String location = resultset.getString(5);
				String vendor = resultset.getString(6);
				int quantity = resultset.getInt(7);
				Date datevalue = resultset.getDate(8);
				Object[] po = {POid, Productid, name, type, location, vendor, quantity, datevalue}; //Add product properties to an object array
				tableModel.addRow(po);   //Create table row containing product data

			}

		}
		catch(Exception e)
		{ System.out.println(e);}  

		return vendorTable;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// Add PO button
		if(e.getSource()==addPoB) {
			int confirm = JOptionPane.showConfirmDialog(vendorFrame, addPoP,"Add PO form", 2);
			if(confirm==JOptionPane.YES_OPTION) {
				//Get the edit form's inputs 
				String productID = productIdTF.getText();
				String name = nameTF.getText(); 
				String type = typeTF.getText(); 
				String location = locationTF.getText(); 
				String vendor = vendorTF.getText(); 
				String quantity = quantityTF.getText(); 
				Date selectedDate = (Date) datePicker.getModel().getValue();


				try {
					//Convert properties that need to be a different data type
					int quantityInt = Integer.parseInt(quantity); 
					int productIdInt = Integer.parseInt(productID); 


					if(quantityInt < 0 | productIdInt < 0) {
						JOptionPane.showMessageDialog(addPoP, "Please enter values greater than 0");
					}
					else {
						//Call the push function to add product to the table
						dbObject.insertIntoPoTable(productIdInt,name, type, location, vendor, quantityInt,selectedDate);
						JOptionPane.showMessageDialog(vendorFrame, "Purchase order for Product: "+name+" created!"); 

						//Table Panel that displays the inventory
						JTable newT = new JTable(tableModel); 
						newT = createPOTable();
						listT.setModel(newT.getModel()); //replace table with updated table

					}
				}
				//Shows an error when user enters incorrect data type
				catch(Exception e1) {
					JOptionPane.showMessageDialog(addPoP,"No purchase order created. Please use correct data format for input."); 
				}


			}


		}

		// Update PO button
		if(e.getSource()==updatePoB) {
			String POid = JOptionPane.showInputDialog(vendorFrame, "Enter the PO ID of the PO you would like to update", "Update Purchase order", JOptionPane.OK_CANCEL_OPTION);

			if(POid == null) {
				JOptionPane.showMessageDialog(vendorFrame, "Update operation cancelled");
			}
			else {
				int confirm = JOptionPane.showConfirmDialog(vendorFrame, addPoP,"Update PO form", 2);
				if(confirm==JOptionPane.YES_OPTION) {
					//Get the edit form's inputs 
					String productID = productIdTF.getText();
					String name = nameTF.getText(); 
					String type = typeTF.getText(); 
					String location = locationTF.getText(); 
					String vendor = vendorTF.getText(); 
					String quantity = quantityTF.getText(); 
					Date selectedDate = (Date) datePicker.getModel().getValue();


					try {
						//Convert properties that need to be a different data type
						int quantityInt = Integer.parseInt(quantity); 
						int productIdInt = Integer.parseInt(productID); 


						if(quantityInt < 0 | productIdInt < 0) {
							JOptionPane.showMessageDialog(addPoP, "Please enter values greater than 0");
						}
						else {
							//Call the push function to add product to the table
							dbObject.updatePoTable(Integer.parseInt(POid),productIdInt,name, type, location, vendor, quantityInt,selectedDate);
							JOptionPane.showMessageDialog(vendorFrame, "Purchase order for Product: "+name+" updated!"); 

						}
					}
					//Shows an error when user enters incorrect data type
					catch(Exception e1) {
						JOptionPane.showMessageDialog(addPoP,"No purchase order updated. Please use correct data format for input."); 
					}

					//Table Panel that displays the inventory
					JTable newT = new JTable(tableModel); 
					newT = createPOTable();
					listT.setModel(newT.getModel()); //replace table with updated table

				}
			}

		}

		//Remove the PO created
		if(e.getSource()==removePoB) {
			String POid = JOptionPane.showInputDialog(vendorFrame, "Enter the PO ID of the PO you would like to remove", "Remove Purchase order", JOptionPane.OK_CANCEL_OPTION);

			if(POid == null) {
				JOptionPane.showMessageDialog(vendorFrame, "Deletion operation cancelled");
			}
			else {

				try {
					//Call the push function to add product to the table
					int flag= dbObject.deletePoTable(Integer.parseInt(POid));
					if (flag==1) {
						JOptionPane.showMessageDialog(vendorFrame, "Purchase order: "+POid+" deleted!"); 
					}
					else {
						JOptionPane.showMessageDialog(addPoP, "No purchase order deleted. Please use correct data format for input."); 
					}

				}
				//Shows an error when user enters incorrect data type
				catch(Exception e1) {
					JOptionPane.showMessageDialog(addPoP,"No purchase order deleted. Please use correct data format for input."); 
				}

				//Table Panel that displays the inventory
				JTable newT = new JTable(tableModel); 
				newT = createPOTable();
				listT.setModel(newT.getModel()); //replace table with updated table

			}

		}

	}

}
