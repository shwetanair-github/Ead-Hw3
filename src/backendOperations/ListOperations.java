package backendOperations;

import java.util.ArrayList;

import FrontendCore.MainMenu;
import backendCore.ListElement;

/*
 * 
 * Class that creates a list and performs operations on it.
 * Extends the ListElement class to access all functions from it directly.
 * 
 * */

public class ListOperations extends ListElement{
	
	ArrayList<ListElement> flowerShopImsList = new ArrayList<ListElement>();
	int arraySize=0;
	MainMenu mainMenuObj =new MainMenu();

	 //Pushes into the list from the rear.
	public void push(int ProductID,String ProductName,double SalePrice,double UnitCost,int VendorID,int QuantityOnHand) {
		  ListElement element= new ListElement(); 
		    element.setProductID(ProductID);
		    element.setProductName(ProductName);
		    element.setSalePrice(SalePrice);
		    element.setUnitCost(UnitCost);
		    element.setVendorID(VendorID);
		    element.setQuantityOnHand(QuantityOnHand);
		    
		    //Add element at 0 index if the list is empty.
		    if(flowerShopImsList.isEmpty())
		    flowerShopImsList.add(0, element);
		    
		    //Else adds to the rear of list.
		    else {
		    	arraySize++;
		    	flowerShopImsList.add(arraySize, element);
		    }  
	}
	
	//Displays the list elements
	public void displayFullList() {
		 System.out.println("The IMS list size: "+flowerShopImsList.size());
		for (Object obj : flowerShopImsList) {   
            ListElement node = (ListElement) obj;
            System.out.println(" Id :- " + node.getProductID());
            System.out.println(" Product Name :- " + node.getProductName());
            System.out.println(" Sale Price :- " + node.getSalePrice());
            System.out.println(" Vendor ID :- " + node.getVendorID());
            System.out.println(" Quantity on Hand :- " + node.getQuantityOnHand());
        
			}
	}
	
	
	
	//Displays the list elements
	public void displayListInFrame() {
	mainMenuObj.displayListInFrame(flowerShopImsList);
	}
	

}
