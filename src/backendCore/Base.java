package backendCore;



import javax.swing.JFrame;

import FrontendCore.MainMenu;
import backendOperations.ListOperations;


public class Base {
	
public static void main(String args[]) {
		
	
    ListOperations listoperation =new ListOperations();

    //Product data
    listoperation.push( 1, "lily", "flower", 1.99, "Chicago", "ChiVendor", 134, false);
    listoperation.push(2, "lily seeds", "seeds", 0.99, "Chicago", "ChiVendor", 104, false);
    listoperation.push(3, "rose", "flower", 3.96, "Warrenville", "ChiVendor", 213, false);
    listoperation.push(4, "rose seeds", "seeds", 0.96, "Chicago", "ChiVendor", 206, true);
    listoperation.push(5, "geranium", "flower", 1.69, "Chicago", "ChiVendor", 213, true);
    listoperation.push(6, "geranium seeds", "seeds", 1.29, "Chicago", "WarnvleVendor", 112, false);
    listoperation.push(7, "daisy", "flower", 2.99, "Chicago", "ChiVendor", 153, false);
    listoperation.push(8, "daisy seeds", "seeds", 0.99, "Warrenville", "WarnvleVendor", 105, true);
    listoperation.push(9, "orchid", "flower", 4.99, "Warrenville", "WarnvleVendor", 54, false);
    listoperation.push(10, "orchid seeds", "seeds", 1.69, "Chicago", "ChiVendor", 68, false);
    listoperation.push(11, "daisy", "flower", 1.49, "Chicago", "ChiVendor", 200, true);
    listoperation.push(12, "daisy seeds", "seeds", 0.99, "Chicago", "ChiVendor", 178, false);
    listoperation.push(13, "daisy seeds", "seeds", 0.99, "Warrenville", "WarnvleVendor", 178, false);
    listoperation.push(14, "poppy", "flower", 0.99, "Warrenville", "WarnvleVendor", 178, false);
   
    //listoperation.displayFullList();
    
    MainMenu app =new MainMenu();
    app.displayListInFrame(ListOperations.flowerShopImsList);
   
	}

}
