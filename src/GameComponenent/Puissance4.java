package GameComponenent;

import java.io.Console;
import java.util.ArrayList;

public class Puissance4 implements Game<Puissance4State,Puissance4Action>{


	@Override
	public Puissance4State getInitialState() {
		return new Puissance4State();
	}

	@Override
    public ArrayList<Puissance4Action> getActions(Puissance4State state) {
        //@todo renvoie la liste actions possible après un coup
    	ArrayList<Puissance4Action> puissance4Action = new ArrayList<Puissance4Action>();

    	Jeton[][] plateau=state.getPlateau();
        for (int i=0 ; i<plateau.length;i++) {
    		for (int j=0;j<plateau[i].length;j++) {
    			if(i==plateau.length-1) { // si on est � la derni�re ligne du tableau
    				if (plateau[i][j].equals(Jeton.Vide)) {
    					Puissance4Action actionPosible = new Puissance4Action(j);
    					puissance4Action.add(actionPosible);

    				}
    			}
    		}
    	}
        return puissance4Action;
    }

    @Override
    public int getDepth() {
        return 100;
    }

    @Override
    public Puissance4State getResult(Puissance4State initialState, Puissance4Action action, boolean joueur) {
        //@todo renvoie l'état (le plateau) après que une action ait été effectué
    	Puissance4State state=new Puissance4State(initialState);
    	Jeton[][] plateau = state.getPlateau();
    	ArrayList<Jeton> places = new ArrayList<Jeton>();
    	int colonne = action.getColonne();
    	
    	for (int i = 0; i<6;i++) {
    		Jeton jeton = plateau[i][colonne];
    			places.add(jeton);
    	}
    	
    	for(int i=0; i<places.size();i++) {
    		Jeton jeton = places.get(i);
    		if ( !jeton.equals(Jeton.Vide) && i == places.size()-1) {
    			/*if(joueur) {
					System.out.println("plus de place sur cette colonne");
				}*/
    		}else if (jeton.equals(Jeton.Vide)){
    			if (joueur) {
    				jeton = Jeton.Jaune; 
    			}else {
    				jeton = Jeton.Rouge; 
    			}
    			plateau[i][colonne] = jeton;
    			state.setPlateau(plateau);
    			break;
    		}
    	}
        return state;
    }


    @Override
    public boolean isTerminal(Puissance4State state) {
        if(
				checkDiagDownRightUpLeft(state) || checkDiagUpLeftToDownRight(state) ||
				checkHorizontal(state) || checkVertical(state) || isFull(state)){
			return true;
		}
        return false;
    }

    @Override
    public int getUtility(Puissance4State state,boolean player) {
		if(player){
			return state.Player1Utility;
		}
		else {
			return -state.Player1Utility;
		}
    }

	private boolean isFull(Puissance4State state){

		for(int i=0; i < 7;i++){
			if(state.getPlateau()[5][i]==Jeton.Vide){
				return false;
			}
		}
		state.Player1Utility=0;
		return true;
	}
	public void setUtilityForTerminal(Puissance4State state,Jeton firstFind){
		if(firstFind==Jeton.Jaune){
			state.Player1Utility =Integer.MAX_VALUE;
		}
		else{
			state.Player1Utility =Integer.MIN_VALUE;
		}
	}
	private boolean checkVertical(Puissance4State state){
		Jeton plateau[][]=state.getPlateau();
		for(int j=0;j<7;j++){
			Jeton firstFind=Jeton.Vide;
			int numberFind=0;
			for(int i=5;i>=0;i--){
				if(firstFind!=Jeton.Vide){
					if(firstFind!=plateau[i][j]) {
						break;
					}
					else {
						numberFind+=1;
					}
				}
				else {
					if (plateau[i][j] != Jeton.Vide) {
						firstFind = plateau[i][j];
						numberFind += 1;
					}
				}
				if(numberFind>=4){
					setUtilityForTerminal(state,firstFind);
					return true;
				}
			}

		}
		return false;
	}

	private boolean checkHorizontal(Puissance4State state){
		Jeton plateau[][]=state.getPlateau();
		for(int i=0;i<6;i++){
			Jeton firstFind=Jeton.Vide;
			int numberFind=0;
			for(int j=0;j<7;j++){
				if(firstFind!=Jeton.Vide){
					if(firstFind!=plateau[i][j]) {
						numberFind=0;
					}
				}

				if (plateau[i][j] != Jeton.Vide) {
					firstFind = plateau[i][j];
					numberFind += 1;

				}
				if(numberFind>=4){
					setUtilityForTerminal(state,firstFind);
					return true;
				}
			}
		}
		return false;
	}

	private boolean checkDiagUpLeftToDownRight(Puissance4State state){
		Jeton plateau[][]=state.getPlateau();
		for(int i=0;i<5;i++){
			Jeton firstFind=Jeton.Vide;
			int numberFind=0;
			for(int j=0;j<=i;j++){
				if(firstFind!=Jeton.Vide){
					if(firstFind!=plateau[5-i+j][j]) {
						numberFind=0;
					}
				}
				if (plateau[i][j] != Jeton.Vide) {
					firstFind = plateau[5-i+j][j];
					numberFind += 1;

				}
				if(numberFind>=4){
					setUtilityForTerminal(state,firstFind);
					return true;
				}
			}
		}
		for(int j=0;j<7;j++){
			Jeton firstFind=Jeton.Vide;
			int numberFind=0;
			for(int i=0;i<=j && i<6;i++){
				if(firstFind!=Jeton.Vide){
					if(firstFind!=plateau[i][6-j+i]) {
						numberFind=0;
					}
				}

				if (plateau[i][j] != Jeton.Vide) {
					firstFind = plateau[i][6-j+i];
					numberFind += 1;

				}
				if(numberFind>=4){
					setUtilityForTerminal(state,firstFind);
					return true;
				}
			}

		}
		return false;
	}
	private boolean checkDiagDownRightUpLeft(Puissance4State state){
		Jeton plateau[][]=state.getPlateau();
		for(int i=0;i<5;i++){
			Jeton firstFind=Jeton.Vide;
			int numberFind=0;
			for(int j=0;j<=i;j++){
				if(firstFind!=Jeton.Vide){
					if(firstFind!=plateau[5-i+j][6-j]) {
						numberFind=0;
					}
				}
				if (plateau[i][j] != Jeton.Vide) {
					firstFind = plateau[5-i+j][6-j];
					numberFind += 1;
				}
				if(numberFind>=4){
					setUtilityForTerminal(state,firstFind);
					return true;
				}
			}

		}
		for(int j=0;j<7;j++){
			Jeton firstFind=Jeton.Vide;
			int numberFind=0;
			for(int i=0;i<=j && i<6;i++){
				if(firstFind!=Jeton.Vide){
					if(firstFind!=plateau[i][j-i]) {
						numberFind=0;
					}
				}
				if (plateau[i][j] != Jeton.Vide) {
					firstFind = plateau[i][j-i];
					numberFind += 1;
				}
				if(numberFind>=4){
					setUtilityForTerminal(state,firstFind);
					return true;
				}
			}
		}
		return false;
	}



}
