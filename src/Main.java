import java.util.Scanner;

import GameComponenent.Jeton;
import GameComponenent.Puissance4;
import GameComponenent.Puissance4Action;
import GameComponenent.Puissance4State;

public class Main {
    public static void main(String[] args) {
    	Puissance4 p4 =  new Puissance4();
        Scanner scanner = new Scanner(System.in);
        Puissance4State p4state =  new Puissance4State();
        
        
        p4state.stateToString();
        p4.getActions(p4state);  
        System.out.println();
        System.out.println("Entrez une collone pour poser une pièce");
        int colonne = scanner.nextInt();
        do {
        	Puissance4Action p4action = new Puissance4Action(colonne);
	        p4.getResult(p4state, p4action);
	        p4state.stateToString();
	        System.out.println();
	        System.out.println("Entrez une collone pour poser une pièce");
	        colonne = scanner.nextInt();
        }while(colonne != 10);
        
        
	    
	     
      
      
      
      
      
    }
}