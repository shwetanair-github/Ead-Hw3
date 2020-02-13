package backendCore;



import javax.swing.JFrame;

import FrontendCore.MainMenu;
import backendOperations.ListOperations;


public class Base {
	
public static void main(String args[]) {
		
	
    ListOperations listoperation =new ListOperations();

    
    listoperation.push(1, "lily", 0.69, 0.23, 1, 134);
    listoperation.push(2, "rose", 3.96, 1.33, 1, 213);
    listoperation.push(3, "geranium", 1.69, 0.23, 1, 213);
     

   
    listoperation.displayFullList();
    
    
    MainMenu app =new MainMenu();
    listoperation.displayListInFrame();
   
    

   
    
	}

}
