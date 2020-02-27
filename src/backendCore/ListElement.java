package backendCore;

/*
 * 
 * Class that creates a list element.
 * The functions to set values to the element and get i.e. retrieve values from the element.
 *
 * */

public class ListElement {
	
	public int ProductID, VendorID, QuantityOnHand;
	public String ProductName, ProductType, Location;
	public double SalePrice;
	public boolean Discount;
	
	//Initiating a list element using a constructor
	public ListElement() {
		ProductID = 0;
		ProductName = null;
		ProductType = null;
		SalePrice = 0;
		Location = null;
		VendorID = 0;
		QuantityOnHand = 0;
		Discount = false;
	}
	
	// Sets the values for each field of the list element.
	public void setProductID(int id) {
		this.ProductID = id;
	}
	public void setProductName(String productName) {
		this.ProductName = productName;
	}
	public void setProductType(String productType) {
		this.ProductType = productType;
	}
	public void setSalePrice(double saleprice) {
		this.SalePrice=saleprice;
	}
	public void setLocation(String location) {
		this.Location = location;
	}
	public void setVendorID(int vendorID) {
		this.VendorID = vendorID;
	}
	public void setQuantityOnHand(int quantityOnHand) {
		this.QuantityOnHand = quantityOnHand;
	}
	public void setDiscount(boolean discount) {
		this.Discount = discount;
	}
	
	// Returns the values for each field of the list element.
	public int getProductID() {
		return this.ProductID;
	}
	public String getProductName() {
		return this.ProductName;
	}
	public String getProductType() {
		return this.ProductType;
	}
	public double getSalePrice() {
		return this.SalePrice;
	}
	public String getLocation() {
		return this.Location;
	}
	public int getVendorID() {
		return this.VendorID;
	}
	public int getQuantityOnHand() {
		return this.QuantityOnHand;
	}
	public boolean getDiscount() {
		return this.Discount;
	}

}
