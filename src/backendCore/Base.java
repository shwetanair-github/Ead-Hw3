package backendCore;



import javax.swing.JFrame;

import FrontendCore.MainMenu;
import backendOperations.ListOperations;


public class Base {
	
public static void main(String args[]) {
		
	
    ListOperations listoperation =new ListOperations();

    
    listoperation.push( 1, "lily", "flower", 0.69, "Chicago", 1, 134, false);
    listoperation.push(2, "rose", "flower", 3.96, "Chicago", 1, 213, false);
    listoperation.push(3, "geranium", "flower", 1.69, "Chicago", 1, 213, true);
     

   
    listoperation.displayFullList();
    
    MainMenu app =new MainMenu();
    listoperation.displayListInFrame();
   
	}

}
