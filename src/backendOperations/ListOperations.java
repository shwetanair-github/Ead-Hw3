package backendOperations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import FrontendCore.MainMenu;
import backendCore.ListElement;

/*
 * 
 * Class that creates a list and performs operations on it.
 * Extends the ListElement class to access all functions from it directly.
 * 
 * */

public class ListOperations extends ListElement implements Comparator<ListElement>{
	
	public static ArrayList<ListElement> flowerShopImsList=new ArrayList<ListElement>();
	ListElement element1= new ListElement(); 
	int arraySize;
	
	
	
	//Pushes into the list from the rear.
	public void push(int ProductID,String ProductName,String ProductType, double SalePrice,String Location,String VendorID,int QuantityOnHand,boolean Discount) {
		ListElement element= new ListElement(); 
		    element.setProductID(ProductID);
		    element.setProductName(ProductName);
		    element.setProductType(ProductType);
		    element.setSalePrice(SalePrice);
		    element.setLocation(Location);
		    element.setVendorID(VendorID);
		    element.setQuantityOnHand(QuantityOnHand);
		    element.setDiscount(Discount);
		    
		    flowerShopImsList.add(element);
		   
		 
	}
	
	//Displays the list elements
	public static void displayFullList() {
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
	public void remove(int productID) {
		
		Iterator<ListElement> itr = flowerShopImsList.iterator(); 
        while (itr.hasNext()) 
        { 
            element1 = (ListElement)itr.next(); 
            if (productID==element1.getProductID()) {
                itr.remove(); 
            	}
        } 
       
	}
	
	//Function to update a product
	public void update(int ProductID,String ProductName,String ProductType,double SalePrice, String Location,String VendorID,int QuantityOnHand,boolean Discount) {
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
	
	// Function that decides what sorting function to apply
	public  ArrayList<ListElement> sortby(String sortby) {
		
		 ArrayList<ListElement> tempList = new ArrayList<ListElement>();
		   tempList=flowerShopImsList;
		   
		if(sortby.equals("id")) {
			Collections.sort(tempList,idComparator);
		}
		if(sortby.equals("price")) {
			Collections.sort(tempList,priceComparator);
		}
		if(sortby.equals("type")) {
			Collections.sort(tempList,typeComparator);
		}
		if(sortby.equals("quantity")) {
			Collections.sort(tempList,qtyComparator);
		}
		if(sortby.equals("discount")) {
			Collections.sort(tempList,discountComparator);
		}
		if(sortby.equals("location")) {
			Collections.sort(tempList,locationComparator);
		}
		if(sortby.equals("vendor")) {
			Collections.sort(tempList,vendorComparator);
		}
		return tempList;
	}
	
		// ID comparator
	 	public static Comparator<ListElement> idComparator = new Comparator<ListElement>() {
	
		 @Override
		 public int compare(ListElement s1, ListElement s2) {
		    Integer id1 = s1.getProductID();
		    Integer id2 = s2.getProductID();
	
		    //ascending order
		    return id1.compareTo(id2);
	
		    }};
	
		// Price comparator
	 	public static Comparator<ListElement> priceComparator = new Comparator<ListElement>() {

		 @Override
		 public int compare(ListElement s1, ListElement s2) {
		    Double salePrice1 = s1.getSalePrice();
		    Double salePrice2 = s2.getSalePrice();

		    //ascending order
		    return salePrice1.compareTo(salePrice2);

		    }};


		 // Type comparator
		 public static Comparator<ListElement> typeComparator = new Comparator<ListElement>() {

		@Override
		public int compare(ListElement s1, ListElement s2) {
		    String type1 = s1.getProductType();
		    String type2 = s2.getProductType();

		    //ascending order
		    return type1.compareTo(type2);

		    }};
		
		// Quantity comparator
		public static Comparator<ListElement> qtyComparator = new Comparator<ListElement>() {

		@Override
		public int compare(ListElement s1, ListElement s2) {
		    Integer qty1 = s1.getQuantityOnHand();
		    Integer qty2 = s2.getQuantityOnHand();

		    //ascending order
		    return qty1.compareTo(qty2);

		    }};    
			
		// Discount comparator    
		public static Comparator<ListElement> discountComparator = new Comparator<ListElement>() {

		@Override
		public int compare(ListElement s1, ListElement s2) {
			String discount1=Boolean.toString(s1.getDiscount());
		    String discount2 = Boolean.toString(s2.getDiscount());

		    //ascending order
		    return discount1.compareTo(discount2);

		    }}; 
		    
		 // Vendor comparator
		 public static Comparator<ListElement> vendorComparator = new Comparator<ListElement>() {

		@Override
		public int compare(ListElement s1, ListElement s2) {
			String vend1 = s1.getVendorID();
			String vend2 = s2.getVendorID();

		    //ascending order
		    return vend1.compareTo(vend2);

		    }}; 
		    
		// Location comparator    
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
	 
		// function to search by either product name or product ID
	   public ArrayList<ListElement> searchby(String searchText) {
		   ArrayList<ListElement> tempList = new ArrayList<ListElement>();
		   tempList=flowerShopImsList;
		   String regex = "[+-]?[0-9][0-9]*"; 
	          
	        // compiling regex 
	        Pattern p = Pattern.compile(regex); 
	          
	        // Creates a matcher that will match input against regex 
	        Matcher m = p.matcher(searchText); 
	          
	        // If match found and equal to input1 
	        if(m.find() && m.group().equals(searchText)) {
	            System.out.println(searchText + " is a valid integer number"); 
	            tempList=searchByIntegerAttributes(Integer.parseInt(searchText));
			   
		   }
	        
	        else {
	        	tempList=searchByProductTextAttributes(searchText);
	        }
		   
		   return tempList;
	   }
	   
	   // Function that searches by product name
	   public ArrayList<ListElement>  searchByProductTextAttributes(String searchtext) {
		   ArrayList<ListElement> tempList = new ArrayList<ListElement>();
		   for (ListElement p1:flowerShopImsList) {
			   if (p1.getProductName().equalsIgnoreCase(searchtext)||
					   p1.getVendorID().equalsIgnoreCase(searchtext)||
					   p1.getProductType().equalsIgnoreCase(searchtext)||
					   p1.getLocation().equalsIgnoreCase(searchtext)||
					   Boolean.toString(p1.getDiscount()).equalsIgnoreCase(searchtext)
					   ) {
				   if(tempList.isEmpty())
					   tempList.add(0, p1);
					    //Else adds to the rear of list.
					    else {
					    	arraySize = tempList.size();
					    	tempList.add(arraySize, p1);
					    }  
			   }
			 
		   }
		   return tempList;
	   }
	   
	   // Function that searches by product ID
	   public ArrayList<ListElement>  searchByIntegerAttributes(int productId) {
		   ArrayList<ListElement> tempList = new ArrayList<ListElement>();
		   for (ListElement p1:flowerShopImsList) {
			   if (p1.getProductID()==productId) {
				   tempList.add(0, p1);
			   }
		   }
		   return tempList;
	   }
	
}
