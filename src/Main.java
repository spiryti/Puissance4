import java.util.Scanner;

import GameComponenent.*;

public class Main {
    public static void main(String[] args) {
    	Puissance4 p4 =  new Puissance4();
        Scanner scanner = new Scanner(System.in);
        Puissance4State p4state =  p4.getInitialState();
        
        boolean joueur =  p4state.isJoueur();
        p4state.stateToString();
		MinMaxSolveur<Puissance4State, Puissance4Action> solveur = new MinMaxSolveur(p4);
		//AlphaBetaSolveur<Puissance4State, Puissance4Action> solveur = new AlphaBetaSolveur(p4);

		while(!p4.isTerminal(p4state)) {
			if(joueur) {
				System.out.println();
				System.out.println("Entrez une colonne pour poser une pièce");
				int colonne = scanner.nextInt()-1;
				System.out.println();
				for (Puissance4Action action : p4.getActions(p4state)) {
					if (colonne == action.getColonne()) {
						Puissance4Action p4action = new Puissance4Action(colonne);
						p4.getResult(p4state, p4action, joueur);
						p4state.stateToString();
						System.out.println();
						joueur = false;
						p4state.setJoueur(joueur);
					}
				}
			}else{
				Puissance4Action p4action = solveur.makeDecision(p4state);
				p4state = p4.getResult(p4state, p4action, joueur);
				p4state.stateToString();
				joueur=true;
				p4state.setJoueur(joueur);
				System.out.println();
			}
			System.out.println();
        }

		if(!joueur){
			System.out.println("Le joueur a gagné");
		}else{
			System.out.println("L'ordi a gagné");
		}

      
    }
}