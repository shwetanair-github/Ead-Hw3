package backendOperations;

import java.util.ArrayList;
import java.util.Iterator;
import FrontendCore.MainMenu;
import backendCore.ListElement;

/*
 * 
 * Class that creates a list and performs operations on it.
 * Extends the ListElement class to access all functions from it directly.
 * 
 * */

public class ListOperations extends ListElement{
	
	public static ArrayList<ListElement> flowerShopImsList = new ArrayList<ListElement>();
	int arraySize;
	ListElement element1= new ListElement(); 
	
	//Pushes into the list from the rear.
	public void push(int ProductID,String ProductName,String ProductType, double SalePrice,String Location,int VendorID,int QuantityOnHand,boolean Discount) {
		  ListElement element= new ListElement(); 
		    element.setProductID(ProductID);
		    element.setProductName(ProductName);
		    element.setProductType(ProductType);
		    element.setSalePrice(SalePrice);
		    element.setLocation(Location);
		    element.setVendorID(VendorID);
		    element.setQuantityOnHand(QuantityOnHand);
		    element.setDiscount(Discount);
		    
		    //Add element at 0 index if the list is empty.
		    if(flowerShopImsList.isEmpty())
		    flowerShopImsList.add(0, element);
		    
		    //Else adds to the rear of list.
		    else {
		    	arraySize = flowerShopImsList.size();
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
            System.out.println(" Product Type:- " + node.getProductType());
            System.out.println(" Sale Price :- " + node.getSalePrice());
            System.out.println(" Location :- " + node.getLocation());
            System.out.println(" Vendor ID :- " + node.getVendorID());
            System.out.println(" Quantity on Hand :- " + node.getQuantityOnHand());
            System.out.println(" Discount :- " + node.getDiscount());
			}
	}
	
	//Function to remove an element
	public void remove(int i) {
		Iterator itr = flowerShopImsList.iterator(); 
        while (itr.hasNext()) 
        { 
            element1 = (ListElement)itr.next(); 
            if (i==element1.getProductID()) 
                itr.remove(); 
        } 
	}
	
	//Function to update a product
	public void update(int ProductID,String ProductName,String ProductType,double SalePrice, String Location,int VendorID,int QuantityOnHand,boolean Discount) {
		Iterator itr = flowerShopImsList.iterator(); 
        while (itr.hasNext()) 
        { 
            element1 = (ListElement)itr.next(); 
            if (ProductID==element1.getProductID()) 
                itr.remove();
            
        } 
        ListElement element= new ListElement(); 
	    element.setProductID(ProductID);
	    element.setProductName(ProductName);
	    element.setProductType(ProductType);
	    element.setSalePrice(SalePrice);
	    element.setLocation(Location);
	    element.setVendorID(VendorID);
	    element.setQuantityOnHand(QuantityOnHand);
	    element.setDiscount(Discount);
	    flowerShopImsList.add((ProductID - 1), element);
		}
}
