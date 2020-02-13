package backendCore;

/*
 * 
 * Class that creates a list element.
 * The functions to set values to the element and get i.e. retrieve values from the element.
 *
 * */

public class ListElement {
	
	
	public int ProductID,VendorID,QuantityOnHand;
	public String ProductName;
	public double SalePrice, UnitCost;		
	
	//Initiating a list element using a constructor
	public ListElement() {
		ProductID = 0;
		ProductName = null;
		SalePrice=0;
		UnitCost=0;
		VendorID=0;
		QuantityOnHand=0;
		
	}
	
	// Sets the values for each field of the list element.
	public void setProductID(int id) {
		this.ProductID = id;
	}
	public void setProductName(String productName) {
		this.ProductName = productName;
	}
	
	public void setSalePrice(double saleprice) {
		this.SalePrice=saleprice;
	}
	public void setUnitCost(double unitCost) {
		this.UnitCost = unitCost;
	}
	
	public void setVendorID(int vendorID) {
		this.VendorID = vendorID;
	}
	public void setQuantityOnHand(int quantityOnHand) {
		this.QuantityOnHand = quantityOnHand;
	}
	
	// Returns the values for each field of the list element.
	public int getProductID() {
		return this.ProductID;
	}
	public String getProductName() {
		return this.ProductName;
	}
	
	public double getSalePrice() {
		return this.SalePrice;
	}
	public double getUnitCost() {
		return this.UnitCost;
	}
	
	public int getVendorID() {
		return this.VendorID;
	}
	public int getQuantityOnHand() {
		return this.QuantityOnHand;
	}

}
