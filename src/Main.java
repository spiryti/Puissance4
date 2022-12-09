import java.util.Scanner;

import GameComponenent.Puissance4;
import GameComponenent.Puissance4Action;
import GameComponenent.Puissance4State;

public class Main {
    public static void main(String[] args) {
    	Puissance4 p4 =  new Puissance4();
        Scanner scanner = new Scanner(System.in);
        Puissance4State p4state =  new Puissance4State();
        
        boolean joueur =  p4state.isJoueur();
        p4state.stateToString();
        p4.getActions(p4state);  
        System.out.println();
        System.out.println("Entrez une colonne pour poser une pièce");
        int colonne = scanner.nextInt();
        do {
        	Puissance4Action p4action = new Puissance4Action(colonne);
	        p4.getResult(p4state, p4action, joueur);
	        p4state.stateToString();
	        if (joueur) { // si jeton jaune joué
	        	joueur = false; // next tour = bouton rouge 
	        	p4state.setJoueur(joueur);
	        }else if (!joueur) { // si jeton rouge joué
	        	joueur = true;// next turn bouton jaune 
	        	p4state.setJoueur(joueur);
	        }
	        System.out.println();
	        System.out.println("Entrez une colonne pour poser une pièce");
	        colonne = scanner.nextInt();
        }while(colonne != 10);
        
        
	    
	     
      
      
      
      
      
    }
}