package backendOperations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import FrontendCore.MainMenu;
import backendCore.ListElement;

/*
 * 
 * Class that creates a list and performs operations on it.
 * Extends the ListElement class to access all functions from it directly.
 * 
 * */

public class ListOperations extends ListElement implements Comparator<ListElement>{
	
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
	
	public void sortby(String sortby) {
		
		if(sortby.equals("price")) {
			Collections.sort(flowerShopImsList,priceComparator);
		}
		if(sortby.equals("type")) {
			Collections.sort(flowerShopImsList,typeComparator);
		}
		if(sortby.equals("quantity")) {
			Collections.sort(flowerShopImsList,qtyComparator);
		}
		if(sortby.equals("discount")) {
			Collections.sort(flowerShopImsList,discountComparator);
		}
		if(sortby.equals("location")) {
			Collections.sort(flowerShopImsList,locationComparator);
		}
		if(sortby.equals("vendor")) {
			Collections.sort(flowerShopImsList,vendorComparator);
		}
	}
	


	 	public static Comparator<ListElement> priceComparator = new Comparator<ListElement>() {

		 @Override
		 public int compare(ListElement s1, ListElement s2) {
		    Double salePrice1 = s1.getSalePrice();
		    Double salePrice2 = s2.getSalePrice();

		    //ascending order
		    return salePrice1.compareTo(salePrice2);

		    }};


	
		 public static Comparator<ListElement> typeComparator = new Comparator<ListElement>() {

		@Override
		public int compare(ListElement s1, ListElement s2) {
		    String type1 = s1.getProductType();
		    String type2 = s2.getProductType();

		    //ascending order
		    return type1.compareTo(type2);

		    }};
		

		public static Comparator<ListElement> qtyComparator = new Comparator<ListElement>() {

		@Override
		public int compare(ListElement s1, ListElement s2) {
		    Integer qty1 = s1.getQuantityOnHand();
		    Integer qty2 = s2.getQuantityOnHand();

		    //ascending order
		    return qty1.compareTo(qty2);

		    }};    
				    
		public static Comparator<ListElement> discountComparator = new Comparator<ListElement>() {

		@Override
		public int compare(ListElement s1, ListElement s2) {
			String discount1=Boolean.toString(s1.getDiscount());
		    String discount2 = Boolean.toString(s2.getDiscount());

		    //ascending order
		    return discount1.compareTo(discount2);

		    }}; 
		    
		 public static Comparator<ListElement> vendorComparator = new Comparator<ListElement>() {

		@Override
		public int compare(ListElement s1, ListElement s2) {
		    Integer vend1 = s1.getVendorID();
		    Integer vend2 = s2.getVendorID();

		    //ascending order
		    return vend1.compareTo(vend2);

		    }}; 
		    
		public static Comparator<ListElement> locationComparator = new Comparator<ListElement>() {

		@Override
		public int compare(ListElement s1, ListElement s2) {
		    String loc1 = s1.getLocation();
		    String loc2 = s2.getLocation();

		    //ascending order
		    return loc1.compareTo(loc2);

		    }};    

			@Override
			public int compare(ListElement o1, ListElement o2) {
				// TODO Auto-generated method stub
				return 0;
			}
	 
	   
	
}
