package backendCore;



import javax.swing.JFrame;

import FrontendCore.MainMenu;
import backendOperations.ListOperations;


public class Base {
	
public static void main(String args[]) {
		
	
    ListOperations listoperation =new ListOperations();

    
    listoperation.push( 1, "lily", "flower", 1.99, "Chicago", 1, 134, false);
    listoperation.push(2, "lily seeds", "seeds", 0.99, "Chicago", 2, 104, false);
    listoperation.push(3, "rose", "flower", 3.96, "Warrenville", 1, 213, false);
    listoperation.push(4, "rose seeds", "seeds", 0.96, "Chicago", 1, 206, true);
    listoperation.push(5, "geranium", "flower", 1.69, "Chicago", 3, 213, true);
    listoperation.push(6, "geranium seeds", "seeds", 1.29, "Chicago", 2, 112, false);
    listoperation.push(7, "daisy", "flower", 2.99, "Chicago", 1, 153, false);
    listoperation.push(8, "daisy seeds", "seeds", 0.99, "Warrenville", 2, 105, true);
    listoperation.push(9, "orchid", "flower", 4.99, "Warrenville", 3, 54, false);
    listoperation.push(10, "orchid seeds", "seeds", 1.69, "Chicago", 3, 68, false);
    listoperation.push(11, "daisy", "flower", 1.49, "Chicago", 3, 200, true);
    listoperation.push(12, "daisy seeds", "seeds", 0.99, "Chicago", 2, 178, false);
   
    //listoperation.displayFullList();
    
    MainMenu app =new MainMenu();
    app.displayListInFrame(ListOperations.flowerShopImsList);
   
	}

}
