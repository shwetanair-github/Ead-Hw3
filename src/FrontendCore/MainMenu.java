package FrontendCore;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import backendCore.ListElement;
import backendOperations.ListOperations;

public class MainMenu extends Frame implements ActionListener {
	
	 private Label lblText;    // Declare a Label component 
	   
	   private Button btnCount;   // Declare a Button component
	   private String productInfo;
	   private ArrayList<String> listToDisplay = new ArrayList<String>();    // Text
	   JFrame master = new JFrame("New frame");
	  
	 
	   // Constructor to setup GUI components and event handlers
	   public MainMenu () {
		   master.setLayout(new FlowLayout());
	         // "super" Frame, which is a Container, sets its layout to FlowLayout to arrange
	         // the components from left-to-right, and flow to next row from top-to-bottom.
	 
	      btnCount = new Button("Display");   // construct the Button component
	      master.add(btnCount);                    // "super" Frame container adds Button component
	      
	      lblText = new Label("Text");  // construct the Label component
	      lblText.setPreferredSize(new Dimension(300,100));
	      master.add(lblText);                    // "super" Frame container adds Label component
	 
	      btnCount.addActionListener(this);
	   
	 
	      master.setTitle("Flower shop IMS Main Menu");  // "super" Frame sets its title
	      master.setSize(1500, 750);        // "super" Frame sets its initial window size
	 
	    
	      master.setVisible(true);         // "super" Frame shows
	      master.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	 
	   }
	   
	   public void displayListInFrame(ArrayList<ListElement> flowerShopImsList) {
		   System.out.println("The IMS list size: "+flowerShopImsList.size());
		   for (Object obj : flowerShopImsList) { 
	            ListElement node = (ListElement) obj;
	            productInfo = node.getProductID() + node.getProductName()+ node.getSalePrice()
	            + node.getVendorID()+ node.getQuantityOnHand();
	            System.out.println(" list :- " + productInfo);
	            listToDisplay.add(productInfo);
		   	}
	   }
	 
	   // ActionEvent handler - Called back upon button-click.
	   public void actionPerformed(ActionEvent evt) {
		   displayListInFrame(ListOperations.flowerShopImsList);
		   
	      // Display the counter value on the TextField 
		   lblText.setText(""+listToDisplay);

	   }

	
}
