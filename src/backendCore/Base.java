package backendCore;



import java.util.Date;

import FrontendCore.MainMenu;
import backendOperations.ListOperations;


public class Base {
	
public static void main(String args[]) {
	
    DatabaseConnection dbObject= new DatabaseConnection();
    dbObject.updateList();
	/*
    dbObject.insertIntoTable("lily", "flower", 1.99, "Chicago", "ChiVendor", 134, false); 
	dbObject.insertIntoTable( "lily seeds", "seeds", 0.99, "Chicago","ChiVendor", 104, false); 
	dbObject.insertIntoTable( "rose", "flower", 3.96,"Warrenville", "ChiVendor", 213, false); 
	dbObject.insertIntoTable("rose seeds", "seeds", 0.96, "Chicago", "ChiVendor", 206, true);
	dbObject.insertIntoTable( "geranium", "flower", 1.69, "Chicago", "ChiVendor", 213, true);
	dbObject.insertIntoTable( "geranium seeds", "seeds", 1.29,"Chicago", "WarnvleVendor", 112, false);
	dbObject.insertIntoTable( "daisy", "flower", 2.99, "Chicago", "ChiVendor", 153, false);
	dbObject.insertIntoTable( "daisy seeds", "seeds", 0.99, "Warrenville","WarnvleVendor", 105, true); 
	dbObject.insertIntoTable( "orchid", "flower", 4.99, "Warrenville", "WarnvleVendor", 54, false); 
	dbObject.insertIntoTable("orchid seeds", "seeds", 1.69, "Chicago", "ChiVendor", 68, false);
	dbObject.insertIntoTable( "daisy", "flower", 1.49, "Chicago", "ChiVendor",200, true); 
	dbObject.insertIntoTable( "daisy seeds", "seeds", 0.99,"Chicago", "ChiVendor", 178, false); 
	dbObject.insertIntoTable( "daisy seeds","seeds", 0.99, "Warrenville", "WarnvleVendor", 178, false);
	dbObject.insertIntoTable( "poppy", "flower", 0.99, "Warrenville","WarnvleVendor", 178, false);
    
    Date date = new Date(System.currentTimeMillis());
    dbObject.insertIntoPoTable(1,"lily", "flower","Chicago", "ChiVendor", 134, date); 
	dbObject.insertIntoPoTable(1,"lily", "flower","Chicago", "ChiVendor", 24, date); 
	dbObject.insertIntoPoTable(3,"rose", "flower","Chicago", "ChiVendor", 94, date); 
	dbObject.insertIntoPoTable(7,"daisy", "flower","Chicago", "ChiVendor", 994, date); 
	dbObject.insertIntoPoTable(14,"poppy", "flower","Chicago", "ChiVendor", 104, date); 
	dbObject.insertIntoPoTable(4,"rose", "seeds","Warrenville", "WarnvleVendor",34, date); 
	dbObject.insertIntoPoTable(1,"lily", "flower","Chicago", "ChiVendor", 134, date); 
	dbObject.insertIntoPoTable(1,"lily", "flower","Chicago", "ChiVendor", 24, date); 
	dbObject.insertIntoPoTable(12,"daisy", "seeds","Warrenville", "WarnvleVendor",42, date); 
	dbObject.insertIntoPoTable(10,"orchid", "flower","Warrenville", "WarnvleVendor",34, date); 
	dbObject.insertIntoPoTable(14,"poppy", "flower","Chicago", "ChiVendor", 104, date); 
	dbObject.insertIntoPoTable(4,"rose", "seeds","Warrenville", "WarnvleVendor",34, date); 
	*/
	
	 
	 
    dbObject.selectFromTable();
    MainMenu app =new MainMenu();
    app.displayListInFrame(ListOperations.flowerShopImsList);
    dbObject.closeDBConnection();
   
	}

}
